# Criptomonedas
Prueba técnica para SoyMomo. La aplicación consume una API de CoinMarketCap para obtener las diez criptomonedas con mayor valor y desplegarlas en pantalla, indicando nombre, código y valor de la criptomineda.

## Decisiones Tomadas
### Arquitectura
Para el desarrollo de este proyecto se optó por utiizar una arquitectura MVVM (Model-View-ViewModel). Ésta decisión se tomó debido a que dicho patrón arquitectónico es ampliamente usado en aplicaciones móviles, contando incluso con soporte nativo en Android Jetpack.
Por otro lado, al utilizar las bibliotecas de Android Jetpack para la aploicación de éste patron, se simplifica el manejo del ciclo de vida de una actividad/fragmento en Android.

### Uso de interfaces para clases de fuente de datos
Se definen varias interfaces para las entidades encargadas de manejar la fuente de datos (por ejemplo, tenemos la interfaz CriptocurrencyInfoDataSource y su realización, la clase CoinMarketCapDataSource). Además, se hace que las dependencias de dichas clases se manejen a nivel de interfaces en lugar de sus realizaciones.
Lo anterior se debe principalmente a dos razones:
- Al depender de interfaces, eventualmente resultaría más sencillo implementar nuevas fuentes de datos (por ejemplo, otra API o llamados a una base de datos local) con un impacto mínimo en las clases ya existentes.
- Al depender de interfaces, resulta conveniente a la hora de hacer pruebas unitarias sobre las clases. De esta forma, se le pueden pasar dobles adecuados como dependencias para facilitar el proceso de pruebas.

### Inyección de dependencias con Hilt
Como mencionamos anteriormente, el uso de interfaces permite reemplazar facilmente una dependencia por otra similar, así como facilita el testeo de los componentes. El uso de inyección de dependencias ayuda a simplificar aún más ambos procesos, manteniendo la asignación de dependencias separada de las clases que las necesitan.
Se optó por el uso de Hilt (en detrimento de Dagger, por ejemplo) dado que tiene una mejor integración con el entorno de desarrollo Android, proveyendo nativamente funcionalidad asociada específicamente a ésta plataforma.

### Uso de DataBinding
Se ha utilizado la biblioteca DataBinding de Android Jetpack para simplificar la forma en que se trabaja la interfaz gráfica de la aplicación móvil, al evitar depender del uso de Ids para identificar la vista que se requiere sea modiciada de acuerdo a los datos recibidos.

### Uso de Retrofit
Se ha utilizado para este proyecto la biblioteca Retrofit debido a su facilidad de uso a la hora de consumir APIs.
Sin embargo, se crea un deserializador personalizado para recibir la llamada de la API de CoinMarketCap, CoinMarketCapDeserializer. Ésto debido a que realmente la aplicación muestra muy poca información en comparación con lo que la API retorna, siendo innecesario parsear un objeto Json tan grande.

## Posibles features a futuro
- Actualmente, la lista de criptomonedas despliega el valor de las mismas en pesos chilenos (CLP). Se podría hacer sin demasiada lógica extra que mostrara el valor en otra moneda configurable (o incluso en otras criptomonedas)
- Se podría hacer un mejor manejo de los mensajes de error desplegados por la app.
- También se podría implementar un sistema de refresco al mover el dedo hacia arriba (swipe up).
- Se podría implementar un sistema de almacenamiento local en base de datos para tener información en caso de no tener internet.
- Se podría mostrar un ícono que representa cada criptomoneda en la vista principal.
- Uso más intensivo de pruebas unitarias y de integración (que esta vez no pude hacer por falta de tiempo y experiencia).

## Posibles errores
- La aplicación es altamente sensible a un cambio en el formato de la API. Si ocurriese algún cambio en la API, la app podría dejar de funcionar.
- Eventualmente, la aplicación podría no resistir que reciba como parámetro un JsonObject demasiado grande para ser parseado.
