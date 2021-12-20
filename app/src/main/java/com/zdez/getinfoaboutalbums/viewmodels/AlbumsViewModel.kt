package com.zdez.getinfoaboutalbums.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zdez.getinfoaboutalbums.BuildConfig
import com.zdez.getinfoaboutalbums.dataclasses.album.Album
import com.zdez.getinfoaboutalbums.dataclasses.album.ListOfAlbums
import com.zdez.getinfoaboutalbums.lastfmapi.ApiLastFm
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AlbumsViewModel: ViewModel() {
    private val apiKey = BuildConfig.API_KEY
    private val _albums = MutableLiveData<List<Album>>()
    val albums: LiveData<List<Album>>
        get() = _albums

    fun getAlbums(artist: String, artistId: String) {
        ApiLastFm.retrofitService.searchAlbums(artist = artist, mbid = artistId, api_key = apiKey)
            .enqueue(object : Callback<ListOfAlbums> {
                override fun onResponse(
                    call: Call<ListOfAlbums>,
                    response: Response<ListOfAlbums>,
                ) {
                    _albums.value = response.body()?.topAlbums?.albums
                }

                override fun onFailure(call: Call<ListOfAlbums>, t: Throwable) {
                    _albums.value = listOf()
                    Log.i("albumsFail", t.message.toString())
                }
            })
    }
}