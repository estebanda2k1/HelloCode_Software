package Gamificacion_Modulo.GUI;

import Gamificacion_Modulo.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import javafx.scene.layout.VBox;

public class DetalleDesafioController {
    
    @FXML
    private Label lblTituloDesafio;
    
    @FXML
    private Label lblDescripcion;
    
    @FXML
    private Label lblTipoDesafio;
    
    @FXML
    private Label lblPuntosRecompensa;
    
    @FXML
    private Label lblProgreso;
    
    @FXML
    private Label lblTiempoRestante;
    
    @FXML
    private Label lblDificultad;
    
    @FXML
    private ProgressBar progressDesafio;
    
    @FXML
    private ImageView imgDesafio;
    
    @FXML
    private Button btnComenzar;
    
    @FXML
    private Button btnContinuar;
    
    @FXML
    private Button btnVolver;
    
    @FXML
    private VBox vboxRequisitos;
    
    @FXML
    private VBox vboxRecompensas;
    
    private Desafio desafioActual;
    
    @FXML
    public void initialize() {
        // Cargar un desafío de ejemplo
        cargarDesafioEjemplo();
    }
    
    private void cargarDesafioEjemplo() {
        // Simular datos de un desafío
        lblTituloDesafio.setText("Estructuras de Datos");
        lblDescripcion.setText("Completa los ejercicios sobre arrays, listas y estructuras de datos básicas.");
        lblTipoDesafio.setText("Semanal");
        lblPuntosRecompensa.setText("250");
        lblProgreso.setText("3/5");
        lblTiempoRestante.setText("4 días");
        lblDificultad.setText("Intermedio");
        
        // Configurar barra de progreso
        progressDesafio.setProgress(0.6); // 60% completado
        
        // Configurar botones según el estado
        btnComenzar.setVisible(false);
        btnContinuar.setVisible(true);
        
        System.out.println(">>> Detalle de desafío cargado");
        System.out.println("- Título: " + lblTituloDesafio.getText());
        System.out.println("- Progreso: " + lblProgreso.getText());
        System.out.println("- Tiempo restante: " + lblTiempoRestante.getText());
    }
    
    public void setDesafio(Desafio desafio) {
        this.desafioActual = desafio;
        
        lblTituloDesafio.setText(desafio.getNombre());
        lblDescripcion.setText(desafio.getDescripcion());
        lblPuntosRecompensa.setText("200"); // Puntos base por defecto
        
        if (desafio instanceof DesafioSemanal) {
            lblTipoDesafio.setText("Semanal");
            lblTiempoRestante.setText("7 días");
        } else if (desafio instanceof DesafioMensual) {
            lblTipoDesafio.setText("Mensual");
            lblTiempoRestante.setText("30 días");
        }
        
        // Configurar estado del desafío
        if (desafio.estaCompletado()) {
            btnComenzar.setVisible(false);
            btnContinuar.setVisible(false);
            progressDesafio.setProgress(1.0);
            lblProgreso.setText("Completado");
        } else {
            btnComenzar.setVisible(true);
            btnContinuar.setVisible(false);
            progressDesafio.setProgress(0.0);
            lblProgreso.setText("0/" + desafio.getLogrosDisponibles().size());
        }
    }
    
    @FXML
    private void onComenzarClicked(ActionEvent event) {
        System.out.println(">>> Comenzando desafío: " + lblTituloDesafio.getText());
        
        // Cambiar estado de botones
        btnComenzar.setVisible(false);
        btnContinuar.setVisible(true);
        
        // Actualizar progreso
        progressDesafio.setProgress(0.1);
        lblProgreso.setText("1/" + (desafioActual != null ? desafioActual.getLogrosDisponibles().size() : "5"));
    }
    
    @FXML
    private void onContinuarClicked(ActionEvent event) {
        System.out.println(">>> Continuando desafío: " + lblTituloDesafio.getText());
        
        // Simular progreso
        double progresoActual = progressDesafio.getProgress();
        if (progresoActual < 1.0) {
            progressDesafio.setProgress(progresoActual + 0.2);
            int completados = (int) (progressDesafio.getProgress() * 5);
            lblProgreso.setText(completados + "/5");
            
            if (progressDesafio.getProgress() >= 1.0) {
                lblProgreso.setText("Completado");
                btnContinuar.setVisible(false);
                System.out.println(">>> ¡Desafío completado!");
            }
        }
    }
    
    @FXML
    private void onVolverClicked(ActionEvent event) {
        System.out.println(">>> Volviendo a la lista de desafíos");
        // Aquí se implementaría la navegación de vuelta
    }
}
