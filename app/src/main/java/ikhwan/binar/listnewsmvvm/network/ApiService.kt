package ikhwan.binar.latihandependencyinjection.network


import ikhwan.binar.chapterlima.networkingviewmodel.model.GetNewsResponseItem
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("/news")
    fun getNews() : Call<List<GetNewsResponseItem>>
}