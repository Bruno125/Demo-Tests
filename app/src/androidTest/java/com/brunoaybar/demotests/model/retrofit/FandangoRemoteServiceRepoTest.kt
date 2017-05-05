package com.brunoaybar.demotests.model.retrofit

import android.util.Log
import com.brunoaybar.demotests.createWireMockConstant
import com.brunoaybar.demotests.model.Movie
import com.brunoaybar.demotests.model.MovieRepository
import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.client.WireMock.*
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import com.github.tomakehurst.wiremock.core.WireMockConfiguration
import com.github.tomakehurst.wiremock.junit.WireMockRule



/**
 * Created by fanlat on 4/05/17.
 */
class FandangoRemoteServiceRepoTest {
    //@get:Rule
    //public val wireMockRule = WireMockRule(WireMockConfiguration.wireMockConfig().port(8080))

    @After
    fun tearDown() {

    }

    lateinit var repository : FandangoRemoteServiceRepo

    @Before
    fun setUp(){
        repository = FandangoRemoteServiceRepo(createWireMockConstant())
        WireMock.configureFor("10.0.2.2",8080)
        WireMock.reset()
    }

    @Test
    fun getMovieDetail() {
        val mockMovie = Movie("1","Test movie","http://wwww.test.com/a.png")
        stubFor(get(urlEqualTo("/movie/${mockMovie.id}"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody("{ \"id\" : \"${mockMovie.id}\", \"name\" : \"${mockMovie.name}\", \"poster\" : \"${mockMovie.posterUrl}\" }")))

        repository.getMovieDetail("1",object: MovieRepository.Callback{
            override fun onCompleted(movie: Movie) {
                assertEquals(mockMovie.id,movie.id)
                assertEquals(mockMovie.name,movie.name)
                assertEquals(mockMovie.posterUrl,movie.posterUrl)
            }
            override fun onError(message: String) {
                fail("Movie detail failed when it should not. Error: $message")
            }
        })


    }

}