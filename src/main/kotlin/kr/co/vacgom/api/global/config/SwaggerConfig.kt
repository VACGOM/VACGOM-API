package kr.co.vacgom.api.global.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.Operation
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.media.Schema
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import io.swagger.v3.oas.models.servers.Server
import kr.co.vacgom.api.global.common.dto.BaseResponse
import org.springdoc.core.customizers.OperationCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class SwaggerConfig {
    @Bean
    fun openAPI(): OpenAPI {
        val jwt = "JWT"
        val securityRequirement: SecurityRequirement = SecurityRequirement().addList(jwt)
        val components = Components().addSecuritySchemes(
            jwt, SecurityScheme()
                .name(jwt)
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
        )

        val server = Server()
        server.url("https://api-dev-v2.vacgom.co.kr")

        return OpenAPI()
            .components(Components())
            .addServersItem(server)
            .info(apiInfo())
            .addSecurityItem(securityRequirement)
            .components(components)
    }

    @Bean
    fun operationCustomizer(): OperationCustomizer {
        return OperationCustomizer { operation, _ ->
            addResponseBodyWrapperSchemaExample(
                operation,
                BaseResponse::class.java,
                "data"
            )
            operation
        }
    }

    private fun addResponseBodyWrapperSchemaExample(operation: Operation, type: Class<*>, wrapFieldName: String) {
        val successResponses = operation.responses.filter { it.key in listOf("200", "201") && it.value.content != null }

        successResponses.forEach { (_, response) ->
            response.content.forEach { (_, mediaType) ->
                mediaType.schema = wrapSchema(mediaType.schema, type, wrapFieldName)
            }
        }
    }

    private fun <T> wrapSchema(originSchema: Schema<*>?, type: Class<T>, wrapFieldName: String): Schema<*> {
        val wrapperSchema = Schema<Any>()
        val instance = type.getDeclaredConstructor().newInstance()

        type.declaredFields
            .filterNot { it.name == "Companion" }
            .forEach { field ->
                field.isAccessible = true
                wrapperSchema.addProperty(field.name, Schema<Any>().example(field.get(instance)))
                field.isAccessible = false
            }

        if (originSchema != null) {
            wrapperSchema.addProperty(wrapFieldName, originSchema)
        }

        return wrapperSchema
    }

    private fun apiInfo(): Info {
        return Info()
            .title("VACGOM Open API")
            .description("VACGOM Open API")
            .version("3.0.0")
    }
}
