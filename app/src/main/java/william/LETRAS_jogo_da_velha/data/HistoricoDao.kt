package william.LETRAS_jogo_da_velha.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface HistoricoDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun inserirHistorico(historico : HistoricoItemModel): Long


    @Query("SELECT * FROM tabela_historico")
    suspend fun buscaHistorico(): List<HistoricoItemModel>
}