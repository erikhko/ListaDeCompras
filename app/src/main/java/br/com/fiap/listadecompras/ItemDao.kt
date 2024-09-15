package br.com.fiap.listadecompras

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

/**
 * A anotação @Dao marca esta interface como um DAO.
 * Ela contém os métodos que serão usados para interagir com o banco de dados.
 */
@Dao
interface ItemDao {

    /**
     * Este método executa uma consulta SQL para retornar todos os itens da tabela ItemModel.
     * Ele retorna um LiveData<List<ItemModel>>, ou seja, uma lista de itens ItemModel envolvida em um LiveData.
     *
     * O LiveData permite que qualquer mudança na lista de itens seja observada em tempo real,
     * possibilitando a atualização automática da UI quando os dados mudam.
     */
    @Query("SELECT * FROM ItemModel")
    fun getAll(): LiveData<List<ItemModel>>

    /**
     * Este método insere um novo item ItemModel no banco de dados.
     * Não retorna nada, apenas executa a inserção.
     *
     * Esse método recebe um único parâmetro do tipo ItemModel, que será adicionado à tabela.
     */
    @Insert
    fun insert(item: ItemModel)

    /**
     * Este método deleta um item específico da tabela ItemModel.
     * Ele recebe um objeto ItemModel como parâmetro e remove o item correspondente da tabela.
     */
    @Delete
    fun delete(item: ItemModel)
}