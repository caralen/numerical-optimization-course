package hr.fer.zemris.apr.dz5.window;

import java.awt.BasicStroke;
import java.awt.Color;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Window extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final String file = "./src/main/resources/data";

	public Window() throws IOException {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Graph");
		setLocation(300, 200);
		setSize(800, 500);
		initGUI();
	}

	private void initGUI() throws IOException {
		JFreeChart xylineChart = ChartFactory.createXYLineChart("", "Vrijeme", "Varijabla stanja", createDataset(),
				PlotOrientation.VERTICAL, true, true, false);

		ChartPanel chartPanel = new ChartPanel(xylineChart);
		chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
		XYPlot plot = xylineChart.getXYPlot();

		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
		renderer.setSeriesPaint(0, Color.RED);
		renderer.setSeriesPaint(1, Color.GREEN);
		renderer.setSeriesPaint(2, Color.YELLOW);
		renderer.setSeriesStroke(0, new BasicStroke(4.0f));
		renderer.setSeriesStroke(1, new BasicStroke(3.0f));
		renderer.setSeriesStroke(2, new BasicStroke(2.0f));
		plot.setRenderer(renderer);
		setContentPane(chartPanel);
	}

	private XYDataset createDataset() throws IOException {
		List<String> lines = Files.readAllLines(Paths.get(file));
		List<XYSeries> series = new ArrayList<>();
		boolean firstLine = true;
		
		for(String line : lines) {
			String[] parts = line.split("\t");
			double t = Double.parseDouble(parts[0]);
			
			String[] values = parts[1].split("\\s");
			
			for(int i = 0; i < values.length-1; i++) {
				if(firstLine) 
					series.add(new XYSeries("x" + (i+1)));
				
				series.get(i).add(t, Double.parseDouble(values[i]));
			}
			firstLine = false;
		}
		final XYSeriesCollection dataset = new XYSeriesCollection();
		for(XYSeries s : series) {
			dataset.addSeries(s);
		}
		return dataset;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			try {
				new Window().setVisible(true);
			} catch (IOException e) {
				System.out.println(e.getMessage());
				System.exit(-1);
			}
		});
	}

}
