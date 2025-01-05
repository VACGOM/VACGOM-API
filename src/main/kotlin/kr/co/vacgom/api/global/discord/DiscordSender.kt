package kr.co.vacgom.api.global.discord

import com.fasterxml.jackson.databind.ObjectMapper
import kr.co.vacgom.api.baby.domain.Baby
import kr.co.vacgom.api.vaccine.domain.UnclassifiedVaccination
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Component
class DiscordSender(
    @Value("\${discord.deployWebhookURL}")
    private val webhookURL: String,

    @Value("\${discord.welcomeWebhookURL}")
    private val welcomeURL: String,

    private val objectMapper: ObjectMapper,

    private val restTemplate: RestTemplate
) {
    fun sendVaccinationError(
        baby: Baby,
        unclassifiedVaccination: UnclassifiedVaccination
    ) {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON

        val now = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초")
        val formattedNow = now.format(formatter)

        val embeds = mapOf(
            "title" to "**[ \uD83D\uDEA8 긴급 \uD83D\uDEA8 ] ERR-001** 미분류 백신 추가 필요",
            "description" to "**미분류 백신** : ${unclassifiedVaccination.name}\n\n" +
                    "**요청시간** : " + formattedNow + "\n\n" +
                    "**LOG**\n\n" +
                    "```json\n{\n" +
                    "\t\"id\" : ${unclassifiedVaccination.id},\n" +
                    "\t\"name\" : ${unclassifiedVaccination.name},\n" +
                    "\t\"doseRound\" : ${unclassifiedVaccination.doseRound},\n" +
                    "\t\"doseRoundDescription\" : ${unclassifiedVaccination.doseRoundDescription},\n" +
                    "\t\"vaccinatedAt\" : ${unclassifiedVaccination.vaccinatedAt},\n" +
                    "\t\"facility\" : ${unclassifiedVaccination.facility},\n" +
                    "\t\"manufacturer\" : ${unclassifiedVaccination.manufacturer},\n" +
                    "\t\"productName\" : ${unclassifiedVaccination.productName},\n" +
                    "\t\"lotNumber\" : ${unclassifiedVaccination.lotNumber},\n" +
                    "\t\"babyId\" : ${unclassifiedVaccination.baby.id}\n}```",
            "color" to "15548997"
        )
        val payload = mapOf(
            "content" to null,
            "embeds" to listOf(embeds)
        )

        val jsonPayload = objectMapper.writeValueAsString(payload)
        val entity = HttpEntity(jsonPayload, headers)
        restTemplate.postForLocation(webhookURL, entity)
    }
}
