package vn.thailam.challenge1.features.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import vn.thailam.challenge1.core.usecase.GetChequesUseCase
import vn.thailam.challenge1.features.list.mapper.ChequeListUiModelMapper
import vn.thailam.challenge1.features.list.models.ChequeListUiModel

class ChequeListViewModel(
    private val getChequesUseCase: GetChequesUseCase,
    private val uiModelMapper: ChequeListUiModelMapper
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _chequeUiModel = MutableLiveData<ChequeListUiModel>()
    val chequeUiModel: LiveData<ChequeListUiModel> = _chequeUiModel

    fun getCheques() {
        getChequesUseCase.execute()
            .map { uiModelMapper.mapToUiModel(it) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _chequeUiModel.value = it
            }, {
                Log.e("ChequeListViewModel", "getCheques error $it")
            })
            .addTo(compositeDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}
