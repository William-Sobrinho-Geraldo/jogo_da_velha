package william.LETRAS_jogo_da_velha.activities

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import william.LETRAS_jogo_da_velha.R
import william.LETRAS_jogo_da_velha.data.JogadoresModel
import william.LETRAS_jogo_da_velha.databinding.ActivityTabuleiro3x3Binding
import william.LETRAS_jogo_da_velha.utilidades.Bot
import william.LETRAS_jogo_da_velha.utilidades.mostrarToast

private const val TAG = "TabuleitoActivity"

class Tabuleiro3x3Activity : AppCompatActivity() {
    private lateinit var binding: ActivityTabuleiro3x3Binding
    private lateinit var botoes: List<ImageButton>

    //COMEÇANDO O JOGO COM OTABULEIRO LIMPO
    private val buttonMarcList = mutableListOf("", "", "", "", "", "", "", "", "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTabuleiro3x3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        botoes = listOf(
            binding.button1, binding.button2, binding.button3,
            binding.button4, binding.button5, binding.button6,
            binding.button7, binding.button8, binding.button9
        )


        val jogador1 = intent.getSerializableExtra("jogador1") as JogadoresModel
        var jogador2 = intent.getSerializableExtra("jogador2") as JogadoresModel
        val btnVsBotAtivo = intent.getBooleanExtra("btnVsBotAtivo", false)

        val bot = Bot()
        var botaoEscolhidoPeloBot = 0
        val blocosVazios: MutableList<Int> = mutableListOf()
        var jogadorAtual = jogador1
        var contadorDeJogadas = 0

        fun ordenaJogadaDoBot() {
            //bot só pode jogar onde estiver vazio
            blocosVazios.clear()
            Log.i(TAG, "ordenaJogadaDoBot:   blocosVazios foi limpa e agora é:  $blocosVazios")

            if (buttonMarcList[0] == "") blocosVazios.add(1)
            if (buttonMarcList[1] == "") blocosVazios.add(2)
            if (buttonMarcList[2] == "") blocosVazios.add(3)
            if (buttonMarcList[3] == "") blocosVazios.add(4)
            if (buttonMarcList[4] == "") blocosVazios.add(5)
            if (buttonMarcList[5] == "") blocosVazios.add(6)
            if (buttonMarcList[6] == "") blocosVazios.add(7)
            if (buttonMarcList[7] == "") blocosVazios.add(8)
            if (buttonMarcList[8] == "") blocosVazios.add(9)
            Log.i(TAG, "ordenaJogadaDoBot:   Os espaços disponíveis são $blocosVazios")


            if (blocosVazios.isNotEmpty()) {
                //Escolhendo botão que o bot Vai clicar aleatóriamente
                botaoEscolhidoPeloBot = blocosVazios.random()
                Log.i(TAG, "ordenaJogadaDoBot:  botãoEscolhido foi  ${botaoEscolhidoPeloBot}")
                //                Handler(Looper.getMainLooper()).postDelayed({
                if (botaoEscolhidoPeloBot == 1 && binding.button1.isEnabled) binding.button1.performClick()
                if (botaoEscolhidoPeloBot == 2 && binding.button2.isEnabled) binding.button2.performClick()
                if (botaoEscolhidoPeloBot == 3 && binding.button3.isEnabled) binding.button3.performClick()
                if (botaoEscolhidoPeloBot == 4 && binding.button4.isEnabled) binding.button4.performClick()
                if (botaoEscolhidoPeloBot == 5 && binding.button5.isEnabled) binding.button5.performClick()
                if (botaoEscolhidoPeloBot == 6 && binding.button6.isEnabled) binding.button6.performClick()
                if (botaoEscolhidoPeloBot == 7 && binding.button7.isEnabled) binding.button7.performClick()
                if (botaoEscolhidoPeloBot == 8 && binding.button8.isEnabled) binding.button8.performClick()
                if (botaoEscolhidoPeloBot == 9 && binding.button9.isEnabled) binding.button9.performClick()
                //                }, 300)

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
            for ( indice in 0 until buttonMarcList.size){
                buttonMarcList[indice] = ""
            }

            //   LIMPANDO AS MARCAS
            binding.button1.setBackgroundColor(resources.getColor(R.color.white))
            binding.button2.setBackgroundColor(resources.getColor(R.color.white))
            binding.button3.setBackgroundColor(resources.getColor(R.color.white))
            binding.button4.setBackgroundColor(resources.getColor(R.color.white))
            binding.button5.setBackgroundColor(resources.getColor(R.color.white))
            binding.button6.setBackgroundColor(resources.getColor(R.color.white))
            binding.button7.setBackgroundColor(resources.getColor(R.color.white))
            binding.button8.setBackgroundColor(resources.getColor(R.color.white))
            binding.button9.setBackgroundColor(resources.getColor(R.color.white))
            //ATIVANDO OS BOTÕES
            binding.button1.isEnabled = true
            binding.button2.isEnabled = true
            binding.button3.isEnabled = true
            binding.button4.isEnabled = true
            binding.button5.isEnabled = true
            binding.button6.isEnabled = true
            binding.button7.isEnabled = true
            binding.button8.isEnabled = true
            binding.button9.isEnabled = true
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
            binding.button1.isEnabled = false
            binding.button2.isEnabled = false
            binding.button3.isEnabled = false
            binding.button4.isEnabled = false
            binding.button5.isEnabled = false
            binding.button6.isEnabled = false
            binding.button7.isEnabled = false
            binding.button8.isEnabled = false
            binding.button9.isEnabled = false
        }

        fun jogoAcabouJogador2Ganhou() {
            mostrarToast("O jogo acabou, ${jogador2.nome} foi o vencedor", this)
            binding.vencedor.text = "Parabéns ${jogador2.nome}, você venceu !"
            binding.vencedor.setTextColor(resources.getColor(R.color.azul))
            //BLOQUEIA TODOS OS BOTÕES POIS O JOGO ACABOU
            binding.button1.isEnabled = false
            binding.button2.isEnabled = false
            binding.button3.isEnabled = false
            binding.button4.isEnabled = false
            binding.button5.isEnabled = false
            binding.button6.isEnabled = false
            binding.button7.isEnabled = false
            binding.button8.isEnabled = false
            binding.button9.isEnabled = false
        }

        fun verificaVencedorComXnova() {
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
                if (buttonMarcList[a] == "x" && buttonMarcList[b] == "x" && buttonMarcList[c] == "x") {
                    jogoAcabouJogador1Ganhou()
                    return
                }
            }
        }

        fun verificaVencedorCom0nova() {
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
                if (buttonMarcList[a] == "0" && buttonMarcList[b] == "0" && buttonMarcList[c] == "0") {
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




        for ((index, botao) in botoes.withIndex()) {
            botao.setOnClickListener {
                if (contadorDeJogadas < 9 && buttonMarcList[index].isEmpty()) {
                    buttonMarcList[index] = if (jogadorAtual == jogador1) "x" else "0"
                    botao.setBackgroundResource(if (jogadorAtual == jogador1) R.drawable.marca_x else R.drawable.marca_bolinha)
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

    }  //onCreate

} // Tabuleiro3x3Activity