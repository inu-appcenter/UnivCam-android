package com.inuappcenter.univcam_android.fragments


import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.*
import android.widget.Toast
import com.inuappcenter.univcam_android.R
import com.inuappcenter.univcam_android.database.RealmHelper
import com.inuappcenter.univcam_android.dialogs.AlbumDialogFragment
import com.inuappcenter.univcam_android.dialogs.AlbumDialogInterface
import com.inuappcenter.univcam_android.entites.Album
import com.inuappcenter.univcam_android.views.AlbumViews.AlbumViewAdapter
import io.realm.Realm
import io.realm.RealmChangeListener
import kotlinx.android.synthetic.main.fragment_album.*
import java.io.File


class AlbumFragment : BaseFragment(){



    val TAKE_CAMERA = 100
    private val TAG = AlbumFragment::class.java.simpleName
    private lateinit var realm: Realm
    private lateinit var realmHelper: RealmHelper
    private lateinit var realmChangeListener: RealmChangeListener<Realm>


    private val mPopupdef: WindowManager.LayoutParams by lazy {
        WindowManager.LayoutParams()
    }
    private lateinit var mAlbumViewAdapter: AlbumViewAdapter

    private lateinit var mOnClickListener: View.OnClickListener

    companion object {
        fun newInstance(): AlbumFragment {
            return AlbumFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater!!.inflate(R.layout.fragment_album, container, false)
        Log.d(TAG,"on CreateView 실행됨")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)


        recyclerview.let{
            it.layoutManager = GridLayoutManager(activity, 2)
            it.setNestedScrollingEnabled(false)  // TODO : nestedscroll
        }

        Realm.init(activity)
        realm = Realm.getDefaultInstance()

        realmHelper = RealmHelper(realm)
        realmHelper.retrieveFromDB()
        mAlbumViewAdapter = AlbumViewAdapter(activity, realmHelper.justRefresh())
        recyclerview.adapter = mAlbumViewAdapter

        //data change events and refresh


//        realmChangeListener = RealmChangeListener {
//            recyclerview.adapter = AlbumViewAdapter(activity, realmHelper.justRefresh())
//        }
//
//        realm.addChangeListener(realmChangeListener)



    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.menu_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        var id = item?.itemId
        when (id) {
            /**
             * 앨범 생성
             * */
            R.id.create_foler -> {
                val ft = fragmentManager.beginTransaction()

                val createAlbumDialog = AlbumDialogFragment.newInstance(AlbumDialogInterface {
                    val albumName = it

                    val sdcardState = Environment.getExternalStorageState()
                    if (Environment.MEDIA_MOUNTED == sdcardState) {
                        val file = File(activity.getExternalFilesDir(null), albumName)
                        if (!file.exists())
                            file.mkdirs()

                        mAlbumViewAdapter.addAlbum(Album(it, "R.drawable.img_example", 0, false))
                        saveAlbumToRealm(albumName)
                    }
                })
                createAlbumDialog.show(ft, "dialog")
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun  saveAlbumToRealm(albumName: String) {
        if (albumName != null && albumName.length > 0) {
            // TODO: default 이미지
            var album = Album(albumName, "R.drawable.img_example", 0, false)
           realmHelper.saveAlbum(album)
        } else {
            Log.d("AlbumFragment","앨범명 입력 안함")
        }
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == RESULT_OK) {
            if (requestCode == TAKE_CAMERA) {
                Toast.makeText(activity, "사진 완료", Toast.LENGTH_SHORT).show()
            }
        }
    }

}
