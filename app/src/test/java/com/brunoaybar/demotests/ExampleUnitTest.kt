package com.brunoaybar.demotests

import org.amshove.kluent.shouldEqual
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).

 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class ExampleUnitTest {

    lateinit var repo : MovieRepository

    @Before
    fun setUp(){
        repo = FandangoMovieRepo()
    }

    @Test
    public fun repository_returns_demo_movie() {
        repo.getSimpleMovie(object: MovieRepository.Callback{
            override fun onCompleted(movie: Movie) {
                movie.name shouldEqual "test"
            }

        })
    }





}