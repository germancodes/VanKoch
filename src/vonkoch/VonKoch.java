/*
 * CSCI 3343 Analysis of Algorithms Spring 2017
 * Chapter 5 Recursive implementation of the von Koch snowflake
 * German M. Candelaria
 * 04/19/2017
 */
package vonkoch;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VonKoch extends Frame implements ActionListener {
    private TextField lvl, len;
    VonKoch() {
        super("von Koch snowflake");
        Label lvlLbl = new Label("level");
        lvl = new TextField("3",3);
        Label lenLbl = new Label("side");
        len = new TextField("200",3);
        Button draw = new Button("draw");
        lvl.addActionListener(this);
        len.addActionListener(this);
        draw.addActionListener(this);
        setLayout(new FlowLayout());
        add(lvlLbl);
        add(lvl);
        add(lenLbl);
        add(len);
        add(draw);
        setSize(600,600);
        setForeground(Color.blue);
        setBackground(Color.white);
        show();
        addWindowListener(new WindowAdapter() {
            @Override
             public void windowClosing(WindowEvent e) {
                System.exit(0);
             }
        });
    }
    private double angle;
    private Point currPt, pt = new Point();
    private void right(double x) {
        angle += x;
    }
    private void left (double x) {
        angle -= x;
    }
    private void drawFourLines(double side, int level, Graphics g) {
        if (level == 0) {
             // arguments to sin() and cos() must be angles given in radians,
             // thus, the angles given in degrees must be multiplied by PI/180;
             pt.x = ((int)(Math.cos(angle*Math.PI/180)*side)) + currPt.x;
             pt.y = ((int)(Math.sin(angle*Math.PI/180)*side)) + currPt.y;
             g.drawLine(currPt.x, currPt.y, pt.x, pt.y);
             currPt.x = pt.x;
             currPt.y = pt.y;
        }
       else { 
            //Modified drawing to accomodate new requirements
             drawFourLines(side/3.0,level-1,g);
             left (90);
             drawFourLines(side/3.0,level-1,g);
             right(90);             
             drawFourLines(side/3.0,level-1,g);
             right (90);
             drawFourLines(side/3.0,level-1,g); 
             left (90);
             drawFourLines(side/3.0,level-1,g);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) { // ActionListener
        repaint();
    }
    @Override
    public void paint(Graphics g) {
        int level = Integer.parseInt(lvl.getText().trim());
        double side = Double.parseDouble(len.getText().trim());
        currPt = new Point(200,200);
        angle = 0;
        //Modified initial loop to draw 4 lines and turn right 90 degree every time
        for (int i = 1; i <= 4; i++) {
            drawFourLines(side,level,g);
            right(90);                                       
        } 
    }
    static public void main(String[] a) {
        VonKoch vonKoch = new VonKoch();
    } 
}

