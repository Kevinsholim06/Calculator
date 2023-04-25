package com.example.calc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity() {
    private lateinit var result: EditText
    private lateinit var newNumber: EditText
    private val displayOperation by lazy(LazyThreadSafetyMode.NONE) { findViewById<TextView>(R.id.operation) }
//    private val displayOperation by lazy(LazyThreadSafetyMode.NONE){findViewById<TextView>(R.id.operation)}

    //    Variables to hold the operands and type of calculations
    private var operand1: Double? = null

    private var pendingOperation = "="

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        result = findViewById(R.id.result)
        newNumber = findViewById(R.id.newNumber)

//        Data input buttons
        val button0: Button = findViewById(R.id.btn0)
        val button1: Button = findViewById(R.id.btn1)
        val button2: Button = findViewById(R.id.btn2)
        val button3: Button = findViewById(R.id.btn3)
        val button4: Button = findViewById(R.id.btn4)
        val button5: Button = findViewById(R.id.btn5)
        val button6: Button = findViewById(R.id.btn6)
        val button7: Button = findViewById(R.id.btn7)
        val button8: Button = findViewById(R.id.btn8)
        val button9: Button = findViewById(R.id.btn9)
        val buttonDot: Button = findViewById(R.id.btnDot)

//        operation buttons
        val buttonEquals = findViewById<Button>(R.id.btnEquals)
        val buttonDivide = findViewById<Button>(R.id.btnDivide)
        val buttonMultiply = findViewById<Button>(R.id.btnMultiply)
        val buttonMinus = findViewById<Button>(R.id.btnMinus)
        val buttonAdd = findViewById<Button>(R.id.btnAdd)

        val listener = View.OnClickListener { v ->
            val b = v as Button
            newNumber.append(b.text)
        }

        button0.setOnClickListener(listener)
        button1.setOnClickListener(listener)
        button2.setOnClickListener(listener)
        button3.setOnClickListener(listener)
        button4.setOnClickListener(listener)
        button5.setOnClickListener(listener)
        button6.setOnClickListener(listener)
        button7.setOnClickListener(listener)
        button8.setOnClickListener(listener)
        button9.setOnClickListener(listener)
        buttonDot.setOnClickListener(listener)

        val opListener = View.OnClickListener { v ->
            val op = (v as Button).text.toString()
            try {
                val value = newNumber.text.toString().toDouble()
                performOperation(value, op)
            } catch (e: NumberFormatException) {
                newNumber.setText("")
            }
            pendingOperation = op
            displayOperation.text = pendingOperation
        }

        buttonEquals.setOnClickListener(opListener)
        buttonDivide.setOnClickListener(opListener)
        buttonMultiply.setOnClickListener(opListener)
        buttonMinus.setOnClickListener(opListener)
        buttonAdd.setOnClickListener(opListener)

    }

    private fun performOperation(value: Double, operation: String) {
        if (operand1 == null) {
            operand1 = value
        } else {

            if (pendingOperation == "=") {
                pendingOperation = operation
            }

            when (pendingOperation) {
                "=" -> operand1 = value
                "/" -> operand1 = if (value == 0.0) {
                    Double.NaN
                } else {
                    operand1!! / value
                }
                "*" -> operand1 = operand1!! * value
                "-" -> operand1 = operand1!! - value
                "+" -> operand1 = operand1!! + value
            }
        }
        result.setText(operand1.toString())
        newNumber.setText("")
    }
}
