package ua.asparian.practice1

import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.content.Context
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
class Task1Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task1)

        // Отримуємо посилання на елементи інтерфейсу
        val inputH: EditText = findViewById(R.id.input_H)
        val inputC: EditText = findViewById(R.id.input_C)
        val inputS: EditText = findViewById(R.id.input_S)
        val inputN: EditText = findViewById(R.id.input_N)
        val inputO: EditText = findViewById(R.id.input_O)
        val inputW: EditText = findViewById(R.id.input_W)
        val inputA: EditText = findViewById(R.id.input_A)
        val calculateButton: Button = findViewById(R.id.calculate_button)
        val resultText: TextView = findViewById(R.id.result_text)

        // Кнопка для повернення на головний екран
        val backButton: ImageButton = findViewById(R.id.back_button)
        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // Обробка події натискання на кнопку "Розрахувати"
        calculateButton.setOnClickListener {
            // Приховуємо клавіатуру
            hideKeyboard()

            // Отримуємо та перетворюємо введені дані
            val H = inputH.text.toString().replace(",", ".").toDoubleOrNull() ?: 0.0
            val C = inputC.text.toString().replace(",", ".").toDoubleOrNull() ?: 0.0
            val S = inputS.text.toString().replace(",", ".").toDoubleOrNull() ?: 0.0
            val N = inputN.text.toString().replace(",", ".").toDoubleOrNull() ?: 0.0
            val O = inputO.text.toString().replace(",", ".").toDoubleOrNull() ?: 0.0
            val W = inputW.text.toString().replace(",", ".").toDoubleOrNull() ?: 0.0
            val A = inputA.text.toString().replace(",", ".").toDoubleOrNull() ?: 0.0

            // Розрахунок коефіцієнтів переходу до сухої та горючої маси
            val KRS = 100 / (100 - W)
            val KRG = 100 / (100 - W - A)

            // Розрахунок нижчої теплоти згоряння для робочої маси
            val QH = (339 * C + 1030 * H - 108.8 * (O - S) - 25 * W) / 1000

            // Розрахунок нижчої теплоти згоряння для сухої та горючої маси
            val QH_dry = (QH + 0.025 * W) * KRS
            val QH_comb = (QH + 0.025 * W) * KRG

            // Виведення результатів
            val result = """
            Коефіцієнт переходу до сухої маси: ${"%.2f".format(KRS)}
            Коефіцієнт переходу до горючої маси: ${"%.2f".format(KRG)}
            
            Склад сухої маси:
            H = ${"%.2f".format(H * KRS)}%, C = ${"%.2f".format(C * KRS)}%, S = ${"%.2f".format(S * KRS)}%, N = ${"%.3f".format(N * KRS)}%, O = ${"%.2f".format(O * KRS)}%, A = ${"%.2f".format(A * KRS)}%
            
            Склад горючої маси:
            H = ${"%.2f".format(H * KRG)}%, C = ${"%.2f".format(C * KRG)}%, S = ${"%.2f".format(S * KRG)}%, N = ${"%.3f".format(N * KRG)}%, O = ${"%.2f".format(O * KRG)}%
            
            Нижча теплота згоряння для робочої маси: ${"%.5f".format(QH)} МДж/кг
            Нижча теплота згоряння для сухої маси: ${"%.5f".format(QH_dry)} МДж/кг
            Нижча теплота згоряння для горючої маси: ${"%.5f".format(QH_comb)} МДж/кг
            """.trimIndent()

            resultText.text = result
        }
    }
    // Функція для приховування клавіатури
    private fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}
