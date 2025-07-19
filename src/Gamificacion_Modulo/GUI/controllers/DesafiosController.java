package Gamificacion_Modulo.GUI.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.event.ActionEvent;

public class DesafiosController {

    @FXML
    private Button btnHome;
    @FXML
    private Button btnProfile;
    @FXML
    private Button btnRanking;

    // Este es el método que se ejecutará cuando se haga clic en el botón 'Home'
    @FXML
    private void onHomeClicked(ActionEvent event) {
        System.out.println(">>> Navegando a Home desde Desafíos");
        // Aquí puedes agregar la lógica para cambiar la escena o abrir otra vista
    }

    // Este es el método que se ejecutará cuando se haga clic en el botón 'Perfil'
    @FXML
    private void onProfileClicked(ActionEvent event) {
        System.out.println(">>> Navegando UE a Perfil desde Desafíos");
        // Agregar lógica para navegar al perfil
    }

    // Este es el método que se ejecutará cuando se haga clic en el botón 'Ranking'
    @FXML
    private void onRankingClicked(ActionEvent event) {
        System.out.println(">>> Navegando a Ranking desde Desafíos");
        // Lógica para mostrar el ranking
    }

    // Aquí está el método 'initialize' único que debes mantener
    @FXML
    private void initialize() {
        System.out.println("Controlador de Desafíos inicializado");
        // Puedes agregar cualquier lógica de inicialización aquí
    }
}
