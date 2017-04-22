package com.brunoaybar.demotests

data class Movie(val name: String)

interface MovieRepository{

    interface Callback{
        fun onCompleted(movie: Movie)
    }

    fun getSimpleMovie(callback: Callback)

}

class FandangoMovieRepo : MovieRepository{

    override fun getSimpleMovie(callback: MovieRepository.Callback) {
        callback.onCompleted(Movie("test"))
    }

}