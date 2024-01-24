package net.harutiro.hellowatch

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class SensorService : Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()

        Log.d("service","onCreate")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        Log.d("service","onStartCommand")


        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d("servce","onDestroy")
    }



}