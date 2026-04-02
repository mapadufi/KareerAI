package com.kiko.kareerai.data.remote.ai

import com.kiko.kareerai.data.model.Perfil

class AIRepository(
    private val service: AIService
) {

    suspend fun analisarCurriculo(apiKey: String, curriculo: String): Perfil {
        val prompt = """
            Analise o currículo abaixo e retorne APENAS um objeto JSON (sem markdown) no seguinte formato:
            {
              "cargo": "nome do cargo",
              "nivel": "junior, pleno ou senior",
              "tecnologias": ["tech1", "tech2"]
            }
            
            Currículo:
            $curriculo
        """.trimIndent()

        val request = AIRequest(
            contents = listOf(Content(parts = listOf(Part(text = prompt))))
        )

        return try {
            val response = service.analisarCurriculo(apiKey, request)
            val content = response.candidates.first().content.parts.first().text
            parsePerfil(content)
        } catch (e: Exception) {
            e.printStackTrace()
            Perfil(cargo = "Não identificado", nivel = "Não identificado")
        }
    }

    suspend fun calcularMatch(apiKey: String, curriculo: String, vagaDescricao: String): Int {
        val prompt = """
            Compare o currículo abaixo com a descrição da vaga fornecida.
            Retorne APENAS um número de 0 a 100 representando a porcentagem de compatibilidade técnica.
            
            Vaga: $vagaDescricao
            Currículo: $curriculo
        """.trimIndent()

        val request = AIRequest(
            contents = listOf(Content(parts = listOf(Part(text = prompt))))
        )

        return try {
            val response = service.analisarCurriculo(apiKey, request)
            val content = response.candidates.first().content.parts.first().text.trim()
            content.filter { it.isDigit() }.toIntOrNull() ?: 50
        } catch (e: Exception) {
            50
        }
    }

    suspend fun gerarPitch(apiKey: String, curriculo: String, vagaTitulo: String, vagaDescricao: String): String {
        val prompt = """
            Com base no currículo abaixo e na descrição da vaga, escreva uma mensagem curta e impactante (Pitch de Venda) de no máximo 3 parágrafos para ser enviada ao recrutador via LinkedIn ou Email.
            
            Vaga: $vagaTitulo
            Descrição: $vagaDescricao
            Currículo: $curriculo
        """.trimIndent()

        val request = AIRequest(
            contents = listOf(Content(parts = listOf(Part(text = prompt))))
        )

        return try {
            val response = service.analisarCurriculo(apiKey, request)
            response.candidates.first().content.parts.first().text
        } catch (e: Exception) {
            "Erro ao gerar apresentação."
        }
    }

    private fun parsePerfil(json: String): Perfil {
        return try {
            val cleanJson = json.replace("```json", "").replace("```", "").trim()
            val obj = org.json.JSONObject(cleanJson)
            val tecnologias = mutableListOf<String>()
            obj.optJSONArray("tecnologias")?.let { for (i in 0 until it.length()) tecnologias.add(it.getString(i)) }
            
            Perfil(
                cargo = obj.optString("cargo"),
                nivel = obj.optString("nivel"),
                tecnologias = tecnologias
            )
        } catch (e: Exception) {
            Perfil(cargo = "Erro", nivel = "Erro")
        }
    }
}
