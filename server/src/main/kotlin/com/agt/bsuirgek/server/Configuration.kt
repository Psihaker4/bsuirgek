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
    Linux("/home/kotone/Documents/","localhost",8080,3306),
    Server("/home/trimple/bsuirgek/", "37.187.116.151", 8080, 3306);

    lateinit var password: String
    lateinit var user: String
    fun init(args: Array<String>) = try {
        user = args[0]
        password = args[1]
        true
    } catch (e: Exception) {
        println("Error: $e")
        false
    }

    fun connectDb() = Database.connect("jdbc:mysql://$url:$mysqlPort/bsuir?useSSL=false", "com.mysql.jdbc.Driver", user, password)
    //"bsuirServer"
    //"avantgarde"

    fun startServer(body: Application.(Configuration) -> Unit) = embeddedServer(Netty, port, url) { body(this, this@Configuration) }.start(true)

}