package com.brunoaybar.demotests.presenter

import com.brunoaybar.demotests.model.Movie
import com.brunoaybar.demotests.model.MovieRepository

interface MoviePresenter{
    fun selectMovie(id: String)
}

interface MovieView{
    fun setPresenter(presenter: MoviePresenter)
    fun setMovieName(name: String)
}

class FandangoMoviePresenter(val repository: MovieRepository, val view: MovieView) : MoviePresenter {
    init {
        view.setPresenter(this)
    }

    override fun selectMovie(id: String) {
        repository.getMovieDetail(id, object: MovieRepository.Callback {
            override fun onError(message: String) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onCompleted(movie: Movie) {
                view.setMovieName(movie.name)
            }
        })
    }

}