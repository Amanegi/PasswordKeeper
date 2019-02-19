package com.example.aman_negi.passwordkeeper.model

import android.content.Context
import android.widget.Toast
import com.shashank.sony.fancytoastlib.FancyToast
import io.realm.Realm
import io.realm.RealmResults


class RealmHelper(internal var realm: Realm, var context: Context) {

    //WRITE
    fun save(site: String, username: String, password: String) {
        realm.executeTransactionAsync({ realm: Realm ->
            //primary key value
            val user = realm.createObject(Person::class.java, site)
            user.musername = username
            user.mpassword = password
        }, {
            FancyToast.makeText(context, "Data Added Successfully", FancyToast.SUCCESS, FancyToast.LENGTH_SHORT, false)
                    .show()
        }, {
            Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
            Toast.makeText(context, "Value Already Exist", Toast.LENGTH_SHORT).show()
        })
    }

    //READ
    fun retrieveAll(): RealmResults<Person> = realm.where(Person::class.java).findAll()

    //DELETE
    fun delete(result: Person?) {
        realm.executeTransaction { result?.deleteFromRealm() }
    }

    //UPDATE
    fun update(site: String, username: String, password: String) {
        val person = Person()
        person.msite = site
        person.musername = username
        person.mpassword = password
        realm.executeTransaction { realm -> realm.copyToRealmOrUpdate(person) }
    }
}