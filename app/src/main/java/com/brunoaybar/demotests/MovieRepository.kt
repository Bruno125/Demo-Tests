package com.brunoaybar.demotests

data class Movie(val id: String, val name: String)

interface MovieRepository{

    interface Callback{
        fun onCompleted(movie: Movie)
    }

    fun getSimpleMovie(callback: Callback)
    fun getMovieDetail(id: String, callback: Callback)
}

class FandangoMovieRepo : MovieRepository{
    override fun getMovieDetail(id: String, callback: MovieRepository.Callback) {
        callback.onCompleted(Movie("1","test"))
    }

    override fun getSimpleMovie(callback: MovieRepository.Callback) {
        callback.onCompleted(Movie("1","test"))
    }

}