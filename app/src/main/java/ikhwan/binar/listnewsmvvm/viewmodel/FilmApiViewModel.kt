package ikhwan.binar.listnewsmvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ikhwan.binar.latihandependencyinjection.network.ApiService
import ikhwan.binar.listnewsmvvm.model.film.GetFilmResponseItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FilmApiViewModel @Inject constructor(private val apiService: ApiService) : ViewModel() {
    val listFilm = MutableLiveData<List<GetFilmResponseItem>>()
    val film : LiveData<List<GetFilmResponseItem>> = listFilm

    val detailFilm = MutableLiveData<GetFilmResponseItem>()
    val dFilm : LiveData<GetFilmResponseItem> = detailFilm

    val id = MutableLiveData<String>()

    fun setId(string: String){
        id.postValue(string)
    }

    init {
        viewModelScope.launch {
            delay(2000)
            listFilm.value = apiService.getFilm()
        }
    }

    fun getDetailFilm(id : String){
        viewModelScope.launch {
            detailFilm.value = apiService.getDetailFilm(id)
        }
    }
}