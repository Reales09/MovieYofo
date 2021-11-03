package main.ui.movie.adapters.concat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.reales.movieyofo.databinding.LatestMovieRowBinding
import com.reales.movieyofo.databinding.PopularMoviesRowBinding
import main.core.BaseConcatHolder
import main.ui.movie.adapters.MovieAdapter

class LatestConcatAdapter (private val moviesAdapter: MovieAdapter): RecyclerView.Adapter<BaseConcatHolder<*>>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseConcatHolder<*> {
            val itemBinding = LatestMovieRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ConcatViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BaseConcatHolder<*>, position: Int) {
        when (holder){
            is ConcatViewHolder -> holder.bind(moviesAdapter)
        }
    }

    override fun getItemCount(): Int = 1
    private inner class ConcatViewHolder(val binding: LatestMovieRowBinding):
        BaseConcatHolder<MovieAdapter>(binding.root){
        override fun bind(adapter: MovieAdapter) {
            binding.rvLatestMovies.adapter = adapter
        }
    }
}