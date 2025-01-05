package kr.co.vacgom.api.global.config

import org.hibernate.boot.model.naming.Identifier
import org.hibernate.boot.model.naming.PhysicalNamingStrategy
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment
import org.springframework.context.annotation.Configuration

@Configuration
class SnakeCaseUpperNamingStrategy : PhysicalNamingStrategy {

    private fun convertToSnakeCaseUpperCase(input: String): String {
        val regex = "([a-z])([A-Z]+)"
        val replacement = "$1_$2"
        return input.replace(regex.toRegex(), replacement).uppercase()
    }

    override fun toPhysicalCatalogName(identifier: Identifier?, jdbcEnvironment: JdbcEnvironment?): Identifier? = null

    override fun toPhysicalSchemaName(identifier: Identifier?, jdbcEnvironment: JdbcEnvironment?): Identifier? = null

    override fun toPhysicalTableName(name: Identifier, context: JdbcEnvironment): Identifier =
        Identifier(convertToSnakeCaseUpperCase(name.text), name.isQuoted)

    override fun toPhysicalSequenceName(identifier: Identifier?, jdbcEnvironment: JdbcEnvironment?): Identifier? = null

    override fun toPhysicalColumnName(name: Identifier, context: JdbcEnvironment): Identifier =
        Identifier(convertToSnakeCaseUpperCase(name.text), name.isQuoted)
}
