package com.darcy.kmpdemo.network.http.urls

import com.darcy.kmpdemo.network.http.urls.Hosts.DOMAIN

object WebSockets {
    const val WEBSOCKET_URL = "ws://$DOMAIN/stomp-ws"
    const val WEBSOCKET_HTTP_URL = "http://$DOMAIN/stomp-ws"
}