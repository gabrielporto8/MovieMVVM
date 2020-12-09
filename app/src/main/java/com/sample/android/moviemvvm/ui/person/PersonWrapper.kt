package com.sample.android.moviemvvm.ui.person

import android.os.Parcelable
import com.sample.android.moviemvvm.domain.Credit
import kotlinx.android.parcel.Parcelize

@Parcelize
class PersonWrapper(
        val credit : Credit,
        val backdropPath: String?) : Parcelable