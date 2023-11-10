package william.LETRAS_jogo_da_velha.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import william.LETRAS_jogo_da_velha.R
import william.LETRAS_jogo_da_velha.databinding.ActivityMainBinding
import william.LETRAS_jogo_da_velha.databinding.ActivityTabuleiroBinding
private const val TAG = "TabuleitoActivity"

class TabuleiroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTabuleiroBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTabuleiroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nomeJogador1 = intent.getStringExtra("jogador1")
        val nomeJogador2 = intent.getStringExtra("jogador2")

        Log.i(TAG, "onCreate: jogador1 é $nomeJogador1 e jogador 2 é $nomeJogador2")

        //mostrar que é a vez do jogador1
        binding.jogadorX.text = nomeJogador1


        binding.button1.setOnClickListener {
            binding.button1.setBackgroundResource(R.drawable.marca_x)
        }

        binding.button2.setOnClickListener {
            binding.button2.setBackgroundResource(R.drawable.marca_bolinha)

        }

    }  //onCreate
}