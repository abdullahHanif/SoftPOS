package com.abdullah.softpos.base

class BaseEventFilter<out T>(private val data: T) {

    private var eventIsHandled = false

    fun getEventIfNotHandled(): T? {
        return if (!eventIsHandled) {
            eventIsHandled = true
            data
        } else {
            null
        }
    }
}

