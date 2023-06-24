package com.mycompany.weightunitconvertor;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class App extends Application {

    private ComboBox<String> fromUnitComboBox;
    private ComboBox<String> toUnitComboBox;
    private TextField inputTextField;
    private Label resultLabel;

    private static final String[] UNIT_OPTIONS = {"Kilogram", "Gram", "Pound", "Ounce", "Carat", "Tonne", "Grain"};

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Weight Unit Converter");

        GridPane gridPane = createGridPane();
        addUIControls(gridPane);

        Scene scene = new Scene(gridPane, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private GridPane createGridPane() {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(15);
        gridPane.setVgap(15);
        gridPane.setPadding(new Insets(30, 30, 30, 30));
        gridPane.setStyle("-fx-font: 20 arial; -fx-font-style: oblique; -fx-background: linear-gradient(to right, rgb(240,255,240), rgb(255,255,224), rgb(255,228,225), rgb(255,255,224), rgb(240,255,240));");
        return gridPane;
    }

    private void addUIControls(GridPane gridPane) {
        Label titleLabel = new Label("Weight Unit Converter");
        titleLabel.setTextFill(Color.rgb(25,0,51));
        titleLabel.setPadding(new Insets(30));
        titleLabel.setStyle("-fx-font: 40 arial; -fx-font-weight: bold;");
        gridPane.add(titleLabel, 0, 0, 2, 1);

        Label fromLabel = new Label("From:");
        gridPane.add(fromLabel, 0, 1);

        fromUnitComboBox = new ComboBox<>();
        fromUnitComboBox.getItems().addAll(UNIT_OPTIONS);
        fromUnitComboBox.setValue(UNIT_OPTIONS[0]);
        fromUnitComboBox.setStyle("-fx-font-style: normal; -fx-background-color: rgb(255,204,204);");
        gridPane.add(fromUnitComboBox, 1, 1);

        Label toLabel = new Label("To:");
        gridPane.add(toLabel, 0, 2);

        toUnitComboBox = new ComboBox<>();
        toUnitComboBox.getItems().addAll(UNIT_OPTIONS);
        toUnitComboBox.setValue(UNIT_OPTIONS[1]);
        toUnitComboBox.setStyle("-fx-font-style: normal; -fx-background-color: rgb(255,204,204);");
        gridPane.add(toUnitComboBox, 1, 2);

        Label inputLabel = new Label("Input:");
        gridPane.add(inputLabel, 0, 3);

        inputTextField = new TextField();
        inputTextField.setStyle("-fx-font-style: normal; -fx-background-color: rgb(255,204,204);");
        gridPane.add(inputTextField, 1, 3);

        Button convertButton = new Button("CONVERT");
        convertButton.setTextFill(Color.rgb(255,255,255));
        convertButton.setStyle("-fx-font-weight: bold; -fx-background-color: rgb(51,51,255);");
        HBox hbox = new HBox(10);
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().add(convertButton);
        gridPane.add(hbox, 0, 4, 2, 1);

        convertButton.setOnAction(event -> convert());

        resultLabel = new Label();
        gridPane.add(resultLabel, 0, 5, 2, 1);
    }

    private void convert() {
        String fromUnit = fromUnitComboBox.getValue();
        String toUnit = toUnitComboBox.getValue();

        try {
            double inputValue = Double.parseDouble(inputTextField.getText());

            double convertedValue = convertWeight(fromUnit, toUnit, inputValue);
            resultLabel.setText("Result: " + inputValue + " " + fromUnit + " = " + convertedValue + " " + toUnit);
            resultLabel.setTextFill(Color.GREEN);
            resultLabel.setPadding(new Insets(25));
            resultLabel.setStyle("-fx-font: 30 arial; -fx-font-weight: bold; -fx-background-color: rgb(204,255,204);");
        } catch (NumberFormatException e) {
            resultLabel.setText("Invalid input...! Try Again...");
            resultLabel.setTextFill(Color.RED);
            resultLabel.setPadding(new Insets(25));
            resultLabel.setStyle("-fx-font: 30 arial; -fx-font-weight: bold; -fx-background-color: rgb(255,204,204);");
        }
    }

    private double convertWeight(String fromUnit, String toUnit, double value) {
        double convertedValue = value;
        // Conversion logic
        switch (fromUnit) {
            case "Kilogram":
                switch (toUnit) {
                    case "Gram":
                        convertedValue *= 1000;
                        break;
                    case "Pound":
                        convertedValue *= 2.2046226218488;
                        break;
                    case "Ounce":
                        convertedValue *= 35.27396194958;
                        break;
                    case "Carat":
                        convertedValue *= 5000;
                        break;
                    case "Tonne":
                        convertedValue *= 0.001;
                        break;
                    case "Grain":
                        convertedValue *= 15432.358352941;
                        break;
                }
                break;
            case "Gram":
                switch (toUnit) {
                    case "Kilogram":
                        convertedValue /= 1000;
                        break;
                    case "Pound":
                        convertedValue *= 0.0022046226218488;
                        break;
                    case "Ounce":
                        convertedValue *= 0.03527396194958;
                        break;
                    case "Carat":
                        convertedValue *= 5;
                        break;
                    case "Tonne":
                        convertedValue *= 0.000001;
                        break;
                    case "Grain":
                        convertedValue *= 15.432358352941;
                        break;
                }
                break;
            case "Pound":
                switch (toUnit) {
                    case "Kilogram":
                        convertedValue *= 0.45359237;
                        break;
                    case "Gram":
                        convertedValue *= 453.59237;
                        break;
                    case "Ounce":
                        convertedValue *= 16;
                        break;
                    case "Carat":
                        convertedValue *= 2267.96185;
                        break;
                    case "Tonne":
                        convertedValue *= 0.00045359237;
                        break;
                    case "Grain":
                        convertedValue *= 7000;
                        break;
                }
                break;
            case "Ounce":
                switch (toUnit) {
                    case "Kilogram":
                        convertedValue *= 0.028349523125;
                        break;
                    case "Gram":
                        convertedValue *= 28.349523125;
                        break;
                    case "Pound":
                        convertedValue *= 0.0625;
                        break;
                    case "Carat":
                        convertedValue *= 141.747615625;
                        break;
                    case "Tonne":
                        convertedValue *= 0.000028349523125;
                        break;
                    case "Grain":
                        convertedValue *= 437.5;
                        break;
                }
                break;
            case "Carat":
                switch (toUnit) {
                    case "Kilogram":
                        convertedValue *= 0.0002;
                        break;
                    case "Gram":
                        convertedValue *= 0.2;
                        break;
                    case "Pound":
                        convertedValue *= 0.00044092452436976;
                        break;
                    case "Ounce":
                        convertedValue *= 0.0070547923899161;
                        break;
                    case "Tonne":
                        convertedValue *= 0.0000002;
                        break;
                    case "Grain":
                        convertedValue *= 3.0864716705883;
                        break;
                }
                break;
            case "Tonne":
                switch (toUnit) {
                    case "Kilogram":
                        convertedValue *= 1000;
                        break;
                    case "Gram":
                        convertedValue *= 1000000;
                        break;
                    case "Pound":
                        convertedValue *= 2204.6226218488;
                        break;
                    case "Carat":
                        convertedValue *= 5000000;
                        break;
                    case "Ounce":
                        convertedValue *= 35273.96194958;
                        break;
                    case "Grain":
                        convertedValue *= 15432358.352941;
                        break;
                }
                break;
            case "Grain":
                switch (toUnit) {
                    case "Kilogram":
                        convertedValue *= 0.00006479891;
                        break;
                    case "Gram":
                        convertedValue *= 0.06479891;
                        break;
                    case "Pound":
                        convertedValue *= 0.00014285714285714;
                        break;
                    case "Carat":
                        convertedValue *= 0.32399455;
                        break;
                    case "Tonne":
                        convertedValue *= 0.00000006479891;
                        break;
                    case "Ounce":
                        convertedValue *= 0.0022857142857143;
                        break;
                }
                break;
        }
        return convertedValue;
    }
}