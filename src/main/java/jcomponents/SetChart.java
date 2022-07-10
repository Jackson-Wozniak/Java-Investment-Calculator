package jcomponents;

import customcolors.CustomColors;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.AreaRenderer;
import org.jfree.chart.renderer.xy.*;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.ui.HorizontalAlignment;
import org.jfree.chart.ui.RectangleEdge;
import org.jfree.chart.ui.RectangleInsets;
import org.jfree.chart.ui.VerticalAlignment;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.w3c.dom.Text;

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
        chart.setBackgroundPaint(CustomColors.darker);
        chart.setTitle("Portfolio Growth");
        TextTitle subtitle = new TextTitle(
                "Final Amount: $" + Math.round(values[values.length - 1] * 100.00) / 100.00,
                new Font(Font.SANS_SERIF, Font.BOLD, 12),
                CustomColors.light,
                RectangleEdge.TOP,
                HorizontalAlignment.CENTER,
                VerticalAlignment.TOP,
                RectangleInsets.ZERO_INSETS);
        chart.addSubtitle(subtitle);
        chart.getTitle().setPaint(CustomColors.teal);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(740,370));
        chartPanel.setBackground(CustomColors.darker);
        chartPanel.setBorder(new LineBorder(CustomColors.darkest, 1));

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
                "",
                "Years",
                "Investment Value ($)",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
        XYPlot plot = chart.getXYPlot();

        //var renderer = new XYLineAndShapeRenderer();
        XYAreaRenderer renderer = new XYAreaRenderer();
        renderer.setOutline(true);
        renderer.setDefaultOutlinePaint(CustomColors.dark);

        renderer.setSeriesPaint(0, CustomColors.teal);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(CustomColors.light);

        plot.getRangeAxis().setLabelPaint(CustomColors.light);
        plot.getRangeAxis().setTickLabelPaint(CustomColors.light);
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(CustomColors.dark);

        plot.getDomainAxis().setLabelPaint(CustomColors.light);
        plot.getDomainAxis().setTickLabelPaint(CustomColors.light);
        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(CustomColors.dark);

        chart.getLegend().setBackgroundPaint(CustomColors.darker);
        chart.getLegend().setItemPaint(CustomColors.light);
        chart.setBorderPaint(CustomColors.teal);
        chart.getPlot().setBackgroundPaint(CustomColors.darker);
        chart.getLegend().setFrame(BlockBorder.NONE);

        return chart;
    }


}
