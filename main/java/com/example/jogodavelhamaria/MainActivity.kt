package com.example.jogodavelhamaria

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jogodavelhamaria.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding

    // Método onCreate é chamado quando a atividade é criada
    override fun onCreate(savedInstanceState: Bundle?) {
        // Infla o layout da atividade usando View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        // Define o conteúdo da visualização como o layout inflado
        setContentView(binding.root)

        // Define um ouvinte de clique para o botão "Jogar com Amigo"
        binding.btnJogarComAmigo.setOnClickListener{
            val i = Intent(this, MultiPlayerActivity::class.java)

            // Inicia a nova atividade
            startActivity(i)
            // Encerra a atividade atual
            finish()
        }

        // Define um ouvinte de clique para o botão "Jogar com Computador"
        binding.btnJogarComComputador.setOnClickListener{
            val i = Intent(this, ComputadorActivity::class.java)

            // Inicia a nova atividade
            startActivity(i)
            // Encerra a atividade atual
            finish()
        }


    }
}