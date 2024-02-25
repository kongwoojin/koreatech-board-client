package data.api

import io.ktor.client.engine.darwin.Darwin

actual fun httpClientEngine(): io.ktor.client.engine.HttpClientEngine {
    return Darwin.create()
}