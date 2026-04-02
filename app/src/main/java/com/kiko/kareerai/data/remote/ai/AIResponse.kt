package com.kiko.kareerai.data.remote.ai

data class AIResponse(
    val candidates: List<Candidate>
)

data class Candidate(
    val content: ResponseContent
)

data class ResponseContent(
    val parts: List<ResponsePart>
)

data class ResponsePart(
    val text: String
)
