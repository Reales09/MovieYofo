package main.data.model.local

import main.aplication.AppConstants
import main.data.model.model.MovieEntity
import main.data.model.model.MovieList
import main.data.model.model.toMovieList

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
    suspend fun saveMovie(movie: MovieEntity){
        movieDao.saveMovie(movie)
    }
}