package com.example.demo;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Login extends Application {

    private static final String PASSWORD_CORRECTA = "12345";
    private Scene previousScene; // Almacena la escena anterior

    public Login(Scene previousScene) {
        this.previousScene = previousScene; // Asigna la escena anterior
    }

    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();

        // Header
        HBox header = new HBox();
        header.setStyle("-fx-padding: 10px; -fx-background-color: #f5f5f5; -fx-border-width: 0 0 1 0; -fx-border-color: #ccc;");
        header.setAlignment(Pos.CENTER_LEFT);
        header.setSpacing(20);

        ImageView logoImage = new ImageView(new Image(getClass().getResource("/images/logoWP.png").toExternalForm()));
        logoImage.setPreserveRatio(true);
        logoImage.setFitHeight(50);
        logoImage.setStyle("-fx-cursor: hand;");
        logoImage.setOnMouseClicked(event -> {
            try {
                stage.setScene(previousScene); // Regresa a la escena previa
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Label title = new Label("INMORTEM");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        header.getChildren().addAll(logoImage, title);

        root.setTop(header);

        VBox loginArea = new VBox(10);
        loginArea.setAlignment(Pos.CENTER);
        loginArea.setPadding(new Insets(20));

        Label passwordLabel = new Label("Contraseña:");
        PasswordField passwordField = new PasswordField();

        Button verificarButton = new Button("Iniciar sesión");
        verificarButton.setOnAction(event -> {
            String contraseñaIngresada = passwordField.getText();

            if (PASSWORD_CORRECTA.equals(contraseñaIngresada)) {
                Admin AdminPage = new Admin();
                try {
                    AdminPage.start(stage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                showAlert("Acceso denegado", "La contraseña ingresada es incorrecta. Intenta de nuevo.");
            }
        });



        loginArea.getChildren().addAll(passwordLabel, passwordField, verificarButton);

        root.setCenter(loginArea);

        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Inicio de sesión");
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
