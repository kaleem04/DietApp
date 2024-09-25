package com.example.dietapp.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Diet(
    @StringRes val dayResourceId : Int,
   @StringRes  val nameResourceId : Int,
   @StringRes  val descriptionResourceId : Int,
   @DrawableRes val imageResourceId : Int

)
