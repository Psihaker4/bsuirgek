package com.agt.bsuirgek.server

import io.ktor.application.Application
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.jetbrains.exposed.sql.Database

enum class Configuration(val rootPath: String,
                         val url: String,
                         val port: Int,
                         val mysqlPort: Int) {
    Local("D:/", "localhost", 8080, 3306),
    Server("/trimple/omega", "deadmedia.ru", 8080, 3306);

    fun connectDB() {
        Database.connect("jdbc:mysql://$url:$mysqlPort/bsuirgek?useSSL=false", "com.mysql.jdbc.Driver", "serverUser", "avantgarde")
    }

    fun startServer(body: Application.() -> Unit) = embeddedServer(Netty, port, url, module = body).start(true)

}