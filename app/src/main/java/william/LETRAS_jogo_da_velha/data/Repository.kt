package william.LETRAS_jogo_da_velha.data

import android.util.Log

class Repository(
    private val jogadoresDao: JogadoresDao,
    private val historicoDao: HistoricoDao,
) {

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

    suspend fun incrementarVitoria(jogadorId: Long) {
        jogadoresDao.incrementarVitoria(jogadorId)
    }

    suspend fun incrementarDerrota(jogadorId: Long) {
        jogadoresDao.incrementarDerrota(jogadorId)
    }


    //FUNÇÕES PARA USO NO HISTÓRICO DE JOGOS
    suspend fun inserirHistorico(historicoItem: HistoricoItemModel) {
        historicoDao.inserirHistorico(historicoItem)
    }

    suspend fun buscaHistorico3x3(): List<HistoricoItemModel> {
        return historicoDao.buscaHistorico3x3()
        Log.i("Historico3x3", "buscaHistorico: a lsita que vem do Dao é ${historicoDao.buscaHistorico3x3()}")
    }

}