package ikhwan.binar.listnewsmvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ikhwan.binar.chapterlima.networkingviewmodel.model.GetNewsResponseItem
import ikhwan.binar.latihandependencyinjection.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsApiViewModel : ViewModel() {

    val listNews = MutableLiveData<List<GetNewsResponseItem>?>()

    fun getAllNews(){
        ApiClient.instance.getNews()
            .enqueue(object : Callback<List<GetNewsResponseItem>>{
                override fun onResponse(
                    call: Call<List<GetNewsResponseItem>>,
                    response: Response<List<GetNewsResponseItem>>
                ) {
                    if (response.isSuccessful){
                        listNews.postValue(response.body())
                    }else{
                        listNews.postValue(null)
                    }
                }

                override fun onFailure(call: Call<List<GetNewsResponseItem>>, t: Throwable) {
                    listNews.postValue(null)
                }

            })
    }
}