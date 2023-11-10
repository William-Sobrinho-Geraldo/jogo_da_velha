package william.LETRAS_jogo_da_velha.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import william.LETRAS_jogo_da_velha.R
import william.LETRAS_jogo_da_velha.data.JogadoresModel
import william.LETRAS_jogo_da_velha.databinding.ActivityTabuleiroBinding
import william.LETRAS_jogo_da_velha.utilidades.mostrarToast

private const val TAG = "TabuleitoActivity"

class TabuleiroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTabuleiroBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTabuleiroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val jogador1 = intent.getSerializableExtra("jogador1") as JogadoresModel
        val jogador2 = intent.getSerializableExtra("jogador2") as JogadoresModel
        var jogadorAtual = jogador1
        var contadorDeJogadas = 0


        var button1Marc = ""
        var button2Marc = ""
        var button3Marc = ""
        var button4Marc = ""
        var button5Marc = ""
        var button6Marc = ""
        var button7Marc = ""
        var button8Marc = ""
        var button9Marc = ""


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

        fun alteraVezDoJogador() {
            verificaVencedorComX()

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


        Log.i(TAG, "onCreate: jogador1 é $jogador1 e jogador 2 é $jogador2")


        //mostrar que é a vez do jogador1 com cor do jogador 1
        binding.jogadorX.text = jogador1.nome



        binding.button1.setOnClickListener {
            if (contadorDeJogadas < 9) {
                if (jogadorAtual == jogador1) {
                    button1Marc = "x"
                    binding.button1.setBackgroundResource(R.drawable.marca_x) // essa é a marca do jogador1
                    alteraVezDoJogador()
                } else {
                    binding.button1.setBackgroundResource(R.drawable.marca_bolinha) //essa é a marca do jogador2
                    alteraVezDoJogador()
                    button1Marc = "0"
                }
            } else mostrarToast("o jogo acabou", this)


            Log.i(TAG, "onCreate: o botão 1 foi marcado com $button1Marc")

        }

        binding.button2.setOnClickListener {
            if (contadorDeJogadas < 9) {
                if (jogadorAtual == jogador1) {
                    button2Marc = "x"
                    binding.button2.setBackgroundResource(R.drawable.marca_x) // essa é a marca do jogador1
                    alteraVezDoJogador()

                } else {
                    binding.button2.setBackgroundResource(R.drawable.marca_bolinha) //essa é a marca do jogador2
                    alteraVezDoJogador()
                    button2Marc = "0"
                }
            } else mostrarToast("o jogo acabou", this)
        }

        binding.button3.setOnClickListener {
            if (contadorDeJogadas < 9) {
                if (jogadorAtual == jogador1) {
                    button3Marc = "x"
                    binding.button3.setBackgroundResource(R.drawable.marca_x) // essa é a marca do jogador1
                    alteraVezDoJogador()
                } else {
                    binding.button3.setBackgroundResource(R.drawable.marca_bolinha) //essa é a marca do jogador2
                    alteraVezDoJogador()
                    button3Marc = "0"
                }
            } else mostrarToast("o jogo acabou", this)
        }

        binding.button4.setOnClickListener {
            if (contadorDeJogadas < 9) {
                if (jogadorAtual == jogador1) {
                    button4Marc = "x"
                    binding.button4.setBackgroundResource(R.drawable.marca_x) // essa é a marca do jogador1
                    alteraVezDoJogador()
                } else {
                    binding.button4.setBackgroundResource(R.drawable.marca_bolinha) //essa é a marca do jogador2
                    alteraVezDoJogador()
                    button4Marc = "0"
                }
            } else mostrarToast("o jogo acabou", this)
        }

        binding.button5.setOnClickListener {
            if (contadorDeJogadas < 9) {
                if (jogadorAtual == jogador1) {
                    button5Marc = "x"
                    binding.button5.setBackgroundResource(R.drawable.marca_x) // essa é a marca do jogador1
                    alteraVezDoJogador()
                } else {
                    binding.button5.setBackgroundResource(R.drawable.marca_bolinha) //essa é a marca do jogador2
                    alteraVezDoJogador()
                    button5Marc = "0"
                }
            } else mostrarToast("o jogo acabou", this)
        }

        binding.button6.setOnClickListener {
            if (contadorDeJogadas < 9) {
                if (jogadorAtual == jogador1) {
                    button6Marc = "x"
                    binding.button6.setBackgroundResource(R.drawable.marca_x) // essa é a marca do jogador1
                    alteraVezDoJogador()
                } else {
                    binding.button6.setBackgroundResource(R.drawable.marca_bolinha) //essa é a marca do jogador2
                    alteraVezDoJogador()
                    button6Marc = "0"
                }
            } else mostrarToast("o jogo acabou", this)
        }

        binding.button7.setOnClickListener {
            if (contadorDeJogadas < 9) {
                if (jogadorAtual == jogador1) {
                    button7Marc = "x"
                    binding.button7.setBackgroundResource(R.drawable.marca_x) // essa é a marca do jogador1
                    alteraVezDoJogador()

                } else {
                    binding.button7.setBackgroundResource(R.drawable.marca_bolinha) //essa é a marca do jogador2
                    alteraVezDoJogador()
                    button7Marc = "0"
                }
            } else mostrarToast("o jogo acabou", this)
        }

        binding.button8.setOnClickListener {
            if (contadorDeJogadas < 9) {
                if (jogadorAtual == jogador1) {
                    button8Marc = "x"
                    binding.button8.setBackgroundResource(R.drawable.marca_x) // essa é a marca do jogador1
                    alteraVezDoJogador()

                } else {
                    binding.button8.setBackgroundResource(R.drawable.marca_bolinha) //essa é a marca do jogador2
                    alteraVezDoJogador()
                    button8Marc = "0"
                }
            } else mostrarToast("o jogo acabou", this)
        }

        binding.button9.setOnClickListener {
            if (contadorDeJogadas < 9) {
                if (jogadorAtual == jogador1) {
                    button9Marc = "x"
                    binding.button9.setBackgroundResource(R.drawable.marca_x) // essa é a marca do jogador1
                    alteraVezDoJogador()

                } else {
                    binding.button9.setBackgroundResource(R.drawable.marca_bolinha) //essa é a marca do jogador2
                    alteraVezDoJogador()
                    button9Marc = "0"
                }
            } else mostrarToast("o jogo acabou", this)
        }

    }  //onCreate

}