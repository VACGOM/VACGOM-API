package kr.co.vacgom.api.global.util

import com.github.f4b6a3.uuid.UuidCreator
import java.util.*

object UuidCreator {
    fun create(): UUID {
        return UuidCreator.getTimeOrderedEpoch()
    }
}
