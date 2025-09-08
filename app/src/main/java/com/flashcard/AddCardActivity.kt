package com.flashcard

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class AddCardActivity : AppCompatActivity() {


    private lateinit var questionEditText: EditText
    private lateinit var answerEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.add_card_activity)
        questionEditText = findViewById(R.id.editTextQuestion)
        answerEditText = findViewById(R.id.editTextAnswer)


        val imageClose = findViewById<ImageView>(R.id.close)
        val download = findViewById<ImageView>(R.id.download)

        download.setOnClickListener {
            saveFlashcard()
        }

        imageClose.setOnClickListener {
            finish()
        }

        val existingQuestion = intent.getStringExtra("question")
        val existingAnswer = intent.getStringExtra("answer")

        if (!existingQuestion.isNullOrEmpty()) {
            questionEditText.setText(existingQuestion)
        }
        if (!existingAnswer.isNullOrEmpty()) {
            answerEditText.setText(existingAnswer)
        }

    }



    private fun saveFlashcard() {
        val question = questionEditText.text.toString().trim()
        val answer = answerEditText.text.toString().trim()

        if (question.isEmpty() || answer.isEmpty()) {
            Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show()
            return
        }

        val data = Intent()
        data.putExtra("question", question)
        data.putExtra("answer", answer)

        setResult(RESULT_OK, data)
        finish()
    }

}