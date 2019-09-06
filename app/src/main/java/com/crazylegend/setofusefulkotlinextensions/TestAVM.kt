package com.crazylegend.setofusefulkotlinextensions

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.crazylegend.kotlinextensions.coroutines.makeApiCallAsync
import com.crazylegend.kotlinextensions.log.debug
import com.crazylegend.kotlinextensions.retrofit.RetrofitClient
import com.crazylegend.kotlinextensions.retrofit.RetrofitResult
import com.crazylegend.kotlinextensions.retrofit.create


/**
 * Created by hristijan on 8/26/19 to long live and prosper !
 */

/**
 * Template created by Hristijan to live long and prosper.
 */

class TestAVM(application: Application) : AndroidViewModel(application) {


    private val postsData: MutableLiveData<RetrofitResult<List<TestModel>>> = MutableLiveData()
    val posts: LiveData<RetrofitResult<List<TestModel>>> = postsData

    private val retrofit by lazy {
        RetrofitClient.moshiInstanceCoroutines(application, TestApi.API, true).create<TestApi>()
    }


    init {
        makeApiCallAsync({
            retrofit?.getPosts()
        }, {
            it.printStackTrace()
        }, { errorBody, responseCode ->
            debug("ERROR ${errorBody?.string().toString()} $responseCode")
        }, {
                debug(it.toString())
        })
    }
}