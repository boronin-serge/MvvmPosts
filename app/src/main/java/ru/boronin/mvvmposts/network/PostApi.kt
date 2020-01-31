package ru.boronin.mvvmposts.network

import io.reactivex.Observable
import retrofit2.http.GET
import ru.boronin.mvvmposts.model.Post

/**
 * Created by Sergey Boronin on 31.01.2020.
 */
interface PostApi {
    /**
     * Get the list of the pots from the API
     */
    @GET("/posts")
    fun getPosts(): Observable<List<Post>>
}