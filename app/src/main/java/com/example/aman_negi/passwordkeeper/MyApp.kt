package com.example.aman_negi.passwordkeeper

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val config = RealmConfiguration.Builder().name("person.realm").build()
        Realm.setDefaultConfiguration(config)
    }
}