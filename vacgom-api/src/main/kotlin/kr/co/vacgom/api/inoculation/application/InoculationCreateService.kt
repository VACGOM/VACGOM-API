package kr.co.vacgom.api.inoculation.application

import kr.co.vacgom.api.inoculation.application.dto.CreateInoculations
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class InoculationCreateService {

    fun createInoculations(request: CreateInoculations.ClientRequest) {

    }
}
