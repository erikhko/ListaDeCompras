package br.com.fiap.listadecompras;

import androidx.room.Database;
import androidx.room.RoomDatabase;

/**
 * A anotação @Database marca esta classe como um banco de dados do Room.
 *
 * entities = [ItemModel::class]: Isso indica que a entidade (tabela) gerenciada por esse banco de dados é a ItemModel.
 * O Room criará uma tabela com base nessa classe.
 *
 * version = 1: Define a versão do banco de dados. Isso é útil para futuras migrações de dados quando houver alterações na estrutura do banco.
 */
@Database(entities = [ItemModel::class], version = 1)

/**
 * A classe ItemDatabase estende a classe RoomDatabase, que é a classe base para todos os bancos de dados criados usando o Room.
 * Como ela é abstrata, o Room gera automaticamente a implementação necessária para essa classe.
 */
abstract class ItemDatabase : RoomDatabase() {

    /**
     * Esse método abstrato retorna uma instância de ItemDao, que é o DAO responsável por interagir com a tabela ItemModel.
     *
     * O Room gera o código para esse método e o ItemDao permitirá realizar operações como inserir, deletar e consultar os itens no banco de dados.
     */
    abstract fun itemDao(): ItemDao
}
