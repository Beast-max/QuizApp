package com.example.quizapp.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api.Model
import com.example.quizapp.REpo.Repo
import kotlinx.coroutines.launch

class questionViewModel:ViewModel() {
    private val question = MutableLiveData<List<com.example.api.Result>>()
    val que :MutableLiveData<List<com.example.api.Result>> = question

    fun fatchquestion(cart:Int,Diff:String) =  viewModelScope.launch {
        Repo.getquestion(cart, Diff).body().let {
            que.postValue(it?.results)
        }
    }

}