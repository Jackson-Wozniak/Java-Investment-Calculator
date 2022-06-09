package Main.java;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;

public class setChart extends JPanel {

    int[] years;
    double[] values;

    public setChart(int[] years, double[] values){
        this.years = years;
        this.values = values;
        XYDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        chartPanel.setPreferredSize(new Dimension(680,350));
        this.add(chartPanel);
    }

    private XYDataset createDataset() {

        XYSeries xy = new XYSeries("Account Balance");
        for(int i = 0; i < this.years.length; i++){
            xy.add(this.years[i], this.values[i]);
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(xy);

        return dataset;
    }

    private JFreeChart createChart(XYDataset dataset) {

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Investment Chart",
                "Years",
                "Total Investment Value ($)",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );


        XYPlot plot = chart.getXYPlot();

        var renderer = new XYLineAndShapeRenderer();
        Color darkRed = new Color(150,0,0);
        renderer.setSeriesPaint(0, darkRed);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);

        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);

        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);

        chart.getLegend().setFrame(BlockBorder.NONE);
        return chart;
    }


}
