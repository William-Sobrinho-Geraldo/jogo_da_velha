package william.LETRAS_jogo_da_velha.viewModels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import william.LETRAS_jogo_da_velha.data.HistoricoItemModel
import william.LETRAS_jogo_da_velha.data.Repository

class Tabuleiro4x4ViewModel(val repository: Repository) : ViewModel() {
    fun incrementarVitoriaPorNome(nome : String) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.incrementarVitoria(nome)
        }
    }

    fun incrementarDerrotaPorNome(nome : String) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.incrementarDerrota(nome)
        }
    }


    //FUNÇÕES PARA USO NO HISTÓRICO DE JOGOS
    fun inserirHistorico(historicoItem: HistoricoItemModel) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.inserirHistorico(historicoItem)
        }
    }

    suspend fun buscaHistorico4x4(): List<HistoricoItemModel> {
        return withContext(Dispatchers.IO) {
            repository.buscaHistorico4x4()
        }
    }

} //viewModel