package com.app.humanresource.Fragments.HomeFragment


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.humanresource.Activities.AllJobsDetailActivity.AllJobsDetailActivity
import com.app.humanresource.Activities.SearchResultActivities.SearchResultActivity
import com.app.humanresource.Adapter.CustomAdapter.CustomAdapter
import com.app.humanresource.Adapter.PopularJobsRecyclerAdapter
import com.app.humanresource.Adapter.RecentPostsRecyclerAdapter
import com.app.humanresource.Fragments.HomeFragment.Presenter.HomePresenter
import com.app.humanresource.Fragments.HomeFragment.View.HomeView
import com.app.humanresource.Fragments.ProfileFragment.ProfileFragment
import com.app.humanresource.Models.GetAllLocationModels.GetallLocationDatum
import com.app.humanresource.Models.GetPopularjobsModels.PopularJobsMessage
import com.app.humanresource.Models.GetallCategoryExample.GetallCategoryDatum
import com.app.humanresource.Models.Profile.ProfileData
import com.app.humanresource.Models.RecentPostModels.RecentPostsMessage
import com.app.humanresource.R
import com.app.humanresource.Utils.CSPreferences
import com.app.humanresource.Utils.Utils
import com.app.humanresource.interfaces.Popularjobsclickinterfaces
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlin.properties.Delegates


class HomeFragment : Fragment(), View.OnClickListener, AdapterView.OnItemSelectedListener, HomeView,
    Popularjobsclickinterfaces, TextView.OnEditorActionListener {
    private var poularjobslist: List<PopularJobsMessage> = ArrayList<PopularJobsMessage>()
    private var recentpostlists: List<RecentPostsMessage> = ArrayList<RecentPostsMessage>()
    private var locationdata: List<GetallLocationDatum> = ArrayList<GetallLocationDatum>()
    private var arraylistdata: List<GetallCategoryDatum> = ArrayList<GetallCategoryDatum>()
    var activity: Activity? = null
    var popularjobs_recyclerview: RecyclerView? = null
    var recentpost_recyclerview: RecyclerView? = null
    var tv_showall2: TextView? = null
    var tv_showall: TextView? = null
    var img_filter: ImageView? = null
    var tv_showalltypes: TextView? = null
    var tv_freelance: TextView? = null
    var tv_remote: TextView? = null
    var tv_contact: TextView? = null
    var tv_parttime: TextView? = null
    var tv_fulltime: TextView? = null
    var user_image: ImageView? = null
    var et_search: EditText? = null
    var minteger: Int? = 1
    var homePresenter: HomePresenter? = null
    var flag = true
    var spinner: Spinner? = null
    var spinner_location: Spinner? = null
    var spinner_salary: Spinner? = null
    var categoryid: String? = null
    var locationtext: String? = null
    var salarytext: String? = null
    var btn_apllyfilter: Button? = null
    var popularJobsRecyclerAdapter: PopularJobsRecyclerAdapter? = null
    var recentPostsRecyclerAdapter: RecentPostsRecyclerAdapter? = null
    var rolesharedpre: String? = null
    var locationarraylist: ArrayList<String> = ArrayList()
    var list_of_items = arrayOf("1k-2k", "3k-4k", "5k-6k", "7k-8k", "9k-10k")
    var bottomSheetDialog: BottomSheetDialog? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_home, container, false)
        activity = getActivity()

        init(view)
        listeners(view)
        homePresenter = HomePresenter(
            activity as FragmentActivity,
            this,
            popularjobs_recyclerview,
            recentpost_recyclerview
        )
        homePresenter!!.getpopularJobs()
        homePresenter!!.getrecentpostJobs()
        homePresenter!!.getallCategory()
        homePresenter!!.getallLocations()
        homePresenter!!.getCurrentUser()
        rolesharedpre = CSPreferences.readString(requireActivity(), Utils.ROLE)


        return view
    }

    private fun init(view: View?) {
        popularjobs_recyclerview = view?.findViewById(R.id.popularjobs_recyclerview)
        recentpost_recyclerview = view?.findViewById(R.id.recentpost_recyclerview)
        tv_showall = view?.findViewById(R.id.tv_showall)
        tv_showall2 = view?.findViewById(R.id.tv_showall2)
        img_filter = view?.findViewById(R.id.img_filter)
        user_image = view?.findViewById(R.id.user_image)
        et_search = view?.findViewById(R.id.et_search)

    }


    private fun listeners(view: View?) {
        tv_showall?.setOnClickListener(this)
        tv_showall2?.setOnClickListener(this)
        img_filter?.setOnClickListener(this)
        user_image?.setOnClickListener(this)
        et_search?.setOnEditorActionListener(this)

    }

    override fun onClick(p0: View?) {
        if (p0 == tv_showall) {
            var intent = Intent(activity, AllJobsDetailActivity::class.java)
            startActivity(intent)
        } else if (p0 == tv_showall2) {
            var intent = Intent(activity, AllJobsDetailActivity::class.java)
            intent.putExtra("role", "recent")
            startActivity(intent)
        } else if (p0 == img_filter) {
            showBottomSheetDialog(view)

        } else if (p0 == tv_showalltypes) {
            if (flag) {
                tv_showalltypes?.setTextColor(Color.BLACK)
                tv_showalltypes?.setBackgroundDrawable(resources.getDrawable(R.drawable.appgoldenbackground))
            } else {
                tv_showalltypes?.setTextColor(Color.WHITE)
                tv_showalltypes?.setBackgroundDrawable(resources.getDrawable(R.drawable.stroke_roundcorner))
            }
            flag = !flag

        } else if (p0 == tv_freelance) {
            if (flag) {
                tv_freelance?.setTextColor(Color.BLACK)
                tv_freelance?.setBackgroundDrawable(resources.getDrawable(R.drawable.appgoldenbackground))
            } else {
                tv_freelance?.setTextColor(Color.WHITE)
                tv_freelance?.setBackgroundDrawable(resources.getDrawable(R.drawable.stroke_roundcorner))
            }
            flag = !flag
        } else if (p0 == tv_remote) {
            if (flag) {
                tv_remote?.setTextColor(Color.BLACK)
                tv_remote?.setBackgroundDrawable(resources.getDrawable(R.drawable.appgoldenbackground))
            } else {
                tv_remote?.setTextColor(Color.WHITE)
                tv_remote?.setBackgroundDrawable(resources.getDrawable(R.drawable.stroke_roundcorner))
            }
            flag = !flag
        } else if (p0 == tv_contact) {
            if (flag) {
                tv_contact?.setTextColor(Color.BLACK)
                tv_contact?.setBackgroundDrawable(resources.getDrawable(R.drawable.appgoldenbackground))
            } else {
                tv_contact?.setTextColor(Color.WHITE)
                tv_contact?.setBackgroundDrawable(resources.getDrawable(R.drawable.stroke_roundcorner))
            }
            flag = !flag
        } else if (p0 == tv_parttime) {
            if (flag) {
                tv_parttime?.setTextColor(Color.BLACK)
                tv_parttime?.setBackgroundDrawable(resources.getDrawable(R.drawable.appgoldenbackground))
            } else {
                tv_parttime?.setTextColor(Color.WHITE)
                tv_parttime?.setBackgroundDrawable(resources.getDrawable(R.drawable.stroke_roundcorner))
            }
            flag = !flag
        } else if (p0 == tv_fulltime) {
            if (flag) {
                tv_fulltime?.setTextColor(Color.BLACK)
                tv_fulltime?.setBackgroundDrawable(resources.getDrawable(R.drawable.appgoldenbackground))
                var fulltime: String = tv_fulltime?.text.toString()
            } else {
                tv_fulltime?.setTextColor(Color.WHITE)
                tv_fulltime?.setBackgroundDrawable(resources.getDrawable(R.drawable.stroke_roundcorner))
            }
            flag = !flag

        } else if (p0 == user_image) {
            val bundle = Bundle()
            bundle.putString("role", rolesharedpre.toString())
            val fragment = ProfileFragment()
            val fm = requireActivity().supportFragmentManager
            fragment.setArguments(bundle);
            val transaction = fm.beginTransaction()
            transaction.add(android.R.id.content, fragment)
            transaction.commit()
        }
    }


    private fun showBottomSheetDialog(view: View?) {
        bottomSheetDialog = BottomSheetDialog(requireContext(), R.style.SheetDialog)
        bottomSheetDialog?.setContentView(R.layout.bottom_sheet_dialog)
        spinner = bottomSheetDialog?.findViewById<Spinner>(R.id.spinner)
        spinner_location = bottomSheetDialog?.findViewById<Spinner>(R.id.spinner_location)
        spinner_salary = bottomSheetDialog?.findViewById<Spinner>(R.id.spinner_salary)
        tv_showalltypes = bottomSheetDialog?.findViewById<TextView>(R.id.tv_showalltypes)
        tv_freelance = bottomSheetDialog?.findViewById<TextView>(R.id.tv_freelance)
        tv_remote = bottomSheetDialog?.findViewById<TextView>(R.id.tv_remote)
        tv_contact = bottomSheetDialog?.findViewById<TextView>(R.id.tv_contact)
        tv_parttime = bottomSheetDialog?.findViewById<TextView>(R.id.tv_parttime)
        tv_fulltime = bottomSheetDialog?.findViewById<TextView>(R.id.tv_fulltime)
        btn_apllyfilter = bottomSheetDialog?.findViewById<Button>(R.id.btn_apllyfilter)
        bottomSheetDialog?.show()
        spinner_salary!!.setOnItemSelectedListener(this)
        btn_apllyfilter!!.setOnClickListener {

        }

        spinner!!.setOnItemSelectedListener(this)
//        var arrayList: ArrayList<CategoryModels> = ArrayList()
//        arrayList.add(CategoryModels("0", "Select Category"))
//        for (i in 1..arraylistdata.size - 1) {
//            arrayList.add(CategoryModels(arraylistdata.get(i).id,arrayList.get(i).category))
//
//            Log.d("sdasdsadasdsad", arrayList.toString())
//
//
//        }
//        val aa = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, arrayList)

        val adapter = CustomAdapter(
            activity as FragmentActivity,
            R.layout.customspinneritem, arraylistdata
        )
        spinner!!.setAdapter(adapter)

        //locationsp
        // inner is good
        spinner_location!!.setOnItemSelectedListener(this)

        locationarraylist.add(0, "Select Location")
        for (i in 1..locationdata.get(0).location.size - 1) {
            locationarraylist.add(locationdata.get(0).location.get(i))
        }

        val adapter_location =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, locationarraylist)
        spinner_location!!.setAdapter(adapter_location)

        //salaryspinner
        val adapter_salary =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, list_of_items)
        spinner_salary!!.setAdapter(adapter_salary)

        //TextViews
        tv_showalltypes?.setOnClickListener(this)
        tv_freelance?.setOnClickListener(this)
        tv_remote?.setOnClickListener(this)
        tv_parttime?.setOnClickListener(this)
        tv_contact?.setOnClickListener(this)
        tv_fulltime?.setOnClickListener(this)


    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        if (p0 == spinner) {
            categoryid = arraylistdata.get(p2).id


        } else if (p0 == spinner_location) {
            locationtext = locationarraylist[p2]

        } else if (p0 == spinner_salary) {
            salarytext = list_of_items[p2]

        }

        btn_apllyfilter?.setOnClickListener {
            var intent = Intent(activity, SearchResultActivity::class.java)
            intent.putExtra("filter", "filters")
            intent.putExtra("categoryid", categoryid)
            intent.putExtra("locationtext", locationtext)
            startActivity(intent)

//

            bottomSheetDialog?.dismiss()
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }

    override fun showMessage(activity: Activity?, msg: String?) {
        Utils.showMessage(activity, msg)
    }

    override fun showDialog(activity: Activity?) {
        Utils.showDialogMethod(activity, requireActivity().fragmentManager)
    }

    override fun hideDialog() {
        Utils.hideDialog()
    }

    override fun setData(activity: FragmentActivity, data: List<GetallCategoryDatum>) {
        this.arraylistdata = data
    }

    override fun setLocationData(activity: FragmentActivity, locdata: List<GetallLocationDatum>) {
        this.locationdata = locdata
    }

    override fun setlistData(activity: FragmentActivity, message: List<RecentPostsMessage>) {
        this.recentpostlists = message
        recentpost_recyclerview?.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        recentPostsRecyclerAdapter = RecentPostsRecyclerAdapter(
            activity as FragmentActivity,
            message as MutableList<RecentPostsMessage>, this
        )
        recentpost_recyclerview?.adapter = recentPostsRecyclerAdapter
    }

    override fun setPopularjobsData(activity: FragmentActivity, message: List<PopularJobsMessage>) {
        this.poularjobslist = message
        popularjobs_recyclerview?.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        popularJobsRecyclerAdapter = PopularJobsRecyclerAdapter(
            activity as FragmentActivity, message as MutableList<PopularJobsMessage>, this
        )
        popularjobs_recyclerview?.adapter = popularJobsRecyclerAdapter
    }

    override fun getData(activity: FragmentActivity, data: ProfileData?) {
        if (!(data?.image.isNullOrEmpty())) {
            Glide.with(this)
                .load(data?.image).placeholder(R.drawable.usericons)
                .into(user_image!!)
        } else {
            user_image?.setImageResource(R.drawable.usericons)
        }
    }

    override fun onItemClick(postion: Int, imgFav: ImageView) {
        Log.d("checkpos", postion.toString())
        if (flag) {
            imgFav!!.setColorFilter(Color.RED)
            homePresenter?.addtofavMethod(poularjobslist.get(postion).id)
            homePresenter?.addtofavMethod(recentpostlists.get(postion).id)
        } else {
            imgFav!!.setColorFilter(Color.parseColor("#DADADA"))
        }
        flag = !flag
//        Toast.makeText(activity,postion,Toast.LENGTH_SHORT).show()
    }

    override fun onEditorAction(p0: TextView?, p1: Int, p2: KeyEvent?): Boolean {
        if (p1 == EditorInfo.IME_ACTION_SEARCH) {
            if (!(et_search?.text?.length == 0)) {
                var intent = Intent(activity, SearchResultActivity::class.java)
                intent.putExtra("search", et_search?.text.toString())
                startActivity(intent)
                homePresenter?.searchjobs(et_search?.text.toString())
                performsearch()
                return true
            } else {
                Toast.makeText(activity, "Please enter text", Toast.LENGTH_SHORT).show()
            }

        }
        return false
    }

    private fun performsearch() {
        et_search?.clearFocus()
        val `in`: InputMethodManager =
            requireActivity()!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        `in`.hideSoftInputFromWindow(et_search?.getWindowToken(), 0)
    }


}