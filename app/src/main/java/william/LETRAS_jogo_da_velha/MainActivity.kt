package william.LETRAS_jogo_da_velha

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import william.LETRAS_jogo_da_velha.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnVsJogador.setOnClickListener {
            onVsJogadorClick()

        }

        binding.btnVsBot.setOnClickListener {
            onVsBotClick()

        }

    }

    fun onVsJogadorClick() {
        binding.btnVsJogador.setBackgroundResource(R.drawable.rounded_button_white)
        binding.textViewBtnVsJogador.setTextColor(resources.getColor(R.color.roxo))

        binding.btnVsBot.setBackgroundResource(R.drawable.rounded_button_transparente)
        binding.textViewBtnVsBot.setTextColor(resources.getColor(R.color.white))
    }

    fun onVsBotClick() {
        binding.btnVsBot.setBackgroundResource(R.drawable.rounded_button_white)
        binding.textViewBtnVsBot.setTextColor(resources.getColor(R.color.roxo))

        binding.btnVsJogador.setBackgroundResource(R.drawable.rounded_button_transparente)
        binding.textViewBtnVsJogador.setTextColor(resources.getColor(R.color.white))
    }
}