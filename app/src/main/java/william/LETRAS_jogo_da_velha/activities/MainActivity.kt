package william.LETRAS_jogo_da_velha.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.ext.android.viewModel
import william.LETRAS_jogo_da_velha.R
import william.LETRAS_jogo_da_velha.data.AppDatabase
import william.LETRAS_jogo_da_velha.data.JogadoresModel
import william.LETRAS_jogo_da_velha.databinding.ActivityMainBinding
import william.LETRAS_jogo_da_velha.utilidades.Bot
import william.LETRAS_jogo_da_velha.utilidades.mostrarToast
import william.LETRAS_jogo_da_velha.utilidades.mostrarToastLonga
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
        //        val textoBotInteligente3x3 = findViewById<TextView>(R.id.botInteligente_defesa_3x3)
        //        val textoBotInteligente4x4 = findViewById<TextView>(R.id.botInteligente_defesa_4x4)

        val viewModel: MainActivityViewModel by viewModel()

        //VARIÁVEIS DO MODO DE JOGO
        var btnVsJogadorAtivo = true
        var btnVsBotAtivo = false

        //VARIÁVEIS DO TAMANHO DO TABULEIRO
        var tabuleiro3x3 = true
        var tabuleiro4x4 = false
        var tabuleiro5x5 = false
        var tabuleiro6x6 = false


        val dao = AppDatabase.getDatabase(this).historicoDao()
        val daojogadores = AppDatabase.getDatabase(this).jogadoresDao()


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

            //LIMPAR O jogador2
            binding.jogador2EditText.setText("")
            binding.jogador2EditText.isEnabled = true

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

            //BLOQUEIA JOGADOR 2
            binding.jogador2EditText.setText(Bot.botPadrao.nome)
            binding.jogador2EditText.isEnabled = false
            binding.jogador2EditText.inputType = InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS //or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD


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

                //Deletando
                //                                    daojogadores.deletarTodosOsJogadores()
                //                                    dao.deletarTodosOHistorico3x3()

                withContext(Dispatchers.Main) {
                    //pode navegar pra outra tela
                    mostrarToast(
                        "Jogador ${jogador1.nome} e jogador ${jogador2.nome} cadastrados",
                        this@MainActivity
                    )
                    delay(600)
                    if (tabuleiro3x3) {
                        // if (btnVsJogadorAtivo) textoBotInteligente3x3.visibility = View.INVISIBLE

                        val vaiProTabuleiro3x3 = Intent(this@MainActivity, Tabuleiro3x3Activity::class.java)
                        vaiProTabuleiro3x3.putExtra("jogador1", jogador1)
                        vaiProTabuleiro3x3.putExtra("jogador2", jogador2)
                        vaiProTabuleiro3x3.putExtra("btnVsJogadorAtivo", btnVsJogadorAtivo)
                        vaiProTabuleiro3x3.putExtra("btnVsBotAtivo", btnVsBotAtivo)
                        startActivity(vaiProTabuleiro3x3)

                        Log.i("bot", "onCreate:  indo pro tabuleiro3x3 e  botão vsBot é $btnVsBotAtivo ")

                    }

                    if (tabuleiro4x4) {
                        //  if (btnVsJogadorAtivo) textoBotInteligente4x4.visibility = View.INVISIBLE
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
            }

            //   LOGANDO NOMES DOS USUÁRIOS
            viewModel.buscaJogadoresNoBD().observe(this@MainActivity) { listaDeJogadores ->
                val nomes = listaDeJogadores.map { it.nome }
                Log.i(TAG, "onCreate: a lista de Nomes de jogadores no bando de dados é:  $nomes")
            }
        }


        //BOTÃO HISTÓRICO CLICADO
        binding.btnHistorico.setOnClickListener {
            if (tabuleiro3x3) startActivity(Intent(this, Historico3x3::class.java))
            if (tabuleiro4x4) startActivity(Intent(this, Historico4x4::class.java))
            if (tabuleiro5x5) {
                startActivity(Intent(this, Historico3x3::class.java))
                mostrarToastLonga("Histórico para o tabuleiro 5x5 em desenvolvimento", this)
            }

            if (tabuleiro6x6) {
                startActivity(Intent(this, Historico3x3::class.java))
                mostrarToastLonga("Histórico para o tabuleiro 6x6 em desenvolvimento", this)
            }
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