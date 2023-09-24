package tn.esprit.colormixer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import tn.esprit.colormixer.databinding.ActivityAnswerBinding
import tn.esprit.colormixer.databinding.ActivityQuestionBinding

class AnswerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAnswerBinding

    private var correctColor = "NONE"
    private var name = "NONE"
    private var color1 = "NONE"
    private var color2 = "NONE"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAnswerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        correctColor = intent.getStringExtra(MIXED_COLOR).toString()
        name = intent.getStringExtra(NAME).toString()

        color1 = intent.getStringExtra(COLOR1).toString()
        color2 = intent.getStringExtra(COLOR2).toString()

        binding.txtChoosed.text = "You choosed $color1 and $color2"

        binding.btnSubmit.setOnClickListener {

            if (!binding.rbGreen.isChecked && !binding.rbPurple.isChecked && !binding.rbOrange.isChecked){
                Snackbar.make(binding.contextView,  "Choose your answer !", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent(this, ResultActivity::class.java).apply {
                putExtra(NAME, name)
                putExtra(RESULT, FAILED)
            }

            if (checkAnswer())
                intent.apply { putExtra(RESULT, SUCCESS) }

            startActivity(intent)
            finish()
        }
    }

    private fun checkAnswer(): Boolean{

        if(binding.rbGreen.isChecked && correctColor.equals(GREEN)){
            return true
        }else if (binding.rbOrange.isChecked && correctColor.equals(ORANGE)){
            return true
        }else if (binding.rbPurple.isChecked && correctColor.equals(PURPLE)){
            return true
        }

        return false
    }
}