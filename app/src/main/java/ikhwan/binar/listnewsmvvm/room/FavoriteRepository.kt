package ikhwan.binar.listnewsmvvm.room

import javax.inject.Inject

class FavoriteRepository @Inject constructor(private val favoriteDao: FavoriteDao){
    suspend fun addFavorite(favorite: Favorite) = favoriteDao.addFavorite(favorite)
}