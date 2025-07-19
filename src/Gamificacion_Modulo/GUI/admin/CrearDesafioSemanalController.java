package Gamificacion_Modulo.GUI.admin;

import Gamificacion_Modulo.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

public class CrearDesafioSemanalController {
    
    @FXML
    private Slider sliderMeta;
    
    @FXML
    private Label lblMeta;
    
    @FXML
    private VBox vboxLogros;
    
    @FXML
    private Label lblLogrosSeleccionados;
    
    @FXML
    private VBox vboxVistaPrevia;
    
    @FXML
    private Button btnCancelar;
    
    @FXML
    private Button btnCrear;
    
    private List<CheckBox> checkBoxesLogros = new ArrayList<>();
    private List<Logro> logrosSeleccionados = new ArrayList<>();
    
    @FXML
    public void initialize() {
        configurarSlider();
        cargarLogrosDisponibles();
        actualizarVistaPrevia();
        
        // Listener para actualización en tiempo real del slider
        sliderMeta.valueProperty().addListener((obs, oldVal, newVal) -> {
            lblMeta.setText(String.valueOf(newVal.intValue()));
            actualizarVistaPrevia();
        });
    }
    
    private void configurarSlider() {
        sliderMeta.setValue(5); // Valor por defecto
        lblMeta.setText("5");
    }
    
    private void cargarLogrosDisponibles() {
        try {
            vboxLogros.getChildren().clear();
            checkBoxesLogros.clear();
            
            List<Logro> logrosDisponibles = Main.getLogrosDisponibles();
            
            for (Logro logro : logrosDisponibles) {
                CheckBox checkBox = new CheckBox();
                checkBox.setText(logro.getNombre() + " - " + logro.getDescripcion() + " (+" + logro.getPuntos() + " pts)");
                checkBox.setUserData(logro);
                checkBox.setWrapText(true);
                
                // Listener para actualizar lista de logros seleccionados
                checkBox.setOnAction(e -> {
                    actualizarLogrosSeleccionados();
                    actualizarVistaPrevia();
                });
                
                checkBoxesLogros.add(checkBox);
                vboxLogros.getChildren().add(checkBox);
            }
            
            System.out.println(">>> Logros disponibles cargados: " + logrosDisponibles.size());
            
        } catch (Exception e) {
            System.err.println("Error al cargar logros: " + e.getMessage());
        }
    }
    
    private void actualizarLogrosSeleccionados() {
        logrosSeleccionados.clear();
        
        for (CheckBox checkBox : checkBoxesLogros) {
            if (checkBox.isSelected()) {
                Logro logro = (Logro) checkBox.getUserData();
                logrosSeleccionados.add(logro);
            }
        }
        
        lblLogrosSeleccionados.setText(logrosSeleccionados.size() + " logros seleccionados");
    }
    
    private void actualizarVistaPrevia() {
        vboxVistaPrevia.getChildren().clear();
        
        int meta = (int) sliderMeta.getValue();
        
        Label lblNombre = new Label("📋 Desafío Semanal");
        lblNombre.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
        
        Label lblDescripcion = new Label("📝 Descripción: Completar " + meta + " actividades en la semana");
        Label lblMetaInfo = new Label("🎯 Meta: " + meta + " actividades semanales");
        Label lblLogros = new Label("🏆 Logros asociados: " + logrosSeleccionados.size());
        Label lblEstado = new Label("📊 Estado: Disponible para asignar");
        
        vboxVistaPrevia.getChildren().addAll(lblNombre, lblDescripcion, lblMetaInfo, lblLogros, lblEstado);
        
        // Mostrar logros seleccionados
        for (Logro logro : logrosSeleccionados) {
            Label lblLogro = new Label("   • " + logro.getNombre() + " (+" + logro.getPuntos() + " pts)");
            lblLogro.setStyle("-fx-text-fill: #6c757d; -fx-font-size: 12px;");
            vboxVistaPrevia.getChildren().add(lblLogro);
        }
        
        if (logrosSeleccionados.isEmpty()) {
            Label lblSinLogros = new Label("   (Sin logros asociados)");
            lblSinLogros.setStyle("-fx-text-fill: #dc3545; -fx-font-size: 12px;");
            vboxVistaPrevia.getChildren().add(lblSinLogros);
        }
    }
    
    @FXML
    private void onCancelarClicked(ActionEvent event) {
        System.out.println(">>> Cancelando creación de desafío semanal");
        cerrarVentana();
    }
    
    @FXML
    private void onCrearClicked(ActionEvent event) {
        try {
            // Validaciones básicas
            int meta = (int) sliderMeta.getValue();
            
            if (meta < 1 || meta > 20) {
                mostrarAlerta("Error", "La meta debe estar entre 1 y 20 actividades");
                return;
            }
            
            if (logrosSeleccionados.isEmpty()) {
                mostrarAlerta("Advertencia", "¿Estás seguro de crear un desafío sin logros asociados?");
                // Continuar con la creación
            }
            
            // Crear el desafío semanal (sin asignar a estudiante específico)
            DesafioSemanal desafio = new DesafioSemanal(meta, logrosSeleccionados);
            
            // Guardar el desafío en la lista central para asignación posterior
            Main.agregarDesafioSinAsignar(desafio);
            
            // Mensaje de éxito
            String mensaje = String.format(
                "¡Desafío semanal creado exitosamente!\n\n" +
                "Meta: %d actividades semanales\n" +
                "Logros asociados: %d\n" +
                "Estado: Disponible para asignar\n\n" +
                "Usa 'Asignar Desafío a Estudiante' para asignarlo.",
                meta,
                logrosSeleccionados.size()
            );
            
            mostrarAlerta("Éxito", mensaje);
            
            System.out.println(">>> DESAFÍO SEMANAL CREADO EXITOSAMENTE");
            System.out.println("ID del desafío: " + desafio.getId());
            System.out.println("Meta: " + meta + " actividades semanales");
            System.out.println("Descripción: " + desafio.getDescripcion());
            System.out.println("Logros asociados (" + logrosSeleccionados.size() + "):");
            for (Logro logro : logrosSeleccionados) {
                System.out.println("   * " + logro.getNombre() + " (+" + logro.getPuntos() + " pts)");
            }
            System.out.println("Estado: Disponible para asignar a estudiantes");
            System.out.println(">>> Usa la opción 5 'Simular Actividad' para asignarlo a un estudiante");
            
            cerrarVentana();
            
        } catch (Exception e) {
            System.err.println("Error al crear desafío semanal: " + e.getMessage());
            mostrarAlerta("Error", "Error al crear el desafío: " + e.getMessage());
        }
    }
    
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(titulo.equals("Error") ? Alert.AlertType.ERROR : 
                               titulo.equals("Advertencia") ? Alert.AlertType.WARNING : 
                               Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    
    private void cerrarVentana() {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }
} 