package com.niraj.qfonapp.app.fragment.clip_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.niraj.qfonapp.databinding.ClipFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ClipFragment: Fragment() {

    private var _binding: ClipFragmentBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ClipFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}