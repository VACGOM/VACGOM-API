package kr.co.vacgom.api.baby.presentation.dto

import io.swagger.v3.oas.annotations.media.Schema
import kr.co.vacgom.api.baby.domain.enums.Gender
import org.springframework.web.multipart.MultipartFile
import java.time.LocalDate
import java.util.*

class BabyDto {
    @Schema(name = "BabyDto.Request.UploadImage")
    class Request {
        data class UploadImage(
            val images: List<MultipartFile>,
        )
    }

    @Schema(name = "BabyDto.Response.Detail")
    class Response {
        data class Detail(
            val id: UUID,
            val name: String,
            val profileImg: String?,
            val gender: Gender,
            val birthday: LocalDate,
        )

        data class UploadedImage(
            val imageUrl: String
        )
    }
}
