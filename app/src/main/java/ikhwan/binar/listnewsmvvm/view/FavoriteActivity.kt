package ikhwan.binar.listnewsmvvm.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ikhwan.binar.listnewsmvvm.R
import ikhwan.binar.listnewsmvvm.viewmodel.NewsApiViewModel
import kotlinx.android.synthetic.main.activity_favorite.*

@AndroidEntryPoint
class FavoriteActivity : AppCompatActivity() {

    private val viewModel: NewsApiViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        fetchData()
    }

    private fun fetchData() {
        viewModel.liveListFav.observe(this){
            rv_fav.layoutManager = LinearLayoutManager(this)
            rv_fav.adapter = FavoriteAdapter(it){ fav ->
                AlertDialog.Builder(this).setTitle("Remove Favorite")
                    .setMessage("Are you sure")
                    .setPositiveButton("Yes"){ _, _ ->
                        viewModel.deleteFavorite(fav)
                        fetchData()
                    }.setNegativeButton("No"){_,_ ->

                    }.show()
            }
        }
        viewModel.getFavorite()
    }
}