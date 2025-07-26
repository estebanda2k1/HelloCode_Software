package Gamificacion_Modulo.clases;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import Modulo_Usuario.Clases.Usuario;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {
    private static final List<Usuario> usuarios = new ArrayList<>();
    private static final List<Logro> logrosDisponibles = new ArrayList<>();
    private static final List<Desafio> desafiosDisponibles = new ArrayList<>();
    private static final Ranking ranking = Ranking.getInstance();
    private static final List<ProgresoEstudiante> progresos = new ArrayList<>();

    // Campo para almacenar el Stage principal para navegación
    private static Stage primaryStage;

    // Referencias a controladores activos para notificaciones
    private static Object currentPerfilController = null;
    private static Object currentRankingController = null;

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage; // Almacenar referencia del Stage principal

        try {
            // Prioridad 1: Cargar PerfilUsuario.fxml (Progreso - interfaz principal)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Gamificacion_Modulo/fxml/PerfilUsuario.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root, 360, 720);
            stage.setTitle("Sistema de Gamificación - HelloCode");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.centerOnScreen();
            stage.show();

            System.out.println(">>> Interfaz gráfica cargada correctamente: PerfilUsuario.fxml (Progreso)");
        } catch (Exception e) {
            System.err.println("Error al cargar PerfilUsuario.fxml, intentando con Desafios.fxml: " + e.getMessage());

            try {
                // Prioridad 2: Cargar Desafios.fxml como fallback
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Gamificacion_Modulo/fxml/Desafios.fxml"));
                Parent root = loader.load();

                Scene scene = new Scene(root, 393, 852);
                stage.setTitle("Sistema de Gamificación - HelloCode");
                stage.setScene(scene);
                stage.setResizable(false);
                stage.centerOnScreen();
                stage.show();

                System.out.println(">>> Interfaz gráfica cargada correctamente: Desafios.fxml (Fallback)");
            } catch (Exception e2) {
                System.err.println("Error al cargar Desafios.fxml: " + e2.getMessage());
                e2.printStackTrace();

                // Fallback final: mostrar ventana simple si falla todo
                mostrarVentanaSimple(stage);
            }
        }
    }

    // Método estático para cambiar escenas desde los controladores
    public static void cambiarEscena(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource(fxmlPath));
            Parent root = loader.load();

            Scene scene = new Scene(root, 360, 720);

            // Encontrar el Stage activo en lugar de usar primaryStage
            Stage stageActivo = null;
            if (primaryStage != null && primaryStage.isShowing()) {
                stageActivo = primaryStage;
            } else {
                // Buscar cualquier Stage abierto de JavaFX
                for (javafx.stage.Window window : javafx.stage.Stage.getWindows()) {
                    if (window instanceof Stage && window.isShowing()) {
                        stageActivo = (Stage) window;
                        break;
                    }
                }
            }

            if (stageActivo != null) {
                stageActivo.setScene(scene);
                stageActivo.centerOnScreen();
                System.out.println(">>> Navegación exitosa a: " + fxmlPath);
            } else {
                System.err.println("No se encontró un Stage activo para cambiar la escena");
            }

        } catch (Exception e) {
            System.err.println("Error al cambiar a la escena: " + fxmlPath);
            e.printStackTrace();
        }
    }

    // Método para obtener referencia del Stage principal
    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    // Métodos para gestión de controladores activos
    public static void registrarPerfilController(Object controller) {
        currentPerfilController = controller;
        System.out.println(">>> Controlador de Perfil registrado para notificaciones");
    }

    public static void registrarRankingController(Object controller) {
        currentRankingController = controller;
        System.out.println(">>> Controlador de Ranking registrado para notificaciones");
    }

    public static void desregistrarPerfilController() {
        currentPerfilController = null;
        System.out.println(">>> Controlador de Perfil desregistrado");
    }

    public static void desregistrarRankingController() {
        currentRankingController = null;
        System.out.println(">>> Controlador de Ranking desregistrado");
    }

    // Método para notificar actualizaciones a las interfaces
    public static void notificarActualizacionInterface() {
        System.out.println(">>> Notificando actualización a interfaces activas...");

        // Usar Platform.runLater para ejecutar en el hilo de JavaFX
        try {
            if (primaryStage != null) {
                javafx.application.Platform.runLater(() -> {
                    try {
                        if (currentPerfilController != null) {
                            currentPerfilController.getClass().getMethod("actualizarDatosPerfil").invoke(currentPerfilController);
                            System.out.println(">>> Perfil actualizado en hilo FX");
                        }
                        if (currentRankingController != null) {
                            currentRankingController.getClass().getMethod("cargarRanking").invoke(currentRankingController);
                            System.out.println(">>> Ranking actualizado en hilo FX");
                        }
                    } catch (Exception e) {
                        System.err.println("Error al actualizar GUI: " + e.getMessage());
                    }
                });
            }
        } catch (Exception e) {
            System.err.println("Error al notificar actualización: " + e.getMessage());
        }
    }

    private void mostrarVentanaSimple(Stage stage) {
        StackPane layout = new StackPane();
        Button button = new Button("Sistema de Gamificación");
        button.setOnAction(actionEvent -> {
            System.out.println("Interfaz gráfica no disponible. Usa la consola.");
//            mostrarMenuConsola();
        });

        layout.getChildren().add(button);
        Scene scene = new Scene(layout, 393, 852);
        stage.setScene(scene);
        stage.setTitle("Sistema de Gamificación - Modo Consola");
        stage.show();
    }



    public static void mostrarGUI() {
        System.out.println(">>> Iniciando interfaz gráfica...");
        launch();
    }

    // Método para inicializar datos cuando se navega desde otro módulo
    public static void inicializarDesdeModuloExterno() {
        if (usuarios.isEmpty()) {
            System.out.println(">>> Inicializando módulo de gamificación desde navegación externa");
            inicializarDatos();
        } else {
            System.out.println(">>> Módulo de gamificación ya inicializado (" + usuarios.size() + " usuarios)");
            // Recargar usuarios para sincronización automática
            recargarUsuarios();
        }

        // Debug: Mostrar usuarios cargados
        System.out.println(">>> USUARIOS DISPONIBLES EN GAMIFICACIÓN:");
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario u = usuarios.get(i);
            System.out.println("   " + (i + 1) + ". " + u.getNombre() + " (" + u.getUsername() + ") - " + u.getEmail());
        }

        // Debug: Mostrar progresos creados
        System.out.println(">>> PROGRESOS CREADOS: " + progresos.size());
        for (ProgresoEstudiante p : progresos) {
            System.out.println("   - " + p.getUsuario().getNombre() + ": " + p.getPuntosTotal() + " puntos");
        }
    }

    // Métodos para acceder a los datos desde los controladores
    public static List<Usuario> getUsuarios() {
        return usuarios;
    }

    public static List<Logro> getLogrosDisponibles() {
        return logrosDisponibles;
    }

    public static List<Desafio> getDesafiosDisponibles() {
        return desafiosDisponibles;
    }

    public static void agregarDesafio(Desafio desafio) {
        desafiosDisponibles.add(desafio);
    }

    public static Ranking getRanking() {
        return ranking;
    }

    public static List<ProgresoEstudiante> getProgresos() {
        return progresos;
    }

    public static void main(String[] args) {
        // Inicializar datos del sistema
        inicializarDatos();

        // Iniciar la interfaz gráfica en un hilo separado
        Thread guiThread = new Thread(() -> {
            try {
//                System.out.println(">>> Iniciando interfaz gráfica...");
                launch();
            } catch (Exception e) {
                System.err.println("Error al iniciar interfaz gráfica: " + e.getMessage());
            }
        });

        guiThread.setDaemon(false); // Mantener la aplicación viva
        guiThread.start();

        // Esperar un momento para que la GUI se inicialice
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Ejecutar la consola en el hilo principal
        System.out.println(">>> Interfaz de consola lista para usar");
        System.out.println(">>> Utiliza la consola para simular actividades y ver cambios en tiempo real en la GUI\n");
//        ejecutarModoConsola();
    }

    private static void inicializarDatos() {
        // Cargar usuarios del módulo de usuarios
        cargarUsuariosDesdeArchivo();

        // Crear automáticamente progresos para todos los usuarios cargados
        for (Usuario usuario : usuarios) {
            ProgresoEstudiante progreso = new ProgresoEstudiante(usuario);
            progresos.add(progreso);
            System.out.println(">>> Progreso creado para usuario: " + usuario.getNombre());
        }

        // Inicializar logros predeterminados
        inicializarLogros();

        // Inicializar desafíos de ejemplo
        inicializarDesafiosEjemplo();
    }

    // Método público para recargar usuarios (para sincronización)
    public static void recargarUsuarios() {
        try {
            List<Usuario> usuariosAnteriores = new ArrayList<>(usuarios);
            usuarios.clear();
            cargarUsuariosDesdeArchivo();

            // Crear progreso para usuarios nuevos
            for (Usuario usuario : usuarios) {
                boolean existeProgreso = progresos.stream()
                        .anyMatch(p -> p.getUsuario().getUsername().equals(usuario.getUsername()));
                if (!existeProgreso) {
                    ProgresoEstudiante nuevoProgreso = new ProgresoEstudiante(usuario);
                    progresos.add(nuevoProgreso);
                    System.out.println(">>> Progreso creado para nuevo usuario: " + usuario.getNombre());
                }
            }

            // Notificar a la GUI sobre la actualización
            notificarActualizacionInterface();

            System.out.println(">>> Usuarios recargados exitosamente. Total: " + usuarios.size());

        } catch (Exception e) {
            System.err.println(">>> Error al recargar usuarios: " + e.getMessage());
        }
    }

    // Método para cargar usuarios desde el archivo del módulo de usuarios
    private static void cargarUsuariosDesdeArchivo() {
        try {
            InputStream inputStream = new FileInputStream(new File("src/main/java/Modulo_Usuario/Usuarios/usuarios.txt"));
//            InputStream inputStream = Main.class.getResourceAsStream("/Modulo_Usuario/Usuarios/usuarios.txt");


            if (inputStream == null) {
                System.err.println(">>> No se pudo encontrar el archivo de usuarios. Creando usuarios por defecto.");
                crearUsuariosDefecto();
                return;
            }

            try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    linea = linea.trim();
                    if (!linea.isEmpty()) {
                        Usuario usuario = Usuario.fromString(linea);
                        if (usuario != null) {
                            // Completar información del usuario con datos por defecto si no tiene
                            if (usuario.getNombre() == null || usuario.getNombre().isEmpty() || usuario.getNombre().equals("null")) {
                                usuario.setNombre("Usuario " + usuario.getUsername());
                            }
                            if (usuario.getEmail() == null || usuario.getEmail().isEmpty() || usuario.getNombre().equals("null")) {
                                usuario.setEmail(usuario.getUsername() + "@email.com");
                            }
                            usuarios.add(usuario);
                            System.out.println(">>> Usuario cargado: " + usuario.getUsername() + " - " + usuario.getNombre());
                        }
                    }
                }
            }

            if (usuarios.isEmpty()) {
                System.out.println(">>> No se encontraron usuarios válidos. Creando usuarios por defecto.");
                crearUsuariosDefecto();
            }

        } catch (Exception e) {
            System.err.println(">>> Error al cargar usuarios: " + e.getMessage());
            e.printStackTrace();
            crearUsuariosDefecto();
        }
    }

    // Método para crear usuarios por defecto si no se pueden cargar del archivo
    private static void crearUsuariosDefecto() {
        Usuario admin = new Usuario("admin", "1234", "Administrador", "admin@email.com");
        Usuario usuario1 = new Usuario("usuario1", "abc", "Usuario Demo", "usuario1@email.com");
        usuarios.add(admin);
        usuarios.add(usuario1);
        System.out.println(">>> Usuarios por defecto creados");
    }

    private static void inicializarLogros() {
        logrosDisponibles.add(new Logro("Principiante", "Completar tu primer desafio",  100));
        logrosDisponibles.add(new Logro("Dedicado", "Completar 3 desafios", 250));
        logrosDisponibles.add(new Logro("Acumulador", "Obtener 500 puntos", 150));
        logrosDisponibles.add(new Logro("Coleccionista", "Obtener 5 logros", 300));
        System.out.println(">>> Logros predeterminados cargados: " + logrosDisponibles.size());
    }
    private static void inicializarDesafiosEjemplo() {
        // Solo crear desafíos de ejemplo si no hay ninguno
        if (desafiosDisponibles.isEmpty()) {
            // Crear algunos desafíos de ejemplo con los logros disponibles
            List<Logro> logrosBasicos = new ArrayList<>();
            if (!logrosDisponibles.isEmpty()) {
                logrosBasicos.add(logrosDisponibles.get(0)); // Logro principiante
            }

            DesafioSemanal desafioSemanalEjemplo1 = new DesafioSemanal(5, 200, logrosBasicos);
            DesafioSemanal desafioSemanalEjemplo2 = new DesafioSemanal(10, 200, logrosBasicos);
            DesafioMensual desafioMensualEjemplo = new DesafioMensual(25, 200, logrosBasicos);

            desafiosDisponibles.add(desafioSemanalEjemplo1);
            desafiosDisponibles.add(desafioSemanalEjemplo2);
            desafiosDisponibles.add(desafioMensualEjemplo);

            System.out.println(">>> Desafíos de ejemplo creados: " + desafiosDisponibles.size());
        }
    }
}