package com.example.aman_negi.passwordkeeper.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.aman_negi.passwordkeeper.R
import com.example.aman_negi.passwordkeeper.model.RealmHelper
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_add_data.*

class AddDataActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_data)
        val mRealm = Realm.getDefaultInstance()
        val realmHelper = RealmHelper(mRealm, this)

        btnOk.setOnClickListener {
            realmHelper.save(edt_site.text.toString(), edt_username.text.toString(), edt_password.text.toString())
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        btnCancel.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}
