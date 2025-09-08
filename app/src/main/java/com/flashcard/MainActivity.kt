package com.flashcard

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private lateinit var textViewQuestion: TextView
    private lateinit var textViewAnswer: TextView
    private lateinit var main: RelativeLayout
//    private lateinit var textViewAnswer1: TextView
//    private lateinit var textViewAnswer2: TextView
//    private lateinit var textViewAnswer3: TextView
//    private lateinit var imageViewShow: ImageView
//    private lateinit var imageViewHide: ImageView
    private lateinit var imageCirclePlus: ImageView

    // ResultLauncher pour recevoir les données
    private val addCardResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val data = result.data
            if (data != null) {
                val question = data.getStringExtra("question") ?: ""
                val answer = data.getStringExtra("answer") ?: ""

                // Remplacer le texte précédent par la nouvelle question
                textViewQuestion.text = question
                textViewAnswer.text = answer

                // Réinitialiser la visibilité
                textViewQuestion.visibility = View.VISIBLE
                textViewAnswer.visibility = View.INVISIBLE

                Toast.makeText(this, "Question mise à jour!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        initializeViews()
        setupClickListeners()
    }

    private fun initializeViews() {
        textViewQuestion = findViewById(R.id.flashcard_question)
        textViewAnswer = findViewById(R.id.flashcard_answer)
        main = findViewById(R.id.main)
//        textViewAnswer1 = findViewById(R.id.answer_1)
//        textViewAnswer2 = findViewById(R.id.answer_2)
//        textViewAnswer3 = findViewById(R.id.answer_3)
//        imageViewShow = findViewById(R.id.show)
//        imageViewHide = findViewById(R.id.hide)
        imageCirclePlus = findViewById(R.id.plus_circle)
    }

    private fun setupClickListeners() {
        textViewQuestion.setOnClickListener {
            textViewQuestion.visibility = View.INVISIBLE
            textViewAnswer.visibility = View.VISIBLE
        }

        textViewAnswer.setOnClickListener {
            textViewAnswer.visibility = View.INVISIBLE
            textViewQuestion.visibility = View.VISIBLE
        }

        main.setOnClickListener {
            resetAnswerBackgrounds()
        }

//        textViewAnswer1.setOnClickListener {
//            textViewAnswer1.setBackgroundColor(ContextCompat.getColor(this, R.color.red_color))
//            textViewAnswer2.setBackgroundColor(ContextCompat.getColor(this, R.color.bg_answer))
//            textViewAnswer3.setBackgroundColor(ContextCompat.getColor(this, R.color.green_color))
//        }
//
//        textViewAnswer2.setOnClickListener {
//            textViewAnswer1.setBackgroundColor(ContextCompat.getColor(this, R.color.bg_answer))
//            textViewAnswer2.setBackgroundColor(ContextCompat.getColor(this, R.color.red_color))
//            textViewAnswer3.setBackgroundColor(ContextCompat.getColor(this, R.color.green_color))
//        }
//
//        textViewAnswer3.setOnClickListener {
//            textViewAnswer1.setBackgroundColor(ContextCompat.getColor(this, R.color.bg_answer))
//            textViewAnswer2.setBackgroundColor(ContextCompat.getColor(this, R.color.bg_answer))
//            textViewAnswer3.setBackgroundColor(ContextCompat.getColor(this, R.color.green_color))
//        }

//        imageViewHide.setOnClickListener {
//            hideMultipleChoiceAnswers()
//        }
//
//        imageViewShow.setOnClickListener {
//            showMultipleChoiceAnswers()
//        }

        imageCirclePlus.setOnClickListener {
            openAddCardActivity()
        }
    }

    private fun resetAnswerBackgrounds() {
        val bgColor = ContextCompat.getColor(this, R.color.bg_answer)
//        textViewAnswer1.setBackgroundColor(bgColor)
//        textViewAnswer2.setBackgroundColor(bgColor)
//        textViewAnswer3.setBackgroundColor(bgColor)
    }

//    private fun hideMultipleChoiceAnswers() {
//        textViewAnswer1.visibility = View.INVISIBLE
//        textViewAnswer2.visibility = View.INVISIBLE
//        textViewAnswer3.visibility = View.INVISIBLE
//        imageViewHide.visibility = View.INVISIBLE
//        imageViewShow.visibility = View.VISIBLE
//    }

//    private fun showMultipleChoiceAnswers() {
//        textViewAnswer1.visibility = View.VISIBLE
//        textViewAnswer2.visibility = View.VISIBLE
//        textViewAnswer3.visibility = View.VISIBLE
//        imageViewHide.visibility = View.VISIBLE
//        imageViewShow.visibility = View.INVISIBLE
//    }

    private fun openAddCardActivity() {
        val intent = Intent(this, AddCardActivity::class.java)
        intent.putExtra("question", textViewQuestion.text.toString())
        intent.putExtra("answer", textViewAnswer.text.toString())
        addCardResultLauncher.launch(intent)
    }
}