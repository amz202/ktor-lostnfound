package example.com.data

import com.mongodb.kotlin.client.coroutine.MongoDatabase
import com.sun.tools.javac.jvm.Items
import example.com.data.model.Item
import com.mongodb.client.model.Filters.eq
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.toList

class MongoDataSource(db:MongoDatabase):ItemDataSource{
    private val items = db.getCollection("items", Item::class.java)

    override suspend fun getItem(name: String): Item? {
        return items.find(eq("name",name)).firstOrNull()
    }

    override suspend fun getItems(): List<Item>? {
        return items.find().toList()
    }

    override suspend fun createItem(item: Item): Boolean {
        return items.insertOne(item).wasAcknowledged()
    }

    override suspend fun deleteItem(name: String): Boolean {
        val deleteResult =  items.deleteOne(eq("name",name))
        return deleteResult.deletedCount>0
    }

}