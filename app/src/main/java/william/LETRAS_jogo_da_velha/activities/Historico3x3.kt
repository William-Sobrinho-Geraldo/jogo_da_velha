package william.LETRAS_jogo_da_velha.activities

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import william.LETRAS_jogo_da_velha.R
import william.LETRAS_jogo_da_velha.data.HistoricoItemModel
import william.LETRAS_jogo_da_velha.databinding.ActivityHistorico3x3Binding
import william.LETRAS_jogo_da_velha.viewModels.Tabuleiro3x3ViewModel


private const val TAG = "Historico3x3"
var historicoList = mutableListOf<HistoricoItemModel>(
    HistoricoItemModel(1, "José", "Maria", true, false),
    HistoricoItemModel(2, "Juciarano", "Leo", false, true),
)

//preciso popular a lista  historicoList


class Historico3x3 : AppCompatActivity() {
    private lateinit var binding: ActivityHistorico3x3Binding

    class HistoricoAdapter(private val historicoJogadoresList: List<HistoricoItemModel>) :
        RecyclerView.Adapter<HistoricoAdapter.HistoricoViewHolder>() {

        inner class HistoricoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val textViewJogador1: TextView = itemView.findViewById(R.id.textViewJogador1)
            val textViewJogador2: TextView = itemView.findViewById(R.id.textViewJogador2)
            val iconeJogador1: ImageView = itemView.findViewById(R.id.icone_campeao_jogador1)
            val iconeJogador2: ImageView = itemView.findViewById(R.id.icone_campeao_jogador2)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoricoViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_do_historico, parent, false)
            return HistoricoViewHolder(view)
        }

        override fun onBindViewHolder(holder: HistoricoViewHolder, position: Int) {
            val historicoItem = historicoJogadoresList[position]
            holder.textViewJogador1.text = historicoItem.jogador1Nome
            holder.textViewJogador2.text = historicoItem.jogador2Nome
            //fazer a logica de vidibilidade do troféu para cada jogador
            val jogador1Venceu = historicoItem.jogador1Venceu
            val jogador2Venceu = historicoItem.jogador2Venceu
            if (jogador1Venceu) {
                holder.iconeJogador1.visibility = View.VISIBLE
                holder.iconeJogador2.visibility = View.GONE
            }

            if (jogador2Venceu) {
                holder.iconeJogador2.visibility = View.VISIBLE
                holder.iconeJogador1.visibility = View.GONE
            }
            //lógica de cores dos nomes para cada jogador
        }

        override fun getItemCount(): Int {
            return historicoJogadoresList.size
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistorico3x3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: Tabuleiro3x3ViewModel by viewModel()

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = HistoricoAdapter(historicoList)
        recyclerView.adapter = adapter

        lifecycleScope.launch {
            val viewModelHistoricoList = async {
                viewModel.buscaHistorico()
            }.await()

            Log.i(TAG, "onCreate:   A LISTA QUE VEIO DO VIEWMODEL É $viewModelHistoricoList ")
            historicoList.clear()
            historicoList.addAll(viewModelHistoricoList)
            historicoList.reverse()
            adapter.notifyDataSetChanged()

        }

        //buscar dados da tabela de histórico e popular a lista que tá la encima "historicoList"


    } //onCreate
} // Historico3x3