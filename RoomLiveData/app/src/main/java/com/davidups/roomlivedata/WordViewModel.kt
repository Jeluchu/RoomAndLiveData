package com.davidups.roomlivedata

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class WordViewModel(application: Application) : AndroidViewModel(application){
    /**
     * Hacemos referencia al repositorio.
     */
    private val repository: WordRepository

    /**
     * Almacenar en cache lista de palabras.
     */
    val allWords: LiveData<List<Word>>

    /**
     * Define a @parentJob y a @coroutineContext. El contexto de la Coroutina por defecto usa el @parentJob y el Main Dispatchers
     * para crear la nueva instancia de @scope basada en la @coroutineContext.
     */
    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
    get() = parentJob + Dispatchers.Main

    private val scope = CoroutineScope(coroutineContext)

    /**
     * Obtenemos la referencia a la @WordDao de @WordRoomDatabase y construya el @WordRepository de el
     * allWord contiene los datos del repositorio.
     */
    init {
        val wordsDao = WordRoomDatabase.getDatabase(application, scope).wordDao()
        repository = WordRepository(wordsDao)
        allWords = repository.allWords
    }

    /**
     * Creamos un metodo insert que llame al inesert del @WordRepository, de esta forma la implementación insert quede
     * oculta de la interfaz de usuario, y se llame fuera del hilo principal, por eso utilizamos una coroutine (IO Dispatchers).
     */
    fun insert(word: Word) = scope.launch(Dispatchers.IO){
        repository.insert(word)
    }

    /**
     * Haciendo un override al método onCleared el cual se llama cuando no se usa el ViewModel, destruye el @parentJob
     */
    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }
}