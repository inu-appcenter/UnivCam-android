package com.inuappcenter.univcam_android.fragments


import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import android.widget.CheckBox
import com.inuappcenter.univcam_android.R
import com.inuappcenter.univcam_android.database.RealmHelper
import com.inuappcenter.univcam_android.dialogs.AlbumDialogFragment
import com.inuappcenter.univcam_android.entites.Album
import com.inuappcenter.univcam_android.entites.AlbumDetail
import com.inuappcenter.univcam_android.views.AlbumDetailViews.AlbumDetailViewAdapter
import io.realm.Realm
import kotlinx.android.synthetic.main.fragment_album_detail.*
import java.io.File
import java.util.*


/**
 * A simple [Fragment] subclass.
 */
class AlbumDetailFragment : Fragment(){



    lateinit var mAlbumViewAdapter: AlbumDetailViewAdapter
    private lateinit var realm: Realm
    private lateinit var realmHelper: RealmHelper
    private lateinit var albumName: String
    var isInActionMode: Boolean = false
    lateinit var originalList: ArrayList<AlbumDetail>
    var selectionList: ArrayList<AlbumDetail>? = null
    var counter: Int = 0



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
        (activity as AppCompatActivity).setSupportActionBar(fragment_album_detail_toolbar)
        (activity as AppCompatActivity).getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        fragment_album_detail_toolbar.setNavigationIcon(R.drawable.ic_back_24_dp)
        fragment_album_detail_toolbar.setNavigationOnClickListener {
            activity.finish()
        }
        fragment_album_detail_toolbar.title = albumName


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
        originalList = realmHelper.retrieveAlbumDetail(albumName)
        mAlbumViewAdapter = AlbumDetailViewAdapter(this, activity, originalList)
        recyclerview.adapter = mAlbumViewAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_detail_album, menu)
        super.onCreateOptionsMenu(menu, inflater)

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        var id = item?.itemId
        when (id) {
        /**
         * 앨범 생성
         * */
            R.id.gallery -> {
                var intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
                intent.addCategory(Intent.CATEGORY_OPENABLE)
                intent.setType("image/*");
                startActivityForResult(intent, 1030)
                }
        }
        return super.onOptionsItemSelected(item)




    }

//    override fun onLongClick(p0: View?): Boolean {
//        fragment_album_detail_toolbar.menu.clear()
//        fragment_album_detail_toolbar.inflateMenu(R.menu.menu_detail_album_action_mode)
//        fragment_album_detail_toolbar.title
//        isInActionMode = true
//        mAlbumViewAdapter.notifyDataSetChanged()
//        (activity as AppCompatActivity).getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
//        fragment_album_detail_toolbar.setNavigationIcon(R.drawable.ic_back_24_dp)
//        fragment_album_detail_toolbar.setNavigationOnClickListener {
//            activity.finish()
//        }
//        return true
//    }

    fun prepareSelection(view: View, position: Int) {
        if((view as CheckBox).isChecked) {
            selectionList?.add(originalList.get(position))
            counter = counter + 1
            updateCounter(counter)

        } else {
            selectionList?.remove(originalList.get(position))
            counter = counter - 1
            updateCounter(counter)
        }
    }

    fun updateCounter(counter: Int) {
        if (counter == 0) {
            fragment_album_detail_toolbar.title = "0개의 사진 선택"
        } else {
            fragment_album_detail_toolbar.title = "${counter}개의 사진 선택"
        }
    }

}
