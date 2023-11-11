package william.LETRAS_jogo_da_velha.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface HistoricoDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun inserirHistorico(historico: HistoricoItemModel): Long

    @Query("SELECT * FROM tabela_historico WHERE TABULEIRO_3X3 = 1")
    suspend fun buscaHistorico3x3(): List<HistoricoItemModel>

    @Query("SELECT * FROM tabela_historico WHERE TABULEIRO_4X4 = 1")
    suspend fun buscaHistorico4x4(): List<HistoricoItemModel>

    @Query("SELECT * FROM tabela_historico WHERE TABULEIRO_5X5 = 1")
    suspend fun buscaHistorico5x5(): List<HistoricoItemModel>
    @Query("SELECT * FROM tabela_historico WHERE TABULEIRO_6X6 = 1")
    suspend fun buscaHistorico6x6(): List<HistoricoItemModel>

}