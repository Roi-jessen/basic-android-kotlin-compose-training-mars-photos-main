package com.example.marsphotos.data
/**
 * Ini adalah import statement yang mengimpor kelas-kelas dan pustaka yang diperlukan untuk mengimplementasikan Dependency Injection dan menghubungkan aplikasi dengan layanan jaringan menggunakan Retrofit.
 */
import com.example.marsphotos.network.MarsApiService
import retrofit2.Retrofit
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType

/**
 * Dependency Injection container at the application level.
 * Ini adalah definisi dari interface AppContainer. Interface ini memiliki properti marsPhotosRepository yang akan digunakan untuk menyediakan repository Mars Photos ke komponen aplikasi yang membutuhkannya.
 */
interface AppContainer {
    val marsPhotosRepository: MarsPhotosRepository
}

/**
 * Implementation for the Dependency Injection container at the application level.
 *
 * Variables are initialized lazily and the same instance is shared across the whole app.
 * Ini adalah deklarasi kelas DefaultAppContainer yang mengimplementasikan AppContainer. Kelas ini juga mendefinisikan URL dasar untuk layanan Retrofit yang digunakan dalam aplikasi.
 */
class DefaultAppContainer : AppContainer {
    private val baseUrl = "https://android-kotlin-fun-mars-server.appspot.com/"

    /**
     * Use the Retrofit builder to build a retrofit object using a kotlinx.serialization converter
     * Ini adalah implementasi Retrofit yang sebenarnya. Ini menggunakan objek Retrofit.Builder untuk mengonfigurasi Retrofit dengan converter Kotlin Serialization untuk menangani respons JSON.
     */
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    /**
     * Retrofit service object for creating api calls
     * Ini adalah inisialisasi yang malas (lazy initialization) dari retrofitService. Objek ini dibuat saat pertama kali diperlukan dan digunakan untuk berkomunikasi dengan layanan Mars API.
     */
    private val retrofitService: MarsApiService by lazy {
        retrofit.create(MarsApiService::class.java)
    }

    /**
     * DI implementation for Mars photos repository
     * Ini adalah inisialisasi yang malas dari properti marsPhotosRepository. Ini menggunakan implementasi NetworkMarsPhotosRepository dengan layanan Retrofit yang telah disiapkan sebelumnya sebagai dependensinya.
     */
    override val marsPhotosRepository: MarsPhotosRepository by lazy {
        NetworkMarsPhotosRepository(retrofitService)
    }
}
