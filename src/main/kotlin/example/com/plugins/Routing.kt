package example.com.plugins

import example.com.data.ItemDataSource
import example.com.routes.createItem
import example.com.routes.deleteItem
import example.com.routes.getItem
import example.com.routes.getItems
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting(datasource: ItemDataSource) {
    routing {
        getItem(datasource)
        getItems(datasource)
        createItem(datasource)
        deleteItem(datasource)
    }
}
