package Gamificacion_Modulo.GUI.controllers;

import Gamificacion_Modulo.Main;
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

    // Botón 1 (izquierda) - navegar a Desafíos
    @FXML
    private void navButton1() {
        System.out.println(">>> Navegando a Desafíos desde Perfil de Usuario");
        try {
            // Cargar Desafios.fxml desde la carpeta fxml
            Main.cambiarEscena("GUI/fxml/Desafios.fxml");
        } catch (Exception e) {
            System.err.println("Error al navegar a Desafios: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Botón 2 (centro) - Perfil - se queda en PerfilUsuario
    @FXML
    private void navButton2() {
        System.out.println(">>> Ya estás en la pantalla de Perfil de Usuario");
        // Ya estamos en la pantalla de perfil, podríamos refrescar datos o mostrar mensaje
        contenido.getChildren().clear();
        contenido.getChildren().add(new Label("Perfil de Usuario - Pantalla Actual"));
    }

    // Botón 3 (derecha) - navegar a Desafíos
    @FXML
    private void navButton3() {
        System.out.println(">>> Navegando a Desafíos desde Perfil de Usuario (botón 3)");
        try {
            // Cargar Desafios.fxml desde la carpeta fxml
            Main.cambiarEscena("GUI/fxml/Desafios.fxml");
        } catch (Exception e) {
            System.err.println("Error al navegar a Desafios: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void initialize() {
        try {
            System.out.println(">>> Controlador de Perfil de Usuario inicializado");
            
            // Verificar si estamos en modo consola
            if (System.console() != null) {
                Stage stage = (Stage) root.getScene().getWindow();
                stage.close();
                System.out.println("\n=== MODO CONSOLA ACTIVADO ===");
                System.out.println("La interfaz gráfica no está disponible.");
                System.out.println("Cierra esta ventana y ejecuta el programa desde la consola.");
                System.exit(0);
            }

            // Inicializar contenido por defecto
            if (contenido != null) {
                contenido.getChildren().clear();
                contenido.getChildren().add(new Label("Bienvenido al Perfil de Usuario"));
            }

        } catch (Exception e) {
            System.err.println("Error al inicializar la interfaz de Perfil: " + e.getMessage());
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
