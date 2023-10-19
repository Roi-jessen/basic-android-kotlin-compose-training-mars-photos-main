package com.example.marsphotos.network
/**
*Ini adalah pernyataan impor yang mengimpor kelas MarsPhoto yang digunakan dalam antarmuka ini, serta anotasi @GET yang digunakan untuk mendeklarasikan metode HTTP yang akan digunakan.
 */
import com.example.marsphotos.model.MarsPhoto
import retrofit2.http.GET

/**
 * A public interface that exposes the [getPhotos] method
 */
interface MarsApiService {
    /**
     * Returns a [List] of [MarsPhoto] and this method can be called from a Coroutine.
     * The @GET annotation indicates that the "photos" endpoint will be requested with the GET
     * HTTP method
     */
    @GET("photos")
    suspend fun getPhotos(): List<MarsPhoto>
    /**
     * Jadi, antarmuka MarsApiService ini adalah bagian penting dari implementasi komunikasi dengan layanan jaringan yang digunakan dalam proyek foto Mars.
     */
}
