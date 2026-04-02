package com.kiko.kareerai.data.remote.jobs

import com.google.gson.annotations.SerializedName

data class JobResponse(

    @SerializedName("jobs")
    val jobs: List<JobItem>
)

data class JobItem(

    @SerializedName("title")
    val title: String,

    @SerializedName("company")
    val company: String,

    @SerializedName("location")
    val location: String,

    @SerializedName("description")
    val description: String? = null,

    @SerializedName("url")
    val url: String,

    @SerializedName("remote")
    val isRemote: Boolean? = false,

    @SerializedName("date_posted")
    val datePosted: String? = null
)