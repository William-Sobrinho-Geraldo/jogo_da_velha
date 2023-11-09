package william.LETRAS_jogo_da_velha.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface JogadoresDao {

    @Insert
    suspend fun inserirJogador (jogador : JogadoresModel) {}

    @Query("SELECT * FROM tabela_de_jogadores")
    suspend fun buscaJogadores() : List<JogadoresModel>

}