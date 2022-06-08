package ikhwan.binar.listnewsmvvm.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import ikhwan.binar.chapterlima.networkingviewmodel.model.GetNewsResponseItem
import ikhwan.binar.listnewsmvvm.R
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

    private fun addToFav(detailNews: GetNewsResponseItem) {
        viewModel.liveListFav.observe(this) { fav ->
            if (fav.isNotEmpty()) {
                for (data in fav) {
                    if (data.idNews == detailNews.id) {
                        btn_fav.setBackgroundResource(R.drawable.ic_baseline_favorite_24)
                        btn_fav.setOnClickListener {
                            viewModel.deleteFavorite(data)
                            val intent = Intent(this, DetailActivity::class.java)
                            intent.putExtra("detail", detailNews)
                            startActivity(intent)
                            finish()
                            Toast.makeText(this, "Removed from favorite", Toast.LENGTH_SHORT).show()
                            btn_fav.setBackgroundResource(R.drawable.ic_baseline_favorite_border_24)
                        }
                        break
                    } else {
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
                            val intent = Intent(this, DetailActivity::class.java)
                            intent.putExtra("detail", detailNews)
                            startActivity(intent)
                            finish()
                            Toast.makeText(this, "Add to favorite", Toast.LENGTH_SHORT).show()
                            btn_fav.setBackgroundResource(R.drawable.ic_baseline_favorite_24)
                        }
                    }
                }
            } else {
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
                        val intent = Intent(this@DetailActivity, DetailActivity::class.java)
                        intent.putExtra("detail", detailNews)
                        startActivity(intent)
                        finish()
                        Toast.makeText(this@DetailActivity, "Add to favorite", Toast.LENGTH_SHORT)
                            .show()
                        btn_fav.setBackgroundResource(R.drawable.ic_baseline_favorite_24)
                    }
                }
            }
        }
        viewModel.getFavorite()
    }
}