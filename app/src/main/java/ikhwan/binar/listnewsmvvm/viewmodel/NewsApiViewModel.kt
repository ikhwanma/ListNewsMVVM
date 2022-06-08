package ikhwan.binar.listnewsmvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ikhwan.binar.listnewsmvvm.model.GetNewsResponseItem
import ikhwan.binar.listnewsmvvm.network.ApiService
import ikhwan.binar.listnewsmvvm.room.Favorite
import ikhwan.binar.listnewsmvvm.room.FavoriteDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NewsApiViewModel @Inject constructor(
    private val apiService: ApiService,
    private val favoriteDao: FavoriteDao
) : ViewModel() {

    private val listNews = MutableLiveData<List<GetNewsResponseItem>>()
    val news: LiveData<List<GetNewsResponseItem>> = listNews

    private val listFav = MutableLiveData<List<Favorite>>()
    val liveListFav : LiveData<List<Favorite>> = listFav

    val detailFav = MutableLiveData<Favorite>()

    init {
        viewModelScope.launch {
            val dataNews = apiService.getNews()
            delay(2000)
            listNews.postValue(dataNews)
        }
    }

    fun addFavorite(favorite: Favorite) {
        viewModelScope.launch(Dispatchers.IO) {
            favoriteDao.addFavorite(favorite)
        }
    }

    fun getFavorite() {
        viewModelScope.launch{
            listFav.postValue(favoriteDao.getFavorite())
        }
    }

    fun getFavoriteDetail(id:String){
        viewModelScope.launch {
            detailFav.postValue(favoriteDao.getFavoriteDetail(id))
        }
    }

    fun deleteFavorite(favorite: Favorite) {
        viewModelScope.launch(Dispatchers.IO){
            favoriteDao.deleteFavorite(favorite)
        }
    }

}