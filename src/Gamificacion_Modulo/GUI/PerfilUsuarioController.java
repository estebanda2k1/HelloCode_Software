package Gamificacion_Modulo.GUI;

import Gamificacion_Modulo.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import java.util.List;

public class PerfilUsuarioController {
    
    @FXML
    private ImageView userAvatar;
    
    @FXML
    private Label lblNombreUsuario;
    
    @FXML
    private Label lblUsername;
    
    @FXML
    private Label lblExpTotal;
    
    @FXML
    private ImageView iconoExperiencia;
    
    @FXML
    private ImageView logroPrincipiante;
    
    @FXML
    private ImageView logroDedicado;
    
    @FXML
    private ImageView logroAcumulador;
    
    @FXML
    private Label lblDesafiosSemanales;
    
    @FXML
    private Label lblDesafiosMensuales;
    
    @FXML
    private Button btnVerProgresoSemanal;
    
    @FXML
    private Button btnVerProgresoMensual;
    
    @FXML
    private Button btnHome;
    
    @FXML
    private Button btnProfile;
    
    @FXML
    private Button btnRanking;
    
    private ProgresoEstudiante progresoActual;
    
    @FXML
    public void initialize() {
        // Cargar datos del primer estudiante por defecto
        cargarDatosEstudiante();
    }
    
    private void cargarDatosEstudiante() {
        List<ProgresoEstudiante> progresos = Main.getProgresos();
        
        if (!progresos.isEmpty()) {
            // Tomar el segundo estudiante (Evelin) como ejemplo
            progresoActual = progresos.size() > 1 ? progresos.get(1) : progresos.get(0);
            
            // Actualizar información del usuario
            lblNombreUsuario.setText(progresoActual.getEstudiante().getNombre());
            lblUsername.setText("@" + progresoActual.getEstudiante().getEmail().split("@")[0]);
            lblExpTotal.setText(String.valueOf(progresoActual.getPuntosTotal()));
            
            // Contar desafíos
            int desafiosSemanales = 0;
            int desafiosMensuales = 0;
            
            for (Desafio desafio : progresoActual.getDesafiosActivos()) {
                if (desafio instanceof DesafioSemanal) {
                    desafiosSemanales++;
                } else if (desafio instanceof DesafioMensual) {
                    desafiosMensuales++;
                }
            }
            
            lblDesafiosSemanales.setText(String.valueOf(desafiosSemanales));
            lblDesafiosMensuales.setText(String.valueOf(desafiosMensuales));
            
            System.out.println(">>> Datos del perfil cargados para: " + progresoActual.getEstudiante().getNombre());
        } else {
            // Datos por defecto si no hay estudiantes
            lblNombreUsuario.setText("Usuario");
            lblUsername.setText("@usuario");
            lblExpTotal.setText("0");
            lblDesafiosSemanales.setText("0");
            lblDesafiosMensuales.setText("0");
        }
    }
    
    @FXML
    private void onHomeClicked(ActionEvent event) {
        System.out.println(">>> Navegando a Home");
        // La navegación será manejada por el controlador principal
    }
    
    @FXML
    private void onProfileClicked(ActionEvent event) {
        System.out.println(">>> En perfil actualmente");
        // Ya estamos en el perfil
    }
    
    @FXML
    private void onRankingClicked(ActionEvent event) {
        System.out.println(">>> Navegando a Ranking");
        // La navegación será manejada por el controlador principal
    }
    
    @FXML
    private void onVerProgresoSemanalClicked(ActionEvent event) {
        System.out.println(">>> Mostrando progreso semanal");
        // Aquí se podría abrir una ventana modal o navegar a una vista detallada
        mostrarProgresoDetallado("semanal");
    }
    
    @FXML
    private void onVerProgresoMensualClicked(ActionEvent event) {
        System.out.println(">>> Mostrando progreso mensual");
        // Aquí se podría abrir una ventana modal o navegar a una vista detallada
        mostrarProgresoDetallado("mensual");
    }
    
    private void mostrarProgresoDetallado(String tipo) {
        if (progresoActual != null) {
            System.out.println("=== PROGRESO " + tipo.toUpperCase() + " ===");
            System.out.println("Estudiante: " + progresoActual.getEstudiante().getNombre());
            System.out.println("Puntos Totales: " + progresoActual.getPuntosTotal());
            
            // Mostrar desafíos activos del tipo especificado
            List<Desafio> desafiosActivos = progresoActual.getDesafiosActivos();
            for (Desafio desafio : desafiosActivos) {
                boolean mostrar = false;
                
                if (tipo.equals("semanal") && desafio instanceof DesafioSemanal) {
                    mostrar = true;
                } else if (tipo.equals("mensual") && desafio instanceof DesafioMensual) {
                    mostrar = true;
                }
                
                if (mostrar) {
                    System.out.println("- " + desafio.getNombre() + ": " + desafio.getDescripcion());
                    if (desafio instanceof DesafioSemanal) {
                        DesafioSemanal ds = (DesafioSemanal) desafio;
                        System.out.println("  Progreso: " + ds.getActividadesCompletadas() + "/" + ds.getMetaSemanal());
                    } else if (desafio instanceof DesafioMensual) {
                        DesafioMensual dm = (DesafioMensual) desafio;
                        System.out.println("  Progreso: " + dm.getActividadesCompletadas() + "/" + dm.getObjetivoMensual());
                    }
                }
            }
        }
    }
    
    public void actualizarDatos() {
        cargarDatosEstudiante();
    }
}
