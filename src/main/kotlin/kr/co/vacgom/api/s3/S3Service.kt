package kr.co.vacgom.api.s3

import io.awspring.cloud.s3.S3Exception
import io.awspring.cloud.s3.S3Operations
import kr.co.vacgom.api.global.util.UuidCreator
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile

@Component
class S3Service(
    @Value("\${spring.cloud.aws.s3.bucket}")
    private val bucketName: String,
    @Value("\${spring.cloud.aws.cloud-front.cdn}")
    private val cdnUrl: String,
    private val s3Operation: S3Operations,
) {
    fun uploadImage(file: MultipartFile): String? {
        if (file.isEmpty) {
            return null
        }

        return file.run {
            val path = "${BABY_PROFILE_DIR}${UuidCreator.create()}.${extractExt(this)}"
            val resource = s3Operation.upload(bucketName, path, inputStream)
            "${cdnUrl}/${resource.filename}"
        }
    }

    private fun extractExt(file: MultipartFile): String {
        file.originalFilename?.let { return it.substringAfterLast(".") }
            ?: throw S3Exception("Invalid file path", null)
    }

    companion object {
        const val BABY_PROFILE_DIR = "baby_profile/"
    }
}
