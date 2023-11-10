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


        fun alteraVezDoJogador() {
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
                    binding.button1.setBackgroundResource(R.drawable.marca_x) // essa é a marca do jogador1
                    alteraVezDoJogador()
                } else {
                    binding.button1.setBackgroundResource(R.drawable.marca_bolinha) //essa é a marca do jogador2
                    alteraVezDoJogador()
                }
            } else mostrarToast("o jogo acabou", this)


            //adicionar a marca do jogador1


            //  altero o texto do jogador x

            // altera a corDoTexto do jogadorX


        }

        binding.button2.setOnClickListener {
            if (contadorDeJogadas < 9) {
                if (jogadorAtual == jogador1) {
                    binding.button2.setBackgroundResource(R.drawable.marca_x) // essa é a marca do jogador1
                    alteraVezDoJogador()
                } else {
                    binding.button2.setBackgroundResource(R.drawable.marca_bolinha) //essa é a marca do jogador2
                    alteraVezDoJogador()
                }
            } else mostrarToast("o jogo acabou", this)
        }

        binding.button3.setOnClickListener {
            if (contadorDeJogadas < 9) {
                if (jogadorAtual == jogador1) {
                    binding.button3.setBackgroundResource(R.drawable.marca_x) // essa é a marca do jogador1
                    alteraVezDoJogador()
                } else {
                    binding.button3.setBackgroundResource(R.drawable.marca_bolinha) //essa é a marca do jogador2
                    alteraVezDoJogador()
                }
            } else mostrarToast("o jogo acabou", this)
        }

        binding.button4.setOnClickListener {
            if (contadorDeJogadas < 9) {
                if (jogadorAtual == jogador1) {
                    binding.button4.setBackgroundResource(R.drawable.marca_x) // essa é a marca do jogador1
                    alteraVezDoJogador()
                } else {
                    binding.button4.setBackgroundResource(R.drawable.marca_bolinha) //essa é a marca do jogador2
                    alteraVezDoJogador()
                }
            } else mostrarToast("o jogo acabou", this)
        }

        binding.button5.setOnClickListener {
            if (contadorDeJogadas < 9) {
                if (jogadorAtual == jogador1) {
                    binding.button5.setBackgroundResource(R.drawable.marca_x) // essa é a marca do jogador1
                    alteraVezDoJogador()
                } else {
                    binding.button5.setBackgroundResource(R.drawable.marca_bolinha) //essa é a marca do jogador2
                    alteraVezDoJogador()
                }
            } else mostrarToast("o jogo acabou", this)
        }

        binding.button6.setOnClickListener {
            if (contadorDeJogadas < 9) {
                if (jogadorAtual == jogador1) {
                    binding.button6.setBackgroundResource(R.drawable.marca_x) // essa é a marca do jogador1
                    alteraVezDoJogador()
                } else {
                    binding.button6.setBackgroundResource(R.drawable.marca_bolinha) //essa é a marca do jogador2
                    alteraVezDoJogador()
                }
            } else mostrarToast("o jogo acabou", this)
        }

        binding.button7.setOnClickListener {
            if (contadorDeJogadas < 9) {
                if (jogadorAtual == jogador1) {
                    binding.button7.setBackgroundResource(R.drawable.marca_x) // essa é a marca do jogador1
                    alteraVezDoJogador()
                } else {
                    binding.button7.setBackgroundResource(R.drawable.marca_bolinha) //essa é a marca do jogador2
                    alteraVezDoJogador()
                }
            } else mostrarToast("o jogo acabou", this)
        }

        binding.button8.setOnClickListener {
            if (contadorDeJogadas < 9) {
                if (jogadorAtual == jogador1) {
                    binding.button8.setBackgroundResource(R.drawable.marca_x) // essa é a marca do jogador1
                    alteraVezDoJogador()
                } else {
                    binding.button8.setBackgroundResource(R.drawable.marca_bolinha) //essa é a marca do jogador2
                    alteraVezDoJogador()
                }
            } else mostrarToast("o jogo acabou", this)
        }

        binding.button9.setOnClickListener {
            if (contadorDeJogadas < 9) {
                if (jogadorAtual == jogador1) {
                    binding.button9.setBackgroundResource(R.drawable.marca_x) // essa é a marca do jogador1
                    alteraVezDoJogador()
                } else {
                    binding.button9.setBackgroundResource(R.drawable.marca_bolinha) //essa é a marca do jogador2
                    alteraVezDoJogador()
                }
            } else mostrarToast("o jogo acabou", this)
        }

    }  //onCreate

}