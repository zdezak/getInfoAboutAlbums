package com.zdez.getinfoaboutalbums.dataclasses

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.zdez.getinfoaboutalbums.BuildConfig
import com.zdez.getinfoaboutalbums.dataclasses.album.Album
import com.zdez.getinfoaboutalbums.dataclasses.album.ListOfAlbums
import com.zdez.getinfoaboutalbums.dataclasses.artist.Artist
import com.zdez.getinfoaboutalbums.dataclasses.artist.ListOfArtists
import com.zdez.getinfoaboutalbums.lastfmapi.ApiLastFm
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Data {
    private val apiKey = BuildConfig.API_KEY
    private val _artist = MutableLiveData<List<Artist>>()
    val artist: LiveData<List<Artist>>
        get() = _artist

    fun getArtist(artist: String) {
        ApiLastFm.retrofitService.searchArtist(artist = artist, api_key = apiKey)
            .enqueue(object : Callback<ListOfArtists> {
                override fun onResponse(
                    call: Call<ListOfArtists>,
                    response: Response<ListOfArtists>,
                ) {
                    _artist.value = response.body()?.results?.artists?.artist
                }

                override fun onFailure(call: Call<ListOfArtists>, t: Throwable) {
                    _artist.value = listOf<Artist>()
                    Log.i("artistFail", t.message.toString())
                }
            })
    }

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