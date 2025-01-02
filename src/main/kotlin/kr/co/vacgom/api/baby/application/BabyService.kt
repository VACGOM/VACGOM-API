package kr.co.vacgom.api.baby.application

import kr.co.vacgom.api.baby.domain.Baby
import kr.co.vacgom.api.baby.presentation.dto.BabyDto
import kr.co.vacgom.api.baby.presentation.dto.BabyDto.Response.DetailWithAge.BabyAge
import kr.co.vacgom.api.baby.repository.BabyRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
class BabyService(
    private val babyRepository: BabyRepository,
) {
    fun getBabiesById(babyIds: List<UUID>): List<Baby> {
        return babyRepository.findBabiesById(babyIds)
    }
    
    fun getBabyById(id: UUID): Baby {
        return babyRepository.findById(id)
    }

    fun getBabyDetailWithAgeById(id: UUID): BabyDto.Response {
        return babyRepository.findById(id).let {
            BabyDto.Response.DetailWithAge(
                id = it.id,
                name = it.name,
                profileImg = it.profileImg,
                gender = it.gender,
                birthday = it.birthday,
                age = BabyAge.create(it.birthday),
            )
        }
    }

    fun getBabyDetailById(id: UUID): BabyDto.Response {
        return babyRepository.findById(id).let {
            BabyDto.Response.Detail(
                id = it.id,
                name = it.name,
                profileImg = it.profileImg,
                gender = it.gender,
                birthday = it.birthday,
            )
        }
    }

    fun updateBabyInfo(babyId: UUID, request: BabyDto.Request.Update): BabyDto.Response.Detail {
        babyRepository.findById(babyId).update(
            request.name,
            request.profileImg,
            request.gender,
            request.birthday
        ).let {
            return BabyDto.Response.Detail(
                id = it.id,
                name = it.name,
                profileImg = it.profileImg,
                gender = it.gender,
                birthday = it.birthday,
            )
        }
    }

    fun saveAll(babies: List<Baby>): List<Baby> {
        return babyRepository.saveAll(babies)
    }
}
