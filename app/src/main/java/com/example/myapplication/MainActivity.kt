package com.example.myapplication

import android.graphics.Movie
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import com.example.myapplication.model.movie
import com.example.myapplication.utils.NetworkUtils
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.io.IOException
import java.net.URL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    private inner class FetchMovie: AsyncTask<String, Void, String>()
    {
        override fun doInBackground(vararg params: String): String {
            if(params.isNullOrEmpty())return "";

            val movieName:String = params [0];
            val movieURL:URL = NetworkUtils().buildSearchUrl(movieName)
            return try {
                NetworkUtils().getResponseFromHttpUrl(movieURL)
            }catch (e: IOException){
                ""
            }
        }

        override fun onPostExecute(movieInfo: String) {
            super.onPostExecute(movieInfo)

            if(!movieInfo.isEmpty())
            {
                val movieJson = JSONObject(movieInfo)
                if(movieJson.getString("Response")=="True")
                {
                    val movie = Gson().fromJson<movie>(movieInfo, Movie::class.java)

                }
                else
                {
                    Snackbar.make(main_ll,"No existe la movie",Snackbar.LENGTH_LONG).show();
                }
            }
        }

    }
}
