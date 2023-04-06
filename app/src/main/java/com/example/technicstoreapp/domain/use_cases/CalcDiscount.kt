package com.example.technicstoreapp.domain.use_cases

import javax.inject.Inject
import kotlin.math.roundToInt

class CalcDiscount @Inject constructor(){

    fun calculatingDiscount(points: Double): Int {
        return (points * 0.01).roundToInt()
    }
}