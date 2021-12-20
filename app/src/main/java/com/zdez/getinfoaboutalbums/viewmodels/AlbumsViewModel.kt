package com.zdez.getinfoaboutalbums.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.zdez.getinfoaboutalbums.BuildConfig
import com.zdez.getinfoaboutalbums.dataclasses.album.Album
import com.zdez.getinfoaboutalbums.dataclasses.album.ListOfAlbums
import com.zdez.getinfoaboutalbums.lastfmapi.ApiLastFm
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AlbumsViewModel(artist: String, artistId: String) : ViewModel() {
    private val apiKey = BuildConfig.API_KEY
    var albums by mutableStateOf(listOf<Album>())
        private set

    init {
        getAlbums(artist = artist, artistId = artistId)
    }
    private fun getAlbums(artist: String, artistId: String) {
        ApiLastFm.retrofitService.searchAlbums(artist = artist, mbid = artistId, api_key = apiKey)
            .enqueue(object : Callback<ListOfAlbums> {
                override fun onResponse(
                    call: Call<ListOfAlbums>,
                    response: Response<ListOfAlbums>,
                ) {
                    albums = response.body()?.topAlbums?.albums ?: listOf()
                }

                override fun onFailure(call: Call<ListOfAlbums>, t: Throwable) {
                    albums = listOf()
                    Log.i("albumsFail", t.message.toString())
                }
            })
    }
}