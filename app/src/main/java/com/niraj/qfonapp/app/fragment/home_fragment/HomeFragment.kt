package com.niraj.qfonapp.app.fragment.home_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.snackbar.Snackbar
import com.niraj.qfonapp.R
import com.niraj.qfonapp.app.adapter.PackageAdapter
import com.niraj.qfonapp.databinding.HomeFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment: Fragment() {

    private val homeFragmentViewModel: HomeFragmentViewModel by viewModels()
    private var _binding: HomeFragmentBinding ?= null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
       return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeViewModel()
        loadData()
    }

    private fun loadData() {
        homeFragmentViewModel.packageAdapter = PackageAdapter(ArrayList(), getGlide())
        binding.packageRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        binding.packageRecyclerview.adapter = homeFragmentViewModel.packageAdapter
    }

    private fun subscribeViewModel(){
        homeFragmentViewModel.error.observe(viewLifecycleOwner) {
            it?.let {
                Snackbar.make(binding.root, it, Snackbar.LENGTH_SHORT).show()
            }
        }

        homeFragmentViewModel.packageListModel.observe(viewLifecycleOwner){
            it?.let { packageList ->
                homeFragmentViewModel.packageAdapter?.setPackageList(packageList)
            }
        }
    }

    private fun getGlide(): RequestManager {
        val requestOptions = RequestOptions().placeholder(R.color.purple_200).error(R.drawable.photo)
        return Glide.with(this).setDefaultRequestOptions(requestOptions)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}