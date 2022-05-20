package ikhwan.binar.listnewsmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import ikhwan.binar.listnewsmvvm.viewmodel.NewsApiViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel : NewsApiViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[NewsApiViewModel::class.java]

        viewModel.getAllNews()
        viewModel.listNews.observe(this){
            rv_news.layoutManager = LinearLayoutManager(this)
            val adapter = NewsAdapter(it!!)
            rv_news.adapter = adapter
        }
    }
}