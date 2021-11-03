package main.data.data.local

import main.data.data.model.MovieEntity
import main.data.data.model.MovieList
import main.data.data.model.toMovieList

class LocalMovieDataSource(private val movieDao: MovieDao) {

    suspend fun getUpcomingMovies(): MovieList {

        return movieDao.getAllMovies().filter { it.movie_type=="upcoming" }.toMovieList()
    }
    suspend fun getTopRatedMovies(): MovieList {

        return movieDao.getAllMovies().filter { it.movie_type=="toprated" }.toMovieList()
    }
    suspend fun getPopularMovies(): MovieList {
        return movieDao.getAllMovies().filter { it.movie_type=="popular" }.toMovieList()
    }
    suspend fun getLatestMovies(): MovieList {
        return movieDao.getAllMovies().filter { it.movie_type=="latest" }.toMovieList()
    }

    suspend fun saveMovie(movie: MovieEntity){
        movieDao.saveMovie(movie)
    }
}