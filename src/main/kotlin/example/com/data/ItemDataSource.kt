package example.com.data

import example.com.data.model.Item

interface ItemDataSource {
    suspend fun getItem(name:String): Item?
    suspend fun getItems():List<Item>?
    suspend fun createItem(item: Item):Boolean
    suspend fun deleteItem(name: String):Boolean
}