# ORTOGRAFIA MARIAMEL
Esta app está orientada a la educación, cuenta con varios ejercicios sobre la temática de ortografía, con dos modelos de actividades: Tarjetas de pares y Completado de oraciones con 
multi-opciones. Los ejercicios fueron creados según la estrategia de enseñanza y aprendizaje: Gamificación.

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
- Al abrir la app, se muestra la vista de inicio *Inicio* luego debes dar click abajo en el boton continuar
- Luego se muestra la vista *Registro* hay el estudiante debe escribir su nombre y elegir su edad, luego debe hacer click en el boton de abajo que dice siguiente.
- Luego se muestra la vista "Menu" y "Unidades" hay se puede ir a cada Tema o Actividad tanto desde el Menu desplegable "Menu" de la izquierda o desde la vista del centro "Unidades".
- En la vista "Portada" se observa unicamente una imagen del libro de donde se basa el contenido de la aplicacion movil.
- Si se hace click en una unidad se mostrara la vista "Unidad" que contiene el tema de la unidad y una descripcion de lo que se aprendera en ese apartado.
- Si se hace click en una actividad se mostrara la vista "Actividad" que es la parte interactiva de la aplicacion, pues es un juego académico, que contiene 3 ejercicios por nivel,
  el primer ejercicio es un juego de ordenar tarjetas con su respectivo par (Monosílabos con su ejemplo) el segundo y tercer juego es sobre completar una oracion con los respectivos
  monosílabos, luego si gana el juego y no pierde el total de vidas sale un mensaje de felicitaciones caso contrario sale un mensaje de vuelvelo a intentar, al final se muestra el
  resultado del puntaje del estudiante para luego dar click en el boton reiniciar, para reiniciar el juego para una nueva partida.

# Ver video Demo
[Ver en YouTube](https://youtu.be/PAHge8WDNmU?si=AmhhfaS9qR9sWHvd)

# Contribución
- Haz un fork del repositorio
- Crea una rama con tu feature: git checkout -b feature/nueva-funcionalidad
- Haz commit de tus cambios: git commit -m "Agrega nueva funcionalidad"
- Haz push a la rama: git push origin feature/nueva-funcionalidad
- Abre un Pull Request

# Licencia
Este proyecto está bajo la licencia GPL-3.0. Consulta el archivo LICENSE para más detalles.
