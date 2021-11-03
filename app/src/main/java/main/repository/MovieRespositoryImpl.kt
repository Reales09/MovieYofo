package main.repository

import main.core.InternetCheck
import main.data.data.local.LocalMovieDataSource
import main.data.data.model.MovieList
import main.data.data.model.toMovieEntity
import main.data.data.remote.RemoteMovieDataSource

class MovieRespositoryImpl(
    private val dataSourceRemote: RemoteMovieDataSource,
    private val dataSourceLocal: LocalMovieDataSource
) : MovieRepository {
    override suspend fun getUpcomingMovies(): MovieList {

        return if (InternetCheck.isNetworkAvaliable()) {

            dataSourceRemote.getUpcomingMovies().results.forEach { movie ->
                dataSourceLocal.saveMovie(movie.toMovieEntity("upcoming"))
            }
            dataSourceLocal.getUpcomingMovies()
        } else {
            dataSourceLocal.getUpcomingMovies()
        }

    }

    override suspend fun getTopRatedMovies(): MovieList {
        return if (InternetCheck.isNetworkAvaliable()) {
            dataSourceRemote.getTopRatedMovies().results.forEach { movie ->
                dataSourceLocal.saveMovie(movie.toMovieEntity("toprated"))
            }
            dataSourceLocal.getTopRatedMovies()
        } else {
            dataSourceLocal.getTopRatedMovies()
        }
    }


    override suspend fun getPopularMovies(): MovieList {

        return if (InternetCheck.isNetworkAvaliable()) {
            dataSourceRemote.getPopularMovies().results.forEach { movie ->
                dataSourceLocal.saveMovie(movie.toMovieEntity("popular"))
            }
            dataSourceLocal.getPopularMovies()
        } else {
            dataSourceLocal.getPopularMovies()
        }

    }

    override suspend fun getLatestMovies(): MovieList {
        return if (InternetCheck.isNetworkAvaliable()) {
            dataSourceRemote.getLatestMovies().results.forEach { movie ->
                dataSourceLocal.saveMovie(movie.toMovieEntity("latest"))
            }
            dataSourceLocal.getLatestMovies()
        } else {
            dataSourceLocal.getLatestMovies()
        }
    }

}
