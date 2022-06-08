package ikhwan.binar.listnewsmvvm.network


import ikhwan.binar.listnewsmvvm.model.GetNewsResponseItem
import ikhwan.binar.listnewsmvvm.model.film.GetFilmResponseItem
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/news")
    suspend fun getNews() : List<GetNewsResponseItem>

    @GET("/film")
    suspend fun getFilm() : List<GetFilmResponseItem>

    @GET("/film/{id}")
    suspend fun getDetailFilm(
        @Path("id") id : String
    ) : GetFilmResponseItem
}