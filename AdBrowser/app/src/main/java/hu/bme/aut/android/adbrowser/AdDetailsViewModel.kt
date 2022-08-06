package hu.bme.aut.android.adbrowser

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hu.bme.aut.android.adbrowser.model.Ad

class AdDetailsViewModel : ViewModel() {
    
    fun getById(id: Long) =
        AdBrowserApplication.adRepository.getById(id).let { MutableLiveData<Ad>(it) }
}
