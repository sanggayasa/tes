package com.akarinti.sapoe.model.questionnaire
import com.akarinti.sapoe.model.Location
import com.google.gson.annotations.SerializedName

data class ParentDetailClean(
    @SerializedName("id") val id: String? = null,
    @SerializedName("assigned_date") val assignedDate: String? = null,
    @SerializedName("day") val day: String? = null,
    @SerializedName("type") val type: String? = null,
    @SerializedName("status") val status: String? = null,
    @SerializedName("location") val location: Location? = null,
    @SerializedName("itemDetail") val itemDetail: ChildClean? = null,
    @SerializedName("form_list") val question_list: List<Question>? = null
)