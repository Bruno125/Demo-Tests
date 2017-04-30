package com.brunoaybar.demotests

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.mock
import org.amshove.kluent.mock
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyObject
import org.mockito.ArgumentMatchers.eq
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.MockitoAnnotations


class MoviePresenterTest{

    lateinit var presenter : FandangoMoviePresenter
    @Mock val repository : MovieRepository = mock()
    @Mock val view : MovieView = mock()

    @Before
    fun setUp(){
        presenter = FandangoMoviePresenter(repository,view)
    }

    @Test
    fun createPresenter_setPresenterToView(){
        verify(view).setPresenter(presenter)
    }

    @Test
    fun whenMovieIsSelected_AndFetchFromRepositorySuccess_ThenDisplaysInView(){
        val movie = Movie("1","name")
        //Movie is selected and presenter is notified
        presenter.selectMovie(movie.id)

        //When we request movies from repo, return stub movie
        argumentCaptor<MovieRepository.Callback>().apply {
            verify(repository).getMovieDetail(any(),capture())
            firstValue.onCompleted(movie)
        }

        //Then view is required to display stub movie name
        verify(view).setMovieName(movie.name)

    }


}