package example.com.routes

import example.com.data.ItemDataSource
import example.com.data.model.Item
import example.com.data.request.ClaimRequest
import example.com.data.request.ItemRequest
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.getItem(dataSource: ItemDataSource){
    get("/get-item"){
        val itemRequest = call.receive<ItemRequest>()
        val item = dataSource.getItem(itemRequest.name)
        if(item==null){
            call.respond(HttpStatusCode.NotFound,"item not found")
        }else{
            call.respond(HttpStatusCode.OK,item)
        }
    }
}

fun Route.getItems(dataSource: ItemDataSource){
    get("/get-items"){
        val items = dataSource.getItems()
        if(items==null){
            call.respond(HttpStatusCode.NotFound,"items are empty")
        }else{
            call.respond(HttpStatusCode.OK,items)
        }
    }
}

fun Route.createItem(dataSource: ItemDataSource){
    post("/create-item"){
        val itemRequest = call.receive<Item>()
        val item = Item(
            name = itemRequest.name,
            category = itemRequest.category,
            description = itemRequest.description,
            place = itemRequest.place,
            property1 = itemRequest.property1,
            property2 = itemRequest.property2,
            property3 = itemRequest.property3
        )
        val result = dataSource.createItem(item)
        if(result){
            call.respond(HttpStatusCode.OK,item)
        }else{
            call.respond(HttpStatusCode.InternalServerError,"Couldn't create item")
        }
    }
}

fun Route.deleteItem(dataSource: ItemDataSource){
    delete("/delete-item"){
        val name = call.parameters["name"]
        if (name == null) {
            call.respond(HttpStatusCode.BadRequest, "Missing item name")
            return@delete
        }
        val item = dataSource.deleteItem(name)
        if(item){
            call.respond(HttpStatusCode.OK,"Item deleted")
        }else{
            call.respond(HttpStatusCode.InternalServerError,"Couldn't delete item")
        }
    }
}

fun Route.claimItem(dataSource: ItemDataSource) {
    post("/claim-item") {
        val claimRequest = call.receive<ClaimRequest>()
        val result = dataSource.claimItem(claimRequest)
        call.respond(HttpStatusCode.OK, result)
    }
}