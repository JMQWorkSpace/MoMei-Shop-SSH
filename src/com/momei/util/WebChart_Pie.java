package com.momei.util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.servlet.http.HttpSession;

import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieToolTipGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.data.general.DefaultPieDataset;

public class WebChart_Pie {

    private DefaultPieDataset data = new DefaultPieDataset();

    public void setValue(String key, double value) {

        data.setValue(key, value);

    }

    public String generatePieChart(String title, HttpSession session,

                                   PrintWriter pw) {

        String filename = null;

        try {

            //����chart����

            PiePlot plot = new PiePlot(data);
            resetPiePlot(plot);  
            
            //��ͳ��ͼƬ�Ͻ�����

            /*plot.setURLGenerator(new StandardPieURLGenerator("url",

                    "section"));*/

            plot.setToolTipGenerator(new StandardPieToolTipGenerator());

            JFreeChart chart = new JFreeChart("", JFreeChart.DEFAULT_TITLE_FONT,

                                              plot, true);

            chart.setBackgroundPaint(java.awt.Color.white); //����ͼƬ�ı���ɫ

            chart.setTitle(title);

            //�����ɵ�ͼƬ�ŵ���ʱĿ¼

            ChartRenderingInfo info = new ChartRenderingInfo(new

                    StandardEntityCollection());

            //550��ͼƬ���ȣ�350��ͼƬ�߶�

            filename = ServletUtilities.saveChartAsPNG(chart, 550, 350, info,

                    session);

            ChartUtilities.writeImageMap(pw, filename, info, false);

            pw.flush();

        } catch (Exception e) {

            System.out.println("Exception - " + e.toString());

            e.printStackTrace(System.out);

            filename = "public_error_550x350.png";

        }

        return filename;

    }
    
    private static void resetPiePlot(PiePlot plot) {  
        String unitSytle = "{0}={1}({2})";  
           
         plot.setNoDataMessage("�޶�Ӧ�����ݣ������²�ѯ��");  
        plot.setNoDataMessagePaint(Color.red);  
           
        //ָ�� section �����ߵĺ��(OutlinePaint����Ϊnull)  
        plot.setOutlineStroke(new BasicStroke(0));  
          
         //���õ�һ�� section �Ŀ�ʼλ�ã�Ĭ����12���ӷ���  
        plot.setStartAngle(90);           
  
        plot.setToolTipGenerator(new StandardPieToolTipGenerator(unitSytle,  
                 NumberFormat.getNumberInstance(),  
                 new DecimalFormat("0.00%")));  
           
         //ָ��ͼƬ��͸����  
         plot.setForegroundAlpha(0.65f);  
           
         //������ǩ��ʾ��ʽ  
         plot.setLabelGenerator(new StandardPieSectionLabelGenerator(unitSytle,  
                NumberFormat.getNumberInstance(),  
                 new DecimalFormat("0.00%")));  
               
         //ͼ����ʾ��ʽ  
         plot.setLegendLabelGenerator(new StandardPieSectionLabelGenerator(unitSytle,  
                 NumberFormat.getNumberInstance(),  
                 new DecimalFormat("0.00%")));  
     }  
    

}