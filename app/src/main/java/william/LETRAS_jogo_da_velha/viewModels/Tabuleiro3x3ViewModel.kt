package william.LETRAS_jogo_da_velha.viewModels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import william.LETRAS_jogo_da_velha.data.HistoricoItemModel
import william.LETRAS_jogo_da_velha.data.JogadoresModel
import william.LETRAS_jogo_da_velha.data.Repository

class Tabuleiro3x3ViewModel(val repository: Repository) : ViewModel() {
    fun incrementarVitoria(nome : String) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.incrementarVitoria(nome)
        }
    }

    fun incrementarDerrota(jogadorId: Long) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.incrementarDerrota(jogadorId)
        }
    }


    //FUNÇÕES PARA USO NO HISTÓRICO DE JOGOS
    fun inserirHistorico(historicoItem: HistoricoItemModel) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.inserirHistorico(historicoItem)
        }
    }

    suspend fun buscaHistorico3x3(): List<HistoricoItemModel> {
        return withContext(Dispatchers.IO) {
            repository.buscaHistorico3x3()
        }
    }

    suspend fun buscaJogadoresOrdenadosPorGanhos(): List<JogadoresModel> {
        return repository.buscaJogadoresOrdenadosPorGanhos()
    }
}

