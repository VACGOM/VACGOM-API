package kr.co.vacgom.api.baby.presentation.dto

import io.swagger.v3.oas.annotations.media.Schema
import kr.co.vacgom.api.baby.domain.enums.Gender
import org.springframework.web.multipart.MultipartFile
import java.time.LocalDate
import java.time.Period
import java.util.*

class BabyDto {
    @Schema(name = "BabyDto.Request.UploadImage")
    class Request {
        data class UploadImage(
            val images: List<MultipartFile>,
        )
    }

    sealed class Response {
        @Schema(name = "BabyDto.Response.Detail")
        data class Detail(
            val id: UUID,
            val name: String,
            val profileImg: String?,
            val gender: Gender,
            val birthday: LocalDate,
        ): Response()

        @Schema(name = "BabyDto.Response.DetailWithAge")
        data class DetailWithAge(
            val id: UUID,
            val name: String,
            val profileImg: String?,
            val gender: Gender,
            val birthday: LocalDate,
            val age: BabyAge,
        ): Response() {
            @Schema(name = "BabyDto.Response.DetailWithAge.BabyAge")
            data class BabyAge(
                val year: Int,
                val month: Int,
                val day: Int,
            ) {
                companion object {
                    fun create(birthday: LocalDate): BabyAge {
                        return Period.between(birthday, LocalDate.now()).let {
                            BabyAge(
                                year = it.years,
                                month = it.months,
                                day = it.days,
                            )
                        }
                    }
                }
            }
        }

        @Schema(name = "BabyDto.Response.UploadedImage")
        data class UploadedImage(
            val imageUrl: String
        )
    }
}
