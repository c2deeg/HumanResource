package com.app.humanresource.interfaces

import android.widget.ImageView
import com.app.humanresource.Models.GetFavjobsModel.GetFavjobsDatum

interface RemoveFromFav {
    fun onItemClick2(postion: MutableList<GetFavjobsDatum>, imgFav: Int, imgRemovefav: ImageView)

}