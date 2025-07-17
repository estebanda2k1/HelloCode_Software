# Interfaz GUI del Módulo de Gamificación

## Estructura de Archivos FXML

### Archivos FXML Principales

1. **MainGamificacion.fxml**
   - Ventana principal del módulo de gamificación
   - Contiene la navegación inferior y el header superior
   - Área de contenido dinámico donde se cargan las otras vistas

2. **PerfilUsuario.fxml**
   - Perfil del usuario con información personal
   - Muestra experiencia total, logros obtenidos
   - Resumen de desafíos completados (semanales y mensuales)

3. **Ranking.fxml**
   - Lista de ranking de estudiantes
   - Filtros por período (semanal, mensual, general)
   - Podium con top 3 usuarios
   - Lista completa de ranking

4. **Desafios.fxml**
   - Vista de desafíos disponibles
   - Pestañas para desafíos semanales y mensuales
   - Lista scrolleable de desafíos

5. **DetalleDesafio.fxml**
   - Vista detallada de un desafío específico
   - Información completa del desafío
   - Progreso y requisitos
   - Botones de acción

6. **Logros.fxml**
   - Galería de logros del usuario
   - Logros obtenidos y bloqueados
   - Progreso general de logros

7. **Estadisticas.fxml**
   - Estadísticas detalladas del usuario
   - Gráficos de progreso
   - Métricas de actividad

### Archivos de Recursos

#### Assets (carpeta assets/)
- **logro_principiante.png** - Imagen del logro principiante
- **logro_dedicado.png** - Imagen del logro dedicado
- **logro_acumulador.png** - Imagen del logro acumulador
- **icono_experiencia.png** - Icono de experiencia
- **default_avatar.svg** - Avatar por defecto

#### Estilos
- **styles.css** - Archivo CSS con estilos para todas las vistas

## Controladores Necesarios

Para que los archivos FXML funcionen, necesitarás crear los siguientes controladores Java:

1. `MainGamificacionController.java`
2. `PerfilUsuarioController.java`
3. `RankingController.java`
4. `DesafiosController.java`
5. `DetalleDesafioController.java`
6. `LogrosController.java`
7. `EstadisticasController.java`

## Características Implementadas

### Navegación
- Barra de navegación inferior con 5 botones principales
- Header superior con título y botón de estadísticas
- Navegación between vistas

### Componentes Visuales
- Cards con sombras y bordes redondeados
- Botones con estados (hover, pressed, selected)
- Barras de progreso personalizadas
- Grillas de logros responsivas
- Listas de ranking con estilos diferenciados

### Responsive Design
- Diseño adaptado para pantalla móvil (393x852px)
- Componentes que se ajustan al ancho disponible
- Scroll vertical donde sea necesario

### Funcionalidades GUI
- Tabs para diferentes tipos de desafíos
- Filtros de ranking por período
- Cards de logros con estado (obtenido/bloqueado)
- Estadísticas con gráficos
- Progreso visual de desafíos

## Próximos Pasos

1. Crear los controladores Java correspondientes
2. Implementar la lógica de navegación entre vistas
3. Conectar con el modelo de datos del módulo de gamificación
4. Implementar la carga dinámica de datos
5. Añadir animaciones y transiciones
6. Integrar con el sistema de notificaciones
7. Implementar persistencia de datos

## Uso

Los archivos FXML están listos para ser cargados usando JavaFX FXMLLoader. Cada vista está diseñada para ser independiente pero comparten el mismo sistema de navegación y estilos.

```java
// Ejemplo de carga de vista
FXMLLoader loader = new FXMLLoader(getClass().getResource("MainGamificacion.fxml"));
Parent root = loader.load();
```

## Notas Técnicas

- Los archivos usan JavaFX 11.0.1
- Todos los IDs de componentes están definidos con prefijo `fx:id`
- Los métodos de acción están definidos con `onAction`
- Se usan tooltips para mejorar la experiencia de usuario
- Los estilos CSS son específicos de JavaFX (-fx-properties)
