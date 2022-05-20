package ikhwan.binar.listnewsmvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import ikhwan.binar.chapterlima.networkingviewmodel.model.GetNewsResponseItem
import ikhwan.binar.listnewsmvvm.R
import ikhwan.binar.listnewsmvvm.viewmodel.NewsApiViewModel
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val detailNews = intent.getParcelableExtra<GetNewsResponseItem>("detail") as GetNewsResponseItem

        tv_news.text = detailNews.title
        tv_author.text = detailNews.author
        tv_tanggal.text = detailNews.createdAt
        tv_description.text = detailNews.description
        Glide.with(this).load(detailNews.image).into(img_news)

       /* viewModel = ViewModelProvider(this)[NewsApiViewModel::class.java]

        viewModel.getDetail(detail.id)
        viewModel.dNews.observe(this){ detailNews ->
            tv_news.text = detailNews.title
            tv_author.text = detailNews.author
            tv_tanggal.text = detailNews.createdAt
            tv_description.text = detailNews.description
            Glide.with(this).load(detailNews.image).into(img_news)
        }*/
    }
}