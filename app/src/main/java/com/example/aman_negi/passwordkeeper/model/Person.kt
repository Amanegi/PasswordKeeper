package com.example.aman_negi.passwordkeeper.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

//we need a primary empty constructor of model class that is why '()'
@RealmClass
open class Person() : RealmObject() {
    @PrimaryKey
    var msite: String? = null
    var musername: String? = null
    var mpassword: String? = null
}