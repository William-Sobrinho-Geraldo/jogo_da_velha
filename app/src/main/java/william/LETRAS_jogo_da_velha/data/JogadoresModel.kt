package william.LETRAS_jogo_da_velha.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "tabela_de_jogadores")
data class JogadoresModel(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "NOME_DO_JOGADOR") val nome: String,
    @ColumnInfo(name = "QUANT_JOGOS_GANHOS") val quantJogosGanhos: Int = 0,
    @ColumnInfo(name = "QUANT_JOGOS_PERDIDOS") val quantJogosPerdidos: Int = 0,
    @ColumnInfo(name = "QUANT_TOTAL_JOGOS") val quantTotalJogos: Int = 0,
    @ColumnInfo(name = "SÍMBOLO_PADRÃO") val simbolo: Int = 0,    //Simbolo == 0 é bolinha == 1 X
    @ColumnInfo(name = "COR_PADRÃO") val cor: Int = 0,                  // cor == 0 é azul == 1 vermelho
) : Serializable


@Entity(tableName = "tabela_historico")
data class HistoricoItemModel(
    @PrimaryKey(autoGenerate = true) val registro : Long = 0,
    @ColumnInfo(name = "NOME_DO_JOGADOR1") val jogador1Nome: String = "",
    @ColumnInfo(name = "NOME_DO_JOGADOR2") val jogador2Nome: String = "",
    @ColumnInfo(name = "JOGADOR1_VENCEU")val jogador1Venceu : Boolean = true,
    @ColumnInfo(name = "JOGADOR2_VENCEU")val jogador2Venceu : Boolean = false,
)
