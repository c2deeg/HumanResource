package com.app.humanresource.interfaces

import android.widget.ImageView
import com.app.humanresource.Models.GetFavjobsModel.GetFavjobsDatum

interface Popularjobsclickinterfaces {
    fun onItemClick(postion: Int, imgFav: ImageView)
}