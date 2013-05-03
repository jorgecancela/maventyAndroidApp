package org.maventy.android.app.visit;

import java.util.ArrayList;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart;
import org.achartengine.chart.LineChart;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;
import org.maventy.R;
import org.maventy.android.utils.Constants;
import org.maventy.android.utils.Tools;
import org.maventy.android.utils.VisitDB;
import org.maventy.android.utils.VisitParcelable;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * 
 * This class plots the indicators calculated in the ShowResult Activity
 * 
 * @author: jcancela
 * 
 */
public class PlotResults extends Activity {
    private GraphicalView mChartView;
    private VisitDB currentVisitDB;
    private Button buttonBack;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.visit_plot_results);

	// Read the last configuration stored in the database and setup the
	// configuration
	Tools.setContext(this.getApplicationContext());
	Tools.setSettings(this.getApplicationContext());
	Tools.setActivity(PlotResults.this);
	
	buttonBack = (Button) findViewById(R.id.button_back_plot);
	buttonBack.setOnClickListener(new View.OnClickListener() {
	    @Override
	    public void onClick(View view) {
		finish();
	    }
	});
	
	// Read the Parcelable visit and the visit ID 
	// It plots the indicators associated with the visit
	try {
	    Intent i = getIntent();
	    VisitParcelable currentVisit = (VisitParcelable) i.getParcelableExtra(Constants.VISIT_PARCEL_STRING);
	    currentVisitDB =  Tools.readParcelableVisit(currentVisit);   
		
	    plotTheVisit();   
	} catch(Exception e) {
	    Tools.showToastMessageAndLog(this.getApplicationContext(), Tools.getStringResource(R.string.unknown_error),
		    "PlotResults", "Exception trying to launch createIntent activity: " + e.toString());
	}
    }

    /** Functions to handle the Menu Button **/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	MenuInflater inflater = getMenuInflater();
	inflater.inflate(R.menu.menu_button, menu);
	return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
	Intent k = Tools.menuSwitcher(this.getApplicationContext(), item);

	if(k == null) {
	    return false;
	} else {
	    startActivity(k);
	    return true;
	}
    }

    @Override
    public void onResume() {
	super.onResume();
    }

    public void plotTheVisit() {
	// First the two lines indicating the thresholds for overweight and malnourished are defined
	// We need to define: the titles of the lines, the x values, the y values, the colors annd the styles
	String[] titles = new String[] { 
		this.getString(R.string.plotvisit_line_over), this.getString(R.string.plotvisit_line_malnourished) };

	List<double[]> thresholds_x = new ArrayList<double[]>();

	for (int i = 0; i < titles.length; i++) {
	    // We need to define at least two points to plot a line
	    thresholds_x.add(new double[] {
		    -1, 6 });
	}

	List<double[]> thresholds_values = new ArrayList<double[]>();
	thresholds_values.add(new double[] {
		Constants.OVERWEIGHT_THRESHOLD, Constants.OVERWEIGHT_THRESHOLD });
	thresholds_values.add(new double[] {
		Constants.MALNOURISED_THRESHOLD, Constants.MALNOURISED_THRESHOLD });

	int[] colors = new int[] {
		Color.YELLOW , Color.RED };
	PointStyle[] styles = new PointStyle[] {
		PointStyle.CIRCLE , PointStyle.CIRCLE };

	XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);
	renderer.setPointSize(1.0f);

	int length = renderer.getSeriesRendererCount();
	for (int i = 0; i < length; i++) {
	    XYSeriesRenderer r = (XYSeriesRenderer) renderer.getSeriesRendererAt(i);
	    r.setLineWidth(2);
	    r.setFillPoints(true);
	}

	// Setting up the chart
	Double minXAxisValue = Double.valueOf(0.0);
	Double maxXAxisValue = Double.valueOf(6.0);
	Double minYAxisValue = Double.valueOf(0.0);
	Double maxYAxisValue = Double.valueOf(100.0);
	setChartSettings(renderer, "", "", this.getString(R.string.plotvisit_yaxis_percentile), minXAxisValue, maxXAxisValue, minYAxisValue, maxYAxisValue, Color.LTGRAY, Color.LTGRAY);

	renderer.setXLabels(0);
	renderer.setYLabels(10);
	renderer.setShowGrid(true);
	renderer.setXLabelsAlign(Align.RIGHT);
	renderer.setYLabelsAlign(Align.RIGHT);

	renderer.setZoomButtonsVisible(false);

	// Adding the labels to the X axis, the Double numbers indicate the position in the axis
	renderer.addXTextLabel(1.0, this.getString(R.string.plotvisit_bar_age_weight));
	renderer.addXTextLabel(2.2, this.getString(R.string.plotvisit_bar_age_height));
	renderer.addXTextLabel(3.3, this.getString(R.string.plotvisit_bar_age_head));
	renderer.addXTextLabel(4.5, this.getString(R.string.plotvisit_bar_age_bmi));
	renderer.addXTextLabel(5.6, this.getString(R.string.plotvisit_bar_weight_height));

	// Now the chart bar is defined
	XYSeries indicatorsBarChart = new XYSeries("");
	// Adding the bars to chart, the Double numbers indicate the position in the axis
	addIndicatorToBarChart(indicatorsBarChart, 0.6, currentVisitDB.getWeightZscore(), currentVisitDB.getWeightPercentile());
	addIndicatorToBarChart(indicatorsBarChart, 1.8, currentVisitDB.getLengthZscore(), currentVisitDB.getLengthPercentile());
	addIndicatorToBarChart(indicatorsBarChart, 3.0, currentVisitDB.getHeadZscore() , currentVisitDB.getHeadPercentile());
	addIndicatorToBarChart(indicatorsBarChart, 4.2, currentVisitDB.getBMIZscore(), currentVisitDB.getBMIPercentile());
	addIndicatorToBarChart(indicatorsBarChart, 5.4, currentVisitDB.getWHZscore(), currentVisitDB.getWHPercentile());

	renderer.setBarSpacing(0.2);
	XYSeriesRenderer barChartRenderer = new XYSeriesRenderer();
	barChartRenderer.setColor(Color.rgb(114, 173, 43));

	XYMultipleSeriesDataset dataset = buildDataset(titles, thresholds_x, thresholds_values);
	dataset.addSeries(0, indicatorsBarChart);
	renderer.addSeriesRenderer(0, barChartRenderer);
	barChartRenderer.setDisplayChartValues(false);
	String[] types = new String[] {
		BarChart.TYPE, LineChart.TYPE, LineChart.TYPE};

	// A specific view is assigned to plot the bar chart within it
	LinearLayout layout = (LinearLayout) findViewById(R.id.chart);
	mChartView = ChartFactory.getCombinedXYChartView(this.getApplicationContext(), dataset, renderer, types);	
	layout.addView(mChartView);
    }

    /** Plots the value of the indicator in the bar chart. If NaN value it plots 0% or 100% **/
    private void addIndicatorToBarChart(XYSeries myIndicatorsBarChart, Double xAxis, Double zscore, Double percentile) {
	if(percentile.isNaN()) {
	    if(zscore < 0) {
		myIndicatorsBarChart.add(xAxis, Double.valueOf(0));
	    } else {
		myIndicatorsBarChart.add(xAxis, Double.valueOf(100));
	    }
	} else {
	    myIndicatorsBarChart.add(xAxis, percentile);				
	}
    }

    /** Plots lines **/
    private void addXYSeries(XYMultipleSeriesDataset dataset, String[] titles, List<double[]> xValues,
	    List<double[]> yValues, int scale) {
	int length = titles.length;
	for (int i = 0; i < length; i++) {
	    XYSeries series = new XYSeries(titles[i], scale);
	    double[] xV = xValues.get(i);
	    double[] yV = yValues.get(i);
	    int seriesLength = xV.length;
	    for (int k = 0; k < seriesLength; k++) {
		series.add(xV[k], yV[k]);
	    }
	    dataset.addSeries(series);
	}
    }

    private XYMultipleSeriesDataset buildDataset(String[] titles, List<double[]> xValues,
	    List<double[]> yValues) {
	XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
	addXYSeries(dataset, titles, xValues, yValues, 0);
	return dataset;
    }

    private XYMultipleSeriesRenderer buildRenderer(int[] colors, PointStyle[] styles) {
	XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
	setRenderer(renderer, colors, styles);
	return renderer;
    }

    private void setChartSettings(XYMultipleSeriesRenderer renderer, String title, String xTitle,
	    String yTitle, double xMin, double xMax, double yMin, double yMax, int axesColor,
	    int labelsColor) {
	// sets lots of default values for this renderer
	renderer.setChartTitle(title);
	renderer.setXTitle(xTitle);
	renderer.setYTitle(yTitle);
	renderer.setXAxisMin(xMin);
	renderer.setXAxisMax(xMax);
	renderer.setYAxisMin(yMin);
	renderer.setYAxisMax(yMax);
	renderer.setAxesColor(axesColor);
	renderer.setLabelsColor(labelsColor);
    }

    private void setRenderer(XYMultipleSeriesRenderer renderer, int[] colors, PointStyle[] styles) {
	//Text size in Axis, Chart title, labels and legend
	renderer.setAxisTitleTextSize(13);
	renderer.setChartTitleTextSize(20);
	renderer.setLabelsTextSize(12);
	renderer.setLegendTextSize(15);

	// Size of points in the line chart
	renderer.setPointSize(5f);

	// Set up chart margins
	renderer.setMargins(new int[] {
		20, 30, 15, 20 });
	int length = colors.length;
	for (int i = 0; i < length; i++) {
	    XYSeriesRenderer r = new XYSeriesRenderer();
	    r.setColor(colors[i]);
	    r.setPointStyle(styles[i]);
	    renderer.addSeriesRenderer(r);
	}
    }
}