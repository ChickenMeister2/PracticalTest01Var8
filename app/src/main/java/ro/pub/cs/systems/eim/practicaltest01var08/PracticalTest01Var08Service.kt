package ro.pub.cs.systems.eim.practicaltest01var08

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service.START_REDELIVER_INTENT
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.os.Build
import android.os.IBinder
import kotlin.math.sin
import kotlin.random.Random
import android.util.Log
import androidx.core.app.NotificationCompat
import android.R.attr.text
import android.app.Service.START_REDELIVER_INTENT
import android.content.Context.NOTIFICATION_SERVICE
import android.annotation.SuppressLint
import android.app.Service

class PracticalTest01Var08Service : Service() {
    private lateinit var answerToPrint: String
    private var answerLength: Int = 0
    override fun onCreate() {
        super.onCreate()
        Log.d(Constants.TAG, "onCreate() method was invoked")
        val channelId = "my_channel_01"
        val channel = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel(
                channelId,
                "Channel human readable title",
                NotificationManager.IMPORTANCE_DEFAULT
            )
        } else {
            TODO("VERSION.SDK_INT < O")
        }

        (getSystemService(NOTIFICATION_SERVICE) as NotificationManager)
            .createNotificationChannel(channel)

        val notification: Notification = NotificationCompat.Builder(this, channelId)
            .setContentTitle("")
            .setContentText("")
            .build()

        startForeground(1, notification)
    }

    override fun onBind(intent: Intent): IBinder? {
        Log.d(Constants.TAG, "onBind() method was invoked")
        return null
    }

    override fun onUnbind(intent: Intent): Boolean {
        Log.d(Constants.TAG, "onUnbind() method was invoked")
        return true
    }

    override fun onRebind(intent: Intent) {
        Log.d(Constants.TAG, "onRebind() method was invoked")
    }

    override fun onDestroy() {
        Log.d(Constants.TAG, "onDestroy() method was invoked")
        super.onDestroy()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
//        Log.d(Constants.TAG, "onStartCommand() method was invoked")

        intent?.getStringExtra("STRING_FOR_BROADCAST")?.let {
            answerToPrint = it
            answerLength = answerToPrint.length
        }
        Log.d(Constants.TAG, "onstart pornit valoarea e $answerToPrint")

        val processingThread = ProcessingThread(this);
        processingThread.start();
        return START_REDELIVER_INTENT
    }

    inner class ProcessingThread(private val context: Context) : Thread() {

        override fun run() {
            Log.d(
                Constants.TAG,
                "Thread.run() was invoked, PID: ${android.os.Process.myPid()} TID: ${android.os.Process.myTid()}"
            )
            while (true) {
                sendMessage(answerToPrint)
                sleep()
            }
            stopSelf()
        }

        private fun sleep() {
            try {
                sleep(Constants.SLEEP_TIME)
            } catch (interruptedException: InterruptedException) {
                Log.e(
                    Constants.TAG,
                    interruptedException.message ?: "InterruptedException occurred"
                )
                interruptedException.printStackTrace()
            }
        }

        private fun sendMessage(cuvant: String) {
            val randomCharToChange = (0..answerLength - 1).random()
            val starsToPrint = String(CharArray(answerLength) { '*' }).toString()
            starsToPrint.replaceRange(randomCharToChange,randomCharToChange,
                cuvant.elementAt(randomCharToChange).toString()
            )
            val newStr = starsToPrint.substring(0, randomCharToChange) + cuvant.toString().get(randomCharToChange) + starsToPrint.substring(randomCharToChange + 1);
            sendBroadcast(
                Intent().apply {
                    action = Constants.BROADCAST_TAG
                    putExtra("message", newStr)
                }
            )
        }

    }
}