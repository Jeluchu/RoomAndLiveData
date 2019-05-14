package com.skeleton.android.features.word

import com.skeleton.android.core.custominterfaces.FirebaseCallBack
import com.skeleton.android.core.exception.Failure
import com.skeleton.android.core.functional.Either
import com.skeleton.android.core.platform.NetworkHandler
import com.skeleton.android.core.platform.ServiceKOs
import java.lang.Exception
import javax.inject.Inject

interface WordRepository {

    fun words(firebaseCallBack: FirebaseCallBack)
    fun addFirebase(word: Word): Either<Failure, Any>

    class Network
    @Inject constructor(private val service: WordService, private val networkHandler: NetworkHandler): WordRepository {

        override fun words(firebaseCallBack: FirebaseCallBack) {
            if (networkHandler.isConnected!!){
                service.words(firebaseCallBack)
            }
        }

        /*override fun add(word: Word): Either<Failure, Any> {
            return try {
                Either.Right(service.add(word.toWordEntity()))
            } catch (e: Exception) {
                Either.Left(Failure.CustomError(ServiceKOs.DATABASE_ACCESS_ERROR, e.message))
            }
        }*/

        override fun addFirebase(word: Word): Either<Failure, Any> {
            return try {
                Either.Right(service.addFirebase(word.toWordEntity()))
            } catch (e: Exception) {
                Either.Left(Failure.CustomError(ServiceKOs.DATABASE_ACCESS_ERROR, e.message))
            }
        }

    }
}