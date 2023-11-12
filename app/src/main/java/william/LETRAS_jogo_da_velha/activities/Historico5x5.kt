package william.LETRAS_jogo_da_velha.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import william.LETRAS_jogo_da_velha.R
import william.LETRAS_jogo_da_velha.databinding.ActivityHistorico4x4Binding
import william.LETRAS_jogo_da_velha.databinding.ActivityHistorico5x5Binding
import william.LETRAS_jogo_da_velha.utilidades.mostrarToastLonga

class Historico5x5 : AppCompatActivity() {
    private lateinit var binding: ActivityHistorico5x5Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistorico5x5Binding.inflate(layoutInflater)
        setContentView(binding.root)

    binding.btnExibirRankind5x5.setOnClickListener {
        mostrarToastLonga("Ranking para tabuleiro 5x5 em construção", this)
    }

    }


}