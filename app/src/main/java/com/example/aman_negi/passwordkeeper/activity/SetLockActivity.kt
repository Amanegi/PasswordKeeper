package com.example.aman_negi.passwordkeeper.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.andrognito.patternlockview.PatternLockView
import com.andrognito.patternlockview.listener.PatternLockViewListener
import com.andrognito.patternlockview.utils.PatternLockUtils
import com.example.aman_negi.passwordkeeper.R
import com.shashank.sony.fancytoastlib.FancyToast
import kotlinx.android.synthetic.main.activity_set_lock.*

class SetLockActivity : AppCompatActivity() {
    var savePattern = ""
    var temp = ""
    var onClearedCalled = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_lock)

        val setPatternView = findViewById<PatternLockView>(R.id.setPatternView)

        val sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        setPatternView.isTactileFeedbackEnabled = true
        setPatternView.addPatternLockListener(object : PatternLockViewListener {
            override fun onComplete(pattern: MutableList<PatternLockView.Dot>?) {
                savePattern = PatternLockUtils.patternToString(setPatternView, pattern)
                if (savePattern.length <= 3) {
                    FancyToast.makeText(this@SetLockActivity, "Connect Atleast 4 Dots", FancyToast.LENGTH_SHORT, FancyToast.WARNING, false).show()
                } else {
                    if (onClearedCalled) {
                        if (savePattern == temp) {
                            FancyToast.makeText(this@SetLockActivity, "Pattern Saved", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, false).show()
                            editor.putString("key_pattern", savePattern)
                            editor.apply()
                            startActivity(Intent(this@SetLockActivity, LockActivity::class.java))
                            finish()
                        } else {
                            FancyToast.makeText(this@SetLockActivity, "Wrong Pattern", FancyToast.LENGTH_SHORT, FancyToast.WARNING, false).show()

                        }
                    } else {
                        onCleared()
                    }
                }
                setPatternView.clearPattern()
            }

            override fun onCleared() {
                onClearedCalled = true
                txtSetpattern.text = "Confirm Pattern"
                temp = savePattern
            }

            override fun onStarted() {}

            override fun onProgress(progressPattern: MutableList<PatternLockView.Dot>?) {}

        })

    }
}
