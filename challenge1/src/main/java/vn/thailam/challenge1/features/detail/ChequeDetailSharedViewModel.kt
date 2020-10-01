package vn.thailam.challenge1.features.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ChequeDetailSharedViewModel : ViewModel() {
    val chequeItemId = MutableLiveData<Long>()

    val sharedElementTransitionName = MutableLiveData<String>()

    fun setChequeItemId(id: Long) {
        if (chequeItemId.value != id) {
            chequeItemId.value = id
        }
    }

    fun setSharedElementTransitionName(name: String) {
        sharedElementTransitionName.value = name
    }
}
