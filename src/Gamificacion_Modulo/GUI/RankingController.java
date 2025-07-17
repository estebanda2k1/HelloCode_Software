package Gamificacion_Modulo.GUI;

import Gamificacion_Modulo.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.shape.Circle;
import javafx.event.ActionEvent;
import java.util.List;

public class RankingController {
    
    @FXML
    private ListView<String> lvRanking;
    
    @FXML
    private RadioButton rbSemanal;
    
    @FXML
    private RadioButton rbMensual;
    
    @FXML
    private RadioButton rbGeneral;
    
    @FXML
    private ToggleGroup toggleGroupFiltro;
    
    @FXML
    private Circle avatar1;
    
    @FXML
    private Circle avatar2;
    
    @FXML
    private Circle avatar3;
    
    @FXML
    private Label lblNombre1;
    
    @FXML
    private Label lblNombre2;
    
    @FXML
    private Label lblNombre3;
    
    @FXML
    private Label lblPuntos1;
    
    @FXML
    private Label lblPuntos2;
    
    @FXML
    private Label lblPuntos3;
    
    @FXML
    private Button btnHome;
    
    @FXML
    private Button btnProfile;
    
    @FXML
    private Button btnRanking;
    
    @FXML
    public void initialize() {
        cargarRanking();
    }
    
    private void cargarRanking() {
        Ranking ranking = Main.getRanking();
        List<ProgresoEstudiante> topEstudiantes = ranking.obtenerRankingGeneral();
        
        // Actualizar top 3
        if (topEstudiantes.size() >= 1) {
            ProgresoEstudiante primero = topEstudiantes.get(0);
            lblNombre1.setText(primero.getEstudiante().getNombre());
            lblPuntos1.setText(primero.getPuntosTotal() + " pts");
        }
        
        if (topEstudiantes.size() >= 2) {
            ProgresoEstudiante segundo = topEstudiantes.get(1);
            lblNombre2.setText(segundo.getEstudiante().getNombre());
            lblPuntos2.setText(segundo.getPuntosTotal() + " pts");
        }
        
        if (topEstudiantes.size() >= 3) {
            ProgresoEstudiante tercero = topEstudiantes.get(2);
            lblNombre3.setText(tercero.getEstudiante().getNombre());
            lblPuntos3.setText(tercero.getPuntosTotal() + " pts");
        }
        
        // Llenar ListView con el ranking completo
        lvRanking.getItems().clear();
        for (int i = 0; i < topEstudiantes.size(); i++) {
            ProgresoEstudiante estudiante = topEstudiantes.get(i);
            String item = (i + 1) + ". " + estudiante.getEstudiante().getNombre() + 
                         " - " + estudiante.getPuntosTotal() + " pts";
            lvRanking.getItems().add(item);
        }
        
        System.out.println(">>> Ranking cargado con " + topEstudiantes.size() + " estudiantes");
    }
    
    @FXML
    private void onFiltroSemanalClicked(ActionEvent event) {
        System.out.println(">>> Filtro semanal seleccionado");
        // Aquí se implementaría el filtro semanal
        cargarRanking();
    }
    
    @FXML
    private void onFiltroMensualClicked(ActionEvent event) {
        System.out.println(">>> Filtro mensual seleccionado");
        // Aquí se implementaría el filtro mensual
        cargarRanking();
    }
    
    @FXML
    private void onFiltroGeneralClicked(ActionEvent event) {
        System.out.println(">>> Filtro general seleccionado");
        cargarRanking();
    }
    
    @FXML
    private void onHomeClicked(ActionEvent event) {
        System.out.println(">>> Navegando a Home desde Ranking");
    }
    
    @FXML
    private void onProfileClicked(ActionEvent event) {
        System.out.println(">>> Navegando a Perfil desde Ranking");
    }
    
    @FXML
    private void onRankingClicked(ActionEvent event) {
        System.out.println(">>> Ya en Ranking");
    }
}
