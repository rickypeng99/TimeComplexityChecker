

import javax.swing.*;
import java.awt.Graphics;
import java.math.BigDecimal;

public class DrawFunction extends JFrame {
    static double timesx = 10000, timesy = 10000;
    private double slope, constant;

    /*void setFunc(){
        Code code = new Code();
        int N1 = 100000;
        int N2 = 200000;
        long time1 = code.time(N1);
        System.out.println(time1);
        long time2 = code.time(N2);
        System.out.println(time2);


        slope = new BigDecimal((float)(time2 - time1) / (N2 - N1)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

        System.out.println(slope);
        constant = slope*N1 - time1;
        System.out.println(constant);
    }*/
    double F(double x) {

        return slope*x-constant;//函数表达式
    }
    int x0, y0;
    static int W = 800, H = 600;
    static double L = -W / 2, R = W / 2;
    Graphics G;
    public void setOrigin(int x, int y) {
        this.x0 = x;
        this.y0 = y;
        // show coordinate axis
        drawLine(-W / 2, 0, W / 2, 0);
        drawLine(0, -H / 2, 0, H / 2);
        drawString("X", W / 2 - 30, -20);
        drawString("Y", -20, H / 2 - 20);
        for (int i = 1; i <= 10; i ++) {
            draw(W / 2 - i - 6, i);
            draw(W / 2 - i - 6, -i);
        }
        for (int i = 1; i <= 10; i ++) {
            draw(-i, H / 2 - i);
            draw(i, H / 2 - i);
        }
    }
    public DrawFunction() {
        //setFunc();
        add(new NewPanel());
    }
    /*public static void main(String[] args) {

        DrawFunction frame = new DrawFunction();
        frame.setTitle("DrawFunction");
        frame.setSize(W, H);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
    }*/
    public class Coordinate2D {
        int x, y;
        public Coordinate2D(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public int getPixelPointX() {
            return x0 + x;
        }
        public int getPixelPointY() {
            return y0 - y;
        }
    }
    class NewPanel extends JPanel {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            G = g;
            setOrigin(W / 2, H / 2);
            // in the following , draw what you want draw!
            for (int i = -W / 2; i <= W / 2; i ++) {
                draw(i, work(i));
            }
            /*
            for (int i = 0; i < 1000; i ++) {
                int x = (int)(Math.random() * 400 - 200);
                int y = (int)(Math.random() * 400 - 200);
                drawString("哈哈", x, y);
            }
            */
        }
    }
    int work(int x) {
        //timesx = 0.01;
        //timesy = 100;
        return (int)(F(x / timesx) * timesy);
    }
    public void draw(int x, int y) {
        int X = new Coordinate2D(x, y).getPixelPointX();
        int Y = new Coordinate2D(x, y).getPixelPointY();
        G.drawLine(X, Y, X, Y);
    }
    public void drawRec(int x1, int y1, int x2, int y2) {
        int dx = x1 < x2? 1 : -1;
        int dy = y1 < y2? 1 : -1;
        for (int i = x1; i != x2 + dx; i += dx) {
            for (int j = y1; j != y2 + dy; j += dy) {
                draw(i, j);
            }
        }
    }
    public void drawLine(int x1, int y1, int x2, int y2) {
        int dx = x1 < x2? 1 : -1;
        if (x1 == x2) drawRec(x1, y1, x2, y2);
        else {
            double d = (double)(y2 - y1) / (x2 - x1);
            for (int i = x1; i != x2 + dx; i += dx) {
                draw(i, (int)(y1 + (i - x1) * d));
            }
        }
    }
    public void drawString(String s, int x, int y) {
        int X = new Coordinate2D(x, y).getPixelPointX();
        int Y = new Coordinate2D(x, y).getPixelPointY();
        G.drawString(s, X, Y);
    }
}