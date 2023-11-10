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

        //COMEÇANDO O JOGO COM OTABULEIRO LIMPO
        var button1Marc = ""
        var button2Marc = ""
        var button3Marc = ""
        var button4Marc = ""
        var button5Marc = ""
        var button6Marc = ""
        var button7Marc = ""
        var button8Marc = ""
        var button9Marc = ""

        fun ordenaJogadaDoBot() {
            //bot só pode jogar onde estiver vazio
            blocosVazios.clear()
            Log.i(TAG, "ordenaJogadaDoBot:   blocosVazios foi limpa e agora é:  $blocosVazios")

            if (button1Marc == "") blocosVazios.add(1)
            if (button2Marc == "") blocosVazios.add(2)
            if (button3Marc == "") blocosVazios.add(3)
            if (button4Marc == "") blocosVazios.add(4)
            if (button5Marc == "") blocosVazios.add(5)
            if (button6Marc == "") blocosVazios.add(6)
            if (button7Marc == "") blocosVazios.add(7)
            if (button8Marc == "") blocosVazios.add(8)
            if (button9Marc == "") blocosVazios.add(9)
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
            for ( indice in 0 until buttonMarcList.size){
                buttonMarcList[indice] = ""
            }

            button1Marc = ""
            button2Marc = ""
            button3Marc = ""
            button4Marc = ""  //VOU TER Q LIMPAR A LISTA NOVA
            button5Marc = ""
            button6Marc = ""
            button7Marc = ""
            button8Marc = ""
            button9Marc = ""
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

        fun verificaVencedorComX() {
            //Horizontais
            if (button1Marc == "x" && button2Marc == "x" && button3Marc == "x") jogoAcabouJogador1Ganhou()
            if (button4Marc == "x" && button5Marc == "x" && button6Marc == "x") jogoAcabouJogador1Ganhou()
            if (button7Marc == "x" && button8Marc == "x" && button9Marc == "x") jogoAcabouJogador1Ganhou()
            //Verticais
            if (button1Marc == "x" && button4Marc == "x" && button7Marc == "x") jogoAcabouJogador1Ganhou()
            if (button2Marc == "x" && button5Marc == "x" && button8Marc == "x") jogoAcabouJogador1Ganhou()
            if (button3Marc == "x" && button6Marc == "x" && button9Marc == "x") jogoAcabouJogador1Ganhou()
            //Diagonais
            if (button1Marc == "x" && button5Marc == "x" && button9Marc == "x") jogoAcabouJogador1Ganhou()
            if (button3Marc == "x" && button5Marc == "x" && button7Marc == "x") jogoAcabouJogador1Ganhou()
        }

        fun verificaVencedorCom0() {
            //Horizontais
            if (button1Marc == "0" && button2Marc == "0" && button3Marc == "0") jogoAcabouJogador2Ganhou()
            if (button4Marc == "0" && button5Marc == "0" && button6Marc == "0") jogoAcabouJogador2Ganhou()
            if (button7Marc == "0" && button8Marc == "0" && button9Marc == "0") jogoAcabouJogador2Ganhou()
            //Verticais
            if (button1Marc == "0" && button4Marc == "0" && button7Marc == "0") jogoAcabouJogador2Ganhou()
            if (button2Marc == "0" && button5Marc == "0" && button8Marc == "0") jogoAcabouJogador2Ganhou()
            if (button3Marc == "0" && button6Marc == "0" && button9Marc == "0") jogoAcabouJogador2Ganhou()
            //Diagonais
            if (button1Marc == "0" && button5Marc == "0" && button9Marc == "0") jogoAcabouJogador2Ganhou()
            if (button3Marc == "0" && button5Marc == "0" && button7Marc == "0") jogoAcabouJogador2Ganhou()
        }

        fun alteraVezDoJogador() {
            //ANTES DE ALTERAR, VERIFICA SE Já TEM VENCEDOR
            verificaVencedorComX()
            verificaVencedorCom0()
            //VERIFICA SE HOUVE EMPATE
            if (contadorDeJogadas == 9) {
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


        //ESCUTA O CLICK EM CADA ImageButton
//        binding.button1.setOnClickListener {
//            if (contadorDeJogadas < 9) {
//                if (jogadorAtual == jogador1) {
//                    button1Marc = "x"
//                    binding.button1.setBackgroundResource(R.drawable.marca_x) // essa é a marca do jogador1
//                    alteraVezDoJogador()
//                } else {
//                    button1Marc = "0"
//                    binding.button1.setBackgroundResource(R.drawable.marca_bolinha) //essa é a marca do jogador2
//                    alteraVezDoJogador()
//                }
//
//                //verifica se está jogando com o bot e manda ele jogar
//                if (jogadorAtual == jogador2) {
//                    ordenaJogadaDoBot()
//                }
//
//            } else mostrarToast("o jogo acabou", this)
//            binding.button1.isEnabled = false
//            Log.i(TAG, "onCreate: botao1 bloqueado para outros clicks")
//
//        }
//
//        binding.button2.setOnClickListener {
//            if (contadorDeJogadas < 9) {
//                if (jogadorAtual == jogador1) {
//                    button2Marc = "x"
//                    binding.button2.setBackgroundResource(R.drawable.marca_x) // essa é a marca do jogador1
//                    alteraVezDoJogador()
//                } else {
//                    button2Marc = "0"
//                    binding.button2.setBackgroundResource(R.drawable.marca_bolinha) //essa é a marca do jogador2
//                    alteraVezDoJogador()
//                }
//                //verifica se está jogando com o bot e manda ele jogar
//                if (jogadorAtual == jogador2) {
//                    ordenaJogadaDoBot()
//                }
//            } else mostrarToast("o jogo acabou", this)
//            binding.button2.isEnabled = false
//            Log.i(TAG, "onCreate: botao2 bloqueado para outros clicks")
//
//        }
//
//        binding.button3.setOnClickListener {
//            if (contadorDeJogadas < 9) {
//                if (jogadorAtual == jogador1) {
//                    button3Marc = "x"
//                    binding.button3.setBackgroundResource(R.drawable.marca_x) // essa é a marca do jogador1
//                    alteraVezDoJogador()
//                } else {
//                    button3Marc = "0"
//                    binding.button3.setBackgroundResource(R.drawable.marca_bolinha) //essa é a marca do jogador2
//                    alteraVezDoJogador()
//                }
//                //verifica se está jogando com o bot e manda ele jogar
//                if (jogadorAtual == jogador2) {
//                    ordenaJogadaDoBot()
//                }
//            } else mostrarToast("o jogo acabou", this)
//            binding.button3.isEnabled = false
//            Log.i(TAG, "onCreate: botao3 bloqueado para outros clicks")
//
//        }
//
//        binding.button4.setOnClickListener {
//            if (contadorDeJogadas < 9) {
//                if (jogadorAtual == jogador1) {
//                    button4Marc = "x"
//                    binding.button4.setBackgroundResource(R.drawable.marca_x) // essa é a marca do jogador1
//                    alteraVezDoJogador()
//                } else {
//                    button4Marc = "0"
//                    binding.button4.setBackgroundResource(R.drawable.marca_bolinha) //essa é a marca do jogador2
//                    alteraVezDoJogador()
//                }
//                //verifica se está jogando com o bot e manda ele jogar
//                if (jogadorAtual == jogador2) {
//                    ordenaJogadaDoBot()
//                }
//            } else mostrarToast("o jogo acabou", this)
//            binding.button4.isEnabled = false
//            Log.i(TAG, "onCreate: botao4 bloqueado para outros clicks")
//
//        }
//
//        binding.button5.setOnClickListener {
//            if (contadorDeJogadas < 9) {
//                if (jogadorAtual == jogador1) {
//                    button5Marc = "x"
//                    binding.button5.setBackgroundResource(R.drawable.marca_x) // essa é a marca do jogador1
//                    alteraVezDoJogador()
//                } else {
//                    button5Marc = "0"
//                    binding.button5.setBackgroundResource(R.drawable.marca_bolinha) //essa é a marca do jogador2
//                    alteraVezDoJogador()
//                }
//                //verifica se está jogando com o bot e manda ele jogar
//                if (jogadorAtual == jogador2) {
//                    ordenaJogadaDoBot()
//                }
//            } else mostrarToast("o jogo acabou", this)
//            binding.button5.isEnabled = false
//            Log.i(TAG, "onCreate: botao5 bloqueado para outros clicks")
//
//        }
//
//        binding.button6.setOnClickListener {
//            if (contadorDeJogadas < 9) {
//                if (jogadorAtual == jogador1) {
//                    button6Marc = "x"
//                    binding.button6.setBackgroundResource(R.drawable.marca_x) // essa é a marca do jogador1
//                    alteraVezDoJogador()
//                } else {
//                    button6Marc = "0"
//                    binding.button6.setBackgroundResource(R.drawable.marca_bolinha) //essa é a marca do jogador2
//                    alteraVezDoJogador()
//                }
//                //verifica se está jogando com o bot e manda ele jogar
//                if (jogadorAtual == jogador2) {
//                    ordenaJogadaDoBot()
//                }
//            } else mostrarToast("o jogo acabou", this)
//            binding.button6.isEnabled = false
//            Log.i(TAG, "onCreate: botao6 bloqueado para outros clicks")
//        }
//
//        binding.button7.setOnClickListener {
//            if (contadorDeJogadas < 9) {
//                if (jogadorAtual == jogador1) {
//                    button7Marc = "x"
//                    binding.button7.setBackgroundResource(R.drawable.marca_x) // essa é a marca do jogador1
//                    alteraVezDoJogador()
//                } else {
//                    button7Marc = "0"
//                    binding.button7.setBackgroundResource(R.drawable.marca_bolinha) //essa é a marca do jogador2
//                    alteraVezDoJogador()
//                }
//                //verifica se está jogando com o bot e manda ele jogar
//                if (jogadorAtual == jogador2) {
//                    ordenaJogadaDoBot()
//                }
//            } else mostrarToast("o jogo acabou", this)
//            binding.button7.isEnabled = false
//            Log.i(TAG, "onCreate: botao7 bloqueado para outros clicks")
//
//        }
//
//        binding.button8.setOnClickListener {
//            if (contadorDeJogadas < 9) {
//                if (jogadorAtual == jogador1) {
//                    button8Marc = "x"
//                    binding.button8.setBackgroundResource(R.drawable.marca_x) // essa é a marca do jogador1
//                    alteraVezDoJogador()
//                } else {
//                    button8Marc = "0"
//                    binding.button8.setBackgroundResource(R.drawable.marca_bolinha) //essa é a marca do jogador2
//                    alteraVezDoJogador()
//                }
//                //verifica se está jogando com o bot e manda ele jogar
//                if (jogadorAtual == jogador2) {
//                    ordenaJogadaDoBot()
//                }
//            } else mostrarToast("o jogo acabou", this)
//            binding.button8.isEnabled = false
//            Log.i(TAG, "onCreate: botao8 bloqueado para outros clicks")
//
//        }
//
//        binding.button9.setOnClickListener {
//            if (contadorDeJogadas < 9) {
//                if (jogadorAtual == jogador1) {
//                    button9Marc = "x"
//                    binding.button9.setBackgroundResource(R.drawable.marca_x) // essa é a marca do jogador1
//                    alteraVezDoJogador()
//                } else {
//                    button9Marc = "0"
//                    binding.button9.setBackgroundResource(R.drawable.marca_bolinha) //essa é a marca do jogador2
//                    alteraVezDoJogador()
//                }
//                //verifica se está jogando com o bot e manda ele jogar
//                if (jogadorAtual == jogador2) {
//                    ordenaJogadaDoBot()
//                }
//            } else mostrarToast("o jogo acabou", this)
//            binding.button9.isEnabled = false
//            Log.i(TAG, "onCreate: botao9 bloqueado para outros clicks")
//
//        }

        //BOTÃO NOVO JOGO
        binding.btnNovoJogo.setOnClickListener {
            limpaTabuleiro()
        }

    }  //onCreate

} // Tabuleiro3x3Activity