package com.iamlomas.bday

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var mDateSetListener: DatePickerDialog.OnDateSetListener
    private val calendar = Calendar.getInstance()
    private var selectedDay: Int = 0
    private var selectedMonth: Int = 0
    private var selectedYear: Int = 0
    private val months = arrayOf(
        "January",
        "February",
        "March",
        "April",
        "May",
        "June",
        "July",
        "August",
        "September",
        "October",
        "November",
        "December"
    )

    private fun createToast(toastText: String) = Toast.makeText(
        this,
        toastText,
        Toast.LENGTH_LONG
    ).show()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvDate.setOnClickListener {
            DatePickerDialog(
                this,
                mDateSetListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        mDateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            selectedDay = dayOfMonth
            selectedMonth = month
            selectedYear = year

            tvDate.text = when (dayOfMonth) {
                1, 21, 31 -> "${dayOfMonth}st ${months[month]}, $year"
                2, 22 -> "${dayOfMonth}nd ${months[month]}, $year"
                3, 23 -> "${dayOfMonth}rd ${months[month]}, $year"
                else -> "${dayOfMonth}th ${months[month]}, $year"
            }
        }

        btnDone.setOnClickListener {
            val firstName = etFirstName.text.toString()

            when {
                firstName.length < 2 -> {
                    createToast("First Name must be greater than 1 character.")
                }

                tvDate.text == null || tvDate.text == "" -> {
                    createToast("Please select birth date.")
                }

                else -> {
                    if (selectedDay == calendar.get(Calendar.DAY_OF_MONTH) &&
                        selectedMonth == calendar.get(Calendar.MONTH)
                    ) {
                        Intent(this, DisplayNameActivity::class.java).apply {
                            putExtra("First_Name", firstName)
                            putExtra("Day", selectedDay)
                            putExtra("Month", selectedMonth)
                            putExtra("Year", selectedYear)
                        }.also {
                            startActivity(it)
                        }
                    } else {
                        Intent(this, TimeRemainingActivity::class.java).apply {
                            putExtra("Day", selectedDay)
                            putExtra("Month", selectedMonth)
                            putExtra("Year", selectedYear)
                        }.also {
                            startActivity(it)
                        }
                    }
                }
            }
        }
    }
}