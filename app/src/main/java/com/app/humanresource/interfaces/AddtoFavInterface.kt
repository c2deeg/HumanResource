package com.app.humanresource.interfaces

import android.widget.ImageView
import com.app.humanresource.Models.SearchModels.SearchMessage

interface AddtoFavInterface {
    fun onItemClick(position: Int, imgFav: ImageView, data: MutableList<SearchMessage>)

}