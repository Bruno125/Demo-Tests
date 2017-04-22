package com.brunoaybar.demotests;

import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by brunoaybar on 21/04/2017.
 */

public class JavaTest {

    @Test
    public void repository_returns_demo_movie(){
        MovieRepository repo = new FandangoMovieRepo();
        repo.getSimpleMovie(new MovieRepository.Callback() {
            @Override
            public void onCompleted(@NotNull Movie movie) {
                assertEquals(movie.getName(),"test");
            }
        });

    }


}
