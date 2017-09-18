package com.inuappcenter.univcam_android.fragments


import android.content.Intent
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.inuappcenter.univcam_android.R
import com.inuappcenter.univcam_android.activities.*
import kotlinx.android.synthetic.main.bottom_bar.*
import kotlinx.android.synthetic.main.content_settings.*
import kotlinx.android.synthetic.main.fragment_album.*


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

        /** 툴바 bold체 */
        val tf: Typeface = Typeface.createFromAsset(context.getAssets(), "nanumbarungothicbold.ttf")
        collapsingToolbarLayout.setCollapsedTitleTypeface(tf)
        collapsingToolbarLayout.setExpandedTitleTypeface(tf)
        collapsingToolbarLayout.setCollapsedTitleTextColor(resources.getColor(R.color.text_primary))
        collapsingToolbarLayout.setExpandedTitleColor(resources.getColor(R.color.text_primary))


        /** 툴바 밑에 line 만들기 */
        appBarLayout.addOnOffsetChangedListener{ appBarLayout, verticalOffset ->
            // 천천히 흐려지기
//            toolbar_line.alpha =  Math.abs(verticalOffset).toFloat() / appBarLayout.getTotalScrollRange()
            if (Math.abs(verticalOffset) == appBarLayout.getTotalScrollRange()) {
                // Collapsed
                toolbar_line.visibility = View.VISIBLE
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                }
//                appBarLayout.setBackgroundResource(R.drawable.univ_stroke)

            } else if (verticalOffset == 0) {
                // Expanded
                toolbar_line.visibility = View.GONE
//                appBarLayout.setBackgroundColor(resources.getColor(R.color.univcam_white))
            } else {
                // Somewhere in between
                toolbar_line.visibility = View.GONE
//                appBarLayout.setBackgroundResource(R.drawable.univ_stroke)
            }

        }

        settings_image.setColorFilter(resources.getColor(R.color.text_primary))
        home_button.setOnClickListener {
            Intent(activity, AlbumActivity::class.java).let{
                startActivity(it)
                activity.overridePendingTransition(0, 0)
            }
        }
        search_button.setOnClickListener {
            Intent(activity, SearchActivity::class.java).let{
                startActivity(it)
                activity.overridePendingTransition(0, 0)
            }
        }

        camera_button.setOnClickListener {
            Intent(activity, AlbumSelectActivity::class.java).let{
                startActivity(it)
                activity.overridePendingTransition(0, 0)
            }
        }

        favorite_button.setOnClickListener {
            Intent(activity, FavoriteActivity::class.java).let{
                startActivity(it)
                activity.overridePendingTransition(0, 0)
            }
        }

        settings_button.setOnClickListener {
            Intent(activity, SettingsActivity::class.java).let{
                startActivity(it)
                activity.overridePendingTransition(0, 0)
                activity.finish()
            }
        }


    }
}
