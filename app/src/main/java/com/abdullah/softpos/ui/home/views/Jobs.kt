package com.abdullah.softpos.ui.home.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.abdullah.softpos.R
import com.abdullah.softpos.base.BaseFragment
import com.abdullah.softpos.databinding.FrJobsBinding
import com.abdullah.softpos.di.app.component.AppComponent
import com.abdullah.softpos.di.factory.ComponentFactory
import com.abdullah.softpos.extension.addFragment
import com.abdullah.softpos.ui.home.adapters.JobsAdapter
import com.abdullah.softpos.ui.home.events.JobsNavEvents
import com.abdullah.softpos.ui.home.factory.JobsViewModelFactory
import com.abdullah.softpos.ui.home.viewmodel.JobsViewModel
import kotlinx.android.synthetic.main.fr_jobs.*
import javax.inject.Inject

class Jobs : BaseFragment() {
    lateinit var adapter: JobsAdapter
    @Inject
    lateinit var viewModelFactory: JobsViewModelFactory

    private val viewModel: JobsViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)
            .get(JobsViewModel::class.java)
    }

    init {
        ComponentFactory.createComponent(AppComponent::class).inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FrJobsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fr_jobs, container, false)
        return binding.root
    }

    override fun initializeComponents() {
        viewModel.fetchJobs()
        setupAdapter()
    }

    override fun setObservers() {
        observeDataEvents(viewModel)

        viewModel.ObNavEvent.observe(this, Observer {
            it.getEventIfNotHandled()?.let { event ->
                when (event) {
                    is JobsNavEvents.NotifyAdapter -> {
                        adapter?.let {
                            adapter.notifyDataSetChanged()
                            //no data found
                            if (viewModel.list.isNullOrEmpty()) {
                                NoDataMessage.visibility = View.VISIBLE
                            } else {
                                NoDataMessage.visibility = View.GONE
                            }
                        }
                    }
                    is JobsNavEvents.JobDetail -> {
                        screenTransactions().addFragment(
                            JobDetails.newInstance(event.job),
                            R.id.container,
                            true
                        )
                    }

                }
            }
        })
    }

    override fun showLoader() {
        root_view.showLoader()
    }

    override fun hideLoader() {
        root_view.hideLoader()
    }

    fun setupAdapter() {
        adapter = JobsAdapter(viewModel)
        rv_jobs_list.adapter = adapter
        rv_jobs_list.layoutManager = LinearLayoutManager(context)
    }

    companion object {
        fun newInstance(): Jobs {
            val fragment = Jobs()
            return fragment
        }
    }

}