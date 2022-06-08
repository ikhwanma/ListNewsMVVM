package ikhwan.binar.listnewsmvvm.view.film

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import ikhwan.binar.listnewsmvvm.R
import ikhwan.binar.listnewsmvvm.model.film.GetFilmResponseItem
import ikhwan.binar.listnewsmvvm.viewmodel.FilmApiViewModel
import kotlinx.android.synthetic.main.activity_detail_film.*

@AndroidEntryPoint
class DetailFilmActivity : AppCompatActivity() {

    private val viewModel : FilmApiViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_film)
        val detail = intent.getParcelableExtra<GetFilmResponseItem>("detail") as GetFilmResponseItem

        viewModel.getDetailFilm(detail.id)
        viewModel.dFilm.observe(this){detailFilm ->
            tv_film.text = detailFilm.name
            tv_sutradara.text = detailFilm.director
            tv_tanggal.text = detailFilm.date
            Glide.with(this).load(detailFilm.image).into(img_film)
            tv_description.text = detailFilm.description
        }
    }
}