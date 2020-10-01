package vn.thailam.challenge1.core.usecase

import io.reactivex.Observable
import vn.thailam.challenge1.core.data.models.ChequeEntity
import vn.thailam.challenge1.core.data.repository.ChequeRepository

class GetChequesUseCase(
    private val chequeRepository: ChequeRepository
) {

    fun execute(): Observable<List<ChequeEntity>> {
        return chequeRepository.getCheques()
            .map { it.filter { item -> item.id != null } }
    }
}
