package com.example.quizapp

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.api.ApiClient
import com.example.api.Model
import com.example.api.Result
import kotlinx.android.synthetic.main.activity_question.*
import kotlinx.android.synthetic.main.wronganswer.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.random.Random

class QuestionActivity : AppCompatActivity() {
    val list = mutableListOf<Result>()
    private var qIndex = 0
    private var questionindex = 0
    private var scroe = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)
        supportActionBar?.hide()
        apicall()
        startquize.setOnClickListener{
            ShowQuestion()
            startquize.visibility = View.GONE

        }
        nextQuestionBtn.setOnClickListener{
            var id:Int = radiogrp.checkedRadioButtonId
            if(id!=-1){
                val radio:RadioButton = radiogrp.findViewById(id)
                checkanswer(radio.text.toString(),list[qIndex-1].correctAnswer)

            }else{
                Toast.makeText(this, " Select option ", Toast.LENGTH_SHORT).show()
            }

        }


    }
    fun apicall(){
        ApiClient.api.getQuestions().enqueue(object : Callback<Model>{
            override fun onResponse(call: Call<Model>, response: Response<Model>) {
                val responsebody = response.body()
                if(responsebody?.responseCode==0)
                {
                    list.addAll(responsebody.results)
                }
            }

            override fun onFailure(call: Call<Model>, t: Throwable) {
                Toast.makeText(this@QuestionActivity,"Error",Toast.LENGTH_LONG).show()
            }

        })
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
            findViewById<TextView>(R.id.txt_play_score).text = "Score :$scroe"
            qIndex++;
        }else{
            val intent = Intent(this,ResultActivity::class.java)
            intent.putExtra("score",scroe)
            startActivity(intent)
            finish()
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
    fun CorrectAnswerDialog(){

            val builder = AlertDialog.Builder(this)
            val view = LayoutInflater.from(this).inflate(R.layout.correctanswerdialog, null)
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
    fun WrongAnswerDialog(){
        val builder = AlertDialog.Builder(this)
        val view = LayoutInflater.from(this).inflate(R.layout.wronganswer, null)
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

}