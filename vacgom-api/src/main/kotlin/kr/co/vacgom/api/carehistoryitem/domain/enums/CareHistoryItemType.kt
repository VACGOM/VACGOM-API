package kr.co.vacgom.api.carehistoryitem.domain.enums

enum class CareHistoryItemType(
    val typeName: String,
) {
    BABY_FOOD("이유식"),
    BABY_FORMULA("분유"),
    BATH("목욕"),
    BREAST_FEEDING("모유수유"),
    BREAST_PUMPING("유축"),
    DIAPER("기저귀"),
    HEALTH("건강"),
    SLEEP("수면"),
    SNACK("간식"),
}
