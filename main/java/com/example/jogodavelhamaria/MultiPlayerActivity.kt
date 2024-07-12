package com.example.jogodavelhamaria

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.jogodavelhamaria.databinding.ActivityMultiPlayerBinding

class MultiPlayerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMultiPlayerBinding
    //Vetor bidimensional que representará o tabuleiro de jogo
    val tabuleiro = arrayOf(
        arrayOf("A", "B", "C"),
        arrayOf("D", "E", "F"),
        arrayOf("G", "H", "I")
    )
    var jogadorAtual = "X"
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMultiPlayerBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }
    //Função associada com todos os botões @param view é o botão clicado
    fun buttonClick(view: View) {
        //O botão clicado é associado com uma constante
        val buttonSelecionado = view as Button
        //O texto do botão recebe o jogador atual
        buttonSelecionado.text = jogadorAtual
        //De acordo com o botão clicado, a posição da matriz receberá o Jogador
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
        //Recebe o jogador vencedor através da função verificaTabuleiro. @param tabuleito
        var vencedor = verificaVencedor(tabuleiro)

        if(!vencedor.isNullOrBlank()) {
            Toast.makeText(this, "Vencedor: " + vencedor, Toast.LENGTH_LONG).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        if(jogadorAtual.equals("X")) {
            buttonSelecionado.setBackgroundResource(R.drawable.salonline)
            jogadorAtual = "O"
        }else{
            buttonSelecionado.setBackgroundResource(R.drawable.soulpower)
            jogadorAtual = "X"
        }
        buttonSelecionado.isEnabled=false
    }

    fun verificaVencedor(tabuleiro: Array<Array<String>>): String? {

        // Verifica linhas e colunas
        for (i in 0 until 3) {
            //Verifica se há três itens iguais na linha
            if (tabuleiro[i][0] == tabuleiro[i][1] && tabuleiro[i][1] == tabuleiro[i][2]) {
                return tabuleiro[i][0]
            }
            //Verifica se há três itens iguais na coluna
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
        //Verifica a quantidade de jogadores
        var empate = 0
        for (linha in tabuleiro) {
            for (valor in linha) {
                if(valor.equals("X")||valor.equals("O")){
                    empate++
                }
            }
        }
        //Se existem 9 jogadas e não há três letras iguais, houve um empate
        if(empate == 9){
            return "Empate"
        }
        // Nenhum vencedor
        return null
    }

}