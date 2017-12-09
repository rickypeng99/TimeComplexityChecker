import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.math.BigDecimal;

public class DrawMath {
    static double slope, constant;
    static long time1, time2;
    static int N1,N2;
    static int[] finalTime =  new int[3];
     static void setFunc(){
        Code code = new Code();
        N1 = 100000;
        N2 = 200000;
        time1 = code.time(N1);
        System.out.println(time1);
        time2 = code.time(N2);
        System.out.println(time2);


        slope = new BigDecimal((float)(time2 - time1) / (N2 - N1)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

        System.out.println(slope);
        constant = slope*N1 - time1;
        System.out.println(constant);
    }
    public static void main(String[] args) {
        setFunc();
        XYSeries series = new XYSeries("xySeries");
        XYSeries series2 = new XYSeries("n^2Series");
        XYSeries series3 = new XYSeries("nSeries");

        /*
        * Tangent line between two data
        * */
        for (int x = N1; x <= N2; x++) {
            int y = (int)(slope*x - constant);
            if(x==N2) {
                finalTime[0] = y;
            }
            series.add(x, y);
        }
        /*
        *
        * O(n^2)
        * */
        for (int x = N1; x <= N2; x++) {

            float y = (float)time1 * ((float)x/(float)N1)*((float)x/(float)N1);
            if(x==N2) {
                finalTime[1] = (int)y;
            }
            series2.add(x, y);
        }
        /*
        * O(n)
        * */

        for (int x = N1; x <= N2; x++) {

            float y = (float)time1 * ((float)x/(float)N1);
            if(x==N2) {
                finalTime[2] = (int)y;
            }
            series3.add(x, y);
        }

        XYSeriesCollection dataset = new XYSeriesCollection();

        dataset.addSeries(series);
        dataset.addSeries(series2);
        dataset.addSeries(series3);
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Time complexity graph", // chart title
                "x", // x axis label  
                "y", // y axis label
                dataset, // data  
                PlotOrientation.VERTICAL,
                false, // include legend  
                false, // tooltips  
                false // urls  
        );

        ChartFrame frame = new ChartFrame("my picture", chart);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int min =99999;
        int pos = 0;
        for (int i = 1; i < finalTime.length; i++) {
          if(Math.abs(finalTime[i] - finalTime[0]) < min)   {
              min = Math.abs(finalTime[i] - finalTime[0]);
              pos = i;
          }
        }
        if (pos==1){
            System.out.println("O(n^2)");
        }





    }

}  