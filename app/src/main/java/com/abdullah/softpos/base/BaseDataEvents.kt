package com.abdullah.softpos.base

sealed class BaseDataEvents {
    object ShowLoader : BaseDataEvents()
    object HideLoader : BaseDataEvents()
}