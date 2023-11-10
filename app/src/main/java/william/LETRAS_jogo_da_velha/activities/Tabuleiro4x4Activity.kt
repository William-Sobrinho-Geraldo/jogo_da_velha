package william.LETRAS_jogo_da_velha.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.ImageButton
import william.LETRAS_jogo_da_velha.R
import william.LETRAS_jogo_da_velha.data.JogadoresModel
import william.LETRAS_jogo_da_velha.databinding.ActivityTabuleiro3x3Binding
import william.LETRAS_jogo_da_velha.databinding.ActivityTabuleiro4x4Binding
import william.LETRAS_jogo_da_velha.utilidades.Bot
import william.LETRAS_jogo_da_velha.utilidades.mostrarToast

private const val TAG = "Tabu4x4"

class Tabuleiro4x4Activity : AppCompatActivity() {
    private lateinit var binding: ActivityTabuleiro4x4Binding
    private lateinit var botoes: List<ImageButton>

    //COMEÇANDO O JOGO COM OTABULEIRO LIMPO
    private val buttonMarcList = mutableListOf("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTabuleiro4x4Binding.inflate(layoutInflater)
        setContentView(binding.root)


        botoes = listOf(
            binding.button1,
            binding.button2,
            binding.button3,
            binding.button4,
            binding.button5,
            binding.button6,
            binding.button7,
            binding.button8,
            binding.button9,
            binding.button10,
            binding.button11,
            binding.button12,
            binding.button13,
            binding.button14,
            binding.button15,
            binding.button16
        )


        val jogador1 = intent.getSerializableExtra("jogador1") as JogadoresModel
        var jogador2 = intent.getSerializableExtra("jogador2") as JogadoresModel
        val btnVsBotAtivo = intent.getBooleanExtra("btnVsBotAtivo", false)
        val quantTotalDeCasas = 16

        val bot = Bot()
        var botaoEscolhidoPeloBot = 0
        val blocosVazios: MutableList<Int> = mutableListOf()
        var jogadorAtual = jogador1
        var contadorDeJogadas = 0

        fun ordenaJogadaDoBot() {
            //bot só pode jogar onde estiver vazio
            blocosVazios.clear()
            Log.i(TAG, "ordenaJogadaDoBot:   blocosVazios foi limpa e agora é:  $blocosVazios")

            //ADICIONANDO NA LISTA DE blocosVazios OS ÍNDICES VAZIOS
            for (indice in buttonMarcList.indices) {
                if (buttonMarcList[indice] == "") blocosVazios.add(indice + 1)
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
            for (indice in 0 until buttonMarcList.size) {
                buttonMarcList[indice] = ""
            }

            for (botao in botoes) {
                botao.setImageResource(R.drawable.imagem_fundo_branco)   //   LIMPANDO AS MARCAS
                botao.isEnabled = true                                              //   ATIVANDO OS BOTÕES
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
        }

        fun jogoAcabouJogador2Ganhou() {
            mostrarToast("O jogo acabou, ${jogador2.nome} foi o vencedor", this)
            binding.vencedor.text = "Parabéns ${jogador2.nome}, você venceu !"
            binding.vencedor.setTextColor(resources.getColor(R.color.azul))
            //BLOQUEIA TODOS OS BOTÕES POIS O JOGO ACABOU
            botoes.forEach { it.isEnabled = false }
        }

        fun verificaVencedorComXnova() {
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
                if (buttonMarcList[a] == "x" && buttonMarcList[b] == "x" && buttonMarcList[c] == "x" && buttonMarcList[d] == "x") {
                    jogoAcabouJogador1Ganhou()
                    return
                }
            }
        }

        fun verificaVencedorCom0nova() {
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
                if (buttonMarcList[a] == "0" && buttonMarcList[b] == "0" && buttonMarcList[c] == "0" && buttonMarcList[d] == "0") {
                    jogoAcabouJogador2Ganhou()
                    return
                }
            }
        }

        fun alteraVezDoJogador() {
            //ANTES DE ALTERAR, VERIFICA SE Já TEM VENCEDOR
            verificaVencedorComXnova()
            verificaVencedorCom0nova()
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


        for ((index, botao) in botoes.withIndex()) {
            botao.setOnClickListener {
                if (contadorDeJogadas < quantTotalDeCasas && buttonMarcList[index].isEmpty()) {
                    buttonMarcList[index] = if (jogadorAtual == jogador1) "x" else "0"
                    botao.setImageResource(if (jogadorAtual == jogador1) R.drawable.marca_x else R.drawable.marca_bolinha)
                    alteraVezDoJogador()

                    //verifica se está jogando com o bot e manda ele jogar
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

    } //   onCreate
} // Tabuleiro4x4Activity