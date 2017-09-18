package com.inuappcenter.univcam_android.fragments


import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.PorterDuff
import android.graphics.Typeface
import android.net.Uri
import android.os.Build
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

        recyclerview.let{
            it.layoutManager = GridLayoutManager(activity, 2)
            it.setNestedScrollingEnabled(false)  // TODO : nestedscroll
        }

        Realm.init(activity)
        realm = Realm.getDefaultInstance()

        realmHelper = RealmHelper(realm)

        var sharedPreferences: SharedPreferences = context.getSharedPreferences("favorite_sort", 0)
        var sort = sharedPreferences.getString("favorite_sort", "name_reverse")

        realmHelper.updateAlbumSorted(sort)
        mAlbumViewAdapter = FavoriteViewAdapter(activity, realmHelper.retrieveFavoriteAlbums())
        recyclerview.adapter = mAlbumViewAdapter


        favorite_image.setColorFilter(resources.getColor(R.color.text_primary))

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
                activity.finish()

            }
        }

        settings_button.setOnClickListener {
            Intent(activity, SettingsActivity::class.java).let{
                startActivity(it)
                activity.overridePendingTransition(0, 0)
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

        inflater?.inflate(R.menu.favorite_main, menu)

        var sortDrawable = menu?.findItem(R.id.sort)?.icon
        sortDrawable?.setColorFilter(resources.getColor(R.color.text_secondary), PorterDuff.Mode.SRC_ATOP)

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        var id = item?.itemId
        when (id) {
            R.id.text_name -> {
                var sharedPreferences: SharedPreferences = context.getSharedPreferences("favorite_sort", 0)
                var editor:SharedPreferences.Editor = sharedPreferences.edit()
                editor.putString("favorite_sort", "name")
                editor.commit()
                realmHelper.updateAlbumSorted("name")
                mAlbumViewAdapter.notifyDataSetChanged()
                mAlbumViewAdapter.update(realmHelper.albums)

            }

            R.id.text_name_reverse -> {
                var sharedPreferences: SharedPreferences = context.getSharedPreferences("favorite_sort", 0)
                var editor:SharedPreferences.Editor = sharedPreferences.edit()
                editor.putString("favorite_sort", "name_reverse")
                editor.commit()
                realmHelper.updateAlbumSorted("name_reverse")
                mAlbumViewAdapter.notifyDataSetChanged()
                mAlbumViewAdapter.update(realmHelper.albums)

            }

            R.id.text_time -> {
                var sharedPreferences: SharedPreferences = context.getSharedPreferences("favorite_sort", 0)
                var editor:SharedPreferences.Editor = sharedPreferences.edit()
                editor.putString("favorite_sort", "time")
                editor.commit()
                realmHelper.updateAlbumSorted("time")
                mAlbumViewAdapter.update(realmHelper.albums)
                mAlbumViewAdapter.notifyDataSetChanged()

            }

            R.id.text_time_reverse -> {
                var sharedPreferences: SharedPreferences = context.getSharedPreferences("favorite_sort", 0)
                var editor:SharedPreferences.Editor = sharedPreferences.edit()
                editor.putString("favorite_sort", "time_reverse")
                editor.commit()
                realmHelper.updateAlbumSorted("time_reverse")
                mAlbumViewAdapter.notifyDataSetChanged()
                mAlbumViewAdapter.update(realmHelper.albums)

            }


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
