package com.skeleton.android.core.custominterfaces

import com.skeleton.android.features.word.Word

interface FirebaseCallBack {
    fun onComplete(wordList: MutableList<Word>)
    fun onFailure(failure: String)
}