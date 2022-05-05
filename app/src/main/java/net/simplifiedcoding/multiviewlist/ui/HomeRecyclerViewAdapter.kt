package net.simplifiedcoding.multiviewlist.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import net.vishtech.multiviewlist.R
import net.vishtech.multiviewlist.databinding.ItemDirectorBinding
import net.vishtech.multiviewlist.databinding.ItemMovieBinding
import net.vishtech.multiviewlist.databinding.ItemTitleBinding
import java.lang.IllegalArgumentException

class HomeRecyclerViewAdapter: RecyclerView.Adapter<HomeRecyclerViewHolder>() {

    var items = listOf<HomeRecyclerViewItem>()
    set(value){
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeRecyclerViewHolder {
        return when(viewType) {
            R.layout.item_title -> HomeRecyclerViewHolder.TitleViewHolder(
                  ItemTitleBinding.inflate(
                      LayoutInflater.from(parent.context),
                      parent,
                      false
                  )
            )

            R.layout.item_movie -> HomeRecyclerViewHolder.MovieViewHolder(
                ItemMovieBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            R.layout.item_director -> HomeRecyclerViewHolder.DirectorViewMolder(
              ItemDirectorBinding.inflate(
                  LayoutInflater.from(parent.context),
                  parent,
                  false
              )

            ) else ->  throw IllegalArgumentException("Unknown view class")
        }
    }

    override fun onBindViewHolder(holder: HomeRecyclerViewHolder, position: Int) {
        when(holder) {
            is HomeRecyclerViewHolder.DirectorViewMolder -> holder.bind(items[position] as HomeRecyclerViewItem.Director)
            is HomeRecyclerViewHolder.MovieViewHolder -> holder.bind(items[position] as HomeRecyclerViewItem.Movie)
            is HomeRecyclerViewHolder.TitleViewHolder -> holder.bind(items[position] as HomeRecyclerViewItem.Title)
        }
    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int): Int {
        return when(items[position]) {
            is HomeRecyclerViewItem.Director -> R.layout.item_director
            is HomeRecyclerViewItem.Movie -> R.layout.item_movie
            is HomeRecyclerViewItem.Title -> R.layout.item_title
        }
    }
}