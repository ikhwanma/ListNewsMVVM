package ikhwan.binar.listnewsmvvm.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ikhwan.binar.chapterlima.networkingviewmodel.model.GetNewsResponseItem
import ikhwan.binar.listnewsmvvm.R
import kotlinx.android.synthetic.main.item_news.view.*

class NewsAdapter(private var listNews : List<GetNewsResponseItem>, val onItemClick: (GetNewsResponseItem) -> Unit) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listNews[position]

        holder.itemView.apply {
            tv_news.text = data.title
            tv_author.text = data.author
            tv_tanggal.text = data.createdAt
            rootView.setOnClickListener {
                onItemClick(data)
            }
            this@NewsAdapter.let {
                Glide.with(holder.itemView)
                    .load(data.image)
                    .into(holder.itemView.img_news)
            }
        }
    }

    override fun getItemCount(): Int {
        return listNews.size
    }


}