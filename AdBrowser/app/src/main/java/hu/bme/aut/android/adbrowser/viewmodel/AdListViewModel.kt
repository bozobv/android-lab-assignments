package hu.bme.aut.android.adbrowser.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hu.bme.aut.android.adbrowser.AdBrowserApplication
import hu.bme.aut.android.adbrowser.model.Ad
import hu.bme.aut.android.adbrowser.repository.AdRepository

class AdListViewModel : ViewModel() {
    private val adRepository: AdRepository
    val adList: MutableLiveData<MutableList<Ad>>


    init {
        adRepository = AdBrowserApplication.adRepository
        adList = adRepository.getAll()
    }

    fun add(ad: Ad) {
        adRepository.add(ad)
    }
}
