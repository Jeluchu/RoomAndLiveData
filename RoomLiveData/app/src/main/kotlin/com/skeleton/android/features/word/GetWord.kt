package com.skeleton.android.features.word

import com.skeleton.android.core.custominterfaces.FirebaseCallBack
import com.skeleton.android.core.interactor.UseCase
import com.skeleton.android.core.interactor.UseCaseWithout
import javax.inject.Inject

class GetWord
@Inject constructor(private val wordRepository: WordRepository): UseCaseWithout<GetWord.Params>(){
    override suspend fun run(params: Params) = wordRepository.words(params.firebaseCallBack)

    class Params(val firebaseCallBack: FirebaseCallBack)
}