package kr.co.vacgom.api.global.util

import com.github.f4b6a3.uuid.UuidCreator
import java.util.*

object UuidUtility {

    fun generateRandomUuid(): UUID {
        return UuidCreator.getTimeOrderedEpochPlus1()
    }
}
