package com.davidups.roomlivedata

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*

@Dao
interface WordDao{

    @Insert
    fun insert (word: Word)

    @Update
    fun update (word: Word)

    @Delete
    fun delete (word: Word)

    @Query("SELECT * FROM word_table ORDER BY word ASC")
    fun getAllWord() : LiveData<List<Word>>

    @Query("DELETE FROM word_table")
    fun deleteAll()

}