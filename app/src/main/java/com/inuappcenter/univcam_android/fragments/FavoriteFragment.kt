package com.inuappcenter.univcam_android.fragments


import android.app.Activity
import android.content.Intent
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.*
import android.widget.Toast
import com.inuappcenter.univcam_android.R
import com.inuappcenter.univcam_android.activities.*
import com.inuappcenter.univcam_android.database.RealmHelper
import com.inuappcenter.univcam_android.views.AlbumViews.FavoriteViewAdapter
import io.realm.Realm
import kotlinx.android.synthetic.main.bottom_bar.*
import kotlinx.android.synthetic.main.fragment_album.*
import java.io.File
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by ichaeeun on 2017. 8. 8..
 */

class FavoriteFragment : BaseFragment() {

    val TAKE_CAMERA = 100

    private lateinit var mAlbumViewAdapter: FavoriteViewAdapter
    private lateinit var realm: Realm
    private lateinit var realmHelper: RealmHelper


    companion object {
        fun newInstance(): FavoriteFragment {
            return FavoriteFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater!!.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(fragment_album_toolbar)

        val tf: Typeface = Typeface.createFromAsset(context.getAssets(), "nanumbarungothicbold.ttf")
        collapsingToolbarLayout.setCollapsedTitleTypeface(tf)
        collapsingToolbarLayout.setExpandedTitleTypeface(tf)
        collapsingToolbarLayout.setCollapsedTitleTextColor(resources.getColor(R.color.text_primary))
        collapsingToolbarLayout.setExpandedTitleColor(resources.getColor(R.color.text_primary))


        recyclerview.let{
            it.layoutManager = GridLayoutManager(activity, 2)
            it.setNestedScrollingEnabled(false)  // TODO : nestedscroll
        }

        Realm.init(activity)
        realm = Realm.getDefaultInstance()

        realmHelper = RealmHelper(realm)
        realmHelper.retrieveFromDB()
        mAlbumViewAdapter = FavoriteViewAdapter(activity, realmHelper.retrieveFavoriteAlbums())
        recyclerview.adapter = mAlbumViewAdapter


        favorite_image.setColorFilter(resources.getColor(R.color.text_primary))

        home_button.setOnClickListener {
            Intent(activity, AlbumActivity::class.java).let{
                startActivity(it)
                activity.overridePendingTransition(0, 0)
                activity.finish()
            }
        }
        search_button.setOnClickListener {
            Intent(activity, SearchActivity::class.java).let{
                startActivity(it)
                activity.overridePendingTransition(0, 0)
                activity.finish()
            }
        }

        camera_button.setOnClickListener {
            Intent(activity, AlbumSelectActivity::class.java).let{
                startActivity(it)
                activity.overridePendingTransition(0, 0)
                activity.finish()
            }
        }

        favorite_button.setOnClickListener {
            Intent(activity, FavoriteActivity::class.java).let{
                startActivity(it)
                activity.overridePendingTransition(0, 0)
                activity.finish()
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


    private fun takePicture(albumName: String) {
        val intent = Intent()
        val TAKE_CAMERA = 100

        val albumPath = File(context.getExternalFilesDir(null),albumName)
        val fileName = SimpleDateFormat("yyyyMMdd_HH_mm_ssSSS").format(Date())
        val filePath = albumPath.toString() + File.separator + fileName + ".jpg"

        val file = File(filePath)
        val outputFileUri = Uri.fromFile(file)

        intent.action = MediaStore.ACTION_IMAGE_CAPTURE
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri)
        activity.startActivityForResult(intent, TAKE_CAMERA)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.favorite_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        var id = item?.itemId
        when (id) {
        /**
         * 앨범 생성
         * */

        }
        return super.onOptionsItemSelected(item)
    }




    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == TAKE_CAMERA) {
                Toast.makeText(activity, "사진 완료", Toast.LENGTH_SHORT).show()
            }
        }
    }




}
