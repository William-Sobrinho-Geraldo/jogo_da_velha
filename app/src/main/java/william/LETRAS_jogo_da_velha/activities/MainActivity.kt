package william.LETRAS_jogo_da_velha.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.ext.android.viewModel
import william.LETRAS_jogo_da_velha.R
import william.LETRAS_jogo_da_velha.data.JogadoresModel
import william.LETRAS_jogo_da_velha.databinding.ActivityMainBinding
import william.LETRAS_jogo_da_velha.utilidades.mostrarToast
import william.LETRAS_jogo_da_velha.viewModels.MainActivityViewModel

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val idsBotoes = listOf(R.id.btn3x3, R.id.btn4x4, R.id.btn5x5, R.id.btn6x6)
    private var idBotaoSelecionado: Int? = idsBotoes[0]


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: MainActivityViewModel by viewModel()

        //VARIÁVEIS DO MODO DE JOGO
        var btnVsJogadorAtivo = true
        var btnVsBotAtivo = false

        //VARIÁVEIS DO TAMANHO DO TABULEIRO
        var tabuleiro3x3 = true
        var tabuleiro4x4 = false
        var tabuleiro5x5 = false
        var tabuleiro6x6 = false

        for (id in idsBotoes) {
            val button = escolherBotao(id)
            button.setOnClickListener { botaoClicado(id) }
        }

        fun botaoVsJogadorClicado() {
            //CORRIGE CORES DOS BOTÕES vsJogador e vsBot
            binding.btnVsJogador.setBackgroundResource(R.drawable.rounded_button_white)
            binding.textViewBtnVsJogador.setTextColor(resources.getColor(R.color.roxo))
            binding.btnVsBot.setBackgroundResource(R.drawable.rounded_button_transparente)
            binding.textViewBtnVsBot.setTextColor(resources.getColor(R.color.white))

            //INDICA QUAL BOTÃO ESTÁ ATIVO ATUALMENTE
            btnVsJogadorAtivo = true
            btnVsBotAtivo = false
        }

        fun botaoVsBotClicado() {
            //CORRIGE CORES DOS BOTÕES vsJogador e vsBot
            binding.btnVsBot.setBackgroundResource(R.drawable.rounded_button_white)
            binding.textViewBtnVsBot.setTextColor(resources.getColor(R.color.roxo))
            binding.btnVsJogador.setBackgroundResource(R.drawable.rounded_button_transparente)
            binding.textViewBtnVsJogador.setTextColor(resources.getColor(R.color.white))

            //INDICA QUAL BOTÃO ESTÁ ATIVO ATUALMENTE
            btnVsBotAtivo = true
            btnVsJogadorAtivo = false
        }

        //BOTÃO COMEÇAR PARTICA CLICADO
        binding.btnComecarPartida.setOnClickListener {
            val jogador1 = JogadoresModel(nome = binding.jogador1EditText.text.toString())
            val jogador2 =
                JogadoresModel(nome = binding.jogador2EditText.text.toString(), cor = 1, simbolo = 1)

            //insiro o nome deses jgoadores no BD
            lifecycleScope.launch(Dispatchers.IO) {
                viewModel.inserirJogadores(jogador1)
                viewModel.inserirJogadores(jogador2)


                val listaDejogadoresNoBD = viewModel.buscaJogadoresNoBD()
                Log.i(TAG, "onCreate: a lista de jogadores no bando de dados é:  $listaDejogadoresNoBD")

                withContext(Dispatchers.Main) {
                    //pode navegar pra outra tela
                    mostrarToast(
                        "Jogador ${jogador1.nome} e jogador ${jogador2.nome} cadastrados",
                        this@MainActivity
                    )
                    delay(600)
                    val intent = Intent(this@MainActivity, TabuleiroActivity::class.java)
                    intent.putExtra("jogador1", jogador1)
                    intent.putExtra("jogador2", jogador2)
                    intent.putExtra("btnVsJogadorAtivo", btnVsJogadorAtivo)
                    intent.putExtra("btnVsBotAtivo", btnVsBotAtivo)
                    startActivity(intent)

                }

                //DELETAR TUDO EVENTUALMENTE - MANTENHA COMENTADO
                //viewModel.deletarTodosOsJogadores()
            }


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

        //BOTÃO 3X3 CLICADO
        binding.btn3x3.setOnClickListener {
            tabuleiro3x3 = true
            tabuleiro4x4 = false
            tabuleiro5x5 = false
            tabuleiro6x6 = false
        }
        //BOTÃO 4X4 CLICADO
        binding.btn4x4.setOnClickListener {
            tabuleiro3x3 = false
            tabuleiro4x4 = true
            tabuleiro5x5 = false
            tabuleiro6x6 = false
        }
        //BOTÃO 5X5 CLICADO
        binding.btn5x5.setOnClickListener {
            tabuleiro3x3 = false
            tabuleiro4x4 = false
            tabuleiro5x5 = true
            tabuleiro6x6 = false
        }
        //BOTÃO 6X6 CLICADO
        binding.btn6x6.setOnClickListener {
            tabuleiro3x3 = false
            tabuleiro4x4 = false
            tabuleiro5x5 = false
            tabuleiro6x6 = true
        }

    }  //onCreate da Activity


    //FUNÇÕES PARA CUSTOMIZAÇÃO DA UI


    private fun escolherBotao(idBotao: Int): Button {
        return when (idBotao) {
            R.id.btn3x3 -> binding.btn3x3
            R.id.btn4x4 -> binding.btn4x4
            R.id.btn5x5 -> binding.btn5x5
            R.id.btn6x6 -> binding.btn6x6
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
            R.id.btn3x3 -> button.text = getString(R.string._3x3)
            R.id.btn4x4 -> button.text = getString(R.string._4x4)
            R.id.btn5x5 -> button.text = getString(R.string._5x5)
            R.id.btn6x6 -> button.text = getString(R.string._6x6)
        }
    }

}