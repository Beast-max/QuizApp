package com.example.quizapp.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.example.api.Result
import com.example.quizapp.R
import com.example.quizapp.ResultActivity
import com.example.quizapp.ViewModel.questionViewModel
import kotlinx.android.synthetic.main.activity_question.*
import kotlinx.android.synthetic.main.fragment_question.view.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class QuestionFragment : Fragment() {
    private val list = mutableListOf<Result>()
    private var qIndex = 0
    private var questionindex = 0
    private var scroe = 0
    private lateinit var viewModel: questionViewModel
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
        val view =  inflater.inflate(R.layout.fragment_question, container, false)
            requireActivity().actionBar?.hide()
        val cart = arguments?.getInt("cart")
        val diff = arguments?.getString("diff")
        viewModel = ViewModelProvider(this).get(questionViewModel::class.java)
                view.startquize.setOnClickListener{
            ShowQuestion()
            startquize.visibility = View.GONE


        }
                    view.nextQuestionBtn.setOnClickListener{
            val id:Int = radiogrp.checkedRadioButtonId
            if(id!=-1){
                val radio: RadioButton = radiogrp.findViewById(id)
                checkanswer(radio.text.toString(),list[qIndex-1].correctAnswer)

            }else{
                Toast.makeText(requireContext(), " Select option ", Toast.LENGTH_SHORT).show()
            }

        }
        viewModel.que.observe({lifecycle}){
            list.addAll(it)
        }
        if (cart != null) {
            if (diff != null) {
                viewModel.fatchquestion(cart,diff)
            }
        }
        return view
    }
    @SuppressLint("SetTextI18n")
    fun ShowQuestion()
    {
        val option = mutableListOf<String>()
        questionindex++
        if(questionindex<=10) {
            tv_noOfQues.text = "$questionindex/10"


            if (list.isNotEmpty()) {
                if (list[qIndex].incorrectAnswers.size != 1) {
                    option.add(list[qIndex].incorrectAnswers[0])
                    option.add(list[qIndex].incorrectAnswers[1])
                    option.add(list[qIndex].incorrectAnswers[2])
                    option.add(list[qIndex].correctAnswer)
                    option.shuffle()
                } else {
                    option.add(list[qIndex].incorrectAnswers[0])
                    option.add("")
                    option.add("")
                    option.add(list[qIndex].correctAnswer)
                    option.shuffle()
                }
            }
        }

        if(qIndex<list.size){

            tv_question.text = list[qIndex].question
            radioButton1.text = option[0]
            radioButton2.text = option[1]
            radioButton3.text = option[2]
            radioButton4.text = option[3]
                view?.findViewById<TextView>(R.id.txt_play_score)?.text = "Score :$scroe"
            qIndex++;
        }else{
            val intent = Intent(requireContext(), ResultActivity::class.java)
            intent.putExtra("score",scroe)

            startActivity(intent)
            requireActivity().finish()
        }


    }
    fun checkanswer(answer:String,c:String){

        if(answer==c)
        {
            scroe += 10
            CorrectAnswerDialog()
            ShowQuestion()
            radiogrp.clearCheck()
        }else{
            WrongAnswerDialog()
            radiogrp.clearCheck()
        }
    }
    @SuppressLint("SetTextI18n")
    fun CorrectAnswerDialog(){

        val builder = AlertDialog.Builder(requireContext())
        val view = LayoutInflater.from(requireContext()).inflate(R.layout.correctanswerdialog, null)
        builder.setView(view)
        val tvScore = view.findViewById<TextView>(R.id.tvDialog_score)
        val correctOkBtn = view.findViewById<Button>(R.id.correct_ok)
        tvScore.text = "Score : $scroe"
        val alertDialog = builder.create()
        correctOkBtn.setOnClickListener {

            alertDialog.dismiss()


        }
        alertDialog.window!!.setBackgroundDrawable(ColorDrawable(0))
        alertDialog.show()
    }
    @SuppressLint("SetTextI18n")
    fun WrongAnswerDialog(){
        val builder = AlertDialog.Builder(requireContext())
        val view = LayoutInflater.from(requireContext()).inflate(R.layout.wronganswer, null)
        builder.setView(view)
        val tvScore = view.findViewById<TextView>(R.id.tvDialog_score)
        val correctOkBtn = view.findViewById<Button>(R.id.correct_ok)
        val skip = view.findViewById<Button>(R.id.f)
        tvScore.text = "Score : $scroe"
        val alertDialog = builder.create()
        correctOkBtn.setOnClickListener {

            alertDialog.dismiss()


        }
        skip.setOnClickListener {
            ShowQuestion()
            alertDialog.dismiss()
        }
        alertDialog.window!!.setBackgroundDrawable(ColorDrawable(0))
        alertDialog.show()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment QuestionFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            QuestionFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}