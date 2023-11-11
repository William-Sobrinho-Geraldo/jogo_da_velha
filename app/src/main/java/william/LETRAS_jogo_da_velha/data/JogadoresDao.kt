package william.LETRAS_jogo_da_velha.data

import android.util.Log
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import androidx.room.util.query

private const val TAG = "JogadoresDao"
@Dao
interface JogadoresDao {

    @Insert (onConflict = OnConflictStrategy.ABORT)
    fun inserirJogador (jogador : JogadoresModel) : Long

    @Query("SELECT * FROM tabela_de_jogadores")
    fun buscaJogadoresNoBD() : List<JogadoresModel>

    @Query("DELETE FROM tabela_de_jogadores")
    fun deletarTodosOsJogadores()


    fun incrementarVitoria(jogador: JogadoresModel) {
        TODO("Not yet implemented")
    }

    fun incrementarDerrota (jogador: JogadoresModel){

    }
}