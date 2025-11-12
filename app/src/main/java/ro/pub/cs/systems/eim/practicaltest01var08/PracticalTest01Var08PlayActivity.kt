package ro.pub.cs.systems.eim.practicaltest01var08

import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import androidx.activity.enableEdgeToEdge

class PracticalTest01Var08PlayActivity : AppCompatActivity() {
    private lateinit var riddleTextView : TextView
    private lateinit var btnCancel : Button
    private lateinit var btnSave : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.secondary_activity)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.secondaryLayout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var gotIntent  = intent;
        gotIntent?.let {
            it.getStringExtra("TEXT_PRINCIPAL")?.let {
                editableText.setText(it)
            }
        }

        btnSave.setOnClickListener {
            setResult(RESULT_OK)
            finish()
        }

        btnCancel.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }



    }
}