package com.skeleton.android.features.word

import java.util.function.UnaryOperator

data class Word(val id: String, val word: String) {

    fun toWordView(): WordView {
        return WordView(id ,word)
    }

    fun toWordEntity(): WordEntity {
        return WordEntity(id,word)
    }

}