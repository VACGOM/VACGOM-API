package kr.co.vacgom.api.member.repository

import kr.co.vacgom.api.member.domain.Member

interface MemberRepository {
    fun save(member: Member)
    fun findBySocialId(socialId: String): Member?
    fun findByIdAndPassword(id: String, password: String): Member?
    fun findByUserId(userId: Long): Member?
    fun deleteByUserId(userId: Long)
}
