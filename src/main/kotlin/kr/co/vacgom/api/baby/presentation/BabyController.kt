package kr.co.vacgom.api.baby.presentation

import kr.co.vacgom.api.baby.application.BabyImageService
import kr.co.vacgom.api.baby.application.BabyService
import kr.co.vacgom.api.baby.presentation.BabyApi.Companion.BABY
import kr.co.vacgom.api.baby.presentation.dto.BabyDto
import kr.co.vacgom.api.global.common.dto.BaseResponse
import kr.co.vacgom.api.global.presentation.GlobalPath.BASE_V3
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(BASE_V3 + BABY)
class BabyController(
    private val babyService: BabyService,
    private val babyImageService: BabyImageService,
): BabyApi {
    @PostMapping("/images")
    override fun uploadBabyImage(@ModelAttribute request: BabyDto.Request.UploadImage): BaseResponse<List<String>> {
        return babyImageService.uploadBabyImages(request.images).let { BaseResponse.success(it) }
    }
}