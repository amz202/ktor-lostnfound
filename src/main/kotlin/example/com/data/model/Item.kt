package example.com.data.model

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId
import java.time.Instant


data class Item(
    val name:String,
    val category:String,
    val description:String,
    @BsonId val id:ObjectId = ObjectId(),
    val foundAt: String = Instant.now().toString()
)
