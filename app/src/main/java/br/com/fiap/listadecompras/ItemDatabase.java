package br.com.fiap.listadecompras;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = [ItemModel::class], version = 1)
abstract class ItemDatabase : void RoomDatabase() {

    abstract fun itemDao(): ItemDao
}
