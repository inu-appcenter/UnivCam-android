package com.inuappcenter.univcam_android.fragments


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.inuappcenter.univcam_android.R
import com.inuappcenter.univcam_android.activities.AlbumSelectActivity
import com.inuappcenter.univcam_android.activities.FavoriteActivity
import com.inuappcenter.univcam_android.activities.SearchActivity
import com.inuappcenter.univcam_android.activities.SettingsActivity
import kotlinx.android.synthetic.main.bottom_bar.*


/**
 * A simple [Fragment] subclass.
 */
class SettingsFragment : Fragment() {

    companion object {
        fun newInstance(): SettingsFragment {
            return SettingsFragment()
        }
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        search_button.setOnClickListener {
            Intent(activity, SearchActivity::class.java).let{
                startActivity(it)
            }
        }

        camera_button.setOnClickListener {
            Intent(activity, AlbumSelectActivity::class.java).let{
                startActivity(it)
            }
        }

        favorite_button.setOnClickListener {
            Intent(activity, FavoriteActivity::class.java).let{
                startActivity(it)
            }
        }

        settings_button.setOnClickListener {
            Intent(activity, SettingsActivity::class.java).let{
                startActivity(it)
            }
        }
    }
}
