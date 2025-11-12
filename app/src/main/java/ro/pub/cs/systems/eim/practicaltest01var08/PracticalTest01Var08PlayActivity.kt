package ro.pub.cs.systems.eim.practicaltest01var08
import android.util.Log
import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import androidx.activity.enableEdgeToEdge

class PracticalTest01Var08PlayActivity : AppCompatActivity() {
    private lateinit var riddleTextView : TextView
    private lateinit var editTextAnswerSecondary : EditText
    private lateinit var btnCheck : Button
    private lateinit var btnBack : Button
    private lateinit var correctAnswer : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.secondary_activity)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.secondaryLayout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        riddleTextView = findViewById(R.id.textViewSecondaryRiddle)
        editTextAnswerSecondary = findViewById(R.id.editTextSecondaryAnswer)
        btnCheck = findViewById(R.id.btnCheck)
        btnBack = findViewById(R.id.btnBack)

        var gotIntent  = intent;
        gotIntent?.let {
            it.getStringExtra("RIDDLE")?.let {
                riddleTextView.text = it
                Log.d("[cosminDima]", it);
            }

            it.getStringExtra("ANSWER")?.let {
                correctAnswer = it
                Log.d("[cosminDima]", it);
            }
        }

        btnCheck.setOnClickListener {
            if(correctAnswer.toString() == editTextAnswerSecondary.text.toString())
                setResult(RESULT_OK)
            else
                setResult(RESULT_CANCELED)
            finish()
        }

        btnBack.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }



    }


}