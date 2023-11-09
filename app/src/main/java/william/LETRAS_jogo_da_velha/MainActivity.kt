package william.LETRAS_jogo_da_velha

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import william.LETRAS_jogo_da_velha.data.AppDatabase
import william.LETRAS_jogo_da_velha.data.JogadoresModel
import william.LETRAS_jogo_da_velha.databinding.ActivityMainBinding
import william.LETRAS_jogo_da_velha.utilidades.mostrarToast


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val idsBotoes = listOf(R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4)
    private var idBotaoSelecionado: Int? = idsBotoes[0]


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val jogadoresDao = AppDatabase.getDatabase(applicationContext).jogadoresDao()


        for (id in idsBotoes) {
            val button = escolherBotao(id)
            button.setOnClickListener { botaoClicado(id) }
        }


        //BOTÃO COMEÇAR PARTICA CLICADO
        binding.btnComecarPartida.setOnClickListener {
            val jogador1 = JogadoresModel(nome = binding.jogador1EditText.text.toString())
            val jogador2 = JogadoresModel(nome = binding.jogador2EditText.text.toString())

            //insiro o nome deses jgoadores no BD
            lifecycleScope.launch {
                jogadoresDao.inserirJogador(jogador1)
                jogadoresDao.inserirJogador(jogador2)
            }

            //pode navegar pra outra tela
            mostrarToast("Jogador ${jogador1.nome} e jogador ${jogador2.nome} cadastrados", this)
        }

        //BOTÃO HISTÓRICO CLICADO



        //BOTÃO VS JOGADOR CLICADO
        binding.btnVsJogador.setOnClickListener {
            botaoVsJogadorClicado()

        }

        //BOTÃO VS BOT CLICADO
        binding.btnVsBot.setOnClickListener {
            botaoVsBotClicado()

        }

    }  //onCreate da Activity


    //FUNÇÕES PARA CUSTOMIZAÇÃO DA UI
    fun botaoVsJogadorClicado() {
        binding.btnVsJogador.setBackgroundResource(R.drawable.rounded_button_white)
        binding.textViewBtnVsJogador.setTextColor(resources.getColor(R.color.roxo))

        binding.btnVsBot.setBackgroundResource(R.drawable.rounded_button_transparente)
        binding.textViewBtnVsBot.setTextColor(resources.getColor(R.color.white))
    }

    fun botaoVsBotClicado() {
        binding.btnVsBot.setBackgroundResource(R.drawable.rounded_button_white)
        binding.textViewBtnVsBot.setTextColor(resources.getColor(R.color.roxo))

        binding.btnVsJogador.setBackgroundResource(R.drawable.rounded_button_transparente)
        binding.textViewBtnVsJogador.setTextColor(resources.getColor(R.color.white))
    }

    private fun escolherBotao(idBotao: Int): Button {
        return when (idBotao) {
            R.id.btn1 -> binding.btn1
            R.id.btn2 -> binding.btn2
            R.id.btn3 -> binding.btn3
            R.id.btn4 -> binding.btn4
            else -> throw IllegalArgumentException("Botão não encontrado com ID: $idBotao")
        }
    }

    private fun botaoClicado(idBotaoClicado: Int) {
        if (idBotaoSelecionado != null) {
            // Reset o botão previamente selecionado
            val botaoAnterior = escolherBotao(idBotaoSelecionado!!)
            limparEstiloBotao(botaoAnterior)
        }

        // Atualiza o botão clicado
        val clickedButton = escolherBotao(idBotaoClicado)
        atualizarEstiloBotao(clickedButton)

        // Atualiza o ID do botão selecionado
        idBotaoSelecionado = idBotaoClicado
    }

    private fun limparEstiloBotao(button: Button) {
        button.setBackgroundResource(R.drawable.rounded_button_transparente)
        button.setTextColor(resources.getColor(R.color.white))
    }

    private fun atualizarEstiloBotao(button: Button) {
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