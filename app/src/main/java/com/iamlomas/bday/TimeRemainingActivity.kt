package com.iamlomas.bday

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bday.IntervalCalculator
import kotlinx.android.synthetic.main.activity_time_remaining.*
import java.util.*

class TimeRemainingActivity : AppCompatActivity() {

    private var selectedDay: Int = 0
    private var selectedMonth: Int = 0
    private var selectedYear: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_remaining)

        val calendar = Calendar.getInstance()
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH)
        val year = calendar.get(Calendar.YEAR)

        selectedDay = intent.getIntExtra("Day", day)
        selectedMonth = intent.getIntExtra("Month", month)
        selectedYear = intent.getIntExtra("Year", year)

        tvTimeRemaining.text =
            "${
                IntervalCalculator.calculateNextBirthDate(
                    selectedYear,
                    selectedMonth,
                    selectedDay
                )
            } left for your next Birth Day celebration!"

        tvPresentAge.text = "Currently you're \n${
            IntervalCalculator.calculateAge(
                selectedYear,
                selectedMonth,
                selectedDay
            )
        }\n old!"
    }
}
