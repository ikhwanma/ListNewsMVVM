package ikhwan.binar.listnewsmvvm.room

import androidx.room.*

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(favorite: Favorite): Long

    @Query("SELECT * FROM Favorite")
    suspend fun getFavorite() : List<Favorite>

    @Query("SELECT * FROM Favorite WHERE Favorite.id_news = :idNews")
    suspend fun getFavoriteDetail(idNews : String) : Favorite

    @Delete
    suspend fun deleteFavorite(favorite: Favorite) : Int
}