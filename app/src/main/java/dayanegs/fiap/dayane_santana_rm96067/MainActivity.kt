package dayanegs.fiap.dayane_santana_rm96067

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.SearchView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import dayanegs.fiap.dayane_santana_rm96067.viewmodel.ItemsAdapter
import dayanegs.fiap.dayane_santana_rm96067.viewmodel.ItemsViewModel
import dayanegs.fiap.dayane_santana_rm96067.viewmodel.ItemsViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var itemsViewModel: ItemsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "EcoDicas"

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val adapter = ItemsAdapter()
        recyclerView.adapter = adapter

        val btnAdd = findViewById<Button>(R.id.button)
        val mensagemEco = findViewById<Button>(R.id.mensagemEco)
        val inputItemName = findViewById<EditText>(R.id.editText)
        val inputItemDescription = findViewById<EditText>(R.id.editText2)

        btnAdd.setOnClickListener {
            val itemName = inputItemName.text.toString()
            val itemDescription = inputItemDescription.text.toString()

            if (itemName.isEmpty()) {
                inputItemName.error = "Campo obrigatório"
                return@setOnClickListener
            }

            if (itemDescription.isEmpty()) {
                inputItemDescription.error = "Campo obrigatório"
                return@setOnClickListener
            }

            itemsViewModel.addItem(itemName, itemDescription)
            inputItemName.text.clear()
            inputItemDescription.text.clear()
        }
        mensagemEco.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Dica da ecoDica")
                .setMessage("CUIDAR DO AMBIENTE FAZ BEM")
                .setNegativeButton("Fechar") { dialog, _ -> dialog.dismiss() }
                .show()
        }


        val factory = ItemsViewModelFactory(application)
        itemsViewModel = ViewModelProvider(this, factory).get(ItemsViewModel::class.java)

        itemsViewModel.itemsLiveData.observe(this) { items ->
            adapter.updateItems(items)
        }

        val searchView: SearchView = findViewById(R.id.search_view)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val searchQuery = newText.orEmpty()
                val filteredList = itemsViewModel.itemsLiveData.value?.filter {
                    it.name.contains(searchQuery, ignoreCase = true)
                } ?: emptyList()

                adapter.updateItems(filteredList)
                return true
            }
        })
    }
}
