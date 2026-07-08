package com.example.foodapp

class Calculadora {

    fun subtotal(cantidad: Int, precio: Double): Double {
        return cantidad * precio
    }

    fun propina(total: Double): Double {
        return total * 0.10
    }

    fun totalFinal(total: Double, incluirPropina: Boolean): Double {
        return if (incluirPropina) total + propina(total) else total
    }
}
