package com.stacktivity.smartwarehouse.inventory

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.stacktivity.smartwarehouse.adapters.ItemListAdapter
import com.stacktivity.smartwarehouse.contracts.InventoryContract
import com.stacktivity.smartwarehouse.data.entities.Item

class InventoryPresenter(
//    private val view: InventoryContract.View,
    private val repository: InventoryContract.Repository
) : InventoryContract.Presenter {

    private lateinit var view: InventoryContract.View

    private val itemListAdapter =
        ItemListAdapter(this)
    override fun getListAdapter(): ItemListAdapter {
        return itemListAdapter
    }

    /*init {
        view.setPresenter(this)
    }*/

    override fun attachView(view: InventoryContract.View) {
        this.view = view
    }

    // For adapter
    override fun getItemsCount(): Int {
        return repository.getItemsCount()
    }

    override fun getItem(pos: Int): Item {
        return repository.getItem(pos)
    }

    @SuppressLint("DefaultLocale")
    override fun processVoiceInputResult(results: ArrayList<String>) {
        var suitableResult: String? = null
        val regexName = Regex("([А-Яа-я]+\\s?)+")
        val regexCount = Regex("[0-9]+")
        val regexQtyName = Regex("([0-9]+.?)+([А-Яа-я]+\\s?)")
        var name = ""
        var count = 0
        var qtyName = ""

        // Check for number in sentence
        for (result in results) {
            view.showDebugMessage(result)

            // Set count
            val resultStringCount: String? = regexCount.find(result)?.value
            count = if (resultStringCount != "" && resultStringCount != null) {
                view.showDebugMessage(resultStringCount)
                resultStringCount.toInt()
            }
            else 0

            // Select the current var
            if (count != 0) {
                suitableResult = result
                break
            }
        }


        if (suitableResult != null) {
            // Set quantity name
            qtyName = regexQtyName.find(suitableResult)?.value ?: ""
            if (qtyName != "") {
                qtyName = getCanonicalQtyName(qtyName.split(' ')[1])
            }

            // Set title
            name = regexName.find(suitableResult)?.value ?: ""
            name = getCanonicalTitle(name)
        }

        // Check results
        when {
            name == "" -> view.showMessage("Наименование товара не присутствует в каталоге")
            count == 0 -> view.showMessage("Не распознано колличество товара")
            qtyName == "" -> view.showMessage("Укажите единицу наименования количества товара")

            else -> {
                repository.addItem(Item(name, count, qtyName))
                itemListAdapter.notifyItemInserted(itemListAdapter.itemCount - 1)
            }
        }
//            view.showMessage(R.string.item_not_found)

        /*val pattern: Pattern = Pattern.compile("([А-Яа-я]+\\s?)+")
        val matcher = pattern.matcher(result)
        val resultName = if (matcher.find()) result.substring(matcher.start(), matcher.end())
                        else null*/


        /*val pattern: Pattern = Pattern.compile(regexName)
        val matcher = pattern.matcher(result)
        resultName = result.substring(matcher.start(), matcher.end())*/
    }

    private fun getCanonicalQtyName(qtyName: String): String {
        return when {
            qtyName.contains("штук") -> "шт"
            qtyName.contains("кило") -> "кг"
            qtyName.contains("грам") -> "г"
            qtyName.contains("милли") -> "мл"
            qtyName.contains("литр") -> "л"
            else -> ""
        }
    }

    private fun getCanonicalTitle(name: String): String {
        var result = ""

        for (title in repository.getCatalog()) {
            if (name.contains(title)) {
                result = title
            }
        }

        return result
    }
}