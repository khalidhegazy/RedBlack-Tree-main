import java.util.Scanner;

class Node {
    int value;
    int color;
    Node parent;
    Node left;
    Node right;
}

public class RedBlackTree {
    PrintGUI Window = new PrintGUI();
    private Node root;
    private Node NULL;

    public RedBlackTree() {
        NULL = new Node();
        NULL.color = 0;
        NULL.left = null;
        NULL.right = null;
        root = NULL;
    }

    private void RedBlackTransplant(Node Node1, Node Node2) {
        if (Node1.parent == null) {
            root = Node2;
        } else if (Node1 == Node1.parent.left) {
            Node1.parent.left = Node2;
        } else {
            Node1.parent.right = Node2;
        }
        Node2.parent = Node1.parent;
    }

    public Node minimum(Node node) {
        while (node.left != NULL) {
            node = node.left;
        }
        return node;
    }

    public void LeftRotate(Node Node1) {
        Node Node2 = Node1.right;
        Node1.right = Node2.left;
        if (Node2.left != NULL) {
            Node2.left.parent = Node1;
        }
        Node2.parent = Node1.parent;
        if (Node1.parent == null) {
            this.root = Node2;
        } else if (Node1 == Node1.parent.left) {
            Node1.parent.left = Node2;
        } else {
            Node1.parent.right = Node2;
        }
        Node2.left = Node1;
        Node1.parent = Node2;
    }

    public void RightRotate(Node Node1) {
        Node Node2 = Node1.left;
        Node1.left = Node2.right;
        if (Node2.right != NULL) {
            Node2.right.parent = Node1;
        }
        Node2.parent = Node1.parent;
        if (Node1.parent == null) {
            this.root = Node2;
        } else if (Node1 == Node1.parent.right) {
            Node1.parent.right = Node2;
        } else {
            Node1.parent.left = Node2;
        }
        Node2.right = Node1;
        Node1.parent = Node2;
    }

    private void InsertFixed(Node Node1) {
        Node Node2;
        while (Node1.parent.color == 1) {
            if (Node1.parent == Node1.parent.parent.right) {
                Node2 = Node1.parent.parent.left;
                if (Node2.color == 1) {
                    Node2.color = 0;
                    Node1.parent.color = 0;
                    Node1.parent.parent.color = 1;
                    Node1 = Node1.parent.parent;
                } else {
                    if (Node1 == Node1.parent.left) {
                        Node1 = Node1.parent;
                        RightRotate(Node1);
                    }
                    Node1.parent.color = 0;
                    Node1.parent.parent.color = 1;
                    LeftRotate(Node1.parent.parent);
                }
            } else {
                Node2 = Node1.parent.parent.right;

                if (Node2.color == 1) {
                    Node2.color = 0;
                    Node1.parent.color = 0;
                    Node1.parent.parent.color = 1;
                    Node1 = Node1.parent.parent;
                } else {
                    if (Node1 == Node1.parent.right) {
                        Node1 = Node1.parent;
                        LeftRotate(Node1);
                    }
                    Node1.parent.color = 0;
                    Node1.parent.parent.color = 1;
                    RightRotate(Node1.parent.parent);
                }
            }
            if (Node1 == root) {
                break;
            }
        }
        root.color = 0;
    }

    public void Insert(int key) {
        Node node = new Node();
        node.parent = null;
        node.value = key;
        node.left = NULL;
        node.right = NULL;
        node.color = 1;

        Node Node1 = null;
        Node Node2 = this.root;

        while (Node2 != NULL) {
            Node1 = Node2;
            if (node.value < Node2.value) {
                Node2 = Node2.left;
            } else {
                Node2 = Node2.right;
            }
        }

        node.parent = Node1;
        if (Node1 == null) {
            root = node;
        } else if (node.value<Node1.value) {
            Node1.left = node;
        } else {
            Node1.right = node;
        }

        if (node.parent == null) {
            node.color = 0;
            return;
        }

        if (node.parent.parent == null) {
            return;
        }
        InsertFixed(node);
    }

    private void DeleteFixed(Node Node1) {
        Node Node2;
        while (Node1 != root && Node1.color == 0) {
            if (Node1 == Node1.parent.left) {
                Node2 = Node1.parent.right;
                if (Node2.color == 1) {
                    Node2.color = 0;
                    Node1.parent.color = 1;
                    LeftRotate(Node1.parent);
                    Node2 = Node1.parent.right;
                }

                if (Node2.left.color == 0 && Node2.right.color == 0) {
                    Node2.color = 1;
                    Node1 = Node1.parent;
                } else {
                    if (Node2.right.color == 0) {
                        Node2.left.color = 0;
                        Node2.color = 1;
                        RightRotate(Node2);
                        Node2 = Node1.parent.right;
                    }

                    Node2.color = Node1.parent.color;
                    Node1.parent.color = 0;
                    Node2.right.color = 0;
                    LeftRotate(Node1.parent);
                    Node1 = root;
                }
            } else {
                Node2 = Node1.parent.left;
                if (Node2.color == 1) {
                    Node2.color = 0;
                    Node1.parent.color = 1;
                    RightRotate(Node1.parent);
                    Node2 = Node1.parent.left;
                }

                if (Node2.right.color == 0 && Node2.right.color == 0) {
                    Node2.color = 1;
                    Node1 = Node1.parent;
                } else {
                    if (Node2.left.color == 0) {
                        Node2.right.color = 0;
                        Node2.color = 1;
                        LeftRotate(Node2);
                        Node2 = Node1.parent.left;
                    }
                    Node2.color = Node1.parent.color;
                    Node1.parent.color = 0;
                    Node2.left.color = 0;
                    RightRotate(Node1.parent);
                    Node1 = root;
                }
            }
        }
        Node1.color = 0;
    }

    public void Delete(int value) {
        Node Node1 = NULL,Node2,Node3,Node4=this.root;
        while (Node4!= NULL) {
            if (Node4.value == value) {
                Node1 = Node4;
            }

            if (Node4.value<=value) {
                Node4=Node4.right;
            } else {
                Node4=Node4.left;
            }
        }

        if (Node1 == NULL) {
            System.out.println("Couldn't find key in the tree");
            return;
        }

        Node3 = Node1;
        int yOriginalColor = Node3.color;
        if (Node1.left == NULL) {
            Node2 = Node1.right;
            RedBlackTransplant(Node1,Node1.right);
        } else if (Node1.right == NULL) {
            Node2 = Node1.left;
            RedBlackTransplant(Node1,Node1.left);
        } else {
            Node3 = minimum(Node1.right);
            yOriginalColor = Node3.color;
            Node2 = Node3.right;
            if (Node3.parent == Node1) {
                Node2.parent = Node3;
            } else {
                RedBlackTransplant(Node3,Node3.right);
                Node3.right = Node1.right;
                Node3.right.parent = Node3;
            }

            RedBlackTransplant(Node1,Node3);
            Node3.left = Node1.left;
            Node3.left.parent = Node3;
            Node3.color = Node1.color;
        }
        if (yOriginalColor == 0) {
            DeleteFixed(Node2);
        }
    }

    private void ColoredPrint(Node root,int width,int height,int i) {
        i++;
        boolean R=false,L=false;
        if (root.right != NULL)
            R=true;
        if (root.left != NULL)
            L=true;
        if (root != NULL) {
            Window.addNode(root.value,width,height,root.color,i,R,L);
            ColoredPrint(root.left,width-(40*i),height+100,i);
            ColoredPrint(root.right,width+(40*i),height+100,i);
        }
    }

    public void Print() {
        Window.nodes.clear();
        Window.setSize(1000,1000);
        Window.setVisible(true);
        ColoredPrint(this.root,500,50,0);
    }

    public void Clear(Node Node1) {
        if (Node1.right != NULL)
            Clear(Node1.right);
        if (Node1.left != NULL)
            Clear(Node1.left);
        Delete(Node1.value);
    }

    public static void main(String[] args) {
        RedBlackTree RBTree = new RedBlackTree();
        boolean flag=true;
        Scanner scanner = new Scanner(System.in); //System.in is a standard input stream.

        while (flag) {
            System.out.print("1-Insert.\n" + "2-Delete.\n" + "3-Clear.\n" + "4-Exit.\n");
            int num = scanner.nextInt();
            switch (num) {
                case 1: {
                    System.out.print("Enter number you want to insert\n");
                    int x = scanner.nextInt();
                    RBTree.Insert(x);
                    RBTree.Print();
                    break;
                }
                case 2: {
                    System.out.print("Enter number you want to delete\n");
                    int x = scanner.nextInt();
                    RBTree.Delete(x);
                    RBTree.Print();
                    break;
                }
                case 3: {
                    RBTree.Clear(RBTree.root);
                    RBTree.Print();
                    break;
                }
                case 4: {
                    flag = false;
                    break;
                }
                default:
                {
                    System.out.println("Please,enter a valid choice");
                }
            }

        }
    }
}