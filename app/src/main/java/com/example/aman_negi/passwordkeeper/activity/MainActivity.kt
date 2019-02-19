package com.example.aman_negi.passwordkeeper.activity

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.aman_negi.passwordkeeper.R
import com.example.aman_negi.passwordkeeper.RowLayout
import com.example.aman_negi.passwordkeeper.adapter.MyAdapter
import com.example.aman_negi.passwordkeeper.model.Person
import com.example.aman_negi.passwordkeeper.model.RealmHelper
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var data = mutableListOf<RowLayout>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mRealm = Realm.getDefaultInstance()
        recyclerView.layoutManager = LinearLayoutManager(this)
        //get data from Realm database
        val results = RealmHelper(mRealm, this).retrieveAll()
        //data source
        var rowLayout: RowLayout
        for (p in results) {
            rowLayout = RowLayout(p.msite, p.musername, p.mpassword)
            data.add(rowLayout)
        }
        val adapter = MyAdapter(this, data)
        recyclerView.adapter = adapter
    }

    //options menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = getMenuInflater()
        menuInflater.inflate(R.menu.options_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_add -> {
                startActivity(Intent(this, AddDataActivity::class.java))
                finish()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    //context menu
    override fun onContextItemSelected(item: MenuItem?): Boolean {
        val mRealm = Realm.getDefaultInstance()
        val position = item?.groupId
        val realmHelper = RealmHelper(mRealm, this)
        val results = mRealm.where(Person::class.java).equalTo("msite", data[position!!].site).findFirst()
        when (item.itemId) {
            111 -> {
                Toast.makeText(this, "Username Copied", Toast.LENGTH_SHORT).show()
                val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clip: ClipData = ClipData.newPlainText("username", results?.musername)
                clipboard.primaryClip = clip
                return true
            }
            112 -> {
                Toast.makeText(this, "Edit", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, UpdateActivity::class.java)
                        .putExtra("siteKey", data[position].site)
                        .putExtra("usernameKey", data[position].username)
                        .putExtra("passwordKey", data[position].password))
                return true
            }
            113 -> {
                Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show()
                realmHelper.delete(results)
                startActivity(Intent(this, MainActivity::class.java))
                finish()
                return true
            }
            else -> {
                return super.onContextItemSelected(item)
            }
        }
    }

}
