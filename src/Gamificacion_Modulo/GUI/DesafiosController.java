package Gamificacion_Modulo.GUI;

import Gamificacion_Modulo.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import java.util.List;

public class DesafiosController {
    
    @FXML
    private TabPane tabPane;
    
    @FXML
    private Tab tabSemanales;
    
    @FXML
    private Tab tabMensuales;
    
    @FXML
    private ScrollPane scrollSemanales;
    
    @FXML
    private ScrollPane scrollMensuales;
    
    @FXML
    private VBox vboxSemanales;
    
    @FXML
    private VBox vboxMensuales;
    
    @FXML
    private Button btnHome;
    
    @FXML
    private Button btnProfile;
    
    @FXML
    private Button btnRanking;
    
    @FXML
    public void initialize() {
        cargarDesafios();
    }
    
    private void cargarDesafios() {
        List<ProgresoEstudiante> progresos = Main.getProgresos();
        
        // Limpiar contenido anterior
        vboxSemanales.getChildren().clear();
        vboxMensuales.getChildren().clear();
        
        // Cargar desafíos de todos los estudiantes
        for (ProgresoEstudiante progreso : progresos) {
            List<Desafio> desafiosActivos = progreso.getDesafiosActivos();
            
            for (Desafio desafio : desafiosActivos) {
                if (desafio instanceof DesafioSemanal) {
                    agregarDesafioSemanal((DesafioSemanal) desafio, progreso);
                } else if (desafio instanceof DesafioMensual) {
                    agregarDesafioMensual((DesafioMensual) desafio, progreso);
                }
            }
        }
        
        System.out.println(">>> Desafíos cargados");
    }
    
    private void agregarDesafioSemanal(DesafioSemanal desafio, ProgresoEstudiante progreso) {
        // Crear representación visual del desafío semanal
        VBox desafioBox = new VBox(5);
        desafioBox.setStyle("-fx-padding: 10; -fx-border-color: #e0e0e0; -fx-border-width: 1; -fx-background-color: #f9f9f9;");
        
        // Aquí normalmente crearías Labels, pero por simplicidad usaremos solo información en consola
        System.out.println("Desafío Semanal: " + desafio.getNombre() + " - " + desafio.getDescripcion());
        System.out.println("Estudiante: " + progreso.getEstudiante().getNombre());
        System.out.println("Progreso: " + desafio.getActividadesCompletadas() + "/" + desafio.getMetaSemanal());
        
        vboxSemanales.getChildren().add(desafioBox);
    }
    
    private void agregarDesafioMensual(DesafioMensual desafio, ProgresoEstudiante progreso) {
        // Crear representación visual del desafío mensual
        VBox desafioBox = new VBox(5);
        desafioBox.setStyle("-fx-padding: 10; -fx-border-color: #e0e0e0; -fx-border-width: 1; -fx-background-color: #f9f9f9;");
        
        // Aquí normalmente crearías Labels, pero por simplicidad usaremos solo información en consola
        System.out.println("Desafío Mensual: " + desafio.getNombre() + " - " + desafio.getDescripcion());
        System.out.println("Estudiante: " + progreso.getEstudiante().getNombre());
        System.out.println("Progreso: " + desafio.getActividadesCompletadas() + "/" + desafio.getObjetivoMensual());
        
        vboxMensuales.getChildren().add(desafioBox);
    }
    
    @FXML
    private void onHomeClicked(ActionEvent event) {
        System.out.println(">>> Navegando a Home desde Desafíos");
    }
    
    @FXML
    private void onProfileClicked(ActionEvent event) {
        System.out.println(">>> Navegando a Perfil desde Desafíos");
    }
    
    @FXML
    private void onRankingClicked(ActionEvent event) {
        System.out.println(">>> Navegando a Ranking desde Desafíos");
    }
}
