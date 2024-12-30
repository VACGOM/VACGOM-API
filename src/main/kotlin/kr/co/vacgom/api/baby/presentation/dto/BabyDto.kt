package kr.co.vacgom.api.baby.presentation.dto

import kr.co.vacgom.api.baby.domain.enums.Gender
import org.springframework.web.multipart.MultipartFile
import java.time.LocalDate
import java.util.*

class BabyDto {
    class Request {
        data class UploadImage(
            val images: List<MultipartFile>,
        )
    }

    class Response {
        data class Detail(
            val id: UUID,
            val name: String,
            val profileImg: String?,
            val gender: Gender,
            val birthday: LocalDate,
        )
    }
}
