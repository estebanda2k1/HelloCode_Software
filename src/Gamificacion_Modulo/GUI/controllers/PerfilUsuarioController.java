package Gamificacion_Modulo.GUI.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PerfilUsuarioController {
    @FXML
    private VBox root;

    @FXML
    private Label userName;
    
    @FXML
    private Label userTag;
    
    @FXML
    private Label expPoints;
    
    @FXML
    private ImageView profileImage;

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
        } catch (Exception e) {
            System.err.println("Error al inicializar la interfaz: " + e.getMessage());
        }
    }

    // Métodos públicos para actualizar la información
    public void actualizarDatosUsuario(String nombre, String tag, String experiencia) {
        if (userName != null) userName.setText(nombre);
        if (userTag != null) userTag.setText("@" + tag);
        if (expPoints != null) expPoints.setText(experiencia);
    }
}
