package com.example.hien_android_final

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.hien_android_final.databinding.ActivityLoginBinding
import com.google.android.material.button.MaterialButtonToggleGroup
import com.google.android.material.button.MaterialButtonToggleGroup.OnButtonCheckedListener
import com.watasolutions.w3_databinding_wm.DataStorage
import com.watasolutions.w3_databinding_wm.MainViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SettingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SettingFragment : Fragment() {
//    var toggleGroup: MaterialButtonToggleGroup? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_setting, container, false)
        var btnLogout = view.findViewById<TextView>(R.id.btnLogout)
        btnLogout?.setOnClickListener {
            var currentD = DataStorage.currentUser.toMutableMap()

            DataStorage.userData.add(currentD)
            val findCurrentData = DataStorage.currentUser.clear()

            Log.v("current", DataStorage.userData.toString())
            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent);
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        toggleGroup = view.findViewById(R.id.toggleGroup)
//        toggleGroup?.addOnButtonCheckedListener(OnButtonCheckedListener { group, checkedId, isChecked ->
//            Log.v("checkID", checkedId.toString())
//            Log.v("checkStatus", isChecked.toString())
//            if (isChecked) {
//                if (checkedId == R.id.btn_lightmode) {
//                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
//                } else {
//                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
//                }
//            }
//        })
        //        toggleGroup = view.findViewById(R.id.toggleGroup)


    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SettingFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SettingFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}