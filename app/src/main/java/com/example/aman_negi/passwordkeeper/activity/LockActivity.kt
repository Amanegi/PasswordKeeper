package com.example.aman_negi.passwordkeeper.activity

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.andrognito.patternlockview.PatternLockView
import com.andrognito.patternlockview.listener.PatternLockViewListener
import com.andrognito.patternlockview.utils.PatternLockUtils
import com.example.aman_negi.passwordkeeper.R
import com.shashank.sony.fancytoastlib.FancyToast
import kotlinx.android.synthetic.main.activity_lock.*

class LockActivity : AppCompatActivity() {
    var savedPattern = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lock)

        val patternView = findViewById<PatternLockView>(R.id.patternView)
        val sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)
        val patternString = sharedPreferences.getString("key_pattern", null)
        if (patternString == null) {
            startActivity(Intent(this, SetLockActivity::class.java))
            finish()
        } else {
            savedPattern = patternString
        }
        ObjectAnimator.ofFloat(img, "translationY", -330f).apply {
            duration = 1000
            start()
        }

        img.postDelayed({
            patternView.visibility = View.VISIBLE
            txtDrawPattern.visibility = View.VISIBLE
        }, 1000)

        patternView.isTactileFeedbackEnabled = true
        patternView.addPatternLockListener(object : PatternLockViewListener {
            override fun onStarted() {}

            override fun onProgress(progressPattern: MutableList<PatternLockView.Dot>?) {}

            override fun onComplete(pattern: MutableList<PatternLockView.Dot>?) {
                if (PatternLockUtils.patternToString(patternView, pattern) == savedPattern) {
                    FancyToast.makeText(
                        this@LockActivity,
                        "Welcome",
                        FancyToast.LENGTH_SHORT,
                        FancyToast.SUCCESS,
                        false
                    ).show()
                    startActivity(Intent(this@LockActivity, MainActivity::class.java))
                    finish()
                } else {
                    FancyToast.makeText(
                        this@LockActivity,
                        "Incorrect Pattern",
                        FancyToast.LENGTH_SHORT,
                        FancyToast.ERROR,
                        false
                    ).show()
                }
                patternView.clearPattern()
            }

            override fun onCleared() {}
        })

    }
}