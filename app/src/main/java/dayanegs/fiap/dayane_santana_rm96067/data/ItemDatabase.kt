package dayanegs.fiap.dayane_santana_rm96067.data

import androidx.room.Database
import androidx.room.RoomDatabase
import dayanegs.fiap.dayane_santana_rm96067.model.ItemModel

@Database(entities = [ItemModel::class], version = 1)
abstract class ItemDatabase : RoomDatabase() {

    abstract fun itemDao(): ItemDao
}