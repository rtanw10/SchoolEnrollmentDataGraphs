package view;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import model.*;

/**
 * A GraphView object updates the line chart and creates a series from a Country object
 *
 * @author Foothill College, Tanvi Waghela
 */
public class GraphView extends LineChart {
    private NumberAxis xAxis;
    private NumberAxis yAxis;
    private DataModel model;

    /**
     * Initializes a new GraphView object
     * @param model A model of the data
     * @param type The type of data that was read
     */
    public GraphView(DataModel model, IndicatorType type){
        super(new NumberAxis(), new NumberAxis());
        this.model = model;
        xAxis = (NumberAxis)(getXAxis());
        yAxis = (NumberAxis)(getYAxis());
        xAxis.setForceZeroInRange(false);
        xAxis.setLabel("Years");
        yAxis.setLabel(type.getLabel());
    }

    /**
     * Gives the user a series that contains the data for a Country object
     * @param currentCountry The current Country object in the data
     * @return Returns a series from the Country
     */
    public Series<Number, Number> seriesFromCountry(Country currentCountry){
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName(currentCountry.getName());
        for (Indicator data : currentCountry.getIndicators()) {
            for (double dataPoint : data.getData()) {
                series.getData().add(new XYChart.Data<Number, Number>(data.getYear(), dataPoint));
            }
        }
        return series;
    }

    /**
     * Gives the user a series that contains the data for a CountryFemaleCompletion object
     * @param currentCountry The current CountryFemaleCompletion object in the data
     * @return Returns a series from the CountryFemaleCompletion
     */
    public Series<Number, Number> seriesFromCountryForFemale(CountryFemaleCompletion currentCountry){
        //extra credit portion
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName(currentCountry.getName());
        for (Indicator data : currentCountry.getIndicators()) {
            for (double dataPoint : data.getData()) {
                series.getData().add(new XYChart.Data<Number, Number>(data.getYear(), dataPoint));
            }
        }
        return series;
    }

    /**
     * Updates the series created from the Country objects
     */
    public void update(){
        for (Country currentCountry : model.getModel()) {
            XYChart.Series<Number, Number> someSeries = this.seriesFromCountry(currentCountry);
            this.getData().add(someSeries);
        }

    }

    /**
     * Updates the series created from the CountryFemaleCompletion objects
     */
    public void femaleCompletionUpdate(){
        //extra credit portion
        for (CountryFemaleCompletion currentCountry : model.getCompletionModel()) {
            XYChart.Series<Number, Number> someSeries = this.seriesFromCountryForFemale(currentCountry);
            this.getData().add(someSeries);
        }

    }
}
