package com.abdullah.softpos.util

import java.text.SimpleDateFormat

val SERVER_FORMAT = "yyyy-MM-dd'T'HH:mm:ss"
val DISPLAY_FORMAT = "MMM dd, yyyy"


fun fromatDateFromServer(time: String?): String {

    time?.let {
        val inputDateFormat = SimpleDateFormat(SERVER_FORMAT)
        val outputDateFormat = SimpleDateFormat(DISPLAY_FORMAT)

        return try {
            outputDateFormat.format(inputDateFormat.parse(time))
        } catch (e: Exception) {
            return time
        }
    } ?: return "-"
}