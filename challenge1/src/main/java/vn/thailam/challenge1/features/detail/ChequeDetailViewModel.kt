package vn.thailam.challenge1.features.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import vn.thailam.challenge1.core.usecase.GetChequeByIdUseCase
import vn.thailam.challenge1.features.detail.mapper.ChequeDetailUiModelMapper
import vn.thailam.challenge1.features.detail.models.ChequeDetailUiModel
import vn.thailam.challenge1.features.detail.models.ChequeItemUiModel

class ChequeDetailViewModel(
    private val getChequeByIdUseCase: GetChequeByIdUseCase,
    private val uiModelMapper: ChequeDetailUiModelMapper
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _chequeUiModel = MutableLiveData<ChequeDetailUiModel>()
    val chequeUiModel: LiveData<ChequeDetailUiModel> = _chequeUiModel

    private val _onTransitionComplete = MutableLiveData<Boolean>()
    val onTransitionComplete: LiveData<Boolean> = _onTransitionComplete

    private val _chequeItems = MediatorLiveData<List<ChequeItemUiModel>>().apply {
        addSource(_chequeUiModel) {
            if (_onTransitionComplete.value == true) {
                value = it.items
            }
        }

        addSource(_onTransitionComplete) {
            val currentItems = _chequeUiModel.value?.items
            if (it && currentItems.isNullOrEmpty().not()) {
                value = currentItems
            }
        }
    }
    val chequeItems: LiveData<List<ChequeItemUiModel>> = _chequeItems

    fun getChequeById(id: Long) {
        val input = GetChequeByIdUseCase.Input(chequeId = id)
        getChequeByIdUseCase.execute(input)
            .map { uiModelMapper.mapToUiModel(it) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _chequeUiModel.value = it
            }, {
                // Log error
            })
            .addTo(compositeDisposable)
    }

    fun setTransitionCompleted() {
        if (_onTransitionComplete.value != true) {
            _onTransitionComplete.value = true
        }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}
