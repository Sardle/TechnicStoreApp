package com.example.technicstoreapp.ui.utils

import javax.inject.Inject
import kotlin.math.roundToInt

class CalcDiscount @Inject constructor(){

    fun calculatingPoints(points: Double): Int {
        return (points * 0.05).roundToInt()
    }

    fun calculatingDiscount(points: Int): Double {
        return (points.toDouble() / 10)
    }
}