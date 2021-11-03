package main.data.data.remote

import main.aplication.AppConstants
import main.data.data.model.MovieList
import main.repository.WebService

class RemoteMovieDataSource(private val webService: WebService) {

    suspend fun getUpcomingMovies(): MovieList = webService.getUpcomingMovies(AppConstants.API_KEY)
    suspend fun getTopRatedMovies(): MovieList = webService.getTopRatedMovies(AppConstants.API_KEY)
    suspend fun getPopularMovies(): MovieList = webService.getPopularMovies(AppConstants.API_KEY)
    suspend fun getLatestMovies(): MovieList = webService.getLatestMovies(AppConstants.API_KEY)

}