package william.LETRAS_jogo_da_velha.activities

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import org.koin.android.ext.koin.androidContext
import william.LETRAS_jogo_da_velha.R
import william.LETRAS_jogo_da_velha.data.AppDatabase
import william.LETRAS_jogo_da_velha.data.JogadoresDao
import william.LETRAS_jogo_da_velha.data.JogadoresModel
import william.LETRAS_jogo_da_velha.data.Repository
import william.LETRAS_jogo_da_velha.databinding.ActivityTabuleiro3x3Binding
import william.LETRAS_jogo_da_velha.utilidades.Bot
import william.LETRAS_jogo_da_velha.utilidades.mostrarToast

private const val TAG = "TabuleitoActivity"

//COMEÇANDO O JOGO COM O TABULEIRO LIMPO
val btnMarcList3x3 = mutableListOf("", "", "", "", "", "", "", "", "")

class Tabuleiro3x3Activity : AppCompatActivity() {
    private lateinit var binding: ActivityTabuleiro3x3Binding
    private lateinit var botoes: List<ImageButton>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTabuleiro3x3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        botoes = listOf(
            binding.button1, binding.button2, binding.button3,
            binding.button4, binding.button5, binding.button6,
            binding.button7, binding.button8, binding.button9
        )

        val repository = Repository(AppDatabase.getDatabase(this).jogadoresDao())
        val jogador1 = intent.getSerializableExtra("jogador1") as JogadoresModel
        var jogador2 = intent.getSerializableExtra("jogador2") as JogadoresModel
        val btnVsBotAtivo = intent.getBooleanExtra("btnVsBotAtivo", false)
        val quantTotalDeCasas = 9


        val bot = Bot()
        var botaoEscolhidoPeloBot = 0
        val blocosVazios: MutableList<Int> = mutableListOf()
        var jogadorAtual = jogador1
        var contadorDeJogadas = 0


        fun ordenaJogadaDoBot() {
            val defenderLinhas = bot.defenderLinhas3x3()
            val defenderColunas = bot.defenderColunas3x3()
            val defenderDiagonais = bot.defenderDiagonais3x3()

            //bot só pode jogar onde estiver vazio
            blocosVazios.clear()
            Log.i(TAG, "ordenaJogadaDoBot:   blocosVazios foi limpa e agora é:  $blocosVazios")

            //ADICIONANDO NA LISTA DE blocosVazios OS ÍNDICES VAZIOS
            for (indice in btnMarcList3x3.indices) {
                if (btnMarcList3x3[indice] == "") blocosVazios.add(indice + 1)
            }
            Log.i(TAG, "ordenaJogadaDoBot:   Os espaços disponíveis são $blocosVazios")


            if (blocosVazios.isNotEmpty()) {
                if (defenderLinhas != null) {
                    botaoEscolhidoPeloBot = defenderLinhas + 1
                    Log.i(TAG, "  BOT DEFENDENDO LINHA NO INDICE $botaoEscolhidoPeloBot")
                } else if (defenderColunas != null) {
                    botaoEscolhidoPeloBot = defenderColunas + 1
                    Log.i(TAG, "  BOT DEFENDENDO COLUNAS NO INDICE $botaoEscolhidoPeloBot")
                } else if (defenderDiagonais != null) {
                    botaoEscolhidoPeloBot = defenderDiagonais + 1
                    Log.i(TAG, "  BOT DEFENDENDO DIAGONAIS NO INDICE $botaoEscolhidoPeloBot")
                } else {
                    //Escolhendo botão que o bot Vai clicar aleatóriamente
                    botaoEscolhidoPeloBot = blocosVazios.random()
                    Log.i(TAG, "ordenaJogadaDoBot:  botãoEscolhido foi  ${botaoEscolhidoPeloBot}")
                }

                Handler(Looper.getMainLooper()).postDelayed({
                    for (indice in 0 until botoes.size) {
                        val botao = botoes[indice]
                        val botaoNumero = indice + 1

                        if (botaoEscolhidoPeloBot == botaoNumero && botao.isEnabled) {
                            botao.performClick()
                            break      // sair do loop depois de encontrar e clicar no botaoEscolhidoPeloBot
                        }
                    }
                }, 700)
            }
        }

        //VERIFICANDO SE VOU JOGAR CONTRA BOT OU OUTRO JOGADOR
        if (btnVsBotAtivo) {
            //o jogador 2 será o Bot
            jogador2 = JogadoresModel(nome = bot.nome, cor = 1, simbolo = 1)
        }



        fun limpaTabuleiro() {
            contadorDeJogadas = 0
            jogadorAtual = jogador1
            binding.jogadorX.text = jogador1.nome
            binding.jogadorX.setTextColor(resources.getColor(R.color.vermelho))
            binding.vencedor.setTextColor(resources.getColor(R.color.transparente))

            // LIMPANDO A LISTA NOVA buttonMarcList
            for (indice in 0 until btnMarcList3x3.size) {
                btnMarcList3x3[indice] = ""
            }
            var delay = 0L     // Atraso inicial em milissegundos

            for ((index, botao) in botoes.withIndex()) {
                val fadeIn = AlphaAnimation(1f, 0f)
                fadeIn.duration = 700
                fadeIn.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation?) {}
                    override fun onAnimationEnd(animation: Animation?) {
                        // Lógica a ser executada no final da animação
                        botao.setImageResource(R.drawable.imagem_fundo_branco)
                        botao.isEnabled = true // Ativando os botões
                    }
                    override fun onAnimationRepeat(animation: Animation?) {}
                })

                // Iniciando a animação
                botao.postDelayed({ botao.startAnimation(fadeIn) }, delay)
                delay += 150 // Adicionando um pouco mais de atraso
            }
        }


        fun alteraCorDoTexto() {
            if (jogadorAtual == jogador1) {
                //Jogador1 é vermelho -> Setar a cor azul
                binding.jogadorX.setTextColor(resources.getColor(R.color.vermelho))

            } else {
                //Jogador2 é azul -> Setar a corvermelha
                binding.jogadorX.setTextColor(resources.getColor(R.color.azul))
            }
        }

        fun alteraOTextoDoJogador() {
            if (jogadorAtual == jogador1) {
                binding.jogadorX.text = jogador1.nome
            } else {
                binding.jogadorX.text = jogador2.nome
            }
        }

        fun jogoAcabouJogador1Ganhou() {
            mostrarToast("O jogo acabou, ${jogador1.nome} foi o vencedor", this)
            binding.vencedor.text = "Parabéns ${jogador1.nome}, você venceu !"
            binding.vencedor.setTextColor(resources.getColor(R.color.vermelho))
            //incrementa vitória pra jogador1 e incrementa derrota pro jogador2
                    //dao
            repository.incrementarVitoria(jogador1)
            //BLOQUEIA TODOS OS BOTÕES POIS O JOGO ACABOU
            botoes.forEach { it.isEnabled = false }
            contadorDeJogadas = 0
        }

        fun jogoAcabouJogador2Ganhou() {
            mostrarToast("O jogo acabou, ${jogador2.nome} foi o vencedor", this)
            binding.vencedor.text = "Parabéns ${jogador2.nome}, você venceu !"
            binding.vencedor.setTextColor(resources.getColor(R.color.azul))
            //incrementa vitória pra jogador1 e incrementa derrota pro jogador2
            //dao

            //BLOQUEIA TODOS OS BOTÕES POIS O JOGO ACABOU
            botoes.forEach { it.isEnabled = false }
            contadorDeJogadas = 0
        }

        fun verificaVencedorComX() {
            val linhas = listOf(
                listOf(0, 1, 2),
                listOf(3, 4, 5),
                listOf(6, 7, 8),
                listOf(0, 3, 6),
                listOf(1, 4, 7),
                listOf(2, 5, 8),
                listOf(0, 4, 8),
                listOf(2, 4, 6)
            )
            for (linha in linhas) {
                val (a, b, c) = linha
                if (btnMarcList3x3[a] == "x" && btnMarcList3x3[b] == "x" && btnMarcList3x3[c] == "x") {
                    botoes[a].setImageResource(R.drawable.marca_x_70)
                    botoes[b].setImageResource(R.drawable.marca_x_70)
                    botoes[c].setImageResource(R.drawable.marca_x_70)
                    jogoAcabouJogador1Ganhou()
                    return
                }
            }
        }

        fun verificaVencedorCom0() {
            val linhas = listOf(
                listOf(0, 1, 2),
                listOf(3, 4, 5),
                listOf(6, 7, 8),
                listOf(0, 3, 6),
                listOf(1, 4, 7),
                listOf(2, 5, 8),
                listOf(0, 4, 8),
                listOf(2, 4, 6)
            )

            for (linha in linhas) {
                val (a, b, c) = linha
                if (btnMarcList3x3[a] == "0" && btnMarcList3x3[b] == "0" && btnMarcList3x3[c] == "0") {
                    botoes[a].setImageResource(R.drawable.marca_bolinha_70)
                    botoes[b].setImageResource(R.drawable.marca_bolinha_70)
                    botoes[c].setImageResource(R.drawable.marca_bolinha_70)
                    jogoAcabouJogador2Ganhou()
                    return
                }
            }
        }

        fun alteraVezDoJogador() {
            //ANTES DE ALTERAR, VERIFICA SE Já TEM VENCEDOR
            verificaVencedorComX()
            verificaVencedorCom0()
            //VERIFICA SE HOUVE EMPATE
            if (contadorDeJogadas == 8) {
                mostrarToast("Tivemos um empate", this)
            }

            jogadorAtual = if (jogadorAtual == jogador1) {
                jogador2
            } else {
                jogador1
            }
            Log.i(TAG, "alteraVezDoJogador:  Agora o JOGADORATUAL é :  ${jogadorAtual.nome}")

            contadorDeJogadas++
            Log.i(TAG, "alteraVezDoJogador: Agora o contador é   $contadorDeJogadas")
            alteraCorDoTexto()
            alteraOTextoDoJogador()
        }

        //MOSTRAR QUE O PRIMEIRO A JOGAR É O JOGADOR1
        binding.jogadorX.text = jogador1.nome

        //   Escutando click dos botões com animação
        for ((index, botao) in botoes.withIndex()) {
            botao.setOnClickListener {
                if (contadorDeJogadas < quantTotalDeCasas && btnMarcList3x3[index].isEmpty()) {
                    val fadeIn = AlphaAnimation(0f, 1f)
                    fadeIn.duration = 900
                    fadeIn.setAnimationListener(object : Animation.AnimationListener {
                        override fun onAnimationStart(animation: Animation?) {
                            botao.setImageResource(if (jogadorAtual == jogador1) R.drawable.marca_x else R.drawable.marca_bolinha)
                            btnMarcList3x3[index] = if (jogadorAtual == jogador1) "x" else "0"
                            alteraVezDoJogador()
                        }
                        override fun onAnimationEnd(animation: Animation?) {  }
                        override fun onAnimationRepeat(animation: Animation?) { }
                    })
                    botao.startAnimation(fadeIn)

                    // Verifica se está jogando com o bot e manda ele jogar
                    if (jogadorAtual == jogador2 && btnVsBotAtivo) {
                        ordenaJogadaDoBot()
                    }
                } else {
                    mostrarToast("o jogo acabou", this)
                }

                botao.isEnabled = false
                Log.i(TAG, "onCreate: botao${index + 1} bloqueado para outros clicks")
            }
        }

        //BOTÃO NOVO JOGO
        binding.btnNovoJogo.setOnClickListener {
            limpaTabuleiro()
        }

        //BOTÃO VOLTAR
        binding.btnVoltar.setOnClickListener {
            limpaTabuleiro()
            finish()
        }

    }  //onCreate

} // Tabuleiro3x3Activity