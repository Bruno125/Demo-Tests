package com.brunoaybar.demotests.model.retrofit

import android.util.Log
import com.brunoaybar.demotests.model.AppConstants
import com.brunoaybar.demotests.model.Movie
import com.brunoaybar.demotests.model.MovieRepository
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.io.IOException


//region //Repository that fetch info from remote repo


data class APIError(var statusCode: Int = 0, var message: String = "")

interface MovieService{
    @GET("movie/{id}")
    fun getMovie(@Path("id") id: String): Call<MovieResponse>
}

object ErrorUtils {
    fun parseError(retrofit: Retrofit, response: Response<*>?): APIError {
        val converter = retrofit.responseBodyConverter<APIError>(APIError::class.java, arrayOfNulls<Annotation>(0))
        val error: APIError

        try {
            error = converter.convert(response?.errorBody())
        } catch (e: Throwable) {
            return APIError()
        }

        return error
    }
}

data class MovieResponse(@SerializedName("id") val id: String,
                         @SerializedName("name") val name: String,
                         @SerializedName("poster") val poster: String){

    fun transformToMovie() : Movie{
        return Movie(id,name,poster)
    }
}

class FandangoRemoteServiceRepo(constants: AppConstants): MovieRepository {
    val retrofit: Retrofit
    val service : MovieService
    init {
        retrofit = Retrofit.Builder()
                .baseUrl(constants.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        service = retrofit.create(MovieService::class.java)
    }

    override fun getMovieDetail(id: String, callback: MovieRepository.Callback) {
        val call = service.getMovie(id)
        call.enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>?, t: Throwable?) {
                callback.onError(t.toString())
            }

            override fun onResponse(call: Call<MovieResponse>?, response: Response<MovieResponse>?) {
                if (response!=null && response.isSuccessful){
                    callback.onCompleted(response.body().transformToMovie())
                }else{
                    val error = ErrorUtils.parseError(retrofit,response)
                    callback.onError(error.message)
                }
            }
        })

    }

}


//endregion
