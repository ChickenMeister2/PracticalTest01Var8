package ro.pub.cs.systems.eim.practicaltest01var08

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PracticalTest01Var08MainActivity : AppCompatActivity() {
    private lateinit var riddleText : EditText
    private lateinit var answerText : EditText
    private lateinit var btnPlay : Button

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
        btnPlay.setOnClickListener {
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
}