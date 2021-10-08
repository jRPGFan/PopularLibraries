package com.example.popularlibraries.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubUserRepository (
    @Expose val id: String? = null,
    @Expose val name: String? = null,
    @Expose val forks: Int? = null
) : Parcelable