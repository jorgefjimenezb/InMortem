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
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class EditGraph extends Application {
    @Override
    public void start(Stage stage) {
        // Contenedor principal
        BorderPane root = new BorderPane();

        // HEADER
        HBox header = new HBox();
        header.setStyle("-fx-padding: 10px; -fx-background-color: #f5f5f5; -fx-border-width: 0 0 1 0; -fx-border-color: #ccc;");
        header.setSpacing(20);
        header.setAlignment(Pos.CENTER_LEFT);

        // Título
        Label title = new Label("INMORTEM");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

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
        header.getChildren().addAll(title, spacer, goBack);
        root.setTop(header);

        // Tabla en el centro
        VBox centerContainer = new VBox(20);
        centerContainer.setAlignment(Pos.CENTER);
        centerContainer.setPadding(new Insets(20));

        Label tableTitle = new Label("Causa");
        tableTitle.setFont(new Font("Arial", 20));
        tableTitle.setStyle("-fx-font-weight: bold;");

        GridPane table = new GridPane();
        table.setPadding(new Insets(20));
        table.setHgap(10);
        table.setVgap(10);
        table.setStyle("-fx-border-color: black; -fx-border-width: 1px;");

        // Encabezados
        Label yearHeader = new Label("Año");
        yearHeader.setStyle("-fx-font-weight: bold;");
        Label deathsHeader = new Label("Decesos");
        deathsHeader.setStyle("-fx-font-weight: bold;");

        table.add(yearHeader, 0, 0);
        table.add(deathsHeader, 1, 0);

        // Filas
        table.add(new Label("2019"), 0, 1);
        table.add(new Label("5032425"), 1, 1);

        table.add(new Label("2020"), 0, 2);
        table.add(new Label("312412"), 1, 2);

        table.add(new Label("2021"), 0, 3);
        table.add(new Label("421412"), 1, 3);

        // Botón para agregar (+)
        Button addButton = new Button("+");
        addButton.setStyle("-fx-background-color: #28a745; -fx-text-fill: white; -fx-font-size: 20px; -fx-cursor: hand;");
        addButton.setOnAction(event -> System.out.println("Agregar nueva fila"));

        centerContainer.getChildren().addAll(tableTitle, table, addButton);
        root.setCenter(centerContainer);

        // Parte inferior: Advertencia y botón Guardar
        VBox bottomContainer = new VBox(10);
        bottomContainer.setPadding(new Insets(10, 20, 20, 20));
        bottomContainer.setAlignment(Pos.BOTTOM_RIGHT);

        Label warningLabel = new Label();
        warningLabel.setTextFill(Color.RED);
        warningLabel.setStyle("-fx-font-size: 12px;");

        Button saveButton = new Button("Guardar");
        saveButton.setStyle("-fx-background-color: #00aaff; -fx-text-fill: white; -fx-padding: 10 20; -fx-font-size: 14px;");

        // Campos de prueba
        TextField exampleField = new TextField();
        exampleField.setPromptText("Campo de prueba");

        // Acción del botón Guardar
        saveButton.setOnAction(event -> {
            if (exampleField.getText().isEmpty()) {
                warningLabel.setText("¡Todos los campos deben estar completos antes de guardar!");
            } else {
                warningLabel.setText("");
                System.out.println("Información guardada correctamente.");
            }
        });

        bottomContainer.getChildren().addAll(warningLabel, saveButton);
        root.setBottom(bottomContainer);

        // Escena
        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Causa - INMORTEM");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}