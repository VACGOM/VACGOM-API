package kr.co.vacgom.api.baby.application

import kr.co.vacgom.api.baby.domain.Baby
import kr.co.vacgom.api.baby.presentation.dto.BabyDto
import kr.co.vacgom.api.baby.repository.BabyRepository
import kr.co.vacgom.api.babymanager.application.BabyManagerService
import kr.co.vacgom.api.babymanager.domain.BabyManager
import kr.co.vacgom.api.user.application.UserQueryService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
class BabyCommandService(
    private val babyManagerService: BabyManagerService,
    private val babyRepository: BabyRepository,
    private val userQueryService: UserQueryService,
) {
    fun createBaby(userId: UUID, request: BabyDto.Request.Create) {
        val findUser = userQueryService.getUserById(userId)

        val savedBaby = Baby(
            name = request.name,
            profileImg = request.profileImg,
            gender = request.gender,
            birthday = request.birthday,
        ).let { save(it) }

        BabyManager(
            user = findUser,
            baby = savedBaby,
            isAdmin = request.isAdmin,
        ).let { babyManagerService.save(it) }
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

    fun save(newBaby: Baby): Baby {
        return babyRepository.save(newBaby)
    }
}
