package data.api

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object KtorHttpClient {
    private const val API_PRODUCTION = "https://api.kongjak.com/v3/"
    private const val API_DEVELOPMENT = "https://dev.api.kongjak.com/v3/"

    val ktorHttpClient = HttpClient(httpClientEngine()) {
        expectSuccess = true

        defaultRequest {
            url(API_DEVELOPMENT)
        }

        install(ContentNegotiation) {
            json(Json)
        }

        install(HttpRequestRetry) {
            retryOnServerErrors(maxRetries = 5)
            exponentialDelay()
        }
    }
}

expect fun httpClientEngine(): HttpClientEngine