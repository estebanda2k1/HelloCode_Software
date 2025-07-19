package Gamificacion_Modulo.GUI.admin;

import Gamificacion_Modulo.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.*;

public class AsignarDesafioController implements Initializable {
    
    @FXML private VBox mainVBox;
    @FXML private ComboBox<String> cbEstudiantes;
    @FXML private Label lblInfoEstudiante;
    @FXML private VBox vboxDesafiosDisponibles;
    @FXML private Label lblDesafioSeleccionado;
    @FXML private VBox vboxDesafiosActivos;
    @FXML private Button btnCancelar;
    @FXML private Button btnAsignar;
    
    private List<Estudiante> estudiantes;
    private List<Desafio> desafiosDisponibles;
    private List<RadioButton> radioButtonsDesafios;
    private ToggleGroup toggleGroupDesafios;
    private Estudiante estudianteSeleccionado;
    private Desafio desafioSeleccionado;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        estudiantes = Main.getEstudiantes();
        desafiosDisponibles = Main.getDesafiosSinAsignar();
        radioButtonsDesafios = new ArrayList<>();
        toggleGroupDesafios = new ToggleGroup();
        
        cargarEstudiantes();
        cargarDesafiosDisponibles();
        configurarEventos();
        
        System.out.println(">>> Asignador de desafíos inicializado");
        System.out.println(">>> Estudiantes disponibles: " + estudiantes.size());
        System.out.println(">>> Desafíos sin asignar: " + desafiosDisponibles.size());
    }
    
    private void cargarEstudiantes() {
        cbEstudiantes.getItems().clear();
        for (Estudiante estudiante : estudiantes) {
            cbEstudiantes.getItems().add(estudiante.getId() + " - " + estudiante.getNombre());
        }
    }
    
    private void cargarDesafiosDisponibles() {
        vboxDesafiosDisponibles.getChildren().clear();
        radioButtonsDesafios.clear();
        
        if (desafiosDisponibles.isEmpty()) {
            Label lblVacio = new Label("No hay desafíos disponibles para asignar");
            lblVacio.getStyleClass().add("info-text");
            lblVacio.setStyle("-fx-text-fill: #666666;");
            vboxDesafiosDisponibles.getChildren().add(lblVacio);
            
            Label lblInstruccion = new Label("Crea desafíos usando las otras opciones del panel de administración");
            lblInstruccion.getStyleClass().add("info-text");
            lblInstruccion.setStyle("-fx-text-fill: #888888; -fx-font-size: 11px;");
            lblInstruccion.setWrapText(true);
            vboxDesafiosDisponibles.getChildren().add(lblInstruccion);
            return;
        }
        
        for (Desafio desafio : desafiosDisponibles) {
            String tipo = desafio instanceof DesafioSemanal ? "Semanal" : "Mensual";
            String meta = "";
            
            if (desafio instanceof DesafioSemanal) {
                meta = " (Meta: " + ((DesafioSemanal) desafio).getMetaSemanal() + " actividades/semana)";
            } else if (desafio instanceof DesafioMensual) {
                meta = " (Meta: " + ((DesafioMensual) desafio).getObjetivoMensual() + " actividades/mes)";
            }
            
            RadioButton rb = new RadioButton();
            rb.setToggleGroup(toggleGroupDesafios);
            rb.setText("🎯 " + desafio.getNombre() + " (" + tipo + ")" + meta);
            rb.setWrapText(true);
            rb.setUserData(desafio);
            
            // Agregar información de logros
            Label lblLogros = new Label("   Logros asociados: " + desafio.getLogrosDisponibles().size() + 
                                      " | Descripción: " + desafio.getDescripcion());
            lblLogros.getStyleClass().add("info-text");
            lblLogros.setStyle("-fx-text-fill: #666666; -fx-font-size: 11px;");
            lblLogros.setWrapText(true);
            
            radioButtonsDesafios.add(rb);
            vboxDesafiosDisponibles.getChildren().addAll(rb, lblLogros);
            
            // Espaciado entre desafíos
            if (desafiosDisponibles.indexOf(desafio) < desafiosDisponibles.size() - 1) {
                vboxDesafiosDisponibles.getChildren().add(new Label(" ")); // Espaciador
            }
        }
        
        // Listener para detectar selección de desafío
        toggleGroupDesafios.selectedToggleProperty().addListener((obs, oldToggle, newToggle) -> {
            if (newToggle != null) {
                desafioSeleccionado = (Desafio) newToggle.getUserData();
                actualizarDesafioSeleccionado();
                validarFormulario();
            }
        });
    }
    
    private void configurarEventos() {
        // Evento al seleccionar estudiante
        cbEstudiantes.setOnAction(e -> {
            String seleccion = cbEstudiantes.getValue();
            if (seleccion != null) {
                long idEstudiante = Long.parseLong(seleccion.split(" - ")[0]);
                estudianteSeleccionado = encontrarEstudiantePorId(idEstudiante);
                if (estudianteSeleccionado != null) {
                    actualizarInfoEstudiante();
                    actualizarDesafiosActivos();
                    validarFormulario();
                }
            }
        });
    }
    
    private void actualizarDesafioSeleccionado() {
        if (desafioSeleccionado != null) {
            String tipo = desafioSeleccionado instanceof DesafioSemanal ? "Semanal" : "Mensual";
            lblDesafioSeleccionado.setText("✅ Seleccionado: " + desafioSeleccionado.getNombre() + " (" + tipo + ")");
            lblDesafioSeleccionado.setStyle("-fx-text-fill: #4CAF50;");
        } else {
            lblDesafioSeleccionado.setText("Ningún desafío seleccionado");
            lblDesafioSeleccionado.setStyle("-fx-text-fill: #666666;");
        }
    }
    
    private void actualizarInfoEstudiante() {
        if (estudianteSeleccionado != null) {
            ProgresoEstudiante progreso = encontrarProgresoPorEstudiante(estudianteSeleccionado);
            if (progreso != null) {
                lblInfoEstudiante.setText(String.format(
                    "ID: %d | Puntos: %d | Logros: %d | Desafíos activos: %d",
                    estudianteSeleccionado.getId(),
                    progreso.getPuntosTotal(),
                    progreso.getLogros().size(),
                    progreso.getDesafiosActivos().size()
                ));
                lblInfoEstudiante.setStyle("-fx-text-fill: #4CAF50;");
            }
        }
    }
    
    private void actualizarDesafiosActivos() {
        vboxDesafiosActivos.getChildren().clear();
        
        if (estudianteSeleccionado != null) {
            ProgresoEstudiante progreso = encontrarProgresoPorEstudiante(estudianteSeleccionado);
            if (progreso != null && !progreso.getDesafiosActivos().isEmpty()) {
                for (Desafio desafio : progreso.getDesafiosActivos()) {
                    String tipo = desafio instanceof DesafioSemanal ? "Semanal" : "Mensual";
                    String estado = desafio.estaCompletado() ? "✅ Completado" : "⏳ En progreso";
                    
                    Label lblDesafio = new Label("• " + desafio.getNombre() + " (" + tipo + ") - " + estado);
                    lblDesafio.getStyleClass().add("info-text");
                    lblDesafio.setWrapText(true);
                    
                    vboxDesafiosActivos.getChildren().add(lblDesafio);
                }
            } else {
                Label lblVacio = new Label("No tiene desafíos activos");
                lblVacio.getStyleClass().add("info-text");
                vboxDesafiosActivos.getChildren().add(lblVacio);
            }
        }
    }
    
    private void validarFormulario() {
        boolean valid = estudianteSeleccionado != null && desafioSeleccionado != null;
        btnAsignar.setDisable(!valid);
    }
    
    @FXML
    private void onAsignarClicked() {
        try {
            if (estudianteSeleccionado == null) {
                mostrarError("Selecciona un estudiante");
                return;
            }
            
            if (desafioSeleccionado == null) {
                mostrarError("Selecciona un desafío");
                return;
            }
            
            // Asignar el desafío usando el método centralizado
            boolean asignado = Main.asignarDesafioAEstudiante(desafioSeleccionado, estudianteSeleccionado);
            
            if (asignado) {
                String tipoDesafio = desafioSeleccionado instanceof DesafioSemanal ? "semanal" : "mensual";
                System.out.println(">>> Desafío " + tipoDesafio + " asignado exitosamente!");
                
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Éxito");
                alert.setHeaderText("Desafío Asignado");
                alert.setContentText("El desafío '" + desafioSeleccionado.getNombre() + 
                                   "' ha sido asignado a " + estudianteSeleccionado.getNombre() + " exitosamente.");
                alert.showAndWait();
                
                // Actualizar la información en pantalla
                desafiosDisponibles = Main.getDesafiosSinAsignar(); // Recargar lista
                cargarDesafiosDisponibles(); // Recargar interfaz
                actualizarInfoEstudiante();
                actualizarDesafiosActivos();
                
                // Limpiar selección
                desafioSeleccionado = null;
                actualizarDesafioSeleccionado();
                validarFormulario();
                
            } else {
                mostrarError("No se pudo asignar el desafío");
            }
            
        } catch (Exception e) {
            mostrarError("Error al asignar desafío: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    @FXML
    private void onCancelarClicked() {
        System.out.println(">>> Cancelando asignación de desafío");
        cerrarVentana();
    }
    
    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Error al Asignar Desafío");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    
    private void cerrarVentana() {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }
    
    // Métodos auxiliares
    private Estudiante encontrarEstudiantePorId(long id) {
        return estudiantes.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);
    }
    
    private ProgresoEstudiante encontrarProgresoPorEstudiante(Estudiante estudiante) {
        return Main.getProgresos().stream()
                .filter(p -> p.getEstudiante().getId() == estudiante.getId())
                .findFirst()
                .orElse(null);
    }
} 