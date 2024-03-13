package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class EmptyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_empty)

        // Получаем переданное название телефона
        val phoneName = intent.getStringExtra("phone_name")

        // Отображаем название телефона на пустой странице
        val textView = findViewById<TextView>(R.id.textView)
        textView.text = "Selected phone: $phoneName"

        // Находим кнопку "Назад" по ее идентификатору
        val backButton = findViewById<Button>(R.id.backButton)

        // Устанавливаем обработчик нажатия на кнопку "Назад"
        backButton.setOnClickListener {
            // Вызываем метод onBackPressed(), чтобы вернуться на предыдущий экран
            onBackPressed()
        }
    }
}

