package example.com

import com.mongodb.kotlin.client.coroutine.MongoClient
import example.com.data.MongoDataSource
import example.com.plugins.configureMonitoring
import example.com.plugins.configureRouting
import example.com.plugins.configureSerialization
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    val mongoPw = System.getenv("MONGO_PW")

    val dbName = "ItemsDatabase"
    val db = MongoClient.create(
        connectionString = "mongodb+srv://abdulmajidzeeshan4:${mongoPw}@cluster0.1nmbv.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0"
    ).getDatabase(dbName)

    val dataSource = MongoDataSource(db)

    configureSerialization()
    configureMonitoring()
    configureRouting(dataSource)
}
