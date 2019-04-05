package com.example.myapplication.Adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.model.movie
import kotlinx.android.synthetic.main.cardview_movie.view.*

class MovieAdapter(var noives : List<movie>):RecyclerView.Adapter<MovieAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_movie,parent,false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int = noives.size



    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(noives[position])

    fun changeList(noives:List<movie>)
    {
        this.noives = noives
        notifyDataSetChanged()
    }



    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        fun bind(movie: movie) = with(itemView)
        {
            Glide.with(itemView.context).load(movie.Poster).placeholder(R.drawable.ic_launcher_background).into(movie_image_cv)
            movie_title_cv.text = movie.Title
            movie_title_cv.text = movie.Plot
            movie_title_cv.text = movie.imdbRating
            movie_title_cv.text = movie.Runtime

        }


    }
}