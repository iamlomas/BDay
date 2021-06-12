package com.iamlomas.bday

import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bday.IntervalCalculator
import kotlinx.android.synthetic.main.activity_display_name.*
import java.util.*

class DisplayNameActivity : AppCompatActivity() {

    private lateinit var music: MediaPlayer
    private var selectedDay: Int = 0
    private var selectedMonth: Int = 0
    private var selectedYear: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_name)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        val calendar = Calendar.getInstance()
        selectedDay = intent.getIntExtra("Day", calendar.get(Calendar.DAY_OF_MONTH))
        selectedMonth = intent.getIntExtra("Month", calendar.get(Calendar.MONTH))
        selectedYear = intent.getIntExtra("Year", calendar.get(Calendar.YEAR))

        tvName.text = intent.getStringExtra("First_Name")

        tvCurrentAge.text = "Congrats! Now you're\n${
            IntervalCalculator.calculateAge(
                selectedYear,
                selectedMonth,
                selectedDay
            )
        }\n old!"

        music = MediaPlayer.create(this, R.raw.happy_bday).apply {
            start()
        }
    }

    fun play(view: View) {
        music.start()
    }

    override fun onPause() {
        super.onPause()
        music.release()
        finish()
    }
}
