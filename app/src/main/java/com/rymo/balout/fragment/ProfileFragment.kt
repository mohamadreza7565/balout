package com.rymo.balout.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rymo.balout.R
import com.rymo.balout.fragment.dialog.AboutMeDialogFragment
import com.rymo.balout.utils.AppConfig
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment(){

    var github = "https://github.com/mohamadreza7565"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_profile, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_git.setOnClickListener {
            AppConfig().openBrowser(requireContext(),github)
        }

        btn_about_me.setOnClickListener {
            AboutMeDialogFragment().show(childFragmentManager,"TAG")
        }

    }

}