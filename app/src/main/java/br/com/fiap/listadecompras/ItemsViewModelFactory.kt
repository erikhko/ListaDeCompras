package br.com.fiap.listadecompras;

import android.app.Application;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * Esta classe herda de ViewModelProvider.Factory, que é usada para criar ViewModels de maneira personalizada,
 * especialmente quando o ViewModel precisa de parâmetros adicionais (como o Application).
 */

/**
 * A fábrica recebe uma instância do Application, que será passada para o ItemsViewModel quando ele for criado.
 * A Application pode ser usada para fornecer o contexto global do app ou acessar recursos, como bancos de dados, a partir do ViewModel.
 */
class ItemsViewModelFactory(private val application: Application) : ViewModelProvider.Factory {

    /**
     * O método create(modelClass: Class<T>) é sobrescrito para criar e retornar uma instância do ItemsViewModel se a classe solicitada for compatível.
     * O código verifica se o modelClass (classe solicitada) é compatível com ItemsViewModel.
     * Se for, ele cria uma nova instância de ItemsViewModel, passando o application para o seu construtor.
     */
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ItemsViewModel::class.java)) {

            /**
             * O @Suppress("UNCHECKED_CAST") está dizendo ao compilador para ignorar o aviso sobre o cast inseguro (as T),
             * pois o código está certo de que o tipo é compatível.
             */
            @Suppress("UNCHECKED_CAST")
            return ItemsViewModel(application) as T
        }

        /**
         * Se a modelClass solicitada não for ItemsViewModel,
         * ele lança uma exceção IllegalArgumentException dizendo que a classe do ViewModel é desconhecida.
         */
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
