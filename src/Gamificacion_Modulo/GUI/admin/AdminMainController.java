package Gamificacion_Modulo.GUI.admin;

import Gamificacion_Modulo.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AdminMainController {
    
    @FXML
    private Button btnVolver;
    
    @FXML
    private Button btnCrearDesafioSemanal;
    
    @FXML
    private Button btnCrearDesafioMensual;
    
    @FXML
    private Button btnCrearLogro;
    
    @FXML
    private Button btnAsignarDesafio;
    
    @FXML
    private Button btnActualizarStats;
    
    @FXML
    private Label lblEstadisticas;
    
    @FXML
    public void initialize() {
        actualizarEstadisticas();
    }
    
    @FXML
    private void onVolverClicked(ActionEvent event) {
        System.out.println(">>> Volviendo al menú principal");
        // Cerrar ventana de administración
        Stage stage = (Stage) btnVolver.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    private void onCrearDesafioSemanalClicked(ActionEvent event) {
        System.out.println(">>> Abriendo creador de desafío semanal");
        abrirVentana("CrearDesafioSemanal.fxml", "Crear Desafío Semanal");
    }
    
    @FXML
    private void onCrearDesafioMensualClicked(ActionEvent event) {
        System.out.println(">>> Abriendo creador de desafío mensual");
        abrirVentana("CrearDesafioMensual.fxml", "Crear Desafío Mensual");
    }
    
    @FXML
    private void onCrearLogroClicked(ActionEvent event) {
        System.out.println(">>> Abriendo creador de logro");
        abrirVentana("CrearLogro.fxml", "Crear Logro Personalizado");
    }
    
    @FXML
    private void onAsignarDesafioClicked(ActionEvent event) {
        System.out.println(">>> Abriendo asignador de desafío");
        abrirVentana("AsignarDesafio.fxml", "Asignar Desafío a Estudiante");
    }
    
    @FXML
    private void onActualizarStatsClicked(ActionEvent event) {
        System.out.println(">>> Actualizando estadísticas del sistema");
        actualizarEstadisticas();
    }
    
    private void actualizarEstadisticas() {
        try {
            int totalEstudiantes = Main.getEstudiantes().size();
            int totalLogros = Main.getLogrosDisponibles().size();
            int totalProgresos = Main.getProgresos().size();
            
            // Contar desafíos activos
            int desafiosActivos = 0;
            for (ProgresoEstudiante progreso : Main.getProgresos()) {
                desafiosActivos += progreso.getDesafiosActivos().size();
            }
            
            String estadisticas = String.format(
                "Estudiantes: %d | Logros: %d | Progreso: %d | Desafíos Activos: %d",
                totalEstudiantes, totalLogros, totalProgresos, desafiosActivos
            );
            
            lblEstadisticas.setText(estadisticas);
            System.out.println(">>> Estadísticas actualizadas: " + estadisticas);
            
        } catch (Exception e) {
            lblEstadisticas.setText("Error al cargar estadísticas");
            System.err.println("Error al actualizar estadísticas: " + e.getMessage());
        }
    }
    
    private void abrirVentana(String fxmlFile, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();
            
            Stage stage = new Stage();
            stage.setTitle(titulo);
            // Forzar dimensiones específicas para todas las ventanas admin
            Scene scene = new Scene(root, 400, 852);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            
            // Centrar la ventana en pantalla
            stage.centerOnScreen();
            
            // Actualizar estadísticas cuando se cierre la ventana
            stage.setOnHidden(e -> actualizarEstadisticas());
            
            stage.showAndWait();
            
        } catch (Exception e) {
            System.err.println("Error al abrir ventana " + fxmlFile + ": " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    // Método estático para abrir la ventana de administración desde cualquier lugar
    public static void mostrarVentanaAdmin() {
        try {
            // Usar reflexión simplificada para crear la ventana
            Class<?> fxmlLoaderClass = Class.forName("javafx.fxml.FXMLLoader");
            Object loader = fxmlLoaderClass.getConstructor(java.net.URL.class)
                    .newInstance(AdminMainController.class.getResource("AdminMain.fxml"));
            
            java.lang.reflect.Method loadMethod = fxmlLoaderClass.getMethod("load");
            Object root = loadMethod.invoke(loader);
            
            Class<?> stageClass = Class.forName("javafx.stage.Stage");
            Object stage = stageClass.getConstructor().newInstance();
            
            java.lang.reflect.Method setTitleMethod = stageClass.getMethod("setTitle", String.class);
            setTitleMethod.invoke(stage, "Panel de Administración - HelloCode");
            
            Class<?> sceneClass = Class.forName("javafx.scene.Scene");
            // Crear Scene con dimensiones específicas 400x852
            Object scene = sceneClass.getConstructor(Class.forName("javafx.scene.Parent"), double.class, double.class)
                    .newInstance(root, 400.0, 852.0);
            
            java.lang.reflect.Method setSceneMethod = stageClass.getMethod("setScene", sceneClass);
            setSceneMethod.invoke(stage, scene);
            
            java.lang.reflect.Method setResizableMethod = stageClass.getMethod("setResizable", boolean.class);
            setResizableMethod.invoke(stage, false);
            
            // Centrar ventana en pantalla
            try {
                java.lang.reflect.Method centerMethod = stageClass.getMethod("centerOnScreen");
                centerMethod.invoke(stage);
            } catch (NoSuchMethodException e) {
                // centerOnScreen no disponible, continuar sin centrar
            }
            
            java.lang.reflect.Method showMethod = stageClass.getMethod("show");
            showMethod.invoke(stage);
            
            System.out.println(">>> Ventana de administración abierta exitosamente (400x852)");
            
        } catch (Exception e) {
            System.err.println("Error al abrir ventana de administración: " + e.getMessage());
            System.err.println("Verifica que JavaFX esté configurado correctamente");
            e.printStackTrace();
        }
    }
} 