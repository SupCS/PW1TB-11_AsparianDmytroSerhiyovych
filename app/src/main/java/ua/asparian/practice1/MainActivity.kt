package ua.asparian.practice1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val task1Button: Button = findViewById(R.id.task1_button)
        val task2Button: Button = findViewById(R.id.task2_button)

        task1Button.setOnClickListener {
            val intent = Intent(this, Task1Activity::class.java)
            startActivity(intent)
        }

        task2Button.setOnClickListener {
            val intent = Intent(this, Task2Activity::class.java)
            startActivity(intent)
        }
    }
}
