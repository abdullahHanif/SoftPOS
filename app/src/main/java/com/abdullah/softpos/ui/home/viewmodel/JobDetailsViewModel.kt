package com.abdullah.softpos.ui.home.viewmodel

import com.abdullah.softpos.base.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class JobDetailsViewModel @Inject constructor() : BaseViewModel(),  CoroutineScope by CoroutineScope(
    Dispatchers.Main) {

}