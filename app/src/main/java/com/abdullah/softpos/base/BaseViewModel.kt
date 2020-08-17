package com.abdullah.softpos.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel(){
    val TAG = this::class.java.name

    protected val _dataEvent: MutableLiveData<BaseEventFilter<BaseDataEvents>> = MutableLiveData()
    val obDataEvent: LiveData<BaseEventFilter<BaseDataEvents>> = _dataEvent


    protected fun showLoader() {
        _dataEvent.value = BaseEventFilter(BaseDataEvents.ShowLoader)
    }

    protected fun hideLoader() {
        _dataEvent.value = BaseEventFilter(BaseDataEvents.HideLoader)
    }

}