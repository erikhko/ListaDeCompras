package br.com.fiap.listadecompras

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: ItemsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        /**
         * O Toolbar é configurado como a barra de ação principal da Activity,
         * e o título é definido como "Lista de Compras".
         */
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Lista de Compras"

        /**
         * O RecyclerView é um componente da interface de usuário usado para exibir a lista de itens.
         */
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        /**
         * O ItemsAdapter é usado para preencher o RecyclerView com os itens.
         * O Adapter também recebe uma função lambda que é chamada quando um item é removido da lista.
         * Essa função chama o método viewModel.removeItem(item) para remover o item correspondente do banco de dados.
         */
        val itemsAdapter = ItemsAdapter { item ->
            viewModel.removeItem(item)
        }
        recyclerView.adapter = itemsAdapter

        /**
         * Um botão e um campo de texto são usados para adicionar novos itens à lista de compras.
         *
         * Quando o botão é clicado, o texto do campo de entrada (EditText) é verificado.
         * Se estiver vazio, um erro é exibido. Caso contrário,
         * o viewModel.addItem() é chamado para adicionar o novo item, e o campo de texto é limpo.
         */
        val button = findViewById<Button>(R.id.button)
        val editText = findViewById<EditText>(R.id.editText)

        button.setOnClickListener {
            if (editText.text.isEmpty()) {
                editText.error = "Preencha um valor"
                return@setOnClickListener
            }

            viewModel.addItem(editText.text.toString())

            editText.text.clear()

        }

        /**
         * Um ItemsViewModelFactory é criado para fornecer uma instância do ItemsViewModel
         * que requer o Application como argumento.
         */
        val viewModelFactory = ItemsViewModelFactory(application)

        /**
         * O ViewModelProvider é usado para associar a MainActivity ao ItemsViewModel.
         * Isso garante que o ViewModel sobreviva a mudanças de configuração (como rotações de tela)
         * sem perder os dados ou a lógica.
         */
        viewModel = ViewModelProvider(this, viewModelFactory).get(ItemsViewModel::class.java)

        /**
         * O viewModel.itemsLiveData é observado pela Activity.
         * Quando a lista de itens muda no banco de dados (por exemplo, quando um item é adicionado ou removido),
         * o Observer é notificado automaticamente, e a lista no RecyclerView é atualizada chamando
         * o método itemsAdapter.updateItems(items).
         */
        viewModel.itemsLiveData.observe(this) { items ->
            itemsAdapter.updateItems(items)
        }
    }
}