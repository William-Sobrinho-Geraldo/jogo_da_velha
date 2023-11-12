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

    suspend fun incrementarVitoria(nome: String) {
        jogadoresDao.incrementarVitoriaPorNome(nome)
    }

    suspend fun incrementarDerrota(nome: String) {
        jogadoresDao.incrementarDerrotaPorNome(nome)
    }


    //FUNÇÕES PARA USO NO HISTÓRICO DE JOGOS
    suspend fun inserirHistorico(historicoItem: HistoricoItemModel) {
        historicoDao.inserirHistorico(historicoItem)
    }

    suspend fun buscaHistorico3x3(): List<HistoricoItemModel> {
        return historicoDao.buscaHistorico3x3()
    }

    suspend fun buscaHistorico4x4(): List<HistoricoItemModel> {
        return historicoDao.buscaHistorico4x4()
    }

    suspend fun buscaHistorico5x5(): List<HistoricoItemModel> {
        return historicoDao.buscaHistorico5x5()
    }

    suspend fun buscaHistorico6x6(): List<HistoricoItemModel> {
        return historicoDao.buscaHistorico6x6()
    }

    suspend fun buscaJogadoresOrdenadosPorGanhos() : List<JogadoresModel>{
        return jogadoresDao.buscaJogadoresOrdenadosPorGanhos()
    }

}