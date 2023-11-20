package william.LETRAS_jogo_da_velha.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import william.LETRAS_jogo_da_velha.data.JogadoresModel
import william.LETRAS_jogo_da_velha.data.Repository

private const val TAG = "MainActivityViewModel"

class MainActivityViewModel(val repository: Repository) : ViewModel() {

    val oJogador1FoiEncontrado = MutableLiveData<Boolean?>(null)
    val oJogador2FoiEncontrado = MutableLiveData<Boolean>(false)


    fun buscaNomeDoJogador1NoBD(nome: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val jogador1Encontrado = async { repository.buscaNomeDoJogadorNoBD(nome) }.await()

            withContext(Dispatchers.Main) {
                oJogador1FoiEncontrado.value = jogador1Encontrado
                Log.i(
                    TAG,
                    "buscaNomeDoJogador1NoBD:    ACABEI DE MUDAR O VALOR DE oJogador1FoiEncontrado para $jogador1Encontrado "
                )
            }
        }
    }

    fun buscaNomeDoJogador2NoBD(nome: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val jogador2Encontrado = repository.buscaNomeDoJogadorNoBD(nome)


            withContext(Dispatchers.Main) {
                oJogador2FoiEncontrado.value = jogador2Encontrado
            }
        }
    }


    fun inserirJogadores(jogador: JogadoresModel) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.inserirJogadores(jogador)
        }
    }

    fun buscaJogadoresNoBD(): LiveData<List<JogadoresModel>> {
        val listaTotalDeJogadoresNoBD = MutableLiveData<List<JogadoresModel>>(emptyList())

        CoroutineScope(Dispatchers.IO).launch {
            val listaDeJogadores = repository.buscaJogadoresNoBD()
            withContext(Dispatchers.Main) {
                listaTotalDeJogadoresNoBD.value = listaDeJogadores
            }
        }
        return listaTotalDeJogadoresNoBD
    }

    suspend fun deletarTodosOsJogadores() {
        repository.deletarTodosOsJogadores()
    }

} // viewModel