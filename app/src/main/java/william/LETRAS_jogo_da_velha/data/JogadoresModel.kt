package william.LETRAS_jogo_da_velha.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tabela_de_jogadores")
data class JogadoresModel(
    @PrimaryKey(autoGenerate = true) val id : Long = 0,
    val nome : String,
    val quantJogosGanhos : Int = 0,
    val quantJogosPerdidos : Int = 0,
    val quantTotalJogos : Int = 0,
)
