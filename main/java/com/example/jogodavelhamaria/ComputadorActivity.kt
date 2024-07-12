package com.example.jogodavelhamaria

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.jogodavelhamaria.databinding.ActivityComputadorBinding
import com.example.jogodavelhamaria.databinding.ActivityMultiPlayerBinding
import kotlin.random.Random

class ComputadorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityComputadorBinding
    // Inicialização do tabuleiro com valores iniciais
    val tabuleiro = arrayOf(
        arrayOf("A", "B", "C"),
        arrayOf("D", "E", "F"),
        arrayOf("G", "H", "I")
    )

    // Define o jogador atual como "X"
    var jogadorAtual = "X"

    // Método onCreate é chamado quando a atividade é criada
    override fun onCreate(savedInstanceState: Bundle?) {
            binding = ActivityComputadorBinding.inflate(layoutInflater)
            super.onCreate(savedInstanceState)
            setContentView(binding.root)
    }
    // Método chamado quando um botão é clicado
    fun buttonClick(view: View) {
        // Converte a visualização clicada para um botão
        val buttonSelecionado = view as Button

        // Atualiza o tabuleiro com base no botão clicado
        when(buttonSelecionado.id){
            binding.buttonUm.id -> tabuleiro[0][0] = jogadorAtual
            binding.buttonDois.id -> tabuleiro[0][1] = jogadorAtual
            binding.buttonTres.id -> tabuleiro[0][2] = jogadorAtual
            binding.buttonQuatro.id -> tabuleiro[1][0] = jogadorAtual
            binding.buttonCinco.id -> tabuleiro[1][1] = jogadorAtual
            binding.buttonSeis.id -> tabuleiro[1][2] = jogadorAtual
            binding.buttonSete.id -> tabuleiro[2][0] = jogadorAtual
            binding.buttonOito.id -> tabuleiro[2][1] = jogadorAtual
            binding.buttonNove.id -> tabuleiro[2][2] = jogadorAtual
        }

        // Muda a cor do botão selecionado para vermelho
        buttonSelecionado.setBackgroundColor(Color.RED)
        // Desabilita o botão para impedir novos cliques
        buttonSelecionado.isEnabled=false

        // Verifica se há um vencedor após o movimento
        var vencedor = verificaVencedor(tabuleiro)
        // Alterna entre os jogadores "X" e "O"
        if (jogadorAtual.equals("X")) {
            buttonSelecionado.setBackgroundResource(R.drawable.salonline)
            jogadorAtual = "O"
        } else {
            buttonSelecionado.setBackgroundResource(R.drawable.salonline)
            jogadorAtual = "X"
        }

        // Se houver um vencedor, exibe uma mensagem e reinicia a atividade
        if(!vencedor.isNullOrBlank()) {
            Toast.makeText(this, "Vencedor: " + vencedor, Toast.LENGTH_LONG).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        buttonSelecionado.isEnabled = false

        // Movimento aleatório do computador
        var rX = Random.nextInt(0, 3)
        var rY = Random.nextInt(0, 3)
        var i = 0
        while (i < 9) {
            rX = Random.nextInt(0, 3)
            rY = Random.nextInt(0, 3)

            if (tabuleiro[rX][rY] != "X" && tabuleiro[rX][rY] != "O") {
                break
            }

            i++
        }

        // Marca a célula escolhida pelo computador como "O"
        tabuleiro[rX][rY]="O"

        val posicao = rX*3 + rY

        // Atualiza a interface de acordo com a posição escolhida pelo computador
        when(posicao){
            1 -> {
                binding.buttonUm.setBackgroundResource(R.drawable.soulpower)
                binding.buttonUm.isEnabled = false
            }
            2 -> {
                binding.buttonDois.setBackgroundResource(R.drawable.soulpower)
                binding.buttonDois.isEnabled = false
            }
            3 -> {
                binding.buttonTres.setBackgroundResource(R.drawable.soulpower)
                binding.buttonTres.isEnabled = false
            }
            4 -> {
                binding.buttonQuatro.setBackgroundResource(R.drawable.soulpower)
                binding.buttonQuatro.isEnabled = false
            }
            5 -> {
                binding.buttonCinco.setBackgroundResource(R.drawable.soulpower)
                binding.buttonCinco.isEnabled = false
            }
            6 -> {
                binding.buttonSeis.setBackgroundResource(R.drawable.soulpower)
                binding.buttonSeis.isEnabled = false
            }
            7 -> {
                binding.buttonSete.setBackgroundResource(R.drawable.soulpower)
                binding.buttonSete.isEnabled = false
            }
            8 -> {
                binding.buttonOito.setBackgroundResource(R.drawable.soulpower)
                binding.buttonOito.isEnabled = false
            }
            9 -> {
                binding.buttonNove.setBackgroundResource(R.drawable.soulpower)
                binding.buttonNove.isEnabled = false
            }
        }
        // Verifica novamente se há um vencedor após o movimento do computador
        vencedor = verificaVencedor(tabuleiro)

        // Se houver um vencedor, exibe uma mensagem e reinicia a atividade
        if(!vencedor.isNullOrBlank()) {
            Toast.makeText(this, "Vencedor: " + vencedor, Toast.LENGTH_LONG).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    // Função para verificar se há ou não um vencedor
    fun verificaVencedor(tabuleiro: Array<Array<String>>): String? {

        // Verifica linhas e colunas
        for (i in 0 until 3) {
            if (tabuleiro[i][0] == tabuleiro[i][1] && tabuleiro[i][1] == tabuleiro[i][2]) {
                return tabuleiro[i][0]
            }
            if (tabuleiro[0][i] == tabuleiro[1][i] && tabuleiro[1][i] == tabuleiro[2][i]) {
                return tabuleiro[0][i]
            }
        }
        // Verifica diagonais
        if (tabuleiro[0][0] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[2][2]) {
            return tabuleiro[0][0]
        }
        if (tabuleiro[0][2] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[2][0]) {
            return tabuleiro[0][2]
        }
        // Verifica se houve empate ou não
        var empate = 0
        for (linha in tabuleiro) {
            for (valor in linha) {
                if(valor.equals("X")||valor.equals("O")){
                    empate++
                }
            }
        }
        // Retorna o "Empate" se todas as células estiverem preenchidas
        if(empate == 9){
            return "Empate"
        }
        // Retorna um valor nulo se não houver vencedor ainda
        return null
    }
}
