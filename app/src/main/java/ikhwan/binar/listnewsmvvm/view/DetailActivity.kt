package ikhwan.binar.listnewsmvvm.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import ikhwan.binar.listnewsmvvm.R
import ikhwan.binar.listnewsmvvm.model.GetNewsResponseItem
import ikhwan.binar.listnewsmvvm.room.Favorite
import ikhwan.binar.listnewsmvvm.viewmodel.NewsApiViewModel
import kotlinx.android.synthetic.main.activity_detail.*


@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private val viewModel: NewsApiViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val detailNews =
            intent.getParcelableExtra<GetNewsResponseItem>("detail") as GetNewsResponseItem

        tv_news.text = detailNews.title
        tv_author.text = detailNews.author
        tv_tanggal.text = detailNews.createdAt
        tv_description.text = detailNews.description
        Glide.with(this).load(detailNews.image).into(img_news)

        add(detailNews)
    }

    private fun add(detailNews: GetNewsResponseItem) {
        viewModel.detailFav.observe(this) { fav ->
            if (fav == null){
                btn_fav.setBackgroundResource(R.drawable.ic_baseline_favorite_border_24)
                btn_fav.setOnClickListener {
                    detailNews.apply {
                        viewModel.addFavorite(
                            Favorite(
                                null,
                                this.id,
                                this.title,
                                this.author,
                                this.createdAt,
                                this.image
                            )
                        )
                    }
                    btn_fav.setBackgroundResource(R.drawable.ic_baseline_favorite_24)
                    add(detailNews)
                    Toast.makeText(this, "Add to favorite", Toast.LENGTH_SHORT).show()
                }
            }
            else {
                btn_fav.setBackgroundResource(R.drawable.ic_baseline_favorite_24)
                btn_fav.setOnClickListener {
                    viewModel.deleteFavorite(fav)
                    btn_fav.setBackgroundResource(R.drawable.ic_baseline_favorite_border_24)
                    add(detailNews)
                    Toast.makeText(this, "Removed from favorite", Toast.LENGTH_SHORT).show()
                }
            }
        }
        viewModel.getFavoriteDetail(detailNews.id)
    }

}