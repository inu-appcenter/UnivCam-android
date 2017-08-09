package com.inuappcenter.univcam_android.entites

/**
 * Created by ichaeeun on 2017. 7. 29..
 */
data class Album(var isFavorite: Boolean, var title: String?, var thumbnail: Int, var quantity: Int)
// TODO : title null이면 안됨. 일단 이렇게 해놓고 수정
