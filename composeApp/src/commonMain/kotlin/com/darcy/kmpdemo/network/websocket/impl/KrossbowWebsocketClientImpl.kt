package com.darcy.kmpdemo.network.websocket.impl

import com.darcy.kmpdemo.bean.websocket.stomp.STOMPMessage
import com.darcy.kmpdemo.log.logE
import com.darcy.kmpdemo.network.http.impl.ktor.ktorClient
import com.darcy.kmpdemo.network.parser.impl.kotlinxJson
import com.darcy.kmpdemo.network.websocket.IWebSocketClient
import com.darcy.kmpdemo.network.websocket.listener.IOuterListener
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.newSingleThreadContext
import org.hildan.krossbow.stomp.StompClient
import org.hildan.krossbow.stomp.StompSession
import org.hildan.krossbow.stomp.config.HeartBeat
import org.hildan.krossbow.stomp.config.HeartBeatTolerance
import org.hildan.krossbow.stomp.sendText
import org.hildan.krossbow.stomp.subscribeText
import org.hildan.krossbow.websocket.WebSocketClient
import org.hildan.krossbow.websocket.ktor.KtorWebSocketClient
import kotlin.time.DurationUnit
import kotlin.time.toDuration

class KrossbowWebsocketClientImpl : IWebSocketClient, IOuterListener {
    companion object {
        private val TAG = KrossbowWebsocketClientImpl::class.simpleName
        private val SEND_PRIVATE = "/app/sendPrivateMessage"
        private val SEND_TARGET_GROUP = "/app/sendTargetGroupMessage"
        private val SEND_TOPIC = "/app/sendGroupAllMessage"
        private val RECEIVE_PRIVATE = "/user/queue/message"
        private val RECEIVE_TARGET_GROUP = "/topic/group/" // + groupId
    }

    private var url: String = ""
    private var fromUser: String = ""
    private var outListener: IOuterListener? = null
    private var session: StompSession? = null

    @OptIn(DelicateCoroutinesApi::class, ExperimentalCoroutinesApi::class)
    private val dispatcher: CoroutineDispatcher = newSingleThreadContext("websocket-stomp")
    private val exceptionHandler: CoroutineExceptionHandler =
        CoroutineExceptionHandler { _, throwable ->
            logE("$TAG exceptionHandler: ${throwable.message}")
            throwable.printStackTrace()
        }
    private val scope = CoroutineScope(dispatcher + SupervisorJob() + exceptionHandler)
    private lateinit var stompClient: StompClient
    private lateinit var webSocketClient: WebSocketClient

    override fun init(url: String, fromUser: String) {
        this.url = url
        this.fromUser = fromUser
        webSocketClient = KtorWebSocketClient(ktorClient)
        // val kcrossbowWebsocketClient: WebSocketClient = WebSocketClient.builtIn()
        stompClient = StompClient(webSocketClient) {
            connectionTimeout = 10.toDuration(DurationUnit.SECONDS)
            disconnectTimeout = 10.toDuration(DurationUnit.SECONDS)
            subscriptionCompletionTimeout = 10.toDuration(DurationUnit.SECONDS)
            receiptTimeout = 10.toDuration(DurationUnit.SECONDS) // 确认帧超时
            autoReceipt = true  // 自动开启确认帧
            gracefulDisconnect = false
            connectWithStompCommand = true
            heartBeat = HeartBeat(
                minSendPeriod = 10.toDuration(DurationUnit.SECONDS),
                expectedPeriod = 10.toDuration(DurationUnit.SECONDS)
            )
            heartBeatTolerance = HeartBeatTolerance()
            defaultSessionCoroutineContext = dispatcher + SupervisorJob() + exceptionHandler
        }
    }

    override suspend fun connect() {
        if (outListener == null) {
            throw NullPointerException("outListener is null. please call setOutListener() first.")
        }
        if (session != null) {
            println("$TAG already connected!")
            return
        }
        runCatching {
            println("$TAG connect...")
            session = stompClient.connect(
                this.url,
                customStompConnectHeaders = mapOf(
                    "Authorization" to fromUser,
//                    "accept-version" to "1.2,1.1,1.0"
                )
            )
            println("$TAG onOpen...")
            onOpen()
            session?.let {
                it.subscribeText(RECEIVE_PRIVATE).collect { message ->
                    println("$TAG onReceive message <-- $message")
                    onMessage(message)
                }
                it.subscribeText(SEND_TOPIC).collect { message ->
                    println("$TAG onReceive topic message <-- $message")
                    onMessage(message)
                }
            } ?: run {
                println("$TAG session is null.")
                onFailure("session is null")
            }
        }.onFailure {
            println("$TAG connect error: ${it.message}")
            onFailure(it.message ?: "")
        }
    }

    override suspend fun disconnect() {
        println("$TAG disconnect...")
        session?.let {
            it.disconnect()
            onClosed()
            session = null
        } ?: run {
            println("$TAG already disconnected!")
            onFailure("already disconnected!")
        }
    }

    override suspend fun send(message: STOMPMessage) {
        val jsonMessage = kotlinxJson.encodeToString(message)
        println("$TAG send message... --> $jsonMessage")
        session?.let {
            it.sendText(SEND_PRIVATE, jsonMessage)
            onSend(jsonMessage)
        } ?: run {
            println("$TAG session is null.")
            onFailure("session is null")
        }
    }

    override suspend fun send(bytes: ByteArray) {
        TODO("Not yet implemented")
    }

    override suspend fun reconnect() {
        disconnect()
        delay(1_000)
        connect()
    }

    /**
     * 设置外部监听器
     */
    override fun setOuterListener(listener: IOuterListener) {
        this.outListener = listener
    }

    override fun onOpen() {
        outListener?.onOpen()
    }

    override fun onSend(message: String) {
        outListener?.onSend(message)
    }

    override fun onSend(bytes: ByteArray) {
        TODO("Not yet implemented")
    }

    override fun onMessage(message: String) {
        outListener?.onMessage(message)
    }

    override fun onMessage(bytes: ByteArray) {
        TODO("Not yet implemented")
    }

    override fun onFailure(errorMessage: String) {
        outListener?.onFailure("Error: $errorMessage.")
    }

    override fun onClosed() {
        outListener?.onClosed()
    }
}