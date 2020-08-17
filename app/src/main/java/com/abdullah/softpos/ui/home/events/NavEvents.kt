package com.abdullah.softpos.ui.home.events

import com.abdullah.softpos.ui.home.model.Job

sealed class JobsNavEvents {
    class JobDetail(val job: Job) : JobsNavEvents()

    object NotifyAdapter : JobsNavEvents()
}