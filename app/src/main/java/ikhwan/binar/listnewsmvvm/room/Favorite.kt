package ikhwan.binar.listnewsmvvm.room

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Entity
@Parcelize
data class Favorite(
    @PrimaryKey(autoGenerate = true) var id: Int?,
    @ColumnInfo(name = "id_news") var idNews: String?,
    @ColumnInfo(name = "name") var name: String?,
    @ColumnInfo(name = "sutradara") var sutradara: String?,
    @ColumnInfo(name = "tanggal") var tanggal: String?,
    @ColumnInfo(name = "image") var image: String?,
) : Parcelable
