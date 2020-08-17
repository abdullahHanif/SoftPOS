package com.abdullah.softpos.ui.home.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.abdullah.softpos.di.app.scope.ApplicationScope
import com.abdullah.softpos.ui.home.viewmodel.JobDetailsViewModel
import javax.inject.Inject

@ApplicationScope
class JobDetailsViewModelFactory @Inject constructor(val viewModel: JobDetailsViewModel) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass != JobDetailsViewModel::class.java)
            throw IllegalArgumentException("Unknown View model class")
        else
            return viewModel as T
    }
}