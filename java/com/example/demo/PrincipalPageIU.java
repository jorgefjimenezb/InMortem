package com.example.demo;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PrincipalPageIU extends Application {

    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();

        // Header

        HBox topBox = new HBox();
        topBox.setStyle("-fx-padding: 10px; -fx-background-color: #f5f5f5; -fx-border-width: 0 0 1 0; -fx-border-color: #ccc;");
        topBox.setAlignment(Pos.CENTER);
        topBox.setSpacing(600);

        HBox leftBox = new HBox(10);
        leftBox.setAlignment(Pos.CENTER_LEFT);

        ImageView logoImage = new ImageView(new Image(getClass().getResource("/images/logoWP.png").toExternalForm()));
        logoImage.setPreserveRatio(true);
        logoImage.setFitHeight(50);

        Label nameLabel = new Label("InMortem");
        nameLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        leftBox.getChildren().addAll(logoImage, nameLabel);

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

        topBox.getChildren().addAll(leftBox, userImage);
        root.setTop(topBox);

        //Body

        VBox centerBox = new VBox(20);
        centerBox.setAlignment(Pos.CENTER);
        centerBox.setStyle("-fx-padding: 20px;");

        Label title = new Label("Causas de mortalidad en México");
        title.setStyle("-fx-font-size: 45px; -fx-font-weight: bold;");

        Label description = new Label(
                "Aquí se muestra información general sobre las estadísticas de mortalidad en México. "
                        + "Explora las causas para encontrar gráficas, utiliza la calculadora de riesgo o contacta a los especialistas para más información.");
        description.setWrapText(true);
        description.setMaxWidth(550);
        description.setStyle("-fx-font-size: 16px;");

        root.setCenter(centerBox);

        HBox botonesCausas = new HBox(20);
        botonesCausas.setAlignment(Pos.CENTER);
        for (int i = 1; i <= 5; i++) {
            final int causaIndex = i;
            Button btnCausa = new Button("Causa " + causaIndex);
            btnCausa.setPrefSize(100, 80);
            btnCausa.setStyle("-fx-background-radius: 15; -fx-font-size: 14px; -fx-cursor: hand;");
            btnCausa.setOnAction(event -> {
                try {
                    InfoCauses infoCausesPage = new InfoCauses();
                    infoCausesPage.setCausaIndex(causaIndex);
                    infoCausesPage.start(stage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });



            botonesCausas.getChildren().add(btnCausa);
        }

        centerBox.getChildren().addAll(title, description, botonesCausas);
        root.setCenter(centerBox);



        // Footer

        HBox footerBox = new HBox(50);
        footerBox.setAlignment(Pos.CENTER);
        footerBox.setStyle("-fx-padding: 10px; -fx-background-color: #c3b4a4;");

        for (int i = 1; i <= 5; i++) {
            final int doctorIndex = i;
            Button btnDoctor = new Button("Doctor " + doctorIndex);
            btnDoctor.setPrefSize(100, 50);
            btnDoctor.setStyle("-fx-background-radius: 15; -fx-font-size: 14px;");

            // Configurar acción al hacer clic en el botón
            btnDoctor.setStyle("-fx-cursor: hand;");
            btnDoctor.setOnAction(event -> {
                try {
                    String url;
                    switch (doctorIndex) {
                        case 1 -> url = "https://www.doctoralia.com.mx/nutriologo-clinico/ciudad-de-mexico";
                        case 2 -> url = "https://www.doctoralia.com.mx/endocrinologo/ciudad-de-mexico";
                        case 3 -> url = "https://www.doctoralia.com.mx/cardiologo/ciudad-de-mexico";
                        case 4 -> url = "https://www.doctoralia.com.mx/nefrologo/ciudad-de-mexico";
                        case 5 -> url = "https://www.doctoralia.com.mx/gastroenterologo/ciudad-de-mexico";
                        default -> {
                            System.out.println("Agende su cita con el doctor más cercano.");
                            return;
                        }
                    }
                    openWebpage(url);
                } catch (Exception e) {
                    System.err.println("Error al abrir la página: " + e.getMessage());
                    e.printStackTrace();
                }
            });



            footerBox.getChildren().add(btnDoctor);
        }

        root.setBottom(footerBox);

        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Página Principal");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    /**
     * Abre un enlace en el navegador predeterminado, compatible con Windows, macOS y Linux.
     */
    private static void openWebpage(String url) {
        try {
            String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                Runtime.getRuntime().exec(new String[]{"cmd", "/c", "start", url});
            } else if (os.contains("mac")) {
                Runtime.getRuntime().exec(new String[]{"open", url});
            } else if (os.contains("nix") || os.contains("nux")) {
                Runtime.getRuntime().exec(new String[]{"xdg-open", url});
            } else {
                System.err.println("Sistema operativo no soportado para abrir URLs.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
