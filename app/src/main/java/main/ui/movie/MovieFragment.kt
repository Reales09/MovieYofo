package main.ui.movie

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import com.reales.movieyofo.R

import com.reales.movieyofo.databinding.FragmentMovieBinding
import main.core.Resource
import main.data.data.local.AppDatabase
import main.data.data.local.LocalMovieDataSource
import main.data.data.model.Movie
import main.data.data.remote.RemoteMovieDataSource
import main.presentation.MovieViewModel
import main.repository.MovieRespositoryImpl
import main.repository.RetrofitClient
import main.ui.movie.adapters.MovieAdapter
import main.ui.movie.adapters.concat.LatestConcatAdapter
import main.ui.movie.adapters.concat.PopularConcatAdapter
import main.ui.movie.adapters.concat.TopRatedConcatAdapter
import main.ui.movie.adapters.concat.UpcomingConcatAdapter


class MovieFragment : Fragment(R.layout.fragment_movie), MovieAdapter.OnMovieClickListener {

    private lateinit var binding: FragmentMovieBinding
    private val viewModel by viewModels<MovieViewModel> {
        MovieViewModel.MovieViewModelFactory(

            MovieRespositoryImpl(
                RemoteMovieDataSource(RetrofitClient.webservice),
                LocalMovieDataSource(AppDatabase.getDatabase(requireContext()).movieDao())
            )
        )
    }

    private lateinit var concatAdapter: ConcatAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieBinding.bind(view)

        concatAdapter = ConcatAdapter()

        viewModel.fetchMainScreenMovies().observe(viewLifecycleOwner, Observer { result ->

            when (result) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    concatAdapter.apply {
                        addAdapter(
                            0,
                            UpcomingConcatAdapter(
                                MovieAdapter(
                                    result.data.t1.results,
                                    this@MovieFragment
                                )
                            )
                        )
                        addAdapter(
                            1,
                            TopRatedConcatAdapter(
                                MovieAdapter(
                                    result.data.t2.results,
                                    this@MovieFragment
                                )
                            )
                        )
                        addAdapter(
                            2,
                            PopularConcatAdapter(
                                MovieAdapter(
                                    result.data.t3.results,
                                    this@MovieFragment
                                )
                            )
                        )
                        addAdapter(
                            3,
                            LatestConcatAdapter(
                                MovieAdapter(
                                    result.data.t4.results,
                                    this@MovieFragment
                                )
                            )
                        )

                    }
                    binding.rvMovies.adapter = concatAdapter
                }
                is Resource.Failure -> {
                    binding.progressBar.visibility = View.GONE
                    Log.d("Error", "${result.exception}")
                }

            }
        })


    }

    override fun onMovieCLick(movie: Movie) {
        val action = MovieFragmentDirections.actionMovieFragmentToMovieDetailFragment(
            movie.poster_path,
            movie.backdrop_path,
            movie.vote_average.toFloat(),
            movie.vote_count,
            movie.overview,
            movie.title,
            movie.release_date,
            movie.original_language

        )
        findNavController().navigate(action)
    }

}