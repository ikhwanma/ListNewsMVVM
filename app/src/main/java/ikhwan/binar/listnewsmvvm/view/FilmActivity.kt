package ikhwan.binar.listnewsmvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ikhwan.binar.listnewsmvvm.R
import ikhwan.binar.listnewsmvvm.viewmodel.FilmApiViewModel
import kotlinx.android.synthetic.main.activity_film.*

@AndroidEntryPoint
class FilmActivity : AppCompatActivity() {

    private val viewModel : FilmApiViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film)

        viewModel.film.observe(this){
            rv_film.layoutManager = LinearLayoutManager(this)
            rv_film.adapter = FilmAdapter(it)
        }
    }
}