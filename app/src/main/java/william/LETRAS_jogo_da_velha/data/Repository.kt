package william.LETRAS_jogo_da_velha.data

class Repository (private val jogadoresDao : JogadoresDao){

    suspend fun inserirJogadores(jogador : JogadoresModel){
        jogadoresDao.inserirJogador(jogador)
    }

    suspend fun buscaJogadoresNoBD () : List<JogadoresModel> {
        return jogadoresDao.buscaJogadoresNoBD()
    }


    suspend fun deletarTodosOsJogadores(){
        jogadoresDao.deletarTodosOsJogadores()
    }

    fun incrementarVitoria (jogadorId: Long) {
        jogadoresDao.incrementarVitoria(jogadorId)
    }

    fun incrementarDerrota (jogadorId: Long) {
        jogadoresDao.incrementarDerrota (jogadorId)
    }

}