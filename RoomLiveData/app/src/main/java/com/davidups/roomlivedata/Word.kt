package com.davidups.roomlivedata

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "word_table")
data class Word(@PrimaryKey @ColumnInfo (name =  "word")val word: String)

/**
        @Entity representa un entidad en una tabla.
        @PrimaryKey cada @Entity necesita una clave privada
        @ColumInfo especifica el nombre de la tabla
 **/