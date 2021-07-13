package com.rymo.balout

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rymo.balout.model.User
import com.rymo.ciel.dao.UserDao
import com.rymo.ciel.database.AppDatabase
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : AppCompatActivity() {

    val context : Context = this
    lateinit var userDao: UserDao
    lateinit var db : AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        db = AppDatabase.getAppDatabase(context)
        userDao = db.getUserDao()

        btn_login.setOnClickListener {
            val user = User()
            user.name = et_firstName.text.toString()
            userDao.insert(user)
            startActivity(Intent(context, MainActivity::class.java))
            finish()
        }

    }
}