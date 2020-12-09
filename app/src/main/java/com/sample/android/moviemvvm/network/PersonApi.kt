package com.sample.android.moviemvvm.network

import com.sample.android.moviemvvm.domain.Person
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface PersonApi {

    @GET("3/person/{personId}")
    fun person(@Path("personId") personId: Any): Observable<Person>
}