package com.abdullah.softpos.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.abdullah.softpos.extension.exhaustive

abstract class BaseFragment : Fragment(){

    var activityViewModel: BaseViewModel? = null
    val TAG = this::class.java.name

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeComponents()
        setUpListeners()
        setObservers()
    }

    protected inline fun <reified T : BaseViewModel> getMyActivityViewModel(): T {
        activity?.let {
            activityViewModel = ViewModelProviders.of(it).get(T::class.java)
        }

        return activityViewModel as T ?: throw ExceptionInInitializerError("Unable to get viewmodel")
    }

    fun screenTransactions() = (activity as AppCompatActivity)

    fun observeDataEvents(viewModel: BaseViewModel){

        viewModel.obDataEvent.observe(this, Observer {
            it.getEventIfNotHandled()?.let{ event ->
                when (event){
                    BaseDataEvents.ShowLoader -> showLoader()
                    BaseDataEvents.HideLoader -> hideLoader()
                }.exhaustive

            }
        })
    }

    open fun setUpListeners() {}
    abstract fun initializeComponents()
    abstract fun setObservers()

    protected abstract fun showLoader()
    protected abstract fun hideLoader()

}