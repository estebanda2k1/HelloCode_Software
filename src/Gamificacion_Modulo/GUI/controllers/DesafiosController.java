package Gamificacion_Modulo.GUI.controllers;

import Gamificacion_Modulo.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.event.ActionEvent;

public class DesafiosController {

    @FXML
    private Button btnHome;
    @FXML
    private Button btnProfile;
    @FXML
    private Button btnRanking;

    // Botón 1 (izquierda) - Home/Inicio - se queda en Desafíos
    @FXML
    private void onHomeClicked(ActionEvent event) {
        System.out.println(">>> Ya estás en la pantalla de Desafíos (Home)");
        // Ya estamos en la pantalla de desafíos, no hacer nada o refrescar
    }

    // Botón 2 (centro) - Perfil - navegar a PerfilUsuario.fxml
    @FXML
    private void onProfileClicked(ActionEvent event) {
        System.out.println(">>> Navegando a Perfil de Usuario desde Desafíos");
        try {
            // Cargar PerfilUsuario.fxml desde la carpeta fxml
            Main.cambiarEscena("GUI/fxml/PerfilUsuario.fxml");
        } catch (Exception e) {
            System.err.println("Error al navegar a PerfilUsuario: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Botón 3 (derecha) - Ranking/Desafíos - se queda en Desafíos
    @FXML
    private void onRankingClicked(ActionEvent event) {
        System.out.println(">>> Ya estás en la pantalla de Desafíos (Ranking)");
        // Ya estamos en la pantalla de desafíos, podríamos mostrar diferentes secciones
        // o simplemente indicar que ya estamos aquí
    }

    // Método de inicialización
    @FXML
    private void initialize() {
        System.out.println(">>> Controlador de Desafíos inicializado");
        // Configuración adicional si es necesaria
    }
}
