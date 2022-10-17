package com.example.imoondshop.untils

import com.google.android.material.progressindicator.CircularProgressIndicator

fun CircularProgressIndicator.showProgress(isShow:Boolean) {
    if (isShow) {
        this.show()
    } else this.hide()
}
typealias TryAgainAction = () -> Unit
interface ProductClickListener {
    fun onClick(position: Int)
}