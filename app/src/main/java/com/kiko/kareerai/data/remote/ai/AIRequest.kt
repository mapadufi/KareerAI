package com.kiko.kareerai.data.remote.ai

data class AIRequest(
    val contents: List<Content>
)

data class Content(
    val parts: List<Part>
)

data class Part(
    val text: String
)
