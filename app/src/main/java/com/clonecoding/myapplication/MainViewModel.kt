package com.clonecoding.myapplication

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.clonecoding.myapplication.databinding.ActivityMainBinding
import kotlin.math.pow
import kotlin.math.roundToInt

class MainViewModel: ViewModel() {
    var bmi = MutableLiveData(0)
    var result = MutableLiveData("")
    var binding: ActivityMainBinding? = null

    fun btn(view: View){
        val txtWeight = binding?.txtWeight
        val txtHeight = binding?.txtHeight

        if(txtHeight?.text.isNullOrBlank() || txtWeight?.text.isNullOrBlank()){
            return
        }

        val weight = txtWeight?.text.toString().toInt()
        val height = txtHeight?.text.toString().toInt()

        val bmi = weight / (height / 100.0).pow(2.0)
        val result = when(bmi){
            in 0.0 .. 18.5 -> "저체중"
            in 18.5 .. 23.0 -> "정상체중"
            in 23.0 .. 25.0 -> "과체중"
            in 25.0 .. 30.0 -> "경도 비만"
            in 30.0 .. 35.0 -> "중정도 비만"
            else -> "고도 비만"
        }

        this.bmi.value = bmi.roundToInt()
        this.result.value = result
    }
}