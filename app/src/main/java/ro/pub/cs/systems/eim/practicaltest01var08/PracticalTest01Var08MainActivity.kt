package ro.pub.cs.systems.eim.practicaltest01var08

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PracticalTest01Var08MainActivity : AppCompatActivity() {
    private lateinit var riddleText : EditText
    private lateinit var answerText : EditText
    private lateinit var btnPlay : Button
    private var lastStatus : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_practical_test01_var08_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnPlay = findViewById(R.id.playButton)
        riddleText = findViewById(R.id.editTextRiddle)
        answerText = findViewById(R.id.editTextAnswer)

        savedInstanceState?.getString("ANSWER")?.let {
            answerText.append(it)
        }
        savedInstanceState?.getString("RIDDLE")?.let {
            riddleText.append(it)
        }
        savedInstanceState?.getInt("LAST_STATUS")?.let {
            if(it == 1) {
                Toast.makeText(this, "Last was a win", Toast.LENGTH_SHORT).show()
            } else
                Toast.makeText(this, "Last was a Lose", Toast.LENGTH_SHORT).show()
        }


        btnPlay.setOnClickListener {
            if(riddleText.text != null && answerText.text != null)
                try {
                    startActivityForResult(
                        Intent(this, PracticalTest01Var08PlayActivity::class.java).apply {
                            putExtra("RIDDLE", riddleText.text.toString())
                            putExtra("ANSWER", answerText.text.toString())
                        },2
                    )
                } catch (e : RuntimeException) {
                    Log.d("[aplicatie]", e.message.toString())
                }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 2) {
            when(resultCode) {
                RESULT_OK -> {
                    Toast.makeText(this, "Victory", Toast.LENGTH_SHORT).show()
                    lastStatus = 1
                }
                RESULT_CANCELED -> {
                    Toast.makeText(this, "Fail", Toast.LENGTH_SHORT).show()
                    lastStatus = 0
                }
                else -> {
                    Toast.makeText(this, "UNKN", Toast.LENGTH_SHORT).show()
                }
            }
            riddleText.setText("")
            answerText.setText("")
        }
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("ANSWER",answerText.text.toString())
        outState.putString("RIDDLE",riddleText.text.toString())
        outState.putInt("LAST_STATUS",lastStatus)
//        Toast.makeText(this, "Am salvat" + editable.append(outState.getString("SAVED_TEXT")), Toast.LENGTH_SHORT).show()
    }
}