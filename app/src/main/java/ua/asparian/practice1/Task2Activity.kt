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

class Task2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task2)
        // Отримуємо посилання на елементи інтерфейсу
        val inputC: EditText = findViewById(R.id.input_C)
        val inputH: EditText = findViewById(R.id.input_H)
        val inputS: EditText = findViewById(R.id.input_S)
        val inputO: EditText = findViewById(R.id.input_O)
        val inputW: EditText = findViewById(R.id.input_W)
        val inputA: EditText = findViewById(R.id.input_A)
        val inputV: EditText = findViewById(R.id.input_V)
        val Q_comb: EditText = findViewById(R.id.input_Q_comb)
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
            val C = inputC.text.toString().replace(",", ".").toDoubleOrNull() ?: 0.0
            val H = inputH.text.toString().replace(",", ".").toDoubleOrNull() ?: 0.0
            val S = inputS.text.toString().replace(",", ".").toDoubleOrNull() ?: 0.0
            val O = inputO.text.toString().replace(",", ".").toDoubleOrNull() ?: 0.0
            val W = inputW.text.toString().replace(",", ".").toDoubleOrNull() ?: 0.0
            val A = inputA.text.toString().replace(",", ".").toDoubleOrNull() ?: 0.0
            val V = inputV.text.toString().replace(",", ".").toDoubleOrNull() ?: 0.0
            val Q_comb_value = Q_comb.text.toString().replace(",", ".").toDoubleOrNull() ?: 0.0

            // Розрахунок коефіцієнту переходу до робочої маси
            val KRS = (100 - W - A) / 100
            val C_work = C * KRS
            val H_work = H * KRS
            val S_work = S * KRS
            val O_work = O * KRS
            val V_work = V * (100 - W) / 100
            val Q_r = Q_comb_value * (100 - W - A) / 100 - 0.025 * W

            // Виведення результатів
            val result = """   
            Склад робочої маси:
            Вуглець: ${"%.2f".format(C_work)}%
            Водень: ${"%.2f".format(H_work)}%
            Сірка: ${"%.2f".format(S_work)}%
            Кисень: ${"%.2f".format(O_work)}%
            Ванадій: ${"%.1f".format(V_work)} мг/кг
            Зола: ${"%.2f".format(A)}%
            
            Нижча теплота згоряння для робочої маси: ${"%.2f".format(Q_r)} МДж/кг
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
