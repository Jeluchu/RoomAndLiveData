# RoomAndLiveData
Kotlin Room and Live Data

### 1.-Clase con lo que va a contener la tabla de la Base de Datos
### 2.-Dao con la que vamos a crear los Insert/Update/Delete/Query
### 3.-RoomDatabase para crear una RoomDatabase
### 4.-Repository
#### Resumen
Los componentes de la aplicación son:
MainActivity: muestra palabras en una lista usando a RecyclerViewy la WordListAdapter. En el MainActivity, hay un Observerque observa las palabras LiveData de la base de datos y es notificado cuando cambian.
NewWordActivity: agrega una nueva palabra a la lista.
WordViewModel(*): proporciona métodos para acceder a la capa de datos y devuelve LiveData para que MainActivity pueda configurar la relación de observador.
LiveData<List<Word>>: Posibilita las actualizaciones automáticas en los componentes de la interfaz de usuario. En el MainActivity, hay un Observerque observa las palabras LiveData de la base de datos y es notificado cuando cambian.
Repository:gestiona una o más fuentes de datos. Los Repositorymétodos de exposición para que ViewModel interactúe con el proveedor de datos subyacente. En esta aplicación, ese backend es una base de datos de habitaciones.
Room: es una envoltura alrededor e implementa una base de datos SQLite. La habitación hace mucho trabajo para ti que solías hacer tú mismo.
DAO: asigna llamadas de método a consultas de base de datos, de modo que cuando el Repositorio llama a un método como getAllWords(), puede ejecutar Room .SELECT * from word_table ORDER BY word ASC
Word: es la clase de entidad que contiene un solo trabajo.
