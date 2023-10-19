package com.example.marsphotos

/**
 * Ini adalah pernyataan impor yang mengimpor kelas Application dari pustaka Android, serta kelas AppContainer dan DefaultAppContainer yang digunakan dalam aplikasi.
 */
import android.app.Application
import com.example.marsphotos.data.AppContainer
import com.example.marsphotos.data.DefaultAppContainer

class MarsPhotosApplication : Application() {
    lateinit var container: AppContainer
    /** Ini adalah deklarasi properti container yang menggunakan kata kunci lateinit, yang berarti bahwa properti ini akan diinisialisasi nanti saat aplikasi dimulai, sebelum properti ini digunakan. Properti ini akan menampung instance dari AppContainer */
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
        /**diinisialisasi dengan instance dari DefaultAppContainer. Dengan cara ini, container Dependency Injection akan tersedia untuk kelas-kelas lain dalam aplikasi */
    }
}
