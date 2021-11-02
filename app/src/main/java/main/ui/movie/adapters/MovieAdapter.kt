package main.ui.movie.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.reales.movieyofo.databinding.MovieItemBinding
import main.core.BaseViewHolder
import main.data.model.model.Movie
import main.data.model.model.MovieList

class MovieAdapter (private val movieList: List <Movie>,
                    private val itemClikListener: OnMovieClickListener
                    ): RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface OnMovieClickListener{
        fun onMovieCLick(movie: Movie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding =MovieItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        val holder = MoviesViewHolder(itemBinding, parent.context)

        itemBinding.root.setOnClickListener{
            val position = holder.adapterPosition.takeIf { it !=DiffUtil.DiffResult.NO_POSITION}
                ?: return@setOnClickListener
            itemClikListener.onMovieCLick(movieList[position])

        }
        return holder

    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder){
            is MoviesViewHolder -> holder.bind(movieList[position])
        }
    }

    override fun getItemCount(): Int = movieList.size
    private inner class MoviesViewHolder(val binding: MovieItemBinding, val context: Context): BaseViewHolder<Movie>(binding.root){
        override fun bind(item: Movie) {

            Glide.with(context).load("https://image.tmdb.org/t/p/w500${item.poster_path}").centerCrop().into(binding.imgMovie)

        }
    }
}