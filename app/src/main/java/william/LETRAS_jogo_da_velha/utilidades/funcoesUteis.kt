package william.LETRAS_jogo_da_velha.utilidades

import android.content.Context
import android.widget.Toast


fun mostrarToast(mensagem : String, context : Context){
    Toast.makeText(context, mensagem, Toast.LENGTH_SHORT).show()
}

fun mostrarToastLonga(mensagem : String, context : Context){
    Toast.makeText(context, mensagem, Toast.LENGTH_LONG).show()
}

