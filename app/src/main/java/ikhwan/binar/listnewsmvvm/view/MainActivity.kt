package ikhwan.binar.listnewsmvvm.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ikhwan.binar.listnewsmvvm.R
import ikhwan.binar.listnewsmvvm.viewmodel.NewsApiViewModel
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var viewModel : NewsApiViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[NewsApiViewModel::class.java]

        viewModel.news.observe(this){
            rv_news.layoutManager = LinearLayoutManager(this)
            val adapter = NewsAdapter(it!!){ data ->
                val intent = Intent(this, DetailActivity::class.java)
                intent.putExtra("detail", data)
                startActivity(intent)
            }
            rv_news.adapter = adapter
        }
    }
}