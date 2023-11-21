package william.LETRAS_jogo_da_velha.utilidades

import william.LETRAS_jogo_da_velha.activities.btnMarcList3x3
import william.LETRAS_jogo_da_velha.activities.btnMarcList4x4
import william.LETRAS_jogo_da_velha.activities.btnMarcList5x5

data class Bot(
    val nome: String = "RichardBot",
    val simbolo: Int = 1,
    val cor: Int = 1,
) {

    companion object {
        val botPadrao: Bot by lazy {
            Bot("TeddyBot")
        }
    }


    // FUNÇÕES DEFESA 3X3
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


    // FUNÇÕES DEFESA 4X4
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


    fun defenderColunas4x4(): Int? {
        var retorno: Int? = null
        // Verifica colunas
        for (i in 0 until 4) {
            if (btnMarcList4x4[i] == "x" && btnMarcList4x4[i + 4] == "x" && btnMarcList4x4[i + 8] == "x" && btnMarcList4x4[i + 12] == "") {
                retorno = i + 12
            }
            if (btnMarcList4x4[i] == "x" && btnMarcList4x4[i + 8] == "" && btnMarcList4x4[i + 12] == "x" && btnMarcList4x4[i + 4] == "x") {
                retorno = i + 8
            }
            if (btnMarcList4x4[i] == "x" && btnMarcList4x4[i + 8] == "x" && btnMarcList4x4[i + 12] == "x" && btnMarcList4x4[i + 4] == "") {
                retorno = i + 4
            }
            if (btnMarcList4x4[i + 4] == "x" && btnMarcList4x4[i + 8] == "x" && btnMarcList4x4[i + 12] == "x" && btnMarcList4x4[i] == "") {
                retorno = i
            }
        }
        return retorno
    }


    fun defenderDiagonais4x4(): Int? {
        var retorno: Int? = null
        // Diagonais
        val diagonais = listOf(
            listOf(0, 5, 10, 15),
            listOf(3, 6, 9, 12)
        )

        for (indice in diagonais) {
            val (a, b, c, d) = indice
            if (btnMarcList4x4[a] == "x" && btnMarcList4x4[b] == "x" && btnMarcList4x4[c] == "x" && btnMarcList4x4[d] == "") {
                retorno = d
            }
            if (btnMarcList4x4[a] == "x" && btnMarcList4x4[b] == "x" && btnMarcList4x4[d] == "x" && btnMarcList4x4[c] == "") {
                retorno = c
            }
            if (btnMarcList4x4[a] == "x" && btnMarcList4x4[d] == "x" && btnMarcList4x4[c] == "x" && btnMarcList4x4[b] == "") {
                retorno = b
            }
            if (btnMarcList4x4[b] == "x" && btnMarcList4x4[c] == "x" && btnMarcList4x4[d] == "x" && btnMarcList4x4[a] == "") {
                retorno = a
            }
        }
        return retorno
    }

    fun defenderDiagonais5x5(): Int? {
        var retorno: Int? = null
        // Diagonais
        val diagonais = listOf(
            listOf(0, 6, 12, 18, 24),
            listOf(4, 8, 12, 16, 20)
        )

        for (indice in diagonais) {
            val (a, b, c, d, e) = indice
            if (btnMarcList5x5[a] == "x" && btnMarcList5x5[b] == "x" && btnMarcList5x5[c] == "x" && btnMarcList5x5[d] == "x" && btnMarcList5x5[e] == "") {
                retorno = e
            }
            if (btnMarcList5x5[a] == "x" && btnMarcList5x5[b] == "x" && btnMarcList5x5[c] == "x" && btnMarcList5x5[d] == "" && btnMarcList5x5[e] == "x") {
                retorno = d
            }
            if (btnMarcList5x5[a] == "x" && btnMarcList5x5[b] == "x" && btnMarcList5x5[c] == "" && btnMarcList5x5[d] == "x" && btnMarcList5x5[e] == "x") {
                retorno = c
            }
            if (btnMarcList5x5[a] == "x" && btnMarcList5x5[b] == "" && btnMarcList5x5[c] == "x" && btnMarcList5x5[d] == "x" && btnMarcList5x5[e] == "x") {
                retorno = b
            }
            if (btnMarcList5x5[a] == "" && btnMarcList5x5[b] == "x" && btnMarcList5x5[c] == "x" && btnMarcList5x5[d] == "x" && btnMarcList5x5[e] == "x") {
                retorno = a
            }
        }
        return retorno
    }


    // FUNÇÕES DEFESA 5X5
    fun defenderLinhas5x5(): Int? {
        var retorno: Int? = null
        // Verifica linhas
        for (i in 0 until 5) {
            if (btnMarcList5x5[i * 5] == "x" && btnMarcList5x5[i * 5 + 1] == "x" && btnMarcList5x5[i * 5 + 2] == "x" && btnMarcList5x5[i * 5 + 3] == "x" && btnMarcList5x5[i * 5 + 4] == "") {
                retorno = i * 5 + 4
            }
            if (btnMarcList5x5[i * 5] == "x" && btnMarcList5x5[i * 5 + 1] == "x" && btnMarcList5x5[i * 5 + 2] == "x" && btnMarcList5x5[i * 5 + 3] == "" && btnMarcList5x5[i * 5 + 4] == "x") {
                retorno = i * 5 + 3
            }
            if (btnMarcList5x5[i * 5] == "x" && btnMarcList5x5[i * 5 + 1] == "x" && btnMarcList5x5[i * 5 + 2] == "" && btnMarcList5x5[i * 5 + 3] == "x" && btnMarcList5x5[i * 5 + 4] == "x") {
                retorno = i * 5 + 2
            }
            if (btnMarcList5x5[i * 5] == "x" && btnMarcList5x5[i * 5 + 1] == "" && btnMarcList5x5[i * 5 + 2] == "x" && btnMarcList5x5[i * 5 + 3] == "x" && btnMarcList5x5[i * 5 + 4] == "x") {
                retorno = i * 5 + 1
            }
            if (btnMarcList5x5[i * 5] == "" && btnMarcList5x5[i * 5 + 1] == "x" && btnMarcList5x5[i * 5 + 2] == "x" && btnMarcList5x5[i * 5 + 3] == "x" && btnMarcList5x5[i * 5 + 4] == "x") {
                retorno = i * 5
            }
        }
        return retorno
    }

    fun defenderColunas5x5(): Int? {
        var retorno: Int? = null
        // Verifica colunas
        for (i in 0 until 5) {
            if (btnMarcList5x5[i] == "x" && btnMarcList5x5[i + 5] == "x" && btnMarcList5x5[i + 10] == "x" && btnMarcList5x5[i + 15] == "x" && btnMarcList5x5[i + 20] == "") {
                retorno = i + 20
            }
            if (btnMarcList5x5[i] == "x" && btnMarcList5x5[i + 5] == "x" && btnMarcList5x5[i + 10] == "x" && btnMarcList5x5[i + 15] == "" && btnMarcList5x5[i + 20] == "x") {
                retorno = i + 15
            }
            if (btnMarcList5x5[i] == "x" && btnMarcList5x5[i + 5] == "x" && btnMarcList5x5[i + 10] == "" && btnMarcList5x5[i + 15] == "x" && btnMarcList5x5[i + 20] == "x") {
                retorno = i + 10
            }
            if (btnMarcList5x5[i] == "x" && btnMarcList5x5[i + 5] == "" && btnMarcList5x5[i + 10] == "x" && btnMarcList5x5[i + 15] == "x" && btnMarcList5x5[i + 20] == "x") {
                retorno = i + 5
            }
            if (btnMarcList5x5[i] == "" && btnMarcList5x5[i + 5] == "x" && btnMarcList5x5[i + 10] == "x" && btnMarcList5x5[i + 15] == "x" && btnMarcList5x5[i + 20] == "x") {
                retorno = i
            }
        }
        return retorno
    }

} //data class Bot
