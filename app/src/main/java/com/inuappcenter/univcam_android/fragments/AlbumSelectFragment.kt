package com.inuappcenter.univcam_android.fragments


import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.*

import com.inuappcenter.univcam_android.R
import com.inuappcenter.univcam_android.database.RealmHelper
import com.inuappcenter.univcam_android.views.AlbumViews.AlbumViewAdapter
import io.realm.Realm
import kotlinx.android.synthetic.main.fragment_album.*
import kotlinx.android.synthetic.main.fragment_album_select.*


/**
 * Created by ichaeeun on 2017. 8. 8..
 */

class AlbumSelectFragment : BaseFragment() {

    private lateinit var mAlbumViewAdapter: AlbumViewAdapter
    private lateinit var realm: Realm
    private lateinit var realmHelper: RealmHelper


    companion object {
        fun newInstance(): AlbumSelectFragment {
            return AlbumSelectFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_album_select, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(fragment_album_select_toolbar)

    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.album_select_menu, menu)

        fragment_album_select_recyclerview.let{
            it.layoutManager = GridLayoutManager(activity, 2)
            it.setNestedScrollingEnabled(false)  // TODO : nestedscroll
        }

        Realm.init(activity)
        realm = Realm.getDefaultInstance()

        realmHelper = RealmHelper(realm)
        realmHelper.retrieveFromDB()
        mAlbumViewAdapter = AlbumViewAdapter(activity, realmHelper.justRefresh())
        fragment_album_select_recyclerview.adapter = mAlbumViewAdapter

    }

    fun prepareSelection(view: View, positon: Int) {

    }





}// Required empty public constructor
