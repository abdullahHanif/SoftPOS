package com.abdullah.softpos.ui.home.viewmodel

import android.graphics.Color
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.abdullah.softpos.base.BaseEventFilter
import com.abdullah.softpos.base.BaseViewModel
import com.abdullah.softpos.data.repository.JobsRepository
import com.abdullah.softpos.network.RequestResult
import com.abdullah.softpos.ui.home.events.JobsNavEvents
import com.abdullah.softpos.ui.home.model.Job
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class JobsViewModel @Inject constructor(
    private val jobsRepository: JobsRepository,
    private val gson: Gson
) : BaseViewModel(),
    CoroutineScope by CoroutineScope(Dispatchers.Main) {

    private var navEvent = MutableLiveData<BaseEventFilter<JobsNavEvents>>()
    val ObNavEvent: LiveData<BaseEventFilter<JobsNavEvents>> = navEvent

    var list: List<Job> = ArrayList()

    fun fetchJobs() {
        showLoader()
        launch {
            val res = jobsRepository.getJobs()
            hideLoader()

            when (res) {
                is RequestResult.Success -> {
                    list = gson.fromJson(res.data.toString(), Array<Job>::class.java).asList()
                    navEvent.value = BaseEventFilter(JobsNavEvents.NotifyAdapter)

                    withContext(Dispatchers.Default) {
                        jobsRepository.storeJobs(list)
                    }
                }
                is RequestResult.Exception -> {
                    Log.d(TAG, res.exception.message)
                }
                is RequestResult.Error -> {
                    Log.d(TAG, res.errorBody.message())
                }

                is RequestResult.Local -> {
                    if ((res.data as List<Job>).isNotEmpty()) {
                        list = res.data
                        navEvent.value = BaseEventFilter(JobsNavEvents.NotifyAdapter)

                    }
                }

            }
        }
    }


    fun getColorForStatus(status: String): Int {
        return when (status.toUpperCase()) {
            "CANCELLED" -> {
                Color.GRAY
            }
            "FULFILLED" -> {
                Color.YELLOW
            }
            "INVOICED" -> {
                Color.BLUE
            }
            "START" -> {
                Color.GREEN
            }
            "ERROR" -> {
                Color.RED
            }
            else -> {
                Color.BLACK
            }
        }
    }

    fun menuItemClick(job: Job) {
        //detail screen trigger of search item
        navEvent.value = BaseEventFilter(JobsNavEvents.JobDetail(job))

    }
}