package view;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.DataModel;
import model.IndicatorType;

import java.awt.*;
import java.io.FileNotFoundException;

import csv.InvalidFileFormatException;

import java.io.FileNotFoundException;

import csv.InvalidFileFormatException;

import static model.IndicatorType.*;

import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import java.util.stream.IntStream;

/**
 * Instantiates an JavaFX application which creates a model of the data.
 * Uses the model to instantiate an object of type  javafx.scene.chart.LineChart
 * via the GraphView class. Then sets up the scene with basic modification to
 * the stage.
 *
 * @author Foothill College, Tanvi Waghela
 */
public class ChartGraph extends Application
{	
	//private static final IndicatorType DEFAULT_INDICATOR = IndicatorType.INVALID;
	//private static final IndicatorType DEFAULT_INDICATOR = IndicatorType.GDP_PER_CAPITA;
	private static final IndicatorType DEFAULT_INDICATOR = IndicatorType.SCHOOL_ENROLLMENT_PRIMARY;
	//private static final IndicatorType DEFAULT_INDICATOR = IndicatorType.SCHOOL_ENROLLMENT_SECONDARY;
	//private static final IndicatorType DEFAULT_INDICATOR = IndicatorType.PRIMARY_COMPLETION_RATE_FEMALE;


	/**
	 * Called by launch method of Application
	 * @param stage: Stage
	 */
	@Override
	public void start(Stage stage)
	{		
		// NOTE: Make sure to use relative path instead of specifying the entire path
		//       such as (/Users/alicew/myworkspace/so_on_and_so_forth).

		// Example of invalid input file
		final String [] INVALID_INPUT = {"resources/childrenEnrolled_invalid.csv"};

		// Example of input file for GDP per capita:
		final String [] GDP_INPUT = { "resources/gdpPerCapita.csv"};

		final String [] ENROLLMENT_PRIMARY_INPUT = { "resources/childrenEnrolledInPrimary.csv"};

		final String [] ENROLLMENT_SECONDARY_INPUT = {"resources/childrenEnrolledInSecondary.csv"};

		final String FEMALE_INPUT = "resources/completionRateFemalePrimaryUpdated.csv";

		// Displays graph* of data by country.
		// TODO: Define the view such that it takes the model as input.
		//       Construct the x and y axis using a NumberAxis, label the axis.
		GraphView primaryGraphView = null;
		GraphView secondaryGraphView = null;
		GraphView gdpGraphView = null;
		GraphView femaleGraphView = null;
		IndicatorType selectedIndicator = DEFAULT_INDICATOR;

		// Provides access to CSV data.
		// TODO: Define the data model by parsing the CSV input file(s).
		//       Provide accessor methods to your the parsed data.
		try 
		{
			DataModel gdpModel = new DataModel(GDP_INPUT);
			gdpGraphView = new GraphView(gdpModel, GDP_PER_CAPITA);

			DataModel enrollmentPrimaryModel = new DataModel(ENROLLMENT_PRIMARY_INPUT);
			primaryGraphView = new GraphView(enrollmentPrimaryModel, SCHOOL_ENROLLMENT_PRIMARY);

			DataModel enrollmentSecondaryModel = new DataModel(ENROLLMENT_SECONDARY_INPUT);
			secondaryGraphView = new GraphView(enrollmentSecondaryModel, SCHOOL_ENROLLMENT_SECONDARY);

			DataModel femaleComletionModel = new DataModel(FEMALE_INPUT);
			femaleGraphView = new GraphView(femaleComletionModel, PRIMARY_COMPLETION_RATE_FEMALE);
		}
		catch (FileNotFoundException e) 
		{
			System.out.printf("Invalid filename");
			System.exit(0);
		}
		catch (InvalidFileFormatException e)
		{
			System.out.printf("Invalid file format %s\n", e.getMessage());
			System.exit(0);
		}

		// TODO: Define the update() method for the model such that:
		//       - Gets all the data** from the model
		//       - Creates a random list of elements from the data.
		//       - Traverses each element and creates a series (i.e. line) 
		//       - Adds the series*** to it's Observablelist.

		//   * Here, displays graph of Indicator data by country.
		//
		//  ** Here, our data is composed of Country objects.
		// 
		// *** Get an instance of javafx.collections.ObservableList via a call 
		//     to getData() method.
		// https://docs.oracle.com/javafx/2/api/javafx/scene/chart/XYChart.html#getData()


		//extra credit portion
		Button femaleButton1 = new Button("Change to Female School Completion Rate");
		StackPane completionButton1 = new StackPane();
		completionButton1.getChildren().add(femaleButton1);

		Button femaleButton2 = new Button("Change to Female School Completion Rate");
		StackPane completionButton2 = new StackPane();
		completionButton2.getChildren().add(femaleButton2);

		Button femaleButton3 = new Button("Change to Female School Completion Rate");
		StackPane completionButton3 = new StackPane();
		completionButton3.getChildren().add(femaleButton3);

		Button differentButton1 = new Button("Change to GDP Capita Enrollment Data");
		StackPane enrollmentData1 = new StackPane();
		enrollmentData1.getChildren().add(differentButton1);

		Button differentButton2 = new Button("Change to School Enrollment Primary Data");
		StackPane enrollmentData2 = new StackPane();
		enrollmentData2.getChildren().add(differentButton2);

		Button differentButton3 = new Button("Change to School Enrollment Secondary Data");
		StackPane enrollmentData3 = new StackPane();
		enrollmentData3.getChildren().add(differentButton3);

		VBox gdpVbox = new VBox(20);
		VBox primaryVbox = new VBox(20);
		VBox secondaryVbox = new VBox(20);
		VBox femaleVbox = new VBox(20);

		gdpVbox.getChildren().addAll(gdpGraphView, completionButton1);
		Scene gdpScene = new Scene(gdpVbox);
		gdpGraphView.update();

		primaryVbox.getChildren().addAll(primaryGraphView, completionButton2);
		Scene primaryScene = new Scene(primaryVbox);
		primaryGraphView.update();

		secondaryVbox.getChildren().addAll(secondaryGraphView, completionButton3);
		Scene secondaryScene = new Scene(secondaryVbox);
		secondaryGraphView.update();

		femaleVbox.getChildren().addAll(femaleGraphView, enrollmentData1, enrollmentData2, enrollmentData3);
		Scene femaleScene = new Scene(femaleVbox);
		femaleGraphView.femaleCompletionUpdate();

		//button os
		femaleButton1.setOnAction(event -> stage.setScene(femaleScene));
		stage.setTitle(PRIMARY_COMPLETION_RATE_FEMALE.getLabel());
		stage.show();

		femaleButton2.setOnAction(event -> stage.setScene(femaleScene));
		stage.setTitle(PRIMARY_COMPLETION_RATE_FEMALE.getLabel());
		stage.show();

		femaleButton3.setOnAction(event -> stage.setScene(femaleScene));
		stage.setTitle(PRIMARY_COMPLETION_RATE_FEMALE.getLabel());
		stage.show();

		differentButton1.setOnAction(event -> stage.setScene(gdpScene));
		stage.setTitle(GDP_PER_CAPITA.getLabel());
		stage.show();

		differentButton2.setOnAction(event -> stage.setScene(primaryScene));
		stage.setTitle(SCHOOL_ENROLLMENT_PRIMARY.getLabel());
		stage.show();

		differentButton3.setOnAction(event -> stage.setScene(secondaryScene));
		stage.setTitle(SCHOOL_ENROLLMENT_SECONDARY.getLabel());
		stage.show();

		for (int i = 0; i < 5; i++) {
			switch(selectedIndicator)
			{
				case GDP_PER_CAPITA:
					stage.setScene(gdpScene);
					stage.setTitle("Enrollment Data In Countries Across the World");
					stage.show();
					selectedIndicator = GDP_PER_CAPITA;
					break;
				case SCHOOL_ENROLLMENT_PRIMARY:
					stage.setScene(primaryScene);
					stage.setTitle("Enrollment Data In Countries Across the World");
					stage.show();
					selectedIndicator = SCHOOL_ENROLLMENT_PRIMARY;
					break;
				case SCHOOL_ENROLLMENT_SECONDARY:
					stage.setScene(secondaryScene);
					stage.setTitle("Enrollment Data In Countries Across the World");
					stage.show();
					selectedIndicator = SCHOOL_ENROLLMENT_SECONDARY;
					break;
				case PRIMARY_COMPLETION_RATE_FEMALE:
					stage.setScene(femaleScene);
					stage.setTitle("Female School Completion Rate In Countries Across the World");
					stage.show();
					selectedIndicator = PRIMARY_COMPLETION_RATE_FEMALE;
					break;
				default:
					System.out.println("WARNING: Invalid indicator selected. Exiting program.");
			}

		}

		// Display the stage
		stage.show();
	}


	/**
	 * Launches a standalone JavaFx App
	 * @param args  Not used.
	 */
	public static void main(String[] args)
	{		
		launch();
	}
}