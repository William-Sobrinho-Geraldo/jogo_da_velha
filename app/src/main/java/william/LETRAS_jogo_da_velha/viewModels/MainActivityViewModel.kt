package william.LETRAS_jogo_da_velha.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import william.LETRAS_jogo_da_velha.data.JogadoresModel
import william.LETRAS_jogo_da_velha.data.Repository

class MainActivityViewModel(val repository: Repository) : ViewModel() {

    suspend fun inserirJogadores(jogador: JogadoresModel) {
        repository.inserirJogadores(jogador)
    }

    fun buscaJogadoresNoBD(): LiveData<List<JogadoresModel>> {
        val liveData = MutableLiveData<List<JogadoresModel>>()
        CoroutineScope(Dispatchers.IO).launch {
            val listaDeJogadores = repository.buscaJogadoresNoBD()
            withContext(Dispatchers.Main){
                liveData.value = listaDeJogadores
            }

        }
        return liveData
    }

    suspend fun deletarTodosOsJogadores() {
        repository.deletarTodosOsJogadores()
    }
}