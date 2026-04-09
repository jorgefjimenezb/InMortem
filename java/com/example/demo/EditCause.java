package com.example.demo;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class EditCause extends Application {
    @Override
    public void start(Stage stage) {
        // Contenedor principal
        BorderPane root = new BorderPane();

        // HEADER
        HBox header = new HBox();
        header.setStyle("-fx-padding: 10px; -fx-background-color: #f5f5f5; -fx-border-width: 0 0 1 0; -fx-border-color: #ccc;");
        header.setSpacing(20);
        header.setAlignment(Pos.CENTER_LEFT);

        // Logo y título
        ImageView logoImage = new ImageView(new Image(getClass().getResource("/images/logoWP.png").toExternalForm()));
        logoImage.setPreserveRatio(true);
        logoImage.setFitHeight(50);

        Label title = new Label("INMORTEM");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Label goBack = new Label("Regresar");
        goBack.setStyle("-fx-font-size: 12px; -fx-cursor: hand;");
        goBack.setOnMouseClicked(mouseEvent -> {
            Admin AdminPage = new Admin();
            try {
                AdminPage.start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Espaciador flexible para alinear el logout a la derecha
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        // Agregar componentes al header
        header.getChildren().addAll(logoImage, title, spacer, goBack);
        root.setTop(header);

        // GridPane para los campos
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(20));

        Label nameLabel = new Label("Nombre");
        TextField nameField = new TextField();

        Label infoLabel = new Label("Información General");
        TextField infoField = new TextField();

        Label precautionsLabel = new Label("Precauciones");
        TextField precautionsField = new TextField();

        Label indicationsLabel = new Label("Indicaciones");
        TextField indicationsField = new TextField();

        // Agregar filas
        grid.add(nameLabel, 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(infoLabel, 0, 1);
        grid.add(infoField, 1, 1);
        grid.add(precautionsLabel, 0, 2);
        grid.add(precautionsField, 1, 2);
        grid.add(indicationsLabel, 0, 3);
        grid.add(indicationsField, 1, 3);


        for (int i = 4; i < 7; i++) {
            grid.add(new Label("..............................."), 0, i, 2, 1);
        }

        root.setCenter(grid);

        // Label para advertencias
        Label warningLabel = new Label();
        warningLabel.setTextFill(Color.RED);
        warningLabel.setStyle("-fx-font-size: 12px;");

        Button saveButton = new Button("Guardar");
        saveButton.setStyle("-fx-background-color: #00aaff; -fx-text-fill: white; -fx-padding: 10 20; -fx-font-size: 14px;");

        saveButton.setOnAction(event -> {
            if (nameField.getText().isEmpty() || infoField.getText().isEmpty() ||
                    precautionsField.getText().isEmpty() || indicationsField.getText().isEmpty()) {
                warningLabel.setText("¡Todos los campos deben estar completos antes de guardar!");
            } else {
                warningLabel.setText("");
                System.out.println("Información guardada correctamente.");
            }
        });

        VBox bottomContainer = new VBox(10, warningLabel, saveButton);
        bottomContainer.setAlignment(Pos.BOTTOM_RIGHT);
        bottomContainer.setPadding(new Insets(10, 0, 0, 0));
        root.setBottom(bottomContainer);

        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Causa - INMORTEM");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}