package com.app.humanresource.Fragments.ProfileFragment

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.app.humanresource.Activities.ChangePasswordActivity.ResetChangePasswordActivity
import com.app.humanresource.Activities.EditProfileActivity.EditProfileActivity
import com.app.humanresource.Activities.FavouritesActivity.FavouritesActivity
import com.app.humanresource.Activities.MyApplicationsActivity.MyApplicationsActivity
import com.app.humanresource.Activities.MyJobsActivity.MyJobsActivity
import com.app.humanresource.Activities.WelcomeActivity.WelcomeActivity
import com.app.humanresource.Fragments.ProfileFragment.Presenter.ProfilePresenter
import com.app.humanresource.Fragments.ProfileFragment.View.ProfileView
import com.app.humanresource.Models.Profile.ProfileData
import com.app.humanresource.R
import com.app.humanresource.Utils.CSPreferences
import com.app.humanresource.Utils.Utils
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView


class ProfileFragment : Fragment(), View.OnClickListener, ProfileView {
    private var data: ProfileData? = null
    var activity: Activity? = null
    var tv_editprofile: TextView? = null
    var tv_signout: TextView? = null
    var tv_username: TextView? = null
    var tv_email: TextView? = null
    var tv_phonenumber: TextView? = null
    var tv_myapplications: TextView? = null
    var tv_roletype: TextView? = null
    lateinit var role: String
    var lin_changepassword: LinearLayout? = null
    var profilePresenter: ProfilePresenter? = null
    var profile_image: CircleImageView? = null
    var img_profileimage: ImageView? = null
    var tv_wishlist: TextView? = null
    var linearwishlist: LinearLayout? = null
    var profileFragment:ProfileFragment?=null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_profile, container, false)
        activity = getActivity()
        init(view)
        listeners(view)
        profilePresenter = ProfilePresenter(activity as FragmentActivity, this)
        profilePresenter?.getCurrentUser()
        role = requireArguments().getString("role")!!
        if (role.equals("employer")) {
            tv_myapplications?.text = "My Jobs"
            linearwishlist?.visibility = View.GONE

        } else {
            tv_myapplications?.text = "My Applications"
            linearwishlist?.visibility = View.VISIBLE
        }
        return view
    }

    private fun init(view: View?) {
        tv_editprofile = view?.findViewById(R.id.tv_editprofile)
        tv_signout = view?.findViewById(R.id.tv_signout)
        tv_username = view?.findViewById(R.id.tv_username)
        tv_email = view?.findViewById(R.id.tv_email)
        lin_changepassword = view?.findViewById(R.id.lin_changepassword)
        tv_myapplications = view?.findViewById(R.id.tv_myapplications)
        tv_phonenumber = view?.findViewById(R.id.tv_phonenumber)
        tv_roletype = view?.findViewById(R.id.tv_roletype)
        img_profileimage = view?.findViewById(R.id.img_profileimage)
        tv_wishlist = view?.findViewById(R.id.tv_wishlist)
        linearwishlist = view?.findViewById(R.id.linearwishlist)

    }

    private fun listeners(view: View?) {
        tv_editprofile?.setOnClickListener(this)
        tv_signout?.setOnClickListener(this)
        lin_changepassword?.setOnClickListener(this)
        tv_myapplications?.setOnClickListener(this)
        tv_phonenumber?.setOnClickListener(this)
        linearwishlist?.setOnClickListener(this)

    }

    override fun onClick(p0: View?) {
        if (p0 == tv_editprofile) {
            var intent = Intent(activity, EditProfileActivity::class.java)
            startActivity(intent)
        } else if (p0 == tv_signout) {
            showDialog()
        } else if (p0 == lin_changepassword) {
            var intent = Intent(activity, ResetChangePasswordActivity::class.java)
            startActivity(intent)
        } else if (p0 == tv_myapplications) {
            if (role.equals("employer")) {
                var intent = Intent(activity, MyJobsActivity::class.java)
                startActivity(intent)
            } else {
                var intent = Intent(activity, MyApplicationsActivity::class.java)
                startActivity(intent)
            }
        }else if(p0==linearwishlist){
            var intent = Intent(activity, FavouritesActivity::class.java)
            startActivity(intent)
//            val fragment: Fragment = FavouriteFragment()
//            val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
//            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
//            fragmentTransaction.replace(android.R.id.content, fragment)
//            fragmentTransaction.addToBackStack(null)
//            fragmentTransaction.commit()
        }
    }


    private fun showDialog() {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.logoutdialog)
        val window = dialog.window
        val wlp = window!!.attributes
        wlp.gravity = Gravity.CENTER
        wlp.flags = wlp.flags and WindowManager.LayoutParams.FLAG_BLUR_BEHIND.inv()
        window.attributes = wlp
        dialog.window!!.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )
        dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        val btn_signout = dialog.findViewById<View>(R.id.btn_signout) as Button
//        val gender: String = CSPreferences.readString(activity, Utils.GENDERSELECT)

        btn_signout.setOnClickListener {
            CSPreferences.putString(requireActivity()!!, Utils.USERLOGIN, "false")
            val intent1 = Intent(activity, WelcomeActivity::class.java)
            startActivity(intent1)
            activity?.finish()

        }
        val btn_cancel = dialog.findViewById<View>(R.id.btn_cancel) as Button
        btn_cancel.setOnClickListener { dialog.dismiss() }
        dialog.show()
    }


    override fun showMessage(activity: Activity?, msg: String?) {
        com.app.humanresource.Utils.Utils.showMessage(activity, msg)
    }

    override fun showDialog(activity: Activity?) {
        com.app.humanresource.Utils.Utils.showDialogMethod(
            activity,
            requireActivity()!!.fragmentManager
        )
    }

    override fun hideDialog() {
        com.app.humanresource.Utils.Utils.hideDialog()
    }

    override fun getData(activity: FragmentActivity, data: ProfileData?) {
        this.data = data
        tv_username?.text = data?.userName
        tv_email?.text = data?.email
        tv_phonenumber?.text = data?.phoneNumber
        tv_roletype?.text = data?.roleType
        if (data?.image !== null) {
            Glide.with(this)
                .load(data.image).placeholder(R.drawable.usericons2)
                .into(img_profileimage!!)
        } else {
            img_profileimage?.setImageResource(R.drawable.usericons)
        }


    }


}