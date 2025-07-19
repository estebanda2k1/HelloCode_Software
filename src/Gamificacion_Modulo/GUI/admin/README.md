# 🛠️ Panel de Administración - Sistema de Gamificación

Esta carpeta contiene las interfaces de administración para crear y gestionar objetos del sistema de gamificación.

## 📁 Estructura de Archivos

### Interfaz Principal

- **`AdminMain.fxml`** - Ventana principal del panel de administración
- **`AdminMainController.java`** - Controlador principal que maneja la navegación

### Creación de Desafíos

- **`CrearDesafioSemanal.fxml`** - Interfaz para crear desafíos semanales
- **`CrearDesafioSemanalController.java`** - Lógica para crear desafíos semanales
- **`CrearDesafioMensual.fxml`** - Interfaz para crear desafíos mensuales
- **`CrearDesafioMensualController.java`** - Lógica para crear desafíos mensuales (por implementar)

### Creación de Logros

- **`CrearLogro.fxml`** - Interfaz para crear logros personalizados
- **`CrearLogroController.java`** - Lógica completa para crear logros

### Asignación de Desafíos

- **`AsignarDesafio.fxml`** - Interfaz para asignar desafíos a estudiantes
- **`AsignarDesafioController.java`** - Lógica para asignar desafíos (por implementar)

## 🚀 Cómo Usar

### Abrir el Panel de Administración

```java
// Desde cualquier lugar del código
AdminMainController.mostrarVentanaAdmin();
```

### Desde Main.java

```java
// Agregar opción en el menú de consola
case 11:
    AdminMainController.mostrarVentanaAdmin();
    break;
```

## 🎯 Funcionalidades Implementadas

### ✅ Crear Desafío Semanal

- Selección de estudiante
- Configuración de meta (1-20 actividades)
- Selección de logros asociados
- Vista previa en tiempo real
- Validaciones completas

### ✅ Crear Logro Personalizado

- Nombre y descripción personalizados
- Criterios de desbloqueo:
  - Por completar desafíos
  - Por obtener puntos
  - Por desbloquear otros logros
- Puntos de recompensa configurables (50-500)
- Vista previa en tiempo real
- Validación de nombres únicos

### 📋 Por Implementar

- Controlador para desafíos mensuales
- Controlador para asignar desafíos
- Interfaz para editar objetos existentes
- Interfaz para eliminar objetos

## 🎨 Características de la Interfaz

### Diseño Consistente

- Usa los mismos estilos que la interfaz principal (`../styles.css`)
- Cards organizados por secciones
- Iconos emoji para mejor UX
- Responsive y accesible

### Validaciones

- Campos requeridos
- Rangos de valores apropiados
- Nombres únicos para logros
- Estudiantes existentes

### Feedback del Usuario

- Alertas informativas
- Vista previa en tiempo real
- Estadísticas del sistema
- Mensajes de éxito/error

## 🔧 Integración con el Sistema

### Acceso a Datos

Todas las interfaces acceden a los datos del sistema a través de métodos estáticos en `Main.java`:

- `Main.getEstudiantes()`
- `Main.getLogrosDisponibles()`
- `Main.getProgresos()`
- `Main.getRanking()`

### Persistencia

Los objetos creados se almacenan directamente en las listas del sistema y están disponibles inmediatamente para toda la aplicación.

### Navegación

El panel usa ventanas modales que se pueden abrir independientemente de la interfaz principal.

## 📝 Notas Técnicas

### Dependencias JavaFX

Las interfaces requieren JavaFX para funcionar. Los errores de linter se resolverán cuando JavaFX esté configurado en el proyecto.

### Patrón MVC

- **Model**: Clases del sistema (`Estudiante`, `Logro`, `Desafio`, etc.)
- **View**: Archivos FXML con la estructura de la interfaz
- **Controller**: Clases Java que manejan la lógica de la interfaz

### Escalabilidad

El diseño permite agregar fácilmente:

- Nuevos tipos de objetos
- Nuevas operaciones (editar, eliminar)
- Nuevos criterios para logros
- Nuevas configuraciones para desafíos
