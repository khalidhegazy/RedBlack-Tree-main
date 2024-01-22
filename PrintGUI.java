import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PrintGUI extends JFrame {
    int width;
    int height;

    ArrayList<Node> nodes;

    public PrintGUI() {
        this.setTitle("Red Black Tree");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        nodes = new ArrayList<Node>();
        width = 30;
        height = 30;
    }

    class Node {
        int value,x, y,i,color;
        boolean R,L;

        public Node(int myValue, int myX, int myY,int myColor,int MyI,boolean MyR,boolean MyL) {
            x = myX;
            y = myY;
            color = myColor;
            value = myValue;
            i=MyI;
            R = MyR;
            L = MyL;
        }
    }

    public void addNode(int value, int x, int y,int color,int i,boolean r,boolean l) {
        nodes.add(new Node(value,x,y,color,i,r,l));
        this.repaint();
    }

    public void paint(Graphics graphics) {
        FontMetrics f = graphics.getFontMetrics();
        int nodeHeight = Math.max(height, f.getHeight());
        graphics.clearRect(0,0,1000,1000);


        for (Node n : nodes) {
            int nodeWidth = Math.max(width,f.stringWidth(String.valueOf(n.value)))+width/2;
            if (n.color==0) {
                graphics.setColor(Color.black);
            }
            else {
                graphics.setColor(Color.red);
            }

            graphics.fillOval(n.x-nodeWidth/2, n.y-nodeHeight/2, nodeWidth, nodeHeight);

            graphics.setColor(Color.white);

            graphics.drawOval(n.x-nodeWidth/2, n.y-nodeHeight/2, nodeWidth, nodeHeight);

            graphics.drawString(String.valueOf(n.value), n.x-f.stringWidth(String.valueOf(n.value))/2, n.y+f.getHeight()/2);
            graphics.setColor(Color.black);
            if (n.L)
                graphics.drawLine(n.x,n.y,n.x-(40*n.i),n.y+100);
            if (n.R)
                graphics.drawLine(n.x,n.y,n.x+(40*n.i),n.y+100);
        }
    }
}