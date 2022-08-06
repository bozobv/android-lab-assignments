package hu.bme.aut.android.adbrowser.repository

import androidx.lifecycle.MutableLiveData
import hu.bme.aut.android.adbrowser.model.Ad

class AdRepository {
    private val ads = mutableListOf(
        Ad(
            1,
            "Extol Körfűrészlap",
            "125mm-es, bontatlan csomagolás. Azért adom el, mert rossz típust vásároltam.",
            "Gyöngyös",
            2500
        ),
        Ad(
            2,
            "Vízforgató kerti medencéhez",
            "Félcolos nyílásra csatlakoztatható",
            "Debrecen",
            12000
        ),

        Ad(
            3,
            "Extol Körfűrészlap",
            "125mm-es, bontatlan csomagolás. Azért adom el, mert rossz típust vásároltam.",
            "Gyöngyös",
            2500
        ),

        Ad(
            4,
            "Pendrive 6GB",
            "Hibátlan, USB2-es.",
            "Gödöllő",
            3700
        )
    )

    fun getAll() = MutableLiveData(ads)

    fun getById(id: Long) = ads.filter { it.id == id }.getOrNull(0).also { MutableLiveData(it) }

    fun add(ad: Ad) {
        ads.add(ad)
    }
}
