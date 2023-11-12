package william.LETRAS_jogo_da_velha.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import william.LETRAS_jogo_da_velha.R
import william.LETRAS_jogo_da_velha.databinding.ActivityHistorico5x5Binding
import william.LETRAS_jogo_da_velha.databinding.ActivityHistorico6x6Binding
import william.LETRAS_jogo_da_velha.utilidades.mostrarToastLonga

class Historico6x6 : AppCompatActivity() {
    private lateinit var binding: ActivityHistorico6x6Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistorico6x6Binding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnExibirRankind6x6.setOnClickListener {
            mostrarToastLonga("Ranking para tabuleiro 6x6 em construção", this)

        }

    }
}