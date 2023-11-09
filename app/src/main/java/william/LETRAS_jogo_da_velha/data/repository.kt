package william.LETRAS_jogo_da_velha.data

import androidx.lifecycle.LifecycleCoroutineScope
import kotlinx.coroutines.withContext

class repository (private val jogadoresDao : JogadoresDao){

    suspend fun inserirJogadores(jogador : JogadoresModel){
        jogadoresDao.inserirJogador(jogador)
    }


}