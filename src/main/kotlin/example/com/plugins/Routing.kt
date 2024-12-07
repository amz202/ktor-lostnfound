package example.com.plugins

import example.com.data.ItemDataSource
import example.com.routes.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting(datasource: ItemDataSource) {
    routing {
        getItem(datasource)
        getItems(datasource)
        createItem(datasource)
        deleteItem(datasource)
        claimItem(datasource)
    }
}
