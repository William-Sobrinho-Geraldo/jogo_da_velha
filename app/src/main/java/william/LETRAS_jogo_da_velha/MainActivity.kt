package william.LETRAS_jogo_da_velha

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import william.LETRAS_jogo_da_velha.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val buttonIds = listOf(R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4)
    private var selectedButtonId: Int? = buttonIds[0]


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        for (buttonId in buttonIds) {
            val button = getButtonById(buttonId)
            button.setOnClickListener { onButtonClick(buttonId) }
        }




        //BOTÃO VS JOGADOR CLICADO
        binding.btnVsJogador.setOnClickListener {
            onVsJogadorClick()

        }

        //BOTÃO VS BOT CLICADO
        binding.btnVsBot.setOnClickListener {
            onVsBotClick()

        }

        //BOTÃO 3x3  CLICADO

        //BOTÃO 4x4  CLICADO

        //BOTÃO 5x5  CLICADO

        //BOTÃO 3x3  CLICADO

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

    private fun getButtonById(buttonId: Int): Button {
        return when (buttonId) {
            R.id.btn1 -> binding.btn1
            R.id.btn2 -> binding.btn2
            R.id.btn3 -> binding.btn3
            R.id.btn4 -> binding.btn4
            else -> throw IllegalArgumentException("Botão não encontrado com ID: $buttonId")
        }
    }

    private fun onButtonClick(clickedButtonId: Int) {
        if (selectedButtonId != null) {
            // Reset o botão previamente selecionado
            val prevSelectedButton = getButtonById(selectedButtonId!!)
            resetButtonStyle(prevSelectedButton)
        }

        // Atualiza o botão clicado
        val clickedButton = getButtonById(clickedButtonId)
        updateButtonStyle(clickedButton)

        // Atualiza o ID do botão selecionado
        selectedButtonId = clickedButtonId
    }

    private fun resetButtonStyle(button: Button) {
        button.setBackgroundResource(R.drawable.rounded_button_transparente)
        button.setTextColor(resources.getColor(R.color.white))
    }

    private fun updateButtonStyle(button: Button) {
        button.setBackgroundResource(R.drawable.rounded_button_white)
        button.setTextColor(resources.getColor(R.color.roxo))
        // Modifique o texto conforme necessário
        when (button.id) {
            R.id.btn1 -> button.text = getString(R.string._3x3)
            R.id.btn2 -> button.text = getString(R.string._4x4)
            R.id.btn3 -> button.text = getString(R.string._5x5)
            R.id.btn4 -> button.text = getString(R.string._6x6)
        }
    }

}