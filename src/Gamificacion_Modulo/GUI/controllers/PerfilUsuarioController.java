package Gamificacion_Modulo.GUI.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PerfilUsuarioController {
    @FXML
    private VBox root;

    @FXML
    private Label userName;

    @FXML private VBox contenido;
    
    @FXML
    private Label userTag;
    
    @FXML
    private Label expPoints;
    
    @FXML
    private ImageView profileImage;

    @FXML
    private HBox bottomNavigation;

    @FXML
    private void navButton1() {
        contenido.getChildren().clear();
        contenido.getChildren().add(new Label("Vista de Inicio"));
    }

    @FXML
    private void navButton2() {
        contenido.getChildren().clear();
        contenido.getChildren().add(new Label("Vista de Logros"));
    }

    @FXML
    private void navButton3() {
        contenido.getChildren().clear();
        contenido.getChildren().add(new Label("Vista de Perfil"));
    }



    @FXML
    private void initialize() {
        try {
            // Verificar si estamos en modo consola
            if (System.console() != null) {
                Stage stage = (Stage) root.getScene().getWindow();
                stage.close();
                System.out.println("\n=== MODO CONSOLA ACTIVADO ===");
                System.out.println("La interfaz gráfica no está disponible.");
                System.out.println("Cierra esta ventana y ejecuta el programa desde la consola.");
                System.exit(0);
            }

            // Inicializar la barra de navegación

        } catch (Exception e) {
            System.err.println("Error al inicializar la interfaz: " + e.getMessage());
        }
    }



    private Button createNavButton(String imageUrl, String text) {
        Button button = new Button();
        button.getStyleClass().add("nav-button");

        ImageView icon = new ImageView(new Image(getClass().getResource(imageUrl).toExternalForm()));
        icon.setFitWidth(24);
        icon.setFitHeight(24);

        button.setGraphic(icon);
        return button;
    }

    // Métodos públicos para actualizar la información
    public void actualizarDatosUsuario(String nombre, String tag, String experiencia) {
        if (userName != null) userName.setText(nombre);
        if (userTag != null) userTag.setText("@" + tag);
        if (expPoints != null) expPoints.setText(experiencia);
    }
}
