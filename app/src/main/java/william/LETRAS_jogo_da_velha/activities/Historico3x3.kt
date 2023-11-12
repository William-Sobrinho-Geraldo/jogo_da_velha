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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import william.LETRAS_jogo_da_velha.R
import william.LETRAS_jogo_da_velha.data.HistoricoItemModel
import william.LETRAS_jogo_da_velha.data.JogadoresModel
import william.LETRAS_jogo_da_velha.databinding.ActivityHistorico3x3Binding
import william.LETRAS_jogo_da_velha.viewModels.Tabuleiro3x3ViewModel


private const val TAG = "Historico3x3"
var historicoList3x3 = mutableListOf<HistoricoItemModel>(
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
                holder.iconeJogador2.visibility = View.INVISIBLE
            }

            if (jogador2Venceu) {
                holder.iconeJogador2.visibility = View.VISIBLE
                holder.iconeJogador1.visibility = View.INVISIBLE
            }
            //lógica de cores dos nomes para cada jogador
        }

        override fun getItemCount(): Int {
            return historicoJogadoresList.size
        }
    } // class HistoricoAdapter


    // JogadoresAdapter.kt
    class JogadoresAdapter(private val listaJogadores: List<JogadoresModel>) :
        RecyclerView.Adapter<JogadoresAdapter.ViewHolder>() {

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val nomeJogador: TextView = itemView.findViewById(R.id.nome_maior_ganhador)
            val quantVitorias: TextView = itemView.findViewById(R.id.quant_vitorias)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_maiores_ganhadores, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val jogador = listaJogadores[position]
            holder.nomeJogador.text = jogador.nome
            holder.quantVitorias.text = jogador.quantJogosGanhos.toString()
        }

        override fun getItemCount(): Int {
            return listaJogadores.size
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistorico3x3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: Tabuleiro3x3ViewModel by viewModel()
        val listaDeMaioresGanhadores = mutableListOf<JogadoresModel>()

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView4x4)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = HistoricoAdapter(historicoList3x3)
        recyclerView.adapter = adapter

        lifecycleScope.launch {
            val viewModelHistoricoList = async {
                viewModel.buscaHistorico3x3()
            }.await()

            Log.i(TAG, "onCreate:   A LISTA QUE VEIO DO VIEWMODEL É $viewModelHistoricoList ")
            historicoList3x3.clear()
            historicoList3x3.addAll(viewModelHistoricoList)
            historicoList3x3.reverse()
            adapter.notifyDataSetChanged()
        }


        //BOTÃO DE RANKING
        binding.btnExibirRankind.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                val listaGanhadores = async {
                    viewModel.buscaJogadoresOrdenadosPorGanhos()
                }.await()
                listaDeMaioresGanhadores.addAll(listaGanhadores)
                Log.i(TAG, "onCreate: listademaioresGanhadores é :    $listaDeMaioresGanhadores")

                //tornar invisivel a recyclerView acima
                recyclerView.visibility = View.INVISIBLE

                // mostrar outra recyclerview com os maiores ganhadores


            } // Coroutine

            //            val listaJogadores: List<JogadoresModel> = // Obtenha a lista de jogadores
            val recyclerViewranking: RecyclerView =
                findViewById(R.id.recyclerView4x4_ranking) // botar Recycler do ranking
            recyclerViewranking.visibility = View.VISIBLE
            recyclerViewranking.layoutManager = LinearLayoutManager(this)
            val adapterRanking = JogadoresAdapter(listaDeMaioresGanhadores)
            recyclerViewranking.adapter = adapterRanking


        } //ClickListener Botão Ranking


        //buscar dados da tabela de histórico e popular a lista que tá la encima "historicoList"

    } //onCreate
} // Historico3x3