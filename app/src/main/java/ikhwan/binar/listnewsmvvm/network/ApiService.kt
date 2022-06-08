package ikhwan.binar.latihandependencyinjection.network


import ikhwan.binar.chapterlima.networkingviewmodel.model.GetNewsResponseItem
import ikhwan.binar.listnewsmvvm.model.film.GetFilmResponseItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/news")
    suspend fun getNews() : List<GetNewsResponseItem>

    @GET("/news/{id}")
    suspend fun getDetail(
        @Path("id") id : String
    ) : GetNewsResponseItem

    @GET("/film")
    suspend fun getFilm() : List<GetFilmResponseItem>

    @GET("/film/{id}")
    suspend fun getDetailFilm(
        @Path("id") id : String
    ) : GetFilmResponseItem
}