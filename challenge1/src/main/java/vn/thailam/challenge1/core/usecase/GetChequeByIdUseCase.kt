package vn.thailam.challenge1.core.usecase

import io.reactivex.Observable
import vn.thailam.challenge1.core.data.models.ChequeEntity
import vn.thailam.challenge1.core.data.repository.ChequeRepository

class GetChequeByIdUseCase(
    private val chequeRepository: ChequeRepository
) {

    fun execute(input: Input): Observable<ChequeEntity> {
        return chequeRepository.getChequeById(input.chequeId)
    }

    data class Input(val chequeId: Long)
}
