package com.example.androiddodo

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androiddodo.databinding.SecondActivityBinding
import com.example.androiddodo.model.Pizza

class SecondActivity : AppCompatActivity(){

    private lateinit var binding: SecondActivityBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SecondActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = Color.BLACK // Устанавливаем черный цвет для полосы состояния

        val selectedPizza: Pizza? = intent.getParcelableExtra("selected_pizza")
        if(selectedPizza!=null){
            binding.pizzaImage.setImageResource(selectedPizza.imageRes)
            binding.pizzaName.text = selectedPizza.name
            binding.pizzaDesc.text = selectedPizza.description
            binding.pizzaPrice.text = "В КОРЗИНУ ЗА ${selectedPizza.price} KZT"
        }

        binding.backButton.setOnClickListener() {
            onBackPressed()
        }

    }
}