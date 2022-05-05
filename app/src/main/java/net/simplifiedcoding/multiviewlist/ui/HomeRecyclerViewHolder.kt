package net.simplifiedcoding.multiviewlist.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import net.vishtech.multiviewlist.R
import net.vishtech.multiviewlist.databinding.ItemDirectorBinding
import net.vishtech.multiviewlist.databinding.ItemMovieBinding
import net.vishtech.multiviewlist.databinding.ItemTitleBinding

sealed class HomeRecyclerViewHolder(private val binding: ViewBinding): RecyclerView.ViewHolder(binding.root){

    class TitleViewHolder(private val binding: ItemTitleBinding): HomeRecyclerViewHolder(binding) {
        fun bind(title: HomeRecyclerViewItem.Title) {
            binding.textViewTitle.text = title.title
        }
    }

    class MovieViewHolder(private val binding: ItemMovieBinding): HomeRecyclerViewHolder(binding) {
        fun bind(movie: HomeRecyclerViewItem.Movie) {
            if(movie.thumbnail != null) {
                binding.imageViewMovie.loadImage(movie.thumbnail)
            } else {
                binding.imageViewMovie.setActualImageResource(R.drawable.not_found)
            }
        }
    }

    class DirectorViewMolder(private val binding: ItemDirectorBinding): HomeRecyclerViewHolder(binding) {
        fun bind(director: HomeRecyclerViewItem.Director) {
            binding.textViewName.text = director.name
            binding.imageViewDirector.loadImage(director.avatar)
            binding.textViewMovies.text = binding.textViewName.context.getString(R.string.total_movies, director.movie_count)
        }
    }
}