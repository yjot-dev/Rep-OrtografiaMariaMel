# ORTOGRAFIA MARIAMEL (OM)
Ortografia MariaMel (OM) es una aplicación móvil educativa diseñada para fortalecer las habilidades de ortografía en español a través de la gamificación. La app se enfoca en ofrecer una experiencia de aprendizaje interactiva y estructurada, con ejercicios basados en el contenido de un libro de texto, presentados en un formato de juego para motivar al estudiante.

# Características principales
- 🪟 Interfaz moderna con Jetpack Compose
- 🌐 Navegación con Navigation Component
- 📊 Integración con ViewModel + StateFlow
- 🎨 Patrón de diseño arquitectónico con MVVM
- 📱 Compatible con Android 7.0 (API 24) en adelante

# Instalación
- Clona el repositorio: git clone https://github.com/yjot-dev/Rep-OrtografiaMariaMel.git
- Abre el proyecto en Android Studio (Giraffe o superior)
- Sincroniza dependencias con Gradle
- Conecta un dispositivo o emulador y ejecuta la app

# Tecnologías usadas
- Kotlin
- Jetpack Compose
- AndroidX (Navigation, Lifecycle, Core KTX)
- Material 3

# Uso
El flujo de uso de la aplicación está diseñado para ser intuitivo y progresivo, guiando al usuario a través de los siguientes pasos:

1. Bienvenida y Registro de Usuario: Al abrir la aplicación, el usuario es recibido en una pantalla de inicio. Para comenzar, debe registrarse proporcionando su nombre y seleccionando su edad. Este paso personaliza la experiencia y permite llevar un registro de su progreso.
2. Acceso a Contenidos (Unidades y Portada): Una vez registrado, el usuario accede al menú principal donde puede explorar los contenidos de dos maneras:
   - Unidades: El núcleo de la aplicación se organiza en unidades temáticas. Cada unidad presenta un tema de ortografía específico (por ejemplo, el uso de la tilde diacrítica) con una descripción clara de los conceptos que se aprenderán.
   - Portada: Esta sección muestra una imagen del libro en el que se basa el contenido de la aplicación, conectando la experiencia digital con su fuente educativa.
3. Aprendizaje Interactivo (Actividades): Cada unidad está asociada a una actividad gamificada que constituye la parte interactiva de la aplicación. El flujo de juego es el siguiente:
   - Al iniciar una actividad, el usuario enfrenta una serie de tres ejercicios diseñados para reforzar el tema de la unidad. Los juegos incluyen emparejar tarjetas (por ejemplo, un monosílabo con su oración de ejemplo) y completar oraciones seleccionando la opción correcta.
   - El sistema cuenta con un sistema de vidas. Si el usuario completa los ejercicios sin perder todas sus vidas, recibe un mensaje de felicitaciones. En caso contrario, se le anima a intentarlo de nuevo.
   - Al finalizar, se muestra un resumen con el puntaje obtenido, permitiendo al usuario reiniciar el juego para una nueva partida y mejorar su resultado.

En resumen, Ortografia MariaMel combina una estructura de contenido educativo sólido con mecánicas de juego para crear una herramienta de aprendizaje efectiva y atractiva. La aplicación guía al estudiante desde el registro y la exploración de temas hasta la práctica interactiva, proporcionando feedback inmediato sobre su desempeño y fomentando la mejora continua.

# Ver video Demo
[Ver en YouTube](https://youtu.be/6ixV2zPoIQo)

# Contribución
- Haz un fork del repositorio
- Crea una rama con tu feature: git checkout -b feature/nueva-funcionalidad
- Haz commit de tus cambios: git commit -m "Agrega nueva funcionalidad"
- Haz push a la rama: git push origin feature/nueva-funcionalidad
- Abre un Pull Request

# Licencia
Este proyecto está bajo la licencia GPL-3.0. Consulta el archivo LICENSE para más detalles.
