package dayanegs.fiap.dayane_santana_rm96067.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import dayanegs.fiap.dayane_santana_rm96067.model.ItemModel

@Dao
interface ItemDao {

    @Query("SELECT * FROM ItemModel")
    fun getAll(): LiveData<List<ItemModel>>

    @Insert
    fun insert(item: ItemModel)
}
