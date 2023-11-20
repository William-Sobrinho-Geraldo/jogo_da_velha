package william.LETRAS_jogo_da_velha.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

private const val TAG = "JogadoresDao"

@Dao
interface JogadoresDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun inserirJogador(jogador: JogadoresModel): Long

    @Query("SELECT * FROM tabela_de_jogadores")
    suspend fun buscaJogadoresNoBD(): List<JogadoresModel>

    @Query("DELETE FROM tabela_de_jogadores")
    fun deletarTodosOsJogadores()

    @Query("SELECT EXISTS( SELECT 1 FROM tabela_de_jogadores WHERE NOME_DO_JOGADOR = :nome LIMIT 1)")
    suspend fun buscaNomeDoJogadorNoBD(nome: String): Boolean


    @Query("UPDATE tabela_de_jogadores SET QUANT_JOGOS_GANHOS = QUANT_JOGOS_GANHOS + 1 WHERE id = :jogadorId")
    suspend fun incrementarVitoria(jogadorId: Long)

    @Query("UPDATE tabela_de_jogadores SET QUANT_JOGOS_GANHOS = QUANT_JOGOS_GANHOS + 1 WHERE NOME_DO_JOGADOR = :nome")
    suspend fun incrementarVitoriaPorNome(nome: String)

    @Query("UPDATE tabela_de_jogadores SET QUANT_JOGOS_PERDIDOS = QUANT_JOGOS_PERDIDOS + 1 WHERE NOME_DO_JOGADOR = :nome")
    suspend fun incrementarDerrotaPorNome(nome: String)

    @Query("SELECT * FROM tabela_de_jogadores ORDER BY QUANT_JOGOS_GANHOS DESC")
    suspend fun buscaJogadoresOrdenadosPorGanhos(): List<JogadoresModel>

}