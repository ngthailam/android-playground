package vn.thailam.challenge1.core.data.repository

import io.reactivex.Observable
import vn.thailam.challenge1.core.data.models.ChequeEntity

interface ChequeRepository {

    fun getCheques(): Observable<List<ChequeEntity>>

    fun getChequeById(id: Long): Observable<ChequeEntity>
}
