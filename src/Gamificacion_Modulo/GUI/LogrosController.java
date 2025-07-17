package Gamificacion_Modulo.GUI;

import Gamificacion_Modulo.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.TilePane;
import javafx.event.ActionEvent;
import java.util.List;

public class LogrosController {
    
    @FXML
    private Label lblLogrosObtenidos;
    
    @FXML
    private Label lblLogrosTotales;
    
    @FXML
    private ProgressBar progressGeneral;
    
    @FXML
    private Label lblPorcentajeProgreso;
    
    @FXML
    private ScrollPane scrollLogros;
    
    @FXML
    private TilePane tileLogros;
    
    @FXML
    private Button btnHome;
    
    @FXML
    private Button btnProfile;
    
    @FXML
    private Button btnRanking;
    
    @FXML
    public void initialize() {
        cargarLogros();
    }
    
    private void cargarLogros() {
        List<Logro> logrosDisponibles = Main.getLogrosDisponibles();
        List<ProgresoEstudiante> progresos = Main.getProgresos();
        
        // Calcular estadísticas usando el primer estudiante
        if (!progresos.isEmpty()) {
            ProgresoEstudiante progreso = progresos.get(0);
            int logrosObtenidos = progreso.getLogros().size();
            int logosTotales = logrosDisponibles.size();
            
            lblLogrosObtenidos.setText(String.valueOf(logrosObtenidos));
            lblLogrosTotales.setText(String.valueOf(logosTotales));
            
            double porcentaje = logosTotales > 0 ? (double) logrosObtenidos / logosTotales : 0;
            progressGeneral.setProgress(porcentaje);
            lblPorcentajeProgreso.setText(String.format("%.0f%% completado", porcentaje * 100));
            
            System.out.println(">>> Logros cargados: " + logrosObtenidos + "/" + logosTotales);
        }
        
        // Mostrar información de logros en consola
        System.out.println("=== LOGROS DISPONIBLES ===");
        for (Logro logro : logrosDisponibles) {
            System.out.println("- " + logro.getNombre() + ": " + logro.getDescripcion());
            System.out.println("  Puntos: " + logro.getPuntos());
            System.out.println("  Criterio: " + logro.getCriteriosDesbloqueo());
            System.out.println();
        }
    }
    
    @FXML
    private void onHomeClicked(ActionEvent event) {
        System.out.println(">>> Navegando a Home desde Logros");
    }
    
    @FXML
    private void onProfileClicked(ActionEvent event) {
        System.out.println(">>> Navegando a Perfil desde Logros");
    }
    
    @FXML
    private void onRankingClicked(ActionEvent event) {
        System.out.println(">>> Navegando a Ranking desde Logros");
    }
}
