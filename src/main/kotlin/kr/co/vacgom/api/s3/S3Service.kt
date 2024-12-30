package kr.co.vacgom.api.s3

import io.awspring.cloud.s3.S3Operations
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartException
import org.springframework.web.multipart.MultipartFile

@Component
class S3Service(
    @Value("\${spring.cloud.aws.s3.bucket}")
    private val bucketName: String,
    private val s3Operation: S3Operations,
) {
    fun uploadImage(path: String, file: MultipartFile?) {
        if (file == null || file.isEmpty) {
            throw MultipartException("파일이 비어있거나 null입니다.")
        }

        s3Operation.upload(bucketName, path, file.inputStream)
    }
}
