package com.rymo.balout

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.rymo.balout.model.User
import com.rymo.ciel.dao.UserDao
import com.rymo.ciel.database.AppDatabase

class SplashActivity : AppCompatActivity() {

    val context: Context = this
    lateinit var userDao: UserDao
    lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        var db = AppDatabase.getAppDatabase(context)
        userDao = db.getUserDao()

            object : CountDownTimer(3000, 3000) {
                override fun onTick(millisUntilFinished: Long) {

                }

                override fun onFinish() {
                    if (userDao.get()==null) {
                        startActivity(Intent(context, SignInActivity::class.java))
                        finish()
                    }else{
                        startActivity(Intent(context, MainActivity::class.java))
                        finish()
                    }
                }

            }.start()

    }
}