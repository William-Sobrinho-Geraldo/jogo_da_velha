package william.LETRAS_jogo_da_velha.data

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
    suspend fun inserirHistorico(historicoItem : HistoricoItemModel) {
        historicoDao.inserirHistorico(historicoItem)
    }

    suspend fun buscaHistorico(){
        historicoDao.buscaHistorico()
    }

}