package com.iions.done.base

import android.annotation.SuppressLint
import android.location.LocationManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

@SuppressLint("CutPasteId")
abstract class BaseFragment<T : ViewDataBinding> : Fragment() {

    abstract fun layout(): Int

    protected var locationManager: LocationManager? = null

    lateinit var binding: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObservers()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layout(), container, false)

        return binding.root
    }

    abstract fun initObservers()

}