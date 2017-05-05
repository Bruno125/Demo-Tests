package com.brunoaybar.demotests.model

data class Movie(val id: String, val name: String, val posterUrl: String)

interface MovieRepository{

    interface Callback{
        fun onCompleted(movie: Movie)
        fun onError(message: String)
    }

    fun getMovieDetail(id: String, callback: Callback)
}
