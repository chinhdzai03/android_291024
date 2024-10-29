package com.example.numberfilter

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.bai2.R

class MainActivity : AppCompatActivity() {

    private lateinit var etNumber: EditText
    private lateinit var rbEven: RadioButton
    private lateinit var rbOdd: RadioButton
    private lateinit var rbSquare: RadioButton
    private lateinit var btnShow: Button
    private lateinit var tvError: TextView
    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etNumber = findViewById(R.id.etNumber)
        rbEven = findViewById(R.id.rbEven)
        rbOdd = findViewById(R.id.rbOdd)
        rbSquare = findViewById(R.id.rbSquare)
        btnShow = findViewById(R.id.btnShow)
        tvError = findViewById(R.id.tvError)
        listView = findViewById(R.id.listView)

        btnShow.setOnClickListener {
            val input = etNumber.text.toString()

            if (input.isEmpty() || input.toIntOrNull() == null || input.toInt() <= 0) {
                tvError.text = "Invalid n !"
                tvError.visibility = TextView.VISIBLE
                listView.adapter = null
            } else {
                tvError.visibility = TextView.GONE
                val n = input.toInt()
                val resultList = when {
                    rbEven.isChecked -> getEvenNumbers(n)
                    rbOdd.isChecked -> getOddNumbers(n)
                    rbSquare.isChecked -> getSquareNumbers(n)
                    else -> emptyList()
                }
                val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, resultList)
                listView.adapter = adapter
            }
        }
    }

    private fun getEvenNumbers(n: Int): List<Int> {
        return (0..n).filter { it % 2 == 0 }
    }

    private fun getOddNumbers(n: Int): List<Int> {
        return (1..n).filter { it % 2 != 0 }
    }

    private fun getSquareNumbers(n: Int): List<Int> {
        return (0..n).filter { isSquare(it) }
    }

    private fun isSquare(number: Int): Boolean {
        val sqrt = Math.sqrt(number.toDouble()).toInt()
        return sqrt * sqrt == number
    }
}
