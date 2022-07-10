package jcomponents;

import customcolors.CustomColors;
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
import javax.swing.border.LineBorder;
import java.awt.*;

public class SetChart extends JPanel {

    private final int[] years;
    private final double[] values;

    public SetChart(int[] years, double[] values){
        this.years = years;
        this.values = values;

        XYDataset dataset = createDataset();

        JFreeChart chart = createChart(dataset);
        chart.getTitle().setPaint(CustomColors.light);
        chart.setBackgroundPaint(CustomColors.darker);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(740,370));
        chartPanel.setBackground(CustomColors.darker);
        chartPanel.setBorder(new LineBorder(CustomColors.dark, 1));

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
        renderer.setSeriesPaint(0, CustomColors.teal);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(CustomColors.light);

        plot.getRangeAxis().setLabelPaint(CustomColors.light);
        plot.getRangeAxis().setTickLabelPaint(CustomColors.light);
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(CustomColors.light);

        plot.getDomainAxis().setLabelPaint(CustomColors.light);
        plot.getDomainAxis().setTickLabelPaint(CustomColors.light);
        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(CustomColors.light);

        chart.getLegend().setBackgroundPaint(CustomColors.darker);
        chart.getLegend().setItemPaint(CustomColors.light);
        chart.setBorderPaint(CustomColors.teal);
        chart.getPlot().setBackgroundPaint(CustomColors.darker);
        chart.getLegend().setFrame(BlockBorder.NONE);

        return chart;
    }


}