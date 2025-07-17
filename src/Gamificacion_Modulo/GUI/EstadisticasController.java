package Gamificacion_Modulo.GUI;

import Gamificacion_Modulo.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.chart.LineChart;
import javafx.event.ActionEvent;
import java.util.List;

public class EstadisticasController {
    
    @FXML
    private ScrollPane scrollEstadisticas;
    
    @FXML
    private LineChart<String, Number> chartProgreso;
    
    @FXML
    private GridPane gridActividad;
    
    @FXML
    private Label lblExpSemanal;
    
    @FXML
    private Label lblExpMensual;
    
    @FXML
    private Label lblDesafiosSemanal;
    
    @FXML
    private Label lblDesafiosMensual;
    
    @FXML
    private Label lblRachaActual;
    
    @FXML
    private Label lblRachaMaxima;
    
    @FXML
    private Button btnHome;
    
    @FXML
    private Button btnProfile;
    
    @FXML
    private Button btnRanking;
    
    @FXML
    public void initialize() {
        cargarEstadisticas();
    }
    
    private void cargarEstadisticas() {
        List<ProgresoEstudiante> progresos = Main.getProgresos();
        
        if (!progresos.isEmpty()) {
            ProgresoEstudiante progreso = progresos.get(0);
            
            // Datos simulados para las estadísticas
            lblExpSemanal.setText("1250");
            lblExpMensual.setText("5420");
            
            // Contar desafíos por tipo
            int semanales = 0;
            int mensuales = 0;
            
            for (Desafio desafio : progreso.getDesafiosActivos()) {
                if (desafio instanceof DesafioSemanal) {
                    semanales++;
                } else if (desafio instanceof DesafioMensual) {
                    mensuales++;
                }
            }
            
            lblDesafiosSemanal.setText(String.valueOf(semanales));
            lblDesafiosMensual.setText(String.valueOf(mensuales));
            
            // Datos simulados para las rachas
            lblRachaActual.setText("7");
            lblRachaMaxima.setText("15");
            
            System.out.println(">>> Estadísticas cargadas para: " + progreso.getEstudiante().getNombre());
            System.out.println("- Desafíos semanales: " + semanales);
            System.out.println("- Desafíos mensuales: " + mensuales);
            System.out.println("- Puntos totales: " + progreso.getPuntosTotal());
            System.out.println("- Logros obtenidos: " + progreso.getLogros().size());
        }
    }
    
    @FXML
    private void onHomeClicked(ActionEvent event) {
        System.out.println(">>> Navegando a Home desde Estadísticas");
    }
    
    @FXML
    private void onProfileClicked(ActionEvent event) {
        System.out.println(">>> Navegando a Perfil desde Estadísticas");
    }
    
    @FXML
    private void onRankingClicked(ActionEvent event) {
        System.out.println(">>> Navegando a Ranking desde Estadísticas");
    }
}
