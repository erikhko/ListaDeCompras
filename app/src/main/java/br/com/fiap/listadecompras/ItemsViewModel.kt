package br.com.fiap.listadecompras

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * A classe ItemsViewModel estende AndroidViewModel, que é uma subclasse de ViewModel que também fornece acesso à Application.
 * Isso é útil porque o ViewModel pode precisar do contexto da aplicação para acessar recursos como o banco de dados.
 *
 * O Application é passado para o ViewModel e usado para construir o banco de dados com o Room.
 */
class ItemsViewModel(application: Application) : AndroidViewModel(application) {

    /**
     * Dentro do ViewModel, um ItemDao é criado para interagir com a tabela ItemModel no banco de dados Room.
     */
    private val itemDao: ItemDao
    val itemsLiveData: LiveData<List<ItemModel>>

    /**
     * No init {} (bloco de inicialização), o banco de dados é construído usando o Room.databaseBuilder(),
     * onde a instância do ItemDatabase é criada e o itemDao é inicializado.
     */
    init {
        val database = Room.databaseBuilder(
            getApplication(),
            ItemDatabase::class.java,
            "items_database"
        ).build()

        itemDao = database.itemDao()

        /**
         * itemsLiveData é um objeto do tipo LiveData<List<ItemModel>>, que expõe a lista de itens do banco de dados de forma reativa.
         *
         * O método itemDao.getAll() é chamado para recuperar os itens e, como ele retorna um LiveData,
         * ele permite que a interface observe e reaja automaticamente às mudanças nos dados (como adição ou remoção de itens).
         *
         * Isso significa que a interface (View) pode "observar" o itemsLiveData e
         * ser atualizada automaticamente quando houver mudanças nos itens do banco de dados.
         */
        itemsLiveData = itemDao.getAll()
    }

    /**
     * Essa função permite adicionar um novo item à lista de compras.
     * Quando chamada, ela cria um novo item (ItemModel) e insere no banco de dados através do itemDao.insert().
     */
    fun addItem(item: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val newItem = ItemModel(name = item)
            itemDao.insert(newItem)
        }
    }

    /**
     * Essa função permite remover um item da lista, chamando itemDao.delete().
     */
    fun removeItem(item: ItemModel) {
        viewModelScope.launch(Dispatchers.IO) {
            itemDao.delete(item)
        }
    }
}