package william.LETRAS_jogo_da_velha.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.ImageButton
import william.LETRAS_jogo_da_velha.R
import william.LETRAS_jogo_da_velha.data.JogadoresModel
import william.LETRAS_jogo_da_velha.databinding.ActivityTabuleiro6x6Binding
import william.LETRAS_jogo_da_velha.utilidades.Bot
import william.LETRAS_jogo_da_velha.utilidades.mostrarToast

private const val TAG = "Tabu6x6"
//COMEÇANDO O JOGO COM OTABULEIRO LIMPO
private val btnMarcList6x6 = mutableListOf(
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    ""
)

class Tabuleiro6x6Activity : AppCompatActivity() {
    private lateinit var binding: ActivityTabuleiro6x6Binding
    private lateinit var botoes: MutableList<ImageButton>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTabuleiro6x6Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val jogador1 = intent.getSerializableExtra("jogador1") as JogadoresModel
        var jogador2 = intent.getSerializableExtra("jogador2") as JogadoresModel
        val btnVsBotAtivo = intent.getBooleanExtra("btnVsBotAtivo", false)
        val quantTotalDeCasas = 36

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
            //bot só pode jogar onde estiver vazio
            blocosVazios.clear()
            Log.i(TAG, "ordenaJogadaDoBot:   blocosVazios foi limpa e agora é:  $blocosVazios")

            //ADICIONANDO NA LISTA DE blocosVazios OS ÍNDICES VAZIOS
            for (indice in btnMarcList6x6.indices) {
                if (btnMarcList6x6[indice] == "") blocosVazios.add(indice + 1)
            }
            Log.i(TAG, "ordenaJogadaDoBot:   Os espaços disponíveis são $blocosVazios")


            if (blocosVazios.isNotEmpty()) {
                //Escolhendo botão que o bot Vai clicar aleatóriamente
                botaoEscolhidoPeloBot = blocosVazios.random()
                Log.i(TAG, "ordenaJogadaDoBot:  botãoEscolhido foi  ${botaoEscolhidoPeloBot}")

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

            //LIMPANDO  A LISTA NOVA    buttonMarcList
            for (indice in 0 until btnMarcList6x6.size) {
                btnMarcList6x6[indice] = ""
            }

            for (botao in botoes) {
                botao.setImageResource(R.drawable.imagem_fundo_branco)   //   LIMPANDO AS MARCAS
                botao.isEnabled = true                                              //   REATIVANDO OS BOTÕES
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
            //BLOQUEIA TODOS OS BOTÕES POIS O JOGO ACABOU
            botoes.forEach { it.isEnabled = false }
            contadorDeJogadas = 0
        }

        fun jogoAcabouJogador2Ganhou() {
            mostrarToast("O jogo acabou, ${jogador2.nome} foi o vencedor", this)
            binding.vencedor.text = "Parabéns ${jogador2.nome}, você venceu !"
            binding.vencedor.setTextColor(resources.getColor(R.color.azul))
            //BLOQUEIA TODOS OS BOTÕES POIS O JOGO ACABOU
            botoes.forEach { it.isEnabled = false }
            contadorDeJogadas = 0
        }

        fun verificaVencedorComX() {
            val linhas = listOf(
                listOf(0, 1, 2, 3, 4, 5),                     // Horizontais
                listOf(6, 7, 8, 9, 10, 11),                  // Horizontais
                listOf(12, 13, 14, 15, 16, 17),            // Horizontais
                listOf(18, 19, 20, 21, 22, 23),           // Horizontais
                listOf(24, 25, 26, 27, 28, 29),         // Horizontais
                listOf(30, 31, 32, 33, 34, 35),         // Horizontais
                listOf(0, 6, 12, 18, 24, 30),              // Verticais
                listOf(1, 7, 13, 19, 25, 31),               // Verticais
                listOf(2, 8, 14, 20, 26, 32),             // Verticais
                listOf(3, 9, 15, 21, 27, 33),              // Verticais
                listOf(4, 10, 16, 22, 28, 34),            // Verticais
                listOf(5, 11, 17, 23, 29, 35),             // Verticais
                listOf(0, 7, 14, 21, 28, 35),              // Diagonais
                listOf(5, 10, 15, 20, 25, 30)             // Diagonais
            )

            for (linha in linhas) {
                if (linha.size == 6) {
                    val a = linha[0]
                    val b = linha[1]
                    val c = linha[2]
                    val d = linha[3]
                    val e = linha[4]
                    val f = linha[5]

                    if (btnMarcList6x6[a] == "x" && btnMarcList6x6[b] == "x" && btnMarcList6x6[c] == "x" &&
                        btnMarcList6x6[d] == "x" && btnMarcList6x6[e] == "x" && btnMarcList6x6[f] == "x"
                    ) {
                        val indices = listOf(a, b, c, d, e, f)
                        for (index in indices) {
                            botoes[index].setImageResource(R.drawable.marca_x_70)
                        }
                        jogoAcabouJogador1Ganhou()
                        return
                    }
                }
            }
        }

        fun verificaVencedorCom0() {
            val linhas = listOf(
                listOf(0, 1, 2, 3, 4, 5),                     //Horizontais
                listOf(6, 7, 8, 9, 10, 11),                  //Horizontais
                listOf(12, 13, 14, 15, 16, 17),            //Horizontais
                listOf(18, 19, 20, 21, 22, 23),           //Horizontais
                listOf(24, 25, 26, 27, 28, 29),         //Horizontais
                listOf(30, 31, 32, 33, 34, 35),         //Horizontais
                listOf(0, 6, 12, 18, 24, 30),              //Verticais
                listOf(1, 7, 13, 19, 25, 31),               //Verticais
                listOf(2, 8, 14, 20, 26, 32),             //Verticais
                listOf(3, 9, 15, 21, 27, 33),              //Verticais
                listOf(4, 10, 16, 22, 28, 34),            //Verticais
                listOf(5, 11, 17, 23, 29, 35),             //Verticais
                listOf(0, 7, 14, 21, 28, 35),              //Diagonais
                listOf(5, 10, 15, 20, 25, 30),            //Diagonais
            )
            for (linha in linhas) {
                if (linha.size == 6) {
                    val a = linha[0]
                    val b = linha[1]
                    val c = linha[2]
                    val d = linha[3]
                    val e = linha[4]
                    val f = linha[5]

                    if (btnMarcList6x6[a] == "0" && btnMarcList6x6[b] == "0" && btnMarcList6x6[c] == "0" &&
                        btnMarcList6x6[d] == "0" && btnMarcList6x6[e] == "0" && btnMarcList6x6[f] == "0"
                    ) {
                        val indices = listOf(a, b, c, d, e, f)
                        for (index in indices) {
                            botoes[index].setImageResource(R.drawable.marca_bolinha_70)
                        }
                        jogoAcabouJogador1Ganhou()
                        return
                    }
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
                if (contadorDeJogadas < quantTotalDeCasas && btnMarcList6x6[index].isEmpty()) {
                    val fadeIn = AlphaAnimation(0f, 1f)
                    fadeIn.duration = 900
                    fadeIn.setAnimationListener(object : Animation.AnimationListener {
                        override fun onAnimationStart(animation: Animation?) {
                            botao.setImageResource(if (jogadorAtual == jogador1) R.drawable.marca_x else R.drawable.marca_bolinha)
                            btnMarcList6x6[index] = if (jogadorAtual == jogador1) "x" else "0"
                            alteraVezDoJogador()
                        }

                        override fun onAnimationEnd(animation: Animation?) {}
                        override fun onAnimationRepeat(animation: Animation?) {}
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

    } //onCreate
} // Tabuleiros6x6Activity