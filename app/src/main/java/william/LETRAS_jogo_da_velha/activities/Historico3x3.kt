package william.LETRAS_jogo_da_velha.activities

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import william.LETRAS_jogo_da_velha.R
import william.LETRAS_jogo_da_velha.data.HistoricoItem
import william.LETRAS_jogo_da_velha.databinding.ActivityHistorico3x3Binding
import william.LETRAS_jogo_da_velha.databinding.ActivityTabuleiro3x3Binding

var historicoList = mutableListOf<HistoricoItem>(
    HistoricoItem("José", "Maria", true, false),
    HistoricoItem("Juciarano", "Leo", false, true),
)

class Historico3x3 : AppCompatActivity() {
    private lateinit var binding: ActivityHistorico3x3Binding

    class HistoricoAdapter(private val historicoList: List<HistoricoItem>) :
        RecyclerView.Adapter<HistoricoAdapter.HistoricoViewHolder>() {

        inner class HistoricoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val textViewJogador1: TextView = itemView.findViewById(R.id.textViewJogador1)
            val textViewJogador2: TextView = itemView.findViewById(R.id.textViewJogador2)
            val iconeJogador1 : ImageView = itemView.findViewById(R.id.icone_campeao_jogador1)
            val iconeJogador2 : ImageView = itemView.findViewById(R.id.icone_campeao_jogador2)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoricoViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_do_historico, parent, false)
            return HistoricoViewHolder(view)
        }

        override fun onBindViewHolder(holder: HistoricoViewHolder, position: Int) {
            val historicoItem = historicoList[position]
            holder.textViewJogador1.text = historicoItem.jogador1Nome
            holder.textViewJogador2.text = historicoItem.jogador2Nome
            //fazer a logica de vidibilidade do troféu para cada jogador
            val jogador1Venceu = historicoItem.jogador1Venceu
            val jogador2Venceu = historicoItem.jogador2Venceu
            if ( jogador1Venceu){
                holder.iconeJogador1.visibility = View.VISIBLE
                holder.iconeJogador2.visibility = View.GONE
            }

            if ( jogador2Venceu){
                holder.iconeJogador2.visibility = View.VISIBLE
                holder.iconeJogador1.visibility = View.GONE
            }
            //lógica de cores dos nomes para cada jogador
        }

        override fun getItemCount(): Int {
            return historicoList.size
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistorico3x3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = HistoricoAdapter(historicoList)
        recyclerView.adapter = adapter




    } //onCreate
} // Historico3x3