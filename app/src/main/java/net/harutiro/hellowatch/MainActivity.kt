package net.harutiro.hellowatch

import android.app.Activity
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import net.harutiro.hellowatch.databinding.ActivityMainBinding

class MainActivity : Activity() , SensorEventListener {

    private lateinit var binding: ActivityMainBinding

    //センサー周り
    private lateinit var sensorManager: SensorManager
    private var accSensor: Sensor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION)

    }

    //センサーに何かしらのイベントが発生したときに呼ばれる
    override fun onSensorChanged(event: SensorEvent) {
        val sensorX: Float
        val sensorY: Float
        val sensorZ: Float
        // Remove the gravity contribution with the high-pass filter.
        if (event.sensor.type == Sensor.TYPE_LINEAR_ACCELERATION) {
            sensorX = event.values[0]
            sensorY = event.values[1]
            sensorZ = event.values[2]
            val strTmp = """加速度センサー
                         X: $sensorX
                         Y: $sensorY
                         Z: $sensorZ"""
            binding.senserText.text = strTmp

            Log.d("mainActivity",strTmp)
        }
    }
    //センサの精度が変更されたときに呼ばれる
    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
    }

    override fun onResume() {
        super.onResume()
        //リスナーとセンサーオブジェクトを渡す
        //第一引数はインターフェースを継承したクラス、今回はthis
        //第二引数は取得したセンサーオブジェクト
        //第三引数は更新頻度 UIはUI表示向き、FASTはできるだけ早く、GAMEはゲーム向き
        sensorManager.registerListener(this, accSensor, SensorManager.SENSOR_DELAY_UI)
    }

    //アクティビティが閉じられたときにリスナーを解除する
    override fun onPause() {
        super.onPause()
        //リスナーを解除しないとバックグラウンドにいるとき常にコールバックされ続ける
        sensorManager.unregisterListener(this)
    }
}