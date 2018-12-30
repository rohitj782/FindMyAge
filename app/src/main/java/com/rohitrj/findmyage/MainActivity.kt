package com.rohitrj.findmyage

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showbutton.setOnClickListener {

            CalculateAge()

        }

        refresh.setOnClickListener {
          RequestRefresh()
        }

        howitworks.setOnClickListener {
            HowItWorks()
        }

        changecolor.setOnClickListener {
            ChangeColor()
        }

        MobileAds.initialize(this, "ca-app-pub-5707633482389752~4492064573")

        lateinit var mAdView : AdView

        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        mAdView.adListener = object: AdListener() {
            override fun onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            override fun onAdFailedToLoad(errorCode : Int) {
                // Code to be executed when an ad request fails.
            }

            override fun onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            override fun onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            override fun onAdClosed() {
                // Code to be executed when when the user is about to return
                // to the app after tapping on an ad.
            }
        }

    }

    private fun ChangeColor() {

        val choice=(1..5).random()
        when(choice){
            1-> {
                constraintlayout.setBackgroundColor(Color.CYAN)
                return
            }
            2->{
                constraintlayout.setBackgroundColor(Color.YELLOW)
                return
            }
            3->{
                constraintlayout.setBackgroundColor(Color.GRAY)
                return
            }
            4->{
                constraintlayout.setBackgroundColor(Color.LTGRAY)
                return
            }
            5->{
                constraintlayout.setBackgroundColor(Color.parseColor("#f1be26"))
                return
            }
        }
    }


    private fun HowItWorks() {
        textname.setText("JOHN")
        textyear.setText("1990")
        textmonth.setText("4")
        textdate.setText("14")

        CalculateAge()
    }

    private fun RequestRefresh() {

        textname.setText("")
        textdate.setText("")
        textmonth.setText("")
        textyear.setText("")

        displaytext.setText("")
        displaytext.visibility = View.INVISIBLE

    }

    private fun CalculateAge() {

        val name = textname.text.toString()
        val date = textdate.text.toString()
        val month = textmonth.text.toString()
        val year = textyear.text.toString()


        var currentDate = Calendar.getInstance().get(Calendar.DATE)
        var currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1
        var currentYear = Calendar.getInstance().get(Calendar.YEAR)


        if (name.isEmpty() || name.length > 25) {
            if (name.isEmpty())
                textname.setError("Enter VAlid Input")
            else
                textname.setError("Limit Exceeded (25)")
            textname.requestFocus()
            return
        }
        if (date.isEmpty() || date.toInt() > 31 || date.toInt() < 1) {
            textdate.setError("Enter Valid Input")
            textdate.requestFocus()
            return
        }
        if (month.isEmpty() || month.toInt() > 12 || month.toInt() < 1) {
            textmonth.setError("Enter Valid Input")
            textmonth.requestFocus()
            return
        }
        if (year.isEmpty() || year.toInt() > currentYear) {
            textyear.setError("Enter Valid Input")
            textyear.requestFocus()
            return
        }

        var myDate = date.toInt()
        var myMonth = month.toInt()
        var myYear = year.toInt()


        val noOfDays = intArrayOf(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)

        if (myDate > currentDate) {
            currentMonth = currentMonth - 1
            currentDate = currentDate + noOfDays[myMonth - 1]
        }
        if (myMonth > currentMonth) {
            currentYear = currentYear - 1
            currentMonth = currentMonth + 12
        }

        var tDate = currentDate - myDate
        var tYear = currentYear - myYear
        var tMonth = currentMonth - myMonth

        displaytext.text = "$name You Are $tYear Years $tMonth Months $tDate Days Old"
        displaytext.visibility = View.VISIBLE


    }
}