package com.anvipus.core.api

import com.anvipus.core.models.ApiResponse
import com.anvipus.core.models.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException

// Base class untuk API call menggunakan Flow
abstract class BaseApiCall<RequestType, ResultType> {

    fun executeApiCall(call: suspend () -> RequestType): Flow<Resource<ResultType>> = flow {
        emit(Resource.loading(null)) // Emit loading state

        try {
            val response = call()
            val apiBody = response

            if(response != null ){
                val data = processResponse(apiBody)
                emit(Resource.success(data))
            }
            else {
                emit(Resource.error( "Terjadi kesalahan"))
            }
        } catch (e: IOException) {
            emit(Resource.error("Terdapat gangguan koneksi, mohon periksa koneksi dan mencoba kembali: ${e.message}"))
        } catch (e: Exception) {
            emit(Resource.error("Kesalahan tidak terduga: ${e.message}"))
        }
    }.flowOn(Dispatchers.IO)

    // Override ini untuk memproses response sebelum diberikan ke UI
    protected open fun processResponse(body: RequestType?): ResultType? {
        return body as? ResultType
    }

    // Override jika ada kondisi sukses yang berbeda
    protected open fun isSuccess(action: String): Boolean {
        return action.contains("SUCCESS", ignoreCase = true)
    }
}