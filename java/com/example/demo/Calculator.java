package com.example.demo;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Calculator extends Application {
    private int causaIndex;

    public void setCausaIndex(int causaIndex) {
        this.causaIndex = causaIndex;
    }

    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();

        HBox header = new HBox();
        header.setStyle("-fx-padding: 10px; -fx-background-color: #f5f5f5; -fx-border-width: 0 0 1 0; -fx-border-color: #ccc;");
        header.setAlignment(Pos.CENTER_LEFT);
        header.setSpacing(20);

        ImageView logoImage = new ImageView(new Image(getClass().getResource("/images/logoWP.png").toExternalForm()));
        logoImage.setPreserveRatio(true);
        logoImage.setFitHeight(50);
        logoImage.setStyle("-fx-cursor: hand;");
        logoImage.setOnMouseClicked(event -> {
            PrincipalPageIU PPPage = new PrincipalPageIU();
            try{
                PPPage.start(stage);
            } catch (Exception e){
                e.printStackTrace();
            }
        });

        Label title = new Label("INMORTEM");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        HBox menuButtons = new HBox(20);
        menuButtons.setAlignment(Pos.CENTER);
        for (int i = 1; i <= 5; i++) {
            final int opcionIndex = i;

            Button menuButton = new Button("Causa " + opcionIndex);
            menuButton.setStyle(
                    "-fx-font-size: 14px; " +
                            "-fx-background-color: transparent; " +
                            "-fx-border-color: transparent; " +
                            "-fx-padding: 10px;" +
                            "-fx-cursor: hand;"
            );

            menuButton.setOnAction(event -> {
                setCausaIndex(causaIndex);
                //updateCalculator();
            });
            menuButtons.getChildren().add(menuButton);
        }

        ImageView userImage = new ImageView(new Image(getClass().getResource("/images/user.png").toExternalForm()));
        userImage.setPreserveRatio(true);
        userImage.setFitHeight(25);
        userImage.setStyle("-fx-cursor: hand;");
        userImage.setOnMouseClicked(event -> {
            Scene currentScene = stage.getScene();
            Login loginPage = new Login(currentScene);
            try {
                loginPage.start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        HBox headerContent = new HBox(20, logoImage, title);
        headerContent.setAlignment(Pos.CENTER_LEFT);
        header.getChildren().addAll(headerContent, menuButtons, userImage);
        HBox.setHgrow(menuButtons, Priority.ALWAYS);
        root.setTop(header);

        BorderPane centerPane = new BorderPane();
        root.setCenter(centerPane);

        VBox leftText = new VBox(10);
        leftText.setStyle("-fx-padding: 20px;");
        leftText.setAlignment(Pos.TOP_LEFT);

        Label titleCause = new Label("Causa - Calculadora");
        titleCause.setStyle("-fx-font-size: 36px; -fx-font-weight: bold;");

        Label description = new Label(
                "Aquí puedes calcular el riesgo (IMC). Ingresa tu altura y peso para obtener el resultado.");
        description.setWrapText(true);
        description.setMaxWidth(300);
        description.setStyle("-fx-font-size: 16px;");

        leftText.getChildren().addAll(titleCause, description);
        centerPane.setLeft(leftText);

        VBox calculatorArea = new VBox(10);
        calculatorArea.setAlignment(Pos.TOP_CENTER);
        calculatorArea.setPadding(new Insets(20));

        Label alturaLabel = new Label("Altura (m):");
        TextField alturaField = new TextField();

        Label pesoLabel = new Label("Peso (kg):");
        TextField pesoField = new TextField();

        Button calcularButton = new Button("Calcular IMC");
        Label resultadoLabel = new Label();
        calcularButton.setStyle("-fx-cursor: hand;");
        calcularButton.setOnAction(event -> {
            try {
                double altura = Double.parseDouble(alturaField.getText());
                double peso = Double.parseDouble(pesoField.getText());

                if (altura <= 0 || peso <= 0) {
                    showAlert("Datos inválidos", "Altura o peso incorrectos. Por favor, verifica los datos ingresados.");
                } else {
                    double imc = peso / (altura * altura);
                    resultadoLabel.setText("Tu índice de masa corporal es: " + String.format("%.2f", imc));
                }
            } catch (NumberFormatException e) {
                showAlert("Error de formato", "Por favor, ingresa números válidos para altura y peso.");
            }
        });

        calculatorArea.getChildren().addAll(alturaLabel, alturaField, pesoLabel, pesoField, calcularButton, resultadoLabel);
        centerPane.setCenter(calculatorArea);

        VBox rightButtons = new VBox(10);
        rightButtons.setPadding(new Insets(50, 20, 0, 0));
        rightButtons.setAlignment(Pos.TOP_CENTER);

        String[] buttonImages = {"/images/info.png", "/images/bar-chart.png", "/images/calculator.png"};
        String[] buttonClasses = {"InfoCauses", "Graphics", "Calculator"};
        for (int i = 0; i < 3; i++) {
            final int optionLB = i;
            Button imageButton = new Button();
            ImageView buttonImage = new ImageView(new Image(getClass().getResource(buttonImages[i]).toExternalForm()));

            buttonImage.setFitHeight(15);
            buttonImage.setFitWidth(15);
            buttonImage.setPreserveRatio(true);

            imageButton.setGraphic(buttonImage);

            if (i == 2) {
                imageButton.setStyle(
                        "-fx-background-radius: 50; " +
                                "-fx-min-width: 40px; " +
                                "-fx-min-height: 40px; " +
                                "-fx-background-color: #D3D3D3; " +
                                "-fx-border-color: transparent; " +
                                "-fx-padding: 0;"
                );
            } else {
                imageButton.setStyle(
                        "-fx-background-radius: 50; " +
                                "-fx-min-width: 40px; " +
                                "-fx-min-height: 40px; " +
                                "-fx-background-color: #000000; " +
                                "-fx-border-color: transparent; " +
                                "-fx-padding: 0;"
                );
            }

            imageButton.setOnMouseEntered(event -> imageButton.setStyle(
                    "-fx-background-radius: 50; " +
                            "-fx-min-width: 40px; " +
                            "-fx-min-height: 40px; " +
                            "-fx-background-color: #D3D3D3; " +
                            "-fx-border-color: transparent; " +
                            "-fx-padding: 0;" +
                            "-fx-cursor: hand;"
            ));

            imageButton.setOnMouseExited(event -> {
                if (optionLB == 2) {
                    imageButton.setStyle(
                            "-fx-background-radius: 50; " +
                                    "-fx-min-width: 40px; " +
                                    "-fx-min-height: 40px; " +
                                    "-fx-background-color: #D3D3D3; " +
                                    "-fx-border-color: transparent; " +
                                    "-fx-padding: 0;"
                    );
                } else {
                    imageButton.setStyle(
                            "-fx-background-radius: 50; " +
                                    "-fx-min-width: 40px; " +
                                    "-fx-min-height: 40px; " +
                                    "-fx-background-color: #000000; " +
                                    "-fx-border-color: transparent; " +
                                    "-fx-padding: 0;"
                    );
                }
            });

            imageButton.setOnAction(event -> {
                try {
                    // creamos la instancia de la clase correspondiente
                    Class<?> pageClass = Class.forName("com.example.demo." + buttonClasses[optionLB]);
                    Application page = (Application) pageClass.getDeclaredConstructor().newInstance();
                    page.start(stage);  // y aquí redirigimos a la nueva página
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            rightButtons.getChildren().add(imageButton);
        }

        centerPane.setRight(rightButtons);

        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Calculadora Causas");
        stage.setScene(scene);
        stage.show();
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch();
    }
}