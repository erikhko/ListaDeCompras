package br.com.fiap.listadecompras;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

import android.app.Application;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

class ItemsViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ItemsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ItemsViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
