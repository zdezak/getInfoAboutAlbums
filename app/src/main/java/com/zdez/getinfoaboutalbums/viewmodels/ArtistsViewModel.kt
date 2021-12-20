package com.zdez.getinfoaboutalbums.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zdez.getinfoaboutalbums.BuildConfig
import com.zdez.getinfoaboutalbums.dataclasses.artist.Artist
import com.zdez.getinfoaboutalbums.dataclasses.artist.ListOfArtists
import com.zdez.getinfoaboutalbums.lastfmapi.ApiLastFm
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArtistsViewModel(artistName: String) : ViewModel() {
    private val apiKey = BuildConfig.API_KEY
    var artist by mutableStateOf(listOf<Artist>())
        private set

    init {
        viewModelScope.launch {
            getArtist(artistName = artistName)
        }
    }

    fun getArtist(artistName: String) {
        ApiLastFm.retrofitService.searchArtist(artist = artistName, api_key = apiKey)
            .enqueue(object : Callback<ListOfArtists> {
                override fun onResponse(
                    call: Call<ListOfArtists>,
                    response: Response<ListOfArtists>,
                ) {
                    artist = response.body()?.results?.artists?.artist ?: listOf<Artist>()
                }

                override fun onFailure(call: Call<ListOfArtists>, t: Throwable) {
                    artist = listOf<Artist>(
                        Artist(
                            t.message.toString(),
                            "",
                            "",
                            "",
                            "",
                            listOf()
                        )
                    )
                }
            })
    }
}