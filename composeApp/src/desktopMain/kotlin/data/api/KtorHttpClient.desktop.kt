package data.api

import io.ktor.client.engine.*
import io.ktor.client.engine.cio.*

actual fun httpClientEngine(): HttpClientEngine {
    return CIO.create()
}