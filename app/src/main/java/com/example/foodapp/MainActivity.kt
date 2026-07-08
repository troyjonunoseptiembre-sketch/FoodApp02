package com.example.foodapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import android.text.TextWatcher
import android.text.Editable
import java.text.NumberFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private val calculadora = Calculadora()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // === ENLAZAR VISTAS DEL XML ===
        val edtChoclo = findViewById<EditText>(R.id.edtCantidadChoclo)
        val edtCazuela = findViewById<EditText>(R.id.edtCantidadCazuela)
        val switchPropina = findViewById<Switch>(R.id.switchPropina)

        val txtSubtotalChoclo = findViewById<TextView>(R.id.txtSubtotalChoclo)
        val txtSubtotalCazuela = findViewById<TextView>(R.id.txtSubtotalCazuela)
        val txtTotalSinPropina = findViewById<TextView>(R.id.txtTotalSinPropina)
        val txtMontoPropina = findViewById<TextView>(R.id.txtMontoPropina)
        val txtTotalFinal = findViewById<TextView>(R.id.txtTotalFinal)

        val formato = NumberFormat.getCurrencyInstance(Locale.US)

        // === FUNCIÓN PARA ACTUALIZAR LOS VALORES ===
        fun actualizar() {
            val cantidadChoclo = edtChoclo.text.toString().toIntOrNull() ?: 0
            val cantidadCazuela = edtCazuela.text.toString().toIntOrNull() ?: 0

            val subtotalChoclo = calculadora.subtotal(cantidadChoclo, 15.0)
            val subtotalCazuela = calculadora.subtotal(cantidadCazuela, 10.0)

            val totalSinPropina = subtotalChoclo + subtotalCazuela
            val montoPropina = calculadora.propina(totalSinPropina)
            val totalFinal = calculadora.totalFinal(totalSinPropina, switchPropina.isChecked)

            txtSubtotalChoclo.text = "Subtotal Choclo: ${formato.format(subtotalChoclo)}"
            txtSubtotalCazuela.text = "Subtotal Cazuela: ${formato.format(subtotalCazuela)}"
            txtTotalSinPropina.text = "Total sin propina: ${formato.format(totalSinPropina)}"
            txtMontoPropina.text = "Propina: ${formato.format(montoPropina)}"
            txtTotalFinal.text = "Total final: ${formato.format(totalFinal)}"
        }

        // === EVENTOS PARA ACTUALIZAR AUTOMÁTICAMENTE ===
        edtChoclo.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                actualizar()
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        edtCazuela.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                actualizar()
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        switchPropina.setOnCheckedChangeListener { _, _ ->
            actualizar()
        }
    }
}
