package fr.isen.osirisnft.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class CommentData(
    @SerializedName("id") var _id: String,
    @SerializedName("user") val user: String,
    @SerializedName("date") val publication_date: String,
    @SerializedName("content") val content: String,
    @SerializedName("likes") val likes_count: Int,
    @SerializedName("replies") val replies: ArrayList<ReplyData>
): Serializable {}