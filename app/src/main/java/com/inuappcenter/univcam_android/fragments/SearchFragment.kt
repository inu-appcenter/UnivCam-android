package com.inuappcenter.univcam_android.fragments


import android.content.Context
import android.content.Intent
import android.graphics.PorterDuff
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.support.v4.content.ContextCompat
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.TypedValue
import android.view.*
import android.view.inputmethod.InputMethodManager
import com.inuappcenter.univcam_android.R
import com.inuappcenter.univcam_android.activities.*
import com.inuappcenter.univcam_android.database.RealmHelper
import com.inuappcenter.univcam_android.views.AlbumViews.SearchAlbumViewAdapter
import io.realm.Realm
import kotlinx.android.synthetic.main.bottom_bar.*
import kotlinx.android.synthetic.main.fragment_search.*


/**
 * Created by ichaeeun on 2017. 8. 8..
 */

class SearchFragment : BaseFragment() {

    private lateinit var realm: Realm
    private lateinit var realmHelper: RealmHelper
    private lateinit var mAlbumViewAdapter: SearchAlbumViewAdapter
    private lateinit var inputMethodManager: InputMethodManager


    companion object {
        fun newInstance(): SearchFragment {
            return SearchFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        inputMethodManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager



        search_image.setColorFilter(resources.getColor(R.color.text_primary))


        /** 툴바 밑에 line 만들기 */
        appBarLayout.addOnOffsetChangedListener{ appBarLayout, verticalOffset ->
            // 천천히 흐려지기
//            toolbar_line.alpha =  Math.abs(verticalOffset).toFloat() / appBarLayout.getTotalScrollRange()

//            Log.d("AbBC",(Math.abs(verticalOffset).toFloat() / appBarLayout.getTotalScrollRange()).toString())

            var a = Math.abs(verticalOffset).toFloat() / appBarLayout.getTotalScrollRange() + 1
            edit_search.setTextSize(TypedValue.COMPLEX_UNIT_DIP,32F*(1/a))
            Log.d("AbBC", (1/a).toString())

//            edit_search.textSize =  appBarLayout.getTotalScrollRange() / Math.abs(verticalOffset).toFloat()
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

        reset_button.setOnClickListener {
            edit_search.text = null
            hideKeyboard()

        }

        recyclerview.let{
            it.layoutManager = GridLayoutManager(activity, 2) as RecyclerView.LayoutManager?
            it.setNestedScrollingEnabled(false)  // TODO : nestedscroll
        }




        Realm.init(activity)
        realm = Realm.getDefaultInstance()

        realmHelper = RealmHelper(realm)
        realmHelper.updateAlbumSorted("time")
        var albumList = realmHelper.albums
        mAlbumViewAdapter = SearchAlbumViewAdapter(this, activity, albumList)
        recyclerview.adapter = mAlbumViewAdapter



        recyclerview.setOnClickListener {
            hideKeyboard()
        }

        edit_search.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun afterTextChanged(editable: Editable) {
                // input창에 문자를 입력할때마다 호출된다.
                // search 메소드를 호출한다.
                val text = edit_search.text.toString()
                Log.d("name","SearchFramgnet $text")
                var size = mAlbumViewAdapter.filter(text)
                if (text.length > 0 && size != 0) {
                    searchSize.text = "${size.toString()}개의 앨범"
                } else if(text.length > 0 && size == 0){
                    searchSize.text = "검색결과가 없습니다"
                } else if(text.length == 0 && size == 0) {
                    searchSize.text = "최근 검색 결과"
                }


                if (text.length == 0) {
                    reset_button.setEnabled(false)

                    var deleteDrawable = reset_button.drawable
                    deleteDrawable?.setColorFilter(ContextCompat.getColor(context, R.color.text_hint), PorterDuff.Mode.SRC_ATOP)
                } else if (text.length > 0) {

                        reset_button.setEnabled(true)
                    var deleteDrawable = reset_button.drawable
                    deleteDrawable?.setColorFilter(ContextCompat.getColor(context, R.color.text_primary), PorterDuff.Mode.SRC_ATOP)
                }

            }
        })


//        showKeyboard()


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
                activity.finish()
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
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.menu_search, menu)

        var resetDrawable = menu?.findItem(R.id.reset)?.icon
        resetDrawable?.setColorFilter(resources.getColor(R.color.text_secondary), PorterDuff.Mode.SRC_ATOP)


    }



    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        var id = item?.itemId
        when (id) {
        /**
         * 앨범 생성
         * */


            R.id.reset -> {

            }



        }
        return super.onOptionsItemSelected(item)
    }

//    var popupdef: WindowManager.LayoutParams? = null

//    override fun onCreate(savedInstanceState: Bundle?) {
//        activity.setTheme(R.style.AppTheme);
//
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_search)
//        popupdef = WindowManager.LayoutParams()
//
//        // 키보드 자동으로 올라오기
//        val handler = Handler()
//        handler.postDelayed(Runnable {
//            edit_search.clearFocus()
//            edit_search.requestFocus()
//            val mgr = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//
//
//            mgr.showSoftInput(edit_search, InputMethodManager.SHOW_IMPLICIT)
//        }, 100)

    fun hideKeyboard() {
        inputMethodManager.hideSoftInputFromWindow(edit_search.getWindowToken(), 0)
    }

//    fun showKeyboard() {
//        val handler = Handler()
//        handler.postDelayed({
//            edit_search.requestFocus()
//            inputMethodManager.showSoftInput(edit_search, InputMethodManager.SHOW_IMPLICIT)
//        }, 100)
//    }
//
    }
