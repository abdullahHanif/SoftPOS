package com.abdullah.softpos.ui.home.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.Serializable

@Entity
data class Job(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var __status: String? = "",
    var customer_name: String? = "",
    var distance: String? = "",
    var extras: String? = "",
    var job_city: String? = "",
    var job_date: String? = "",
    var job_latitude: String? = "",
    var job_longitude: String? = "",
    var job_postalcode: Int? = 0,
    var job_street: String? = "",
    var order_duration: Double? = 0.0,
    var order_id: String? = "",
    var order_time: String? = "",
    var payment_method: String? = "",
    var price: String? = "",
    var recurrency: Int? = 0,
    var status: String? = ""
):Serializable

class JobTypeConverter {
    @androidx.room.TypeConverter
    fun stringToJob(json: String): List<Job> {
        val gson = Gson()
        val type = object : TypeToken<List<Job>>() {

        }.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun jobToString(list: List<Job>): String {
        val gson = Gson()
        val type = object : TypeToken<List<Job>>() {

        }.type
        return gson.toJson(list, type)
    }
}