package example.com.data.model

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId


data class Item(
    val name:String,
    val category:String,
    @BsonId val id:ObjectId = ObjectId()
)
