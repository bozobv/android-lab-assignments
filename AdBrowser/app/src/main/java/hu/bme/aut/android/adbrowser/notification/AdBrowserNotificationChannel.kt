package hu.bme.aut.android.adbrowser.notification

enum class AdBrowserNotificationChannel(
    val id: String,
    val channelName: String,
    val channelDescription: String
) {
    NEW_ENTRIES("hu.bme.aut.adbrowser.newentries", "Új cikkek", "Értesítés új hirdetésekről"),
    PROMO("hu.bme.aut.adbrowser.promo", "Promóció", "Értesítés akcióinkról")
}
