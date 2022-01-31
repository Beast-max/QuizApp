package com.example.quizapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.quizapp.ViewModel.questionViewModel
import kotlinx.android.synthetic.main.fragment_choose_type.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ChooseType.newInstance] factory method to
 * create an instance of this fragment.
 */
class ChooseType : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_choose_type, container, false)

        view.gk.setOnClickListener {
            val bundle  = Bundle()
            bundle.putInt("cart",9)
            it.findNavController().navigate(R.id.mainfrag,bundle)

        }
        view.mthology.setOnClickListener {
            val bundle  = Bundle()
            bundle.putInt("cart",20)
            it.findNavController().navigate(R.id.mainfrag,bundle)
        }
        view.sports.setOnClickListener {
            val bundle  = Bundle()
            bundle.putInt("cart",21)
            it.findNavController().navigate(R.id.mainfrag,bundle)
        }
        view.veh.setOnClickListener {
            val bundle  = Bundle()
            bundle.putInt("cart",21)
            it.findNavController().navigate(R.id.mainfrag,bundle)
        }
        view.animals.setOnClickListener {
            val bundle  = Bundle()
            bundle.putInt("cart",27)
            it.findNavController().navigate(R.id.mainfrag,bundle)
        }
        view.celebs.setOnClickListener {
            val bundle  = Bundle()
            bundle.putInt("cart",26)
            it.findNavController().navigate(R.id.mainfrag,bundle)
        }
        view.history.setOnClickListener {
            val bundle  =Bundle()
            bundle.putInt("cart",23)
            it.findNavController().navigate(R.id.mainfrag,bundle)
        }
        view.comics.setOnClickListener {
            val bundle  = Bundle()
            bundle.putInt("cart",29)
            it.findNavController().navigate(R.id.mainfrag,bundle)
        }
        view.gadgets.setOnClickListener {
            val bundle  = Bundle()
            bundle.putInt("cart",30)
            it.findNavController().navigate(R.id.mainfrag,bundle)
        }
        view.videogame.setOnClickListener {
            val bundle  = Bundle()
            bundle.putInt("cart",15)
            it.findNavController().navigate(R.id.mainfrag,bundle)
        }
        view.politics.setOnClickListener {
            val bundle  =Bundle()
            bundle.putInt("cart",24)
            it.findNavController().navigate(R.id.mainfrag,bundle)
        }

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ChooseType.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ChooseType().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}