package com.skeleton.android.features.word

import android.arch.lifecycle.MutableLiveData
import com.skeleton.android.core.custominterfaces.FirebaseCallBack
import com.skeleton.android.core.exception.Failure
import com.skeleton.android.core.platform.BaseViewModel
import javax.inject.Inject

class GetWordViewModel
@Inject constructor(private val getWord: GetWord): BaseViewModel(){

    var words: MutableLiveData<List<WordView>> = MutableLiveData()

    fun words() = getWord.invoke(
        GetWord.Params(object : FirebaseCallBack {
            override fun onComplete(wordList: MutableList<Word>) {
                handleWordList(wordList)
            }

            override fun onFailure(failure: String) {
                handleFailure(Failure.CustomError(0, failure))
            }

        }))
    private fun handleWordList(words: List<Word>){
        this.words.value = words.map {
            it.toWordView()
        }
    }
}