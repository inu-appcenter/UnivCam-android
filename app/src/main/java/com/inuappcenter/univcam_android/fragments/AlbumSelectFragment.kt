package com.inuappcenter.univcam_android.fragments


import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.*
import android.widget.CheckBox

import com.inuappcenter.univcam_android.R
import com.inuappcenter.univcam_android.database.RealmHelper
import com.inuappcenter.univcam_android.entites.Album
import com.inuappcenter.univcam_android.views.AlbumViews.AlbumViewAdapter
import com.inuappcenter.univcam_android.views.AlbumViews.AlbumViewSelectAdapter
import io.realm.Realm
import kotlinx.android.synthetic.main.fragment_album.*
import kotlinx.android.synthetic.main.fragment_album_select.*


/**
 * Created by ichaeeun on 2017. 8. 8..
 */

class AlbumSelectFragment : BaseFragment() {

    private lateinit var mAlbumViewSelectAdapter: AlbumViewSelectAdapter
    private lateinit var realm: Realm
    private lateinit var realmHelper: RealmHelper
    private lateinit var initialList: ArrayList<Album>
    private lateinit var sectionList: ArrayList<Album>


    companion object {
        fun newInstance(): AlbumSelectFragment {
            return AlbumSelectFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater!!.inflate(R.layout.fragment_album_select, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(fragment_album_select_toolbar)

        fragment_album_select_recyclerview.let{
            it.layoutManager = GridLayoutManager(activity, 2)
            it.setNestedScrollingEnabled(false)  // TODO : nestedscroll
        }

        Realm.init(activity)
        realm = Realm.getDefaultInstance()

        realmHelper = RealmHelper(realm)
        realmHelper.retrieveFromDB()
        initialList = realmHelper.justRefresh()
        mAlbumViewSelectAdapter = AlbumViewSelectAdapter(this, initialList)
        fragment_album_select_recyclerview.adapter = mAlbumViewSelectAdapter

    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.album_select_menu, menu)



    }

    fun prepareSelection(view: View, positon: Int) {
        if((view as CheckBox).isChecked) {
            sectionList.add(initialList[positon])
        } else {
            sectionList.remove(initialList[positon])
        }
        //TODO: 사진 저장
    }





}// Required empty public constructor
