package kr.co.vacgom.api.baby.application

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.co.vacgom.api.baby.presentation.dto.BabyDto
import kr.co.vacgom.api.global.util.UuidCreator
import kr.co.vacgom.api.s3.S3Service
import org.apache.tomcat.util.http.fileupload.InvalidFileNameException
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class BabyImageService(
    @Value("\${spring.cloud.aws.cloud-front.cdn}")
    private val cdnUrl: String,
    private val s3Service: S3Service,
) {
    fun uploadBabyImages(images: List<MultipartFile>): List<BabyDto.Response.UploadedImage> {

        return images.map {
            val path = "${BABY_PROFILE_DIR}${UuidCreator.create()}.${extractExt(it)}"

            CoroutineScope(Dispatchers.IO).launch {
                s3Service.uploadImage(path, it)
            }

            BabyDto.Response.UploadedImage("${cdnUrl}/${path}")
        }
    }

    private fun extractExt(file: MultipartFile): String {
        file.originalFilename?.let { return it.substringAfterLast(".") }
            ?: throw InvalidFileNameException(file.originalFilename, "파일 이름이 존재하지 않습니다.")
    }

    companion object {
        const val BABY_PROFILE_DIR = "baby_profile/"
    }
}
