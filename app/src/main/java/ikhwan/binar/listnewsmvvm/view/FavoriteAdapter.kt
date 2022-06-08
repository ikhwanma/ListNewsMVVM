package ikhwan.binar.listnewsmvvm.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ikhwan.binar.listnewsmvvm.R
import ikhwan.binar.listnewsmvvm.room.Favorite
import kotlinx.android.synthetic.main.item_news.view.*

class FavoriteAdapter (private var listFav : List<Favorite>, val onItemClick : (Favorite) -> Unit) : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>(){
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listFav[position]

        holder.itemView.apply {
            tv_news.text = data.name
            tv_author.text = data.sutradara
            tv_tanggal.text = data.tanggal
            rootView.setOnClickListener {
                onItemClick(data)
            }
            this.let {
                Glide.with(holder.itemView)
                    .load(data.image)
                    .into(holder.itemView.img_news)
            }
        }
    }

    override fun getItemCount(): Int {
        return listFav.size
    }


}