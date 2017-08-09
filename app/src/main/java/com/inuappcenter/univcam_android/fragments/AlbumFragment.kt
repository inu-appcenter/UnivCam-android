package com.inuappcenter.univcam_android.fragments


import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.*
import com.inuappcenter.univcam_android.R
import com.inuappcenter.univcam_android.dialogs.AlbumDialogFragment
import com.inuappcenter.univcam_android.dialogs.AlbumDialogInterface
import com.inuappcenter.univcam_android.entites.Album
import com.inuappcenter.univcam_android.views.AlbumViews.AlbumViewAdapter
import kotlinx.android.synthetic.main.fragment_album.*


/**
 * Created by ichaeeun on 2017. 8. 6..
 */

class AlbumFragment : BaseFragment(){

    val TAG = AlbumFragment::class.java.simpleName

    private val mPopupdef: WindowManager.LayoutParams by lazy {
        WindowManager.LayoutParams()
    }
    private val mAlbumViewAdapter: AlbumViewAdapter by lazy {
        AlbumViewAdapter(activity)
    }

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
            it.adapter = mAlbumViewAdapter
            it.setNestedScrollingEnabled(false)  // TODO : nestedscroll
            Log.d("AlbumFragment", "실행됨 ㅠ")
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.menu_main, menu)
        Log.d("OnCreateOptionMenu","menu실행됨")
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        var id = item?.itemId
        when (id) {
            R.id.create_foler -> {
                val ft = fragmentManager.beginTransaction()

                val createAlbumDialog = AlbumDialogFragment.newInstance(AlbumDialogInterface {
                    mAlbumViewAdapter.addAlbum(Album(false, it, R.drawable.img_example, 0))
                })
                createAlbumDialog.show(ft, "dialog")
            }
        }
        return super.onOptionsItemSelected(item)
    }


}
