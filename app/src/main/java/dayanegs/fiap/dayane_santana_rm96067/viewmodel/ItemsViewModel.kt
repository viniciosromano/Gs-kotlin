package dayanegs.fiap.dayane_santana_rm96067.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import dayanegs.fiap.dayane_santana_rm96067.data.ItemDao
import dayanegs.fiap.dayane_santana_rm96067.data.ItemDatabase
import dayanegs.fiap.dayane_santana_rm96067.model.ItemModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ItemsViewModel(application: Application) : AndroidViewModel(application) {

    private val itemDao: ItemDao

    val itemsLiveData: LiveData<List<ItemModel>>

    init {
        val database = Room.databaseBuilder(
            getApplication(),
            ItemDatabase::class.java,
            "items_database"
        ).build()

        itemDao = database.itemDao()

        itemsLiveData = itemDao.getAll()
    }
    fun addItem(item: String, item2: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val newItem = ItemModel(name = item, desc = item2)
            itemDao.insert(newItem)
        }
    }

}