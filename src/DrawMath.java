import javax.swing.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;

import static java.lang.Math.log;

public class DrawMath {
    static float slope, constant;
    static long time1, time2;
    static int N1,N2;
    static int[] finalTime =  new int[5];

     static void setFunc(){
        Code code = new Code();
        N1 = 50000;
        N2 = 100000;
        time1 = code.time(N1);
        System.out.println(time1);
        time2 = code.time(N2);
        System.out.println(time2);


        slope = (float)(time2 - time1) / (float)(N2 - N1);

        constant = slope*N1 - time1;
    }
    public static void main(String[] args) {
        setFunc();
        XYSeries series = new XYSeries("xySeries");

        XYSeries series2 = new XYSeries("n^2Series");
        XYSeries series3 = new XYSeries("nSeries");
        XYSeries series4 = new XYSeries("nlognSeries");
        XYSeries series5 = new XYSeries("constantSeries");

        float y1 = (slope*N2 - constant);
        float y2 = (float)time1 * ((float)N2/(float)N1)*((float)N2/(float)N1);
        float y3 = (float)time1 * ((float)N2/(float)N1);
        float y4 = (float)time1 * (((float)N2*((float)log((float)N2)/(float)log((float)2))) / ((float)N1*((float)log((float)N1)/(float)log((float)2)))) ;
        float y5 = (float)time1;

        finalTime[0] = (int)y1;
        finalTime[1] = (int)y2;
        finalTime[2] = (int)y3;
        finalTime[3] = (int)y4;
        finalTime[4] = (int)y5;

        result();



        /*
        * Tangent line between two data
        * */
        for (int x = N1; x <= N2; x++) {
            float y = (slope*x - constant);

            series.add(x, y);
        }
        /*
        *
        * O(n^2)
        * */
        for (int x = N1; x <= N2; x++) {

            float y = (float)time1 * ((float)x/(float)N1)*((float)x/(float)N1);

            series2.add(x, y);
        }
        /*
        * O(n)
        * */

        for (int x = N1; x <= N2; x++) {

            float y = (float)time1 * ((float)x/(float)N1);

            series3.add(x, y);
        }
        /*
        *
        * O(nlogn)
        *
        * */

        for (int x = N1; x <= N2; x++) {

            float y = (float)time1 * (((float)x*((float)log((float)x)/(float)log((float)2))) / ((float)N1*((float)log((float)N1)/(float)log((float)2)))) ;

            series4.add(x, y);
        }

        /*
        *
        * O(1)
        *
        * */
        for (int x = N1; x <= N2; x++) {

            float y = (float)time1;

            series5.add(x, y);
        }




        XYSeriesCollection dataset = new XYSeriesCollection();

        dataset.addSeries(series);  //0
        dataset.addSeries(series2); //1
        dataset.addSeries(series3); //2
        dataset.addSeries(series4); //3
        dataset.addSeries(series5);
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Time complexity graph \n **Note: The original linear graph is in black \n" +
                        "The O(N^2) graph is in orange \n The O(N) graph is in blue \n " +
                        "The O(NlogN) graph is in red \n The O(1) graph is in pink", // chart title
                "x", // x axis label  
                "y", // y axis label
                dataset, // data  
                PlotOrientation.VERTICAL,
                false, // include legend  
                false, // tooltips  
                false // urls  
        );
        XYPlot plot = (XYPlot) chart.getPlot();
        XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer)plot.getRenderer();
        renderer.setSeriesPaint(4,Color.pink);
        renderer.setSeriesPaint(3,Color.red);
        renderer.setSeriesPaint(2,Color.blue);
        renderer.setSeriesPaint(1,Color.orange);
        renderer.setSeriesPaint(0,Color.black);


        ChartFrame frame= new ChartFrame("Graph of functions", chart);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);




    }
    static void result(){
        int min =Math.abs(finalTime[1] - finalTime[0]);
        int pos = 1;
        for (int i = 1; i < finalTime.length; i++) {
            if(Math.abs(finalTime[i] - finalTime[0]) < min)   {
                min = Math.abs(finalTime[i] - finalTime[0]);
                pos = i;
            }
        }
        String front = "The time complexity should be ";
        if (pos==1){
            JOptionPane.showMessageDialog(null,front+ "O(N^2)!");
        } else if(pos ==2){
            JOptionPane.showMessageDialog(null,front+ "O(N)!");

        } else if (pos == 3){
            JOptionPane.showMessageDialog(null,front+ "O(Nlog(N))!");
        } else if (pos == 4){
            JOptionPane.showMessageDialog(null,front+"O(1)!");
        }
    }

}  