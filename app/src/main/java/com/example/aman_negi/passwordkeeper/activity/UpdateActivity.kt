package com.example.aman_negi.passwordkeeper.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.aman_negi.passwordkeeper.R
import com.example.aman_negi.passwordkeeper.model.RealmHelper
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_add_data.*

class UpdateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_data)
        val mRealm = Realm.getDefaultInstance()
        val realmHelper = RealmHelper(mRealm, this)
        //fetching intent data
        val intentSite = intent.getStringExtra("siteKey")
        val intentUsername = intent.getStringExtra("usernameKey")
        val intentPassword = intent.getStringExtra("passwordKey")
        //ui changes
        btnOk.setText("Update")
        edt_site.visibility = View.GONE
        txtSiteName.append(" : " + intentSite)
        edt_username.setText(intentUsername)
        edt_password.setText(intentPassword)

        btnOk.setOnClickListener {
            realmHelper.update(edt_site.text.toString(), edt_username.text.toString(), edt_password.text.toString())
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        btnCancel.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}