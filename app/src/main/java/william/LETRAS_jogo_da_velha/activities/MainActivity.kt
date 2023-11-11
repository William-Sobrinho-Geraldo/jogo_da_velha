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


        fun escolherBotao(idBotao: Int): Button {
            when (idBotao) {
                R.id.btn3x3 -> {
                    tabuleiro3x3 = true
                    tabuleiro4x4 = false
                    tabuleiro5x5 = false
                    tabuleiro6x6 = false
                }

                R.id.btn4x4 -> {
                    tabuleiro3x3 = false
                    tabuleiro4x4 = true
                    tabuleiro5x5 = false
                    tabuleiro6x6 = false
                }

                R.id.btn5x5 -> {
                    tabuleiro3x3 = false
                    tabuleiro4x4 = false
                    tabuleiro5x5 = true
                    tabuleiro6x6 = false
                }

                R.id.btn6x6 -> {
                    tabuleiro3x3 = false
                    tabuleiro4x4 = false
                    tabuleiro5x5 = false
                    tabuleiro6x6 = true
                }

                else -> throw IllegalArgumentException("Botão não encontrado com ID: $idBotao")
            }

            return when (idBotao) {
                R.id.btn3x3 -> binding.btn3x3
                R.id.btn4x4 -> binding.btn4x4
                R.id.btn5x5 -> binding.btn5x5
                R.id.btn6x6 -> binding.btn6x6
                else -> throw IllegalArgumentException("Botão não encontrado com ID: $idBotao")
            }
        }

        fun botaoClicado(idBotaoClicado: Int) {
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

                withContext(Dispatchers.Main) {
                    //pode navegar pra outra tela
                    mostrarToast(
                        "Jogador ${jogador1.nome} e jogador ${jogador2.nome} cadastrados",
                        this@MainActivity
                    )
                    delay(600)
                    if (tabuleiro3x3) {
                        val vaiProTabuleiro3x3 = Intent(this@MainActivity, Tabuleiro3x3Activity::class.java)
                        vaiProTabuleiro3x3.putExtra("jogador1", jogador1)
                        vaiProTabuleiro3x3.putExtra("jogador2", jogador2)
                        vaiProTabuleiro3x3.putExtra("btnVsJogadorAtivo", btnVsJogadorAtivo)
                        vaiProTabuleiro3x3.putExtra("btnVsBotAtivo", btnVsBotAtivo)
                        startActivity(vaiProTabuleiro3x3)

                        Log.i("bot", "onCreate:  indo pro tabuleiro3x3 e  botão vsBot é $btnVsBotAtivo ")

                    }

                    if (tabuleiro4x4) {
                        val vaiProTabuleiro4x4 = Intent(this@MainActivity, Tabuleiro4x4Activity::class.java)
                        vaiProTabuleiro4x4.putExtra("jogador1", jogador1)
                        vaiProTabuleiro4x4.putExtra("jogador2", jogador2)
                        vaiProTabuleiro4x4.putExtra("btnVsJogadorAtivo", btnVsJogadorAtivo)
                        vaiProTabuleiro4x4.putExtra("btnVsBotAtivo", btnVsBotAtivo)
                        startActivity(vaiProTabuleiro4x4)

                    }
                    if (tabuleiro5x5) {
                        val vaiProTabuleiro5x5 = Intent(this@MainActivity, Tabuleiro5x5Activity::class.java)
                        vaiProTabuleiro5x5.putExtra("jogador1", jogador1)
                        vaiProTabuleiro5x5.putExtra("jogador2", jogador2)
                        vaiProTabuleiro5x5.putExtra("btnVsJogadorAtivo", btnVsJogadorAtivo)
                        vaiProTabuleiro5x5.putExtra("btnVsBotAtivo", btnVsBotAtivo)
                        startActivity(vaiProTabuleiro5x5)
                    }
                    if (tabuleiro6x6) {
                        val vaiProTabuleiro6x6 = Intent(this@MainActivity, Tabuleiro6x6Activity::class.java)
                        vaiProTabuleiro6x6.putExtra("jogador1", jogador1)
                        vaiProTabuleiro6x6.putExtra("jogador2", jogador2)
                        vaiProTabuleiro6x6.putExtra("btnVsJogadorAtivo", btnVsJogadorAtivo)
                        vaiProTabuleiro6x6.putExtra("btnVsBotAtivo", btnVsBotAtivo)
                        startActivity(vaiProTabuleiro6x6)
                    }
                }

                //DELETAR TUDO EVENTUALMENTE - MANTENHA COMENTADO
                //viewModel.deletarTodosOsJogadores()
            }

            //   LOGANDO NOMES DOS USUÁRIOS
            viewModel.buscaJogadoresNoBD().observe(this@MainActivity) { listaDeJogadores ->
                val nomes = listaDeJogadores.map { it.nome }
                Log.i(TAG, "onCreate: a lista de Nomes de jogadores no bando de dados é:  $nomes")
            }
        }


        //BOTÃO HISTÓRICO CLICADO
        binding.btnHistorico.setOnClickListener {
            startActivity(Intent(this, Historico3x3::class.java))

        }


        //BOTÃO VS JOGADOR CLICADO
        binding.btnVsJogador.setOnClickListener {
            botaoVsJogadorClicado()
        }

        //BOTÃO VS BOT CLICADO
        binding.btnVsBot.setOnClickListener {
            botaoVsBotClicado()
        }

        //        //BOTÃO 3X3 CLICADO
        //        binding.btn3x3.setOnClickListener {
        tabuleiro3x3 = true
        tabuleiro4x4 = false
        tabuleiro5x5 = false
        tabuleiro6x6 = false

    }  //onCreate da Activity


    //FUNÇÕES PARA CUSTOMIZAÇÃO DA UI


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