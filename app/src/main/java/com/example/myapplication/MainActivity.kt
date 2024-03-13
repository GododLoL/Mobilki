package com.example.myapplication

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {

    private lateinit var listView: ListView // Ссылка на ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val timerTextView = findViewById<TextView>(R.id.timerTextView)


        listView = findViewById<ListView>(R.id.listView)

        // Получаем массив элементов списка смартфонов
        val smartphones = arrayOf(
            Smartphone("Samsung Galaxy S21", 1000, R.drawable.samsung_galaxy_s21),
            Smartphone("iPhone 12", 1200, R.drawable.iphone_12),
            Smartphone("Google Pixel 5", 800, R.drawable.pixel_5),
            Smartphone("OnePlus 8T", 750, R.drawable.oneplus_8t)
        )

        // Создаем адаптер для списка смартфонов
        val adapter = SmartphoneAdapter(this, smartphones)
        listView.adapter = adapter

        // Обработчик клика на элемент списка
        listView.setOnItemClickListener { parent, view, position, id ->
            // Получаем выбранный смартфон
            val selectedItem = smartphones[position]

            // Переходим на пустую страницу с названием выбранного смартфона
            navigateToEmptyPage(selectedItem.name)

            // Меняем фон выбранного элемента списка на черный
            view.setBackgroundColor(Color.BLACK)
        }
        val handler = Handler()
        val runnable = object : Runnable {
            var seconds = 0
            var minutes = 0
            var hours = 0

            override fun run() {
                // Увеличиваем время
                seconds++
                if (seconds >= 60) {
                    seconds = 0
                    minutes++
                    if (minutes >= 60) {
                        minutes = 0
                        hours++
                    }
                }

                // Форматируем время в строку и отображаем в TextView
                val timeString = String.format("%02d:%02d:%02d", hours, minutes, seconds)
                timerTextView.text = timeString

                // Постоянно повторяем через 1 секунду
                handler.postDelayed(this, 1000)
            }
        }

        // Запускаем Handler для увеличения времени
        handler.post(runnable)


        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            val url = "https://www.youtube.com"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }

        val buttonOpenPinterest = findViewById<Button>(R.id.button_web_view)
        buttonOpenPinterest.setOnClickListener {
            openPinterest()
        }
    }

    private fun navigateToEmptyPage(phoneName: String) {
        val intent = Intent(this, EmptyActivity::class.java)
        intent.putExtra("phone_name", phoneName)
        startActivity(intent)
    }

    private fun openPinterest() {
        val webView = WebView(this)
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()
        webView.loadUrl("https://ru.pinterest.com/")
        setContentView(webView)
    }

    data class Smartphone(val name: String, val price: Int, val imageResource: Int)

    override fun onResume() {
        super.onResume()

        for (i in 0 until listView.childCount) {
            val item = listView.getChildAt(i)
            item.setBackgroundColor(Color.WHITE)
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}