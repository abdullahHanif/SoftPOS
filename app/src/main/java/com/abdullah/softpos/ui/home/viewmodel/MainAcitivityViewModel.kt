package com.abdullah.softpos.ui.home.viewmodel

import com.abdullah.softpos.base.BaseViewModel
import com.abdullah.softpos.di.app.scope.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject


@ApplicationScope
class MainActivityViewModel @Inject constructor() : BaseViewModel(),
    CoroutineScope by CoroutineScope(Dispatchers.Main) {

}