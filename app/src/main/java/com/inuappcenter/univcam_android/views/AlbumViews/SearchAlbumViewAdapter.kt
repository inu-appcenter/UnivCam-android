package com.inuappcenter.univcam_android.views.AlbumViews

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.support.v7.widget.PopupMenu
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.inuappcenter.univcam_android.R
import com.inuappcenter.univcam_android.activities.AlbumDetailActivity
import com.inuappcenter.univcam_android.database.RealmHelper
import com.inuappcenter.univcam_android.entites.Album
import com.inuappcenter.univcam_android.entites.Picture
import com.inuappcenter.univcam_android.fragments.BaseFragment
import io.realm.Realm
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by ichaeeun on 2017. 7. 29..
 */

class SearchAlbumViewAdapter(var fragment: BaseFragment, var context: Activity, private var items:ArrayList<Album>) : RecyclerView.Adapter<AlbumViewHolder>() {

    private lateinit var realm: Realm
    private lateinit var realmHelper: RealmHelper

    private var arrayList: ArrayList<Album>? = null

    init {
        arrayList = ArrayList<Album>()
        arrayList?.addAll(items)
    }





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return AlbumViewHolder(layoutInflater.inflate(R.layout.album_item, parent, false))
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        Realm.init(context)
        realm = Realm.getDefaultInstance()

        realmHelper = RealmHelper(realm)
        realmHelper.updateAlbumSorted("time")

        val item = items.get(position)
        //TODO: 이미지 가져오는 DB- 처음은 default
        if (realmHelper.getLatestPicturePath(item.albumName) != null) {
            Glide.with(context)
                    .load(File(realmHelper.getLatestPicturePath(item.albumName)))
                    .into(holder.thumbnail)
        } else {
            holder.thumbnail.setImageResource(R.drawable.img_box_174_dp);
        }
        holder.tv_title.setText(item.albumName)
        holder.tv_quantity.setText("${realmHelper.countPictures(item.albumName)}장의 사진")
        holder.is_favorite.setChecked(item.favorite)
        holder.album_menu.setOnClickListener {
            var popupMenu = PopupMenu(context, holder.album_menu)
            popupMenu.inflate(R.menu.menu_album)
            popupMenu.setOnMenuItemClickListener{
                when(it.itemId) {
                    R.id.text_name -> {

                        true
                    }
                    R.id.text_name_reverse -> {

                        true
                    }
                    R.id.text_name -> {

                        true
                    }
                    R.id.time -> {

                        true
                    }
                    R.id.text_time_reverse -> {

                        true
                    }

                    else -> {
                        false
                    }
                }

            }
            popupMenu.show()
        }

        holder.is_favorite.setOnCheckedChangeListener {
            compoundButton, isChecked ->
            realmHelper.updateFavoriteAlbum(item.albumName, isChecked)
        }

        // TODO : 카메라 사진 찍기
        holder.camera_button.setOnClickListener{
            takePicture(position, holder.tv_title.text.toString())
        }

        //TODO: DetailPage
        holder.select_layout.setOnClickListener {
            val intent = Intent(context, AlbumDetailActivity::class.java)
            intent.putExtra("albumName", holder.tv_title.text.toString())
            context.startActivity(intent)
        }


    }

    override fun getItemCount(): Int {
        return items.size
    }

    private fun takePicture(position: Int, albumName: String) {
        val intent = Intent()
        val TAKE_CAMERA = 100

        var date = Date()
        val albumPath = File(context.getExternalFilesDir(null), albumName)

        val fileName = SimpleDateFormat("yyyyMMdd_HH_mm_ssSSS").format(date)
        val category = SimpleDateFormat("yyyy년 MM월 dd일").format(date)
        val yearMonthday = SimpleDateFormat("yyyyMMdd").format(date)
        val filePath = albumPath.toString() + File.separator + fileName + ".jpg"

        val file = File(filePath)
        val outputFileUri = Uri.fromFile(file)

        // TODO: 풀사이즈를 받음
        intent.action = MediaStore.ACTION_IMAGE_CAPTURE
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri)
        context.startActivityForResult(intent, TAKE_CAMERA)
        var picture: Picture = Picture(file.toString(), date, yearMonthday, category)

        realmHelper.savePicture(albumName, picture)
    }


    /**  검색 */
    fun filter(charText: String): Int {
        var searchAlbumName = charText.toLowerCase(Locale.getDefault())
        items.clear()
        if (searchAlbumName.count() == 0) {
            //TODO: 검색 history 보여주기
            arrayList?.let { items.addAll(it) }
            Log.d("name", "isEmpty")
            notifyDataSetChanged()
            return 0
        } else {
            Log.d("name","여기는 됨")
            for (album in this!!.arrayList!!) {
                Log.d("name","albumSize ${items.size}")
                var name: String = album.albumName
                Log.d("name", name)
                if (name.toLowerCase().contains(searchAlbumName)) {
                    items?.add(album)
                }
            }
            notifyDataSetChanged()
            return items.size
        }


    }



}