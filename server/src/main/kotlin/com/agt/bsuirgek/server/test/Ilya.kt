package com.agt.bsuirgek.server.test

import com.agt.bsuirgek.server.Configuration
import com.agt.bsuirgek.server.server

fun main(args: Array<String>) {
    Configuration.Local.startServer { config ->
        server(config)
    }
}