package data.api

import io.ktor.client.engine.*
import io.ktor.client.engine.js.*

actual fun httpClientEngine(): HttpClientEngine {
    return Js.create()
}