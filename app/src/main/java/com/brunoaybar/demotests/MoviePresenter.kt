package com.brunoaybar.demotests

interface MoviePresenter{
    fun selectMovie(id: String)
}

interface MovieView{
    fun setPresenter(presenter: MoviePresenter)
    fun setMovieName(name: String)
}

class FandangoMoviePresenter(val repository: MovieRepository, val view:MovieView) : MoviePresenter{
    init {
        view.setPresenter(this)
    }

    override fun selectMovie(id: String) {
        repository.getMovieDetail(id, object: MovieRepository.Callback{
            override fun onCompleted(movie: Movie) {
                view.setMovieName(movie.name)
            }
        })
    }

}