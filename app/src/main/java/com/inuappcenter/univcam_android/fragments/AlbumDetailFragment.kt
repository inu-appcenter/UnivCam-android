package com.inuappcenter.univcam_android.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.inuappcenter.univcam_android.R
import com.inuappcenter.univcam_android.database.RealmHelper
import com.inuappcenter.univcam_android.views.AlbumDetailViews.AlbumDetailViewAdapter
import io.realm.Realm
import kotlinx.android.synthetic.main.fragment_album.*


/**
 * A simple [Fragment] subclass.
 */
class AlbumDetailFragment : Fragment() {


    private lateinit var mAlbumViewAdapter: AlbumDetailViewAdapter
    private lateinit var realm: Realm
    private lateinit var realmHelper: RealmHelper
    private lateinit var albumName: String


    companion object {
        fun newInstance(): AlbumDetailFragment {
            return AlbumDetailFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        albumName = activity.intent.extras!!.getString("albumName")
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater!!.inflate(R.layout.fragment_album_detail, container, false)

    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(fragment_album_toolbar)


        recyclerview.let{
            var linerlayoutManager = LinearLayoutManager(activity)
            linerlayoutManager.orientation = LinearLayoutManager.VERTICAL
            it.layoutManager =linerlayoutManager
            it.setNestedScrollingEnabled(false)  // TODO : nestedscroll
        }

        Realm.init(activity)
        realm = Realm.getDefaultInstance()

        realmHelper = RealmHelper(realm)
        realmHelper.retrieveFromDB()
        mAlbumViewAdapter = AlbumDetailViewAdapter(this, activity, realmHelper.retrieveAlbumDetail(albumName))
        recyclerview.adapter = mAlbumViewAdapter
    }

}
