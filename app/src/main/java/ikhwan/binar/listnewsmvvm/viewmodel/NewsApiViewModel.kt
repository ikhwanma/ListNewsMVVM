package ikhwan.binar.listnewsmvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ikhwan.binar.chapterlima.networkingviewmodel.model.GetNewsResponseItem
import ikhwan.binar.latihandependencyinjection.network.ApiService
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NewsApiViewModel @Inject constructor(private val apiService: ApiService) : ViewModel(){

    val listNews = MutableLiveData<List<GetNewsResponseItem>>()
    val news : LiveData<List<GetNewsResponseItem>> = listNews

    val detailNews = MutableLiveData<GetNewsResponseItem>()
    val dNews : LiveData<GetNewsResponseItem> = detailNews

    init{
        viewModelScope.launch {
            val dataNews = apiService.getNews()
            delay(2000)
            listNews.postValue(dataNews)
        }
    }

    fun getDetail(id : String){
        viewModelScope.launch {
            val detail = apiService.getDetail(id)
            delay(2000)
            detailNews.postValue(detail)
        }
    }

}