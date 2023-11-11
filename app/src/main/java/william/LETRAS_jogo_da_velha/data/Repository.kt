package william.LETRAS_jogo_da_velha.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Repository(private val jogadoresDao: JogadoresDao) {

    suspend fun inserirJogadores(jogador: JogadoresModel) {
        jogadoresDao.inserirJogador(jogador)
    }

    suspend fun buscaJogadoresNoBD(): List<JogadoresModel> {
        //        var lista = MutableLiveData<JogadoresModel>()

        return jogadoresDao.buscaJogadoresNoBD()
    }


    suspend fun deletarTodosOsJogadores() {
        jogadoresDao.deletarTodosOsJogadores()
    }

    fun incrementarVitoria(jogadorId: Long) {
        jogadoresDao.incrementarVitoria(jogadorId)
    }

    fun incrementarDerrota(jogadorId: Long) {
        jogadoresDao.incrementarDerrota(jogadorId)
    }

}