package kr.co.vacgom.api.baby.presentation

import kr.co.vacgom.api.auth.security.util.SecurityContextUtil
import kr.co.vacgom.api.baby.application.BabyCommandService
import kr.co.vacgom.api.baby.application.BabyImageService
import kr.co.vacgom.api.baby.application.BabyQueryService
import kr.co.vacgom.api.baby.presentation.BabyApi.Companion.BABY
import kr.co.vacgom.api.baby.presentation.dto.BabyDto
import kr.co.vacgom.api.global.common.dto.BaseResponse
import kr.co.vacgom.api.global.presentation.GlobalPath.BASE_V3
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping(BASE_V3 + BABY)
class BabyController(
    private val babyCommandService: BabyCommandService,
    private val babyQueryService: BabyQueryService,
    private val babyImageService: BabyImageService,
): BabyApi {
    @GetMapping("/{babyId}")
    override fun getBabyDetail(
        @PathVariable babyId: UUID,
        @RequestParam withAge: Boolean?
    ): BaseResponse<BabyDto.Response> {
        return when(withAge) {
            true -> babyQueryService.getBabyDetailWithAgeById(babyId).let { BaseResponse.success(it) }
            else -> babyQueryService.getBabyDetailById(babyId).let { BaseResponse.success(it) }
        }
    }

    @GetMapping
    override fun getUserBabyDetailsWithAge(): BaseResponse<List<BabyDto.Response.DetailWithAge>> {
        val userId = SecurityContextUtil.getPrincipal()
        return babyQueryService.getUserBabyDetailsWithAge(userId).let { BaseResponse.success(it) }
    }

    @PostMapping
    override fun createBaby(@RequestBody request: BabyDto.Request.Create) {
        val userId = SecurityContextUtil.getPrincipal()
        babyCommandService.createBaby(userId, request)
    }

    @PostMapping("/images", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    override fun uploadBabyImage(@ModelAttribute request: BabyDto.Request.UploadImage): BaseResponse<List<BabyDto.Response.UploadedImage>> {
        return babyImageService.uploadBabyImages(request.images).let { BaseResponse.success(it) }
    }

    @PatchMapping("/{babyId}")
    override fun updateBaby(
        @PathVariable babyId: UUID,
        @RequestBody request: BabyDto.Request.Update
    ): BaseResponse<BabyDto.Response.Detail> {
        return babyCommandService.updateBabyInfo(babyId, request).let { BaseResponse.success(it) }
    }
}
