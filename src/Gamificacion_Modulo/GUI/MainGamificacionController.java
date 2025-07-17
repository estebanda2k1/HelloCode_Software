package Gamificacion_Modulo.GUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.event.ActionEvent;
import java.io.IOException;

public class MainGamificacionController {
    
    @FXML
    private StackPane contentPane;
    
    @FXML
    private Label lblTitulo;
    
    @FXML
    private Button btnHome;
    
    @FXML
    private Button btnDesafios;
    
    @FXML
    private Button btnLogros;
    
    @FXML
    private Button btnRanking;
    
    @FXML
    private Button btnPerfil;
    
    @FXML
    private Button btnEstadisticas;
    
    private String currentView = "perfil";
    
    @FXML
    public void initialize() {
        // Cargar la vista inicial del perfil
        cargarVista("PerfilUsuario.fxml", "Perfil");
        actualizarEstadoNavegacion("perfil");
    }
    
    @FXML
    private void onHomeClicked(ActionEvent event) {
        cargarVista("PerfilUsuario.fxml", "Inicio");
        actualizarEstadoNavegacion("home");
    }
    
    @FXML
    private void onDesafiosClicked(ActionEvent event) {
        cargarVista("Desafios.fxml", "Desafíos");
        actualizarEstadoNavegacion("desafios");
    }
    
    @FXML
    private void onLogrosClicked(ActionEvent event) {
        cargarVista("Logros.fxml", "Logros");
        actualizarEstadoNavegacion("logros");
    }
    
    @FXML
    private void onRankingClicked(ActionEvent event) {
        cargarVista("Ranking.fxml", "Ranking");
        actualizarEstadoNavegacion("ranking");
    }
    
    @FXML
    private void onPerfilClicked(ActionEvent event) {
        cargarVista("PerfilUsuario.fxml", "Perfil");
        actualizarEstadoNavegacion("perfil");
    }
    
    @FXML
    private void onEstadisticasClicked(ActionEvent event) {
        cargarVista("Estadisticas.fxml", "Estadísticas");
        actualizarEstadoNavegacion("estadisticas");
    }
    
    private void cargarVista(String fxmlFile, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent vista = loader.load();
            
            contentPane.getChildren().clear();
            contentPane.getChildren().add(vista);
            
            lblTitulo.setText(titulo);
            
            System.out.println(">>> Vista cargada: " + titulo);
        } catch (IOException e) {
            System.err.println("Error al cargar la vista " + fxmlFile + ": " + e.getMessage());
            e.printStackTrace();
            
            // Mostrar mensaje de error en la interfaz
            mostrarMensajeError("Error al cargar " + titulo);
        }
    }
    
    private void actualizarEstadoNavegacion(String vistaActual) {
        // Remover estilo de selección de todos los botones
        btnHome.getStyleClass().removeAll("selected");
        btnDesafios.getStyleClass().removeAll("selected");
        btnLogros.getStyleClass().removeAll("selected");
        btnRanking.getStyleClass().removeAll("selected");
        btnPerfil.getStyleClass().removeAll("selected");
        
        // Agregar estilo de selección al botón activo
        switch (vistaActual) {
            case "home":
                btnHome.getStyleClass().add("selected");
                break;
            case "desafios":
                btnDesafios.getStyleClass().add("selected");
                break;
            case "logros":
                btnLogros.getStyleClass().add("selected");
                break;
            case "ranking":
                btnRanking.getStyleClass().add("selected");
                break;
            case "perfil":
                btnPerfil.getStyleClass().add("selected");
                break;
        }
        
        this.currentView = vistaActual;
    }
    
    private void mostrarMensajeError(String mensaje) {
        Label errorLabel = new Label(mensaje);
        errorLabel.setStyle("-fx-text-fill: red; -fx-font-size: 16px; -fx-padding: 20px;");
        
        contentPane.getChildren().clear();
        contentPane.getChildren().add(errorLabel);
    }
    
    public String getCurrentView() {
        return currentView;
    }
}
