package william.LETRAS_jogo_da_velha.utilidades

import william.LETRAS_jogo_da_velha.activities.btnMarcList3x3
import william.LETRAS_jogo_da_velha.activities.btnMarcList4x4

data class Bot(
    val nome: String = "RichardBot",
    val simbolo: Int = 1,
    val cor: Int = 1,
) {

    fun defenderLinhas3x3(): Int? {
        var retorno: Int? = null
        for (i in 0 until 3) {
            if (btnMarcList3x3[i * 3] == "x" && btnMarcList3x3[i * 3 + 1] == "x" &&
                btnMarcList3x3[i * 3 + 2] == ""
            ) {
                retorno = i * 3 + 2
            }
            if (btnMarcList3x3[i * 3] == "x" && btnMarcList3x3[i * 3 + 2] == "x" &&
                btnMarcList3x3[i * 3 + 1] == ""
            ) {
                retorno = i * 3 + 1
            }
            if (btnMarcList3x3[i * 3 + 1] == "x" && btnMarcList3x3[i * 3 + 2] == "x" &&
                btnMarcList3x3[i * 3] == ""
            ) {
                retorno = i * 3
            }
        }
        return retorno
    }

    fun defenderColunas3x3(): Int? {
        var retorno: Int? = null

        for (i in 0 until 3) {
            if (btnMarcList3x3[i] == "x" && btnMarcList3x3[i + 3] == "x" && btnMarcList3x3[i + 6] == "") {
                retorno = i + 6
            }
            if (btnMarcList3x3[i] == "x" && btnMarcList3x3[i + 6] == "x" && btnMarcList3x3[i + 3] == "") {
                retorno = i + 3
            }
            if (btnMarcList3x3[i + 3] == "x" && btnMarcList3x3[i + 6] == "x" && btnMarcList3x3[i] == "") {
                retorno = i
            }
        }
        return retorno
    }

    fun defenderDiagonais3x3(): Int? {
        var retorno: Int? = null
        // Diagonais
        val diagonais = listOf(
            listOf(0, 4, 8),
            listOf(2, 4, 6)
        )

        for (indice in diagonais) {
            val (a, b, c) = indice
            if (btnMarcList3x3[a] == "x" && btnMarcList3x3[b] == "x" && btnMarcList3x3[c] == "") {
                retorno = c
            }
            if (btnMarcList3x3[a] == "x" && btnMarcList3x3[c] == "x" && btnMarcList3x3[b] == "") {
                retorno = b
            }
            if (btnMarcList3x3[b] == "x" && btnMarcList3x3[c] == "x" && btnMarcList3x3[a] == "") {
                retorno = a
            }
        }
        return retorno
    }

    fun defenderLinhas4x4(): Int? {
        var retorno: Int? = null
        // Verifica linhas
        for (i in 0 until 4) {
            if (btnMarcList4x4[i * 4] == "x" && btnMarcList4x4[i * 4 + 1] == "x" && btnMarcList4x4[i * 4 + 2] == "x" && btnMarcList4x4[i * 4 + 3] == "") {
                retorno = i * 4 + 3
            }
            if (btnMarcList4x4[i * 4] == "x" && btnMarcList4x4[i * 4 + 2] == "x" && btnMarcList4x4[i * 4 + 3] == "x" && btnMarcList4x4[i * 4 + 1] == "") {
                retorno = i * 4 + 1
            }
            if (btnMarcList4x4[i * 4 + 1] == "x" && btnMarcList4x4[i * 4 + 2] == "x" && btnMarcList4x4[i * 4 + 3] == "x" && btnMarcList4x4[i * 4] == "") {
                retorno = i * 4
            }
            if (btnMarcList4x4[i * 4 + 1] == "x" && btnMarcList4x4[i * 4 + 2] == "" && btnMarcList4x4[i * 4 + 3] == "x" && btnMarcList4x4[i * 4] == "x") {
                retorno = i * 4 + 2
            }
        }
        return retorno
    }

} //data class Bot
