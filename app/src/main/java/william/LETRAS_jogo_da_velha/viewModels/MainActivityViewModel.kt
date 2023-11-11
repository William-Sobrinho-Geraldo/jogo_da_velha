package william.LETRAS_jogo_da_velha.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import william.LETRAS_jogo_da_velha.data.JogadoresModel
import william.LETRAS_jogo_da_velha.data.Repository

class MainActivityViewModel(val repository: Repository) : ViewModel() {

    suspend fun inserirJogadores(jogador: JogadoresModel) {
        repository.inserirJogadores(jogador)
    }

    suspend fun buscaJogadoresNoBD(): MutableLiveData<List<JogadoresModel>> {
        val liveData = MutableLiveData<List<JogadoresModel>>()
        liveData.value = repository.buscaJogadoresNoBD()
        return liveData
    }

    suspend fun deletarTodosOsJogadores() {
        repository.deletarTodosOsJogadores()
    }
}