package ro.pub.cs.systems.eim.practicaltest01var08

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class Receiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Log.d("[recievedmain]","am primit ceva" + intent.action)
        val action = intent.action
        val data: String? = when (action) {
            Constants.BROADCAST_TAG -> intent.getStringExtra("message")
            else -> "Nu stiu ce e in el"
        }
        Toast.makeText(context, data, Toast.LENGTH_SHORT).show()
//        adb shell am broadcast -a com.colocviurezolvatacasa.BROADCAST --es "message" "Salut lume!"
//        adb shell am broadcast -a com.colocviurezolvatacasa.BROADCAST \
//            -n com.colocviurezolvatacasa/.Receiver --es "message" "hello"
//        Log.d("[recievedmain]", "Am primit $data")

    }


}
