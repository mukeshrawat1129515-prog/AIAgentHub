package com.example.aiagenthub

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*
import java.net.URL

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val promptInput = findViewById<EditText>(R.id.promptInput)
        val sendButton = findViewById<Button>(R.id.sendButton)
        val resClaude = findViewById<TextView>(R.id.resClaude)
        val resGPT = findViewById<TextView>(R.id.resGPT)
        val resGemini = findViewById<TextView>(R.id.resGemini)

        sendButton.setOnClickListener {
            val prompt = promptInput.text.toString()
            if (prompt.isEmpty()) {
                Toast.makeText(this, "Please enter a prompt", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            GlobalScope.launch(Dispatchers.Main) {
                sendButton.isEnabled = false
                resClaude.text = "Claude is thinking..."
                resGPT.text = "GPT-4 is thinking..."
                resGemini.text = "Gemini is thinking..."

                try {
                    val response = withContext(Dispatchers.IO) {
                        URL("https://your-worker.your-subdomain.workers.dev").readText()
                    }
                    resClaude.text = "Claude: " + response 
                    resGPT.text = "GPT-4: " + response
                    resGemini.text = "Gemini: " + response
                } catch (e: Exception) {
                    resClaude.text = "Error: ${e.message}"
                }
                sendButton.isEnabled = true
            }
        }
    }
}
