package vn.thailam.challenge1.core.data.repository

import io.reactivex.Observable
import vn.thailam.challenge1.core.annotation.ChequeStatus.Companion.CHECKED
import vn.thailam.challenge1.core.annotation.ChequeStatus.Companion.COMPLAINT
import vn.thailam.challenge1.core.annotation.ChequeStatus.Companion.PENDING
import vn.thailam.challenge1.core.annotation.ChequeStatus.Companion.UNCHECKED
import vn.thailam.challenge1.core.data.models.ChequeEntity
import vn.thailam.challenge1.core.data.models.ChequeItemEntity

class ChequeRepositoryImpl : ChequeRepository {

    private val cheques by lazy { generateFakeCheques() }


    override fun getCheques(): Observable<List<ChequeEntity>> {
        return Observable.fromCallable {
            cheques
        }
    }

    override fun getChequeById(id: Long): Observable<ChequeEntity> {
        return Observable.fromCallable {
            cheques.firstOrNull { it.id == id }
        }
    }

    private fun generateFakeCheques(): List<ChequeEntity> {
        val list = mutableListOf<ChequeEntity>()
        for (i in 0 until 20) {
            list.add(
                ChequeEntity(
                    id = i.toLong(),
                    name = "Item for $i",
                    amount = "15,000",
                    iconUrl = "https://robohash.org/$i",
                    status = getRandomChequeStatus(),
                    unixDate = System.currentTimeMillis(),
                    items = listOf(
                        ChequeItemEntity(
                            id = i.toLong(),
                            name = "Leather Jacket <<Blue Ivy>>",
                            quantity = 5,
                            amountPerItem = "200",
                            totalAmount = "1000"
                        ),
                        ChequeItemEntity(
                            id = i.toLong() + 2L,
                            name = "Lingerie <<Victoria Secret>>",
                            quantity = 15,
                            amountPerItem = "2200",
                            totalAmount = "10400"
                        ),
                        ChequeItemEntity(
                            id = i.toLong() + 3L,
                            name = "Jimmy Choos",
                            quantity = 2,
                            amountPerItem = "5700",
                            totalAmount = "10200"
                        ),
                        ChequeItemEntity(
                            id = i.toLong() + 4L,
                            name = "JX2400 Trackers <<Nike>>",
                            quantity = 1,
                            amountPerItem = "620",
                            totalAmount = "3306"
                        )
                    )
                )
            )
        }
        return list
    }

    private fun getRandomChequeStatus(): String {
        val list = listOf(
            UNCHECKED,
            CHECKED,
            COMPLAINT,
            PENDING
        )

        return list.random()
    }
}
