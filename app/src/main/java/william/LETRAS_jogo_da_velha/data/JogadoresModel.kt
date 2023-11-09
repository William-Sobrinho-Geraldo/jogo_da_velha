package william.LETRAS_jogo_da_velha.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tabela_de_jogadores")
data class JogadoresModel(
    @PrimaryKey(autoGenerate = true) val id : Long = 0,
    @ColumnInfo(name = "NOME_DO_JOGADOR") val nome : String,
    @ColumnInfo(name = "QUANT_JOGOS_GANHOS") val quantJogosGanhos : Int = 0,
    @ColumnInfo(name = "QUANT_JOGOS_PERDIDOS") val quantJogosPerdidos : Int = 0,
    @ColumnInfo(name = "QUANT_TOTAL_JOGOS") val quantTotalJogos : Int = 0,
)
