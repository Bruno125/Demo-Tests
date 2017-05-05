package com.brunoaybar.demotests.model


class FandangoMovieRepo : MovieRepository {
    override fun getMovieDetail(id: String, callback: MovieRepository.Callback) {
        callback.onCompleted(Movie(id,"test $id","https://cdn.cinepapaya.com/stat/img/static-cp/cine/movietranslation/dia-del-atentado-poster-1485279001.jpg?w=195"))
    }
}