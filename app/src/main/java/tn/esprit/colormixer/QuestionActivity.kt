package tn.esprit.colormixer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import tn.esprit.colormixer.databinding.ActivityQuestionBinding

const val RED = "RED"
const val BLUE = "BLUE"
const val YELLOW = "YELLOW"
const val PURPLE = "PURPLE"
const val GREEN = "GREEN"
const val ORANGE = "ORANGE"

const val NAME = "NAME"
const val MIXED_COLOR = "COLOR"
const val COLOR1 = "COLOR 1"
const val COLOR2 = "COLOR 2"
const val RESULT = "RESULT"
const val SUCCESS = "SUCCESS"
const val FAILED = "FAILED"

class QuestionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuestionBinding

    private var colorMixed = "NONE"
    private var color1 = "NONE"
    private var color2 = "NONE"
    private var name = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnMix.setOnClickListener {
            mixColor()
        }

    }

    private fun mixColor(){

        if (binding.tfFullName.text.toString().isEmpty()) {
            Snackbar.make(binding.contextView, "You must enter your name !", Snackbar.LENGTH_SHORT).show()
            return
        }

        if (binding.cbBlue.isChecked && binding.cbRed.isChecked && binding.cbYellow.isChecked){
            Snackbar.make(binding.contextView, "You must choose 2 colors !", Snackbar.LENGTH_SHORT).show()
            return
        }else if (binding.cbBlue.isChecked && binding.cbRed.isChecked){
            colorMixed = PURPLE
            color1 = BLUE
            color2 = RED
        }else if (binding.cbBlue.isChecked && binding.cbYellow.isChecked){
            colorMixed = GREEN
            color1 = BLUE
            color2 = YELLOW
        }else if (binding.cbYellow.isChecked && binding.cbRed.isChecked){
            colorMixed = ORANGE
            color1 = YELLOW
            color2 = RED
        }else{
            Snackbar.make(binding.contextView,"You must choose 2 colors !", Snackbar.LENGTH_SHORT).show()
            return
        }

        name = binding.tfFullName.text.toString()

        val intent = Intent(this, AnswerActivity::class.java).apply {
            putExtra(NAME, name)
            putExtra(MIXED_COLOR, colorMixed)
            putExtra(COLOR1, color1)
            putExtra(COLOR2, color2)
        }

        startActivity(intent)
    }
}