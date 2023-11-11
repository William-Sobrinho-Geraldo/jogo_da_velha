package william.LETRAS_jogo_da_velha.viewModels

import androidx.lifecycle.ViewModel
import william.LETRAS_jogo_da_velha.data.Repository

class Tabuleiro3x3ViewModel(val repository: Repository) : ViewModel() {
    fun incrementarVitoria(jogadorId: Long) {
        repository.incrementarVitoria(jogadorId)
    }

    fun incrementarDerrota(jogadorId: Long) {
        repository.incrementarDerrota(jogadorId)
    }
}