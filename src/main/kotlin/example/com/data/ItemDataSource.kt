package example.com.data

import example.com.data.model.Item
import example.com.data.request.ClaimRequest

interface ItemDataSource {
    suspend fun getItem(name:String): Item?
    suspend fun getItems():List<Item>?
    suspend fun createItem(item: Item):Boolean
    suspend fun deleteItem(name: String):Boolean
    suspend fun claimItem(item:ClaimRequest):Boolean
}