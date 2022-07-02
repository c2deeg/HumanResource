package com.app.humanresource.Fragments.EmployerHomeFragment

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.humanresource.R
import android.content.Intent
import android.graphics.Color
import android.widget.*
import androidx.fragment.app.FragmentActivity
import com.app.humanresource.Activities.JobDescriptionActivity.JobDescriptionActivity
import com.app.humanresource.Fragments.EmployerHomeFragment.Presenter.EmployerHomePresenter
import com.app.humanresource.Fragments.EmployerHomeFragment.View.EmployerHomeView
import com.app.humanresource.Utils.Utils
import android.text.Editable

import android.text.TextWatcher
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.humanresource.Adapter.JobTypeRecyclerViewAdapter
import com.app.humanresource.Fragments.ProfileFragment.ProfileFragment
import com.app.humanresource.Models.Profile.ProfileData
import com.app.humanresource.StaticModels.JobTypeRecyclerModelClass
import com.app.humanresource.Utils.CSPreferences
import com.bumptech.glide.Glide


class EmployerHomeFragment : Fragment(), AdapterView.OnItemSelectedListener, View.OnClickListener,
    EmployerHomeView {
    var activity: Activity? = null
    var list_of_items = arrayOf("Item 1", "Item 2", "Item 3")
    var tv_jobdescription: TextView? = null
    var tv_companyDetails: TextView? = null
    var tv_jobdetails: TextView? = null
    var employerHomePresenter: EmployerHomePresenter? = null
    var et_title: EditText? = null
    var et_category: EditText? = null
    var et_homeowner: EditText? = null
    var et_citylocation: EditText? = null
    var et_pricefrom: EditText? = null
    var et_priceto: EditText? = null
    var flag = true
    var numberofworkers = arrayOf("1", "2", "3","4","5","6","7","8","9","10")
    var spinnertext:String?=null
    var jobtype_recycerview:RecyclerView?=null
    var _img_userimage:ImageView?=null
    var rolesharedpre:String?=null



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_employer_home, container, false)
        activity = getActivity()
        init(view)
        listeners(view)
        employerHomePresenter = EmployerHomePresenter(requireActivity(), this)
        rolesharedpre = CSPreferences.readString(requireActivity(), Utils.ROLE)
        employerHomePresenter?.getCurrentUser()
//        number of workers spinner_________________________________________________________________
        val numof_workersspinner = view?.findViewById<Spinner>(R.id.numof_workersspinner)
        numof_workersspinner!!.setOnItemSelectedListener(this)
        val numof_workersspinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, numberofworkers)
        numof_workersspinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        numof_workersspinner!!.setAdapter(numof_workersspinnerAdapter)
  //categoryapi for edittext_____________________________________________________________________________________
        et_category?.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }
            override fun afterTextChanged(s: Editable) {
               employerHomePresenter?.createCategoryAPI(et_category?.text.toString())
            }
        })
  //jobtypeRecylerview______________________________________________________________________________________
        var jobtypemodelclass: ArrayList<JobTypeRecyclerModelClass> = ArrayList()
        jobtypemodelclass.add(
            JobTypeRecyclerModelClass(
                "Full Time",
            )
        )
        jobtypemodelclass.add(
            JobTypeRecyclerModelClass(
                "Part Time",
            )
        )
        jobtypemodelclass.add(
            JobTypeRecyclerModelClass(
                "Contract",
            )
        )
        jobtypemodelclass.add(
            JobTypeRecyclerModelClass(
                "Remote",
            )
        )
        jobtypemodelclass.add(
            JobTypeRecyclerModelClass(
                "Freelancer",
            )
        )
        jobtype_recycerview?.layoutManager =
            GridLayoutManager(this.context, 3, RecyclerView.VERTICAL, false)
        val jobTypeRecyclerViewAdapter = JobTypeRecyclerViewAdapter(activity as FragmentActivity,jobtypemodelclass)
        jobtype_recycerview?.adapter = jobTypeRecyclerViewAdapter
        return view
    }

    private fun init(view: View?) {
        tv_jobdescription = view?.findViewById(R.id.tv_jobdescription)
        tv_companyDetails = view?.findViewById(R.id.tv_companyDetails)
        tv_jobdetails = view?.findViewById(R.id.tv_jobdetails)
        et_title = view?.findViewById(R.id.et_title)
        et_category = view?.findViewById(R.id.et_category)
        et_homeowner = view?.findViewById(R.id.et_homeowner)
        et_citylocation = view?.findViewById(R.id.et_citylocation)
        et_pricefrom = view?.findViewById(R.id.et_pricefrom)
        et_priceto = view?.findViewById(R.id.et_priceto)
        jobtype_recycerview = view?.findViewById(R.id.jobtype_recycerview)
        _img_userimage = view?.findViewById(R.id._img_userimage)
    }

    private fun listeners(view: View?) {
        tv_jobdescription?.setOnClickListener(this)
        tv_companyDetails?.setOnClickListener(this)
        _img_userimage?.setOnClickListener(this)
    }
    override fun onClick(p0: View?) {
        if (p0 == tv_jobdescription) {
            if (et_title?.text?.length == 0 || et_category?.text?.length == 0 || et_citylocation?.text?.length == 0
                || et_pricefrom?.text?.length == 0 || et_priceto?.text?.length == 0 || et_priceto?.text?.length == 0) {
                validation()
            } else {
                tv_jobdetails?.setTextColor(Color.WHITE)
                tv_companyDetails?.setTextColor(Color.WHITE)
                tv_jobdescription?.setTextColor(Color.parseColor("#EECB4F"))
                var inent =
                    Intent(activity as FragmentActivity?, JobDescriptionActivity::class.java)
                inent.putExtra("et_title", et_title?.text.toString())
                inent.putExtra("et_category", et_category?.text.toString())
                inent.putExtra("et_citylocation", et_citylocation?.text.toString())
                inent.putExtra("et_pricefrom", et_pricefrom?.text.toString())
                inent.putExtra("et_priceto", et_priceto?.text.toString())
                inent.putExtra("et_numofworkers", spinnertext)
                startActivity(inent)
            }
        } else if (p0 == tv_companyDetails) {
            tv_jobdescription?.setTextColor(Color.WHITE)
            tv_jobdetails?.setTextColor(Color.WHITE)
            tv_companyDetails?.setTextColor(Color.parseColor("#EECB4F"))
            Toast.makeText(requireContext(), "Please Enter Job Description", Toast.LENGTH_SHORT)
                .show()
        }else if (p0==_img_userimage){
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

    override fun onResume() {
        super.onResume()
        tv_jobdetails?.setTextColor(Color.parseColor("#EECB4F"))
        tv_companyDetails?.setTextColor(Color.WHITE)
        tv_jobdescription?.setTextColor(Color.WHITE)
    }


    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
//        Toast.makeText(requireContext(), getString(R.string.selected_item) + " " +
//                            "" + numberofworkers[p2], Toast.LENGTH_SHORT).show()
        spinnertext = numberofworkers[p2]
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

    override fun getData(activity: FragmentActivity, data: ProfileData?) {
        if (data?.image !== null) {
            Glide.with(this)
                .load(data.image)
                .into(_img_userimage!!)
        } else {
            _img_userimage?.setImageResource(R.drawable.ic_launcher_background)
        }
    }

    fun validation() {
        if (et_title?.text?.length == 0) {
            Toast.makeText(activity, "Please enter job title", Toast.LENGTH_SHORT).show()
        } else if (et_category?.text?.length == 0) {
            Toast.makeText(activity, "Please enter job category", Toast.LENGTH_SHORT).show()
        } else if (et_citylocation?.text?.length == 0) {
            Toast.makeText(activity, "Please enter job city", Toast.LENGTH_SHORT).show()
        } else if (et_pricefrom?.text?.length == 0) {
            Toast.makeText(activity, "Please enter price", Toast.LENGTH_SHORT).show()

        } else if (et_priceto?.text?.length == 0) {
            Toast.makeText(activity, "Please enter price", Toast.LENGTH_SHORT).show()
        } else if (et_priceto?.text?.length == 0) {
            Toast.makeText(activity, "Please enter number number of workers", Toast.LENGTH_SHORT)
                .show()
        }
    }




}