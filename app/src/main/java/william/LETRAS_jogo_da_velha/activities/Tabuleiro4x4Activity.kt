package william.LETRAS_jogo_da_velha.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.ImageButton
import org.koin.androidx.viewmodel.ext.android.viewModel
import william.LETRAS_jogo_da_velha.R
import william.LETRAS_jogo_da_velha.data.HistoricoItemModel
import william.LETRAS_jogo_da_velha.data.JogadoresModel
import william.LETRAS_jogo_da_velha.databinding.ActivityTabuleiro4x4Binding
import william.LETRAS_jogo_da_velha.utilidades.Bot
import william.LETRAS_jogo_da_velha.utilidades.mostrarToast
import william.LETRAS_jogo_da_velha.viewModels.Tabuleiro4x4ViewModel

private const val TAG = "Tabu4x4"

//COMEÇANDO O JOGO COM OTABULEIRO LIMPO
val btnMarcList4x4 = mutableListOf("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "")

class Tabuleiro4x4Activity : AppCompatActivity() {
    private lateinit var binding: ActivityTabuleiro4x4Binding
    private lateinit var botoes: MutableList<ImageButton>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTabuleiro4x4Binding.inflate(layoutInflater)
        setContentView(binding.root)


        val viewModel: Tabuleiro4x4ViewModel by viewModel()
        val jogador1 = intent.getSerializableExtra("jogador1") as JogadoresModel
        var jogador2 = intent.getSerializableExtra("jogador2") as JogadoresModel
        val btnVsBotAtivo = intent.getBooleanExtra("btnVsBotAtivo", false)
        val quantTotalDeCasas = 16

        val bot = Bot()
        var botaoEscolhidoPeloBot = 0
        val blocosVazios: MutableList<Int> = mutableListOf()
        var jogadorAtual = jogador1
        var contadorDeJogadas = 0

        //Populando lista de  ImageButtons
        botoes = mutableListOf()
        for (i in 1..quantTotalDeCasas) {
            val buttonId = resources.getIdentifier("button$i", "id", packageName)
            val button = findViewById<ImageButton>(buttonId)
            botoes.add(button)
        }


        fun ordenaJogadaDoBot() {
            val defenderLinhas = bot.defenderLinhas4x4()
            val defenderColunas = bot.defenderColunas4x4()
            val defenderDiagonais = bot.defenderDiagonais4x4()

            //bot só pode jogar onde estiver vazio
            blocosVazios.clear()
            Log.i(TAG, "ordenaJogadaDoBot:   blocosVazios foi limpa e agora é:  $blocosVazios")

            //ADICIONANDO NA LISTA DE blocosVazios OS ÍNDICES VAZIOS
            for (indice in btnMarcList4x4.indices) {
                if (btnMarcList4x4[indice] == "") blocosVazios.add(indice + 1)
            }
            Log.i(TAG, "ordenaJogadaDoBot:   Os espaços disponíveis são $blocosVazios")


            if (blocosVazios.isNotEmpty()) {
                if (defenderLinhas != null) {
                    botaoEscolhidoPeloBot = defenderLinhas + 1
                    Log.i(TAG, "  BOT DEFENDENDO LINHA NO INDICE $botaoEscolhidoPeloBot")
                } else if (defenderColunas != null) {
                    botaoEscolhidoPeloBot = defenderColunas + 1
                    Log.i(TAG, "  BOT DEFENDENDO COLUNA NO INDICE $botaoEscolhidoPeloBot")
                } else if (defenderDiagonais != null) {
                    botaoEscolhidoPeloBot = defenderDiagonais + 1
                    Log.i(TAG, "  BOT DEFENDENDO DIAGONAL NO INDICE $botaoEscolhidoPeloBot")
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
            for (indice in 0 until btnMarcList4x4.size) {
                btnMarcList4x4[indice] = ""
            }
            var delay = 0L     // Atraso inicial em milissegundos

            for ((index, botao) in botoes.withIndex()) {
                val fadeIn = AlphaAnimation(1f, 0f)
                fadeIn.duration = 500
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
                delay += 120 // Adicionando um pouco mais de atraso
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

            viewModel.incrementarVitoria(jogador1.nome)
            viewModel.incrementarDerrota(jogador2.id)

            val historicoItem = HistoricoItemModel(
                0,
                jogador1Nome = jogador1.nome,
                jogador2Nome = jogador2.nome,
                jogador1Venceu = true,
                jogador2Venceu = false,
                tabuleiro4x4 = true
            )
            viewModel.inserirHistorico(historicoItem)
            Log.i(TAG, "jogoAcabouJogador2Ganhou: JOGADOR 11 : $jogador1")
            Log.i(TAG, "jogoAcabouJogador2Ganhou: JOGADOR 22 : $jogador2")

            //BLOQUEIA TODOS OS BOTÕES POIS O JOGO ACABOU
            botoes.forEach { it.isEnabled = false }
            contadorDeJogadas = 0
        }

        fun jogoAcabouJogador2Ganhou() {
            mostrarToast("O jogo acabou, ${jogador2.nome} foi o vencedor", this)
            binding.vencedor.text = "Parabéns ${jogador2.nome}, você venceu !"
            binding.vencedor.setTextColor(resources.getColor(R.color.azul))
            //incrementa vitória pra jogador1 e incrementa derrota pro jogador2
            viewModel.incrementarVitoria(jogador2.nome)
            viewModel.incrementarDerrota(jogador1.id)
            val historicoItem = HistoricoItemModel(
                0,
                jogador1Nome = jogador1.nome,
                jogador2Nome = jogador2.nome,
                jogador1Venceu = false,
                jogador2Venceu = true,
                tabuleiro4x4 = true
            )
            viewModel.inserirHistorico(historicoItem)
            Log.i(TAG, "jogoAcabouJogador2Ganhou: JOGADOR 11 : $jogador1")
            Log.i(TAG, "jogoAcabouJogador2Ganhou: JOGADOR 22 : $jogador2")

            //BLOQUEIA TODOS OS BOTÕES POIS O JOGO ACABOU
            botoes.forEach { it.isEnabled = false }
            contadorDeJogadas = 0
        }

        fun verificaVencedorComX() {
            val linhas = listOf(
                listOf(0, 1, 2, 3),         //Horizontais
                listOf(4, 5, 6, 7),        //Horizontais
                listOf(8, 9, 10, 11),      //Horizontais
                listOf(12, 13, 14, 15),   //Horizontais
                listOf(0, 4, 8, 12),       //Verticais
                listOf(1, 5, 9, 13),       //Verticais
                listOf(2, 6, 10, 14),       //Verticais
                listOf(3, 7, 11, 15),       //Verticais
                listOf(0, 5, 10, 15),       //Diagonais
                listOf(3, 6, 9, 12),       //Diagonais
            )
            for (linha in linhas) {
                val (a, b, c, d) = linha
                if (btnMarcList4x4[a] == "x" && btnMarcList4x4[b] == "x" && btnMarcList4x4[c] == "x" && btnMarcList4x4[d] == "x") {
                    botoes[a].setImageResource(R.drawable.marca_x_70)
                    botoes[b].setImageResource(R.drawable.marca_x_70)
                    botoes[c].setImageResource(R.drawable.marca_x_70)
                    botoes[d].setImageResource(R.drawable.marca_x_70)
                    jogoAcabouJogador1Ganhou()
                    return
                }
            }
        }

        fun verificaVencedorCom0() {
            val linhas = listOf(
                listOf(0, 1, 2, 3),         //Horizontais
                listOf(4, 5, 6, 7),        //Horizontais
                listOf(8, 9, 10, 11),      //Horizontais
                listOf(12, 13, 14, 15),   //Horizontais
                listOf(0, 4, 8, 12),       //Verticais
                listOf(1, 5, 9, 13),       //Verticais
                listOf(2, 6, 10, 14),       //Verticais
                listOf(3, 7, 11, 15),       //Verticais
                listOf(0, 5, 10, 15),       //Diagonais
                listOf(3, 6, 9, 12),       //Diagonais
            )

            for (linha in linhas) {
                val (a, b, c, d) = linha
                if (btnMarcList4x4[a] == "0" && btnMarcList4x4[b] == "0" && btnMarcList4x4[c] == "0" && btnMarcList4x4[d] == "0") {
                    botoes[a].setImageResource(R.drawable.marca_bolinha_70)
                    botoes[b].setImageResource(R.drawable.marca_bolinha_70)
                    botoes[c].setImageResource(R.drawable.marca_bolinha_70)
                    botoes[d].setImageResource(R.drawable.marca_bolinha_70)
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
            if (contadorDeJogadas == (quantTotalDeCasas - 1)) {
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
                if (contadorDeJogadas < quantTotalDeCasas && btnMarcList4x4[index].isEmpty()) {
                    val fadeIn = AlphaAnimation(0f, 1f)
                    fadeIn.duration = 900
                    fadeIn.setAnimationListener(object : Animation.AnimationListener {
                        override fun onAnimationStart(animation: Animation?) {
                            botao.setImageResource(if (jogadorAtual == jogador1) R.drawable.marca_x else R.drawable.marca_bolinha)
                            btnMarcList4x4[index] = if (jogadorAtual == jogador1) "x" else "0"
                            alteraVezDoJogador()

                            // Verifica se está jogando com o bot e manda ele jogar
                            if (jogadorAtual == jogador2 && btnVsBotAtivo) {
                                ordenaJogadaDoBot()
                            }
                        }

                        override fun onAnimationEnd(animation: Animation?) {}
                        override fun onAnimationRepeat(animation: Animation?) {}
                    })
                    botao.startAnimation(fadeIn)

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

    } //   onCreate
} // Tabuleiro4x4Activity