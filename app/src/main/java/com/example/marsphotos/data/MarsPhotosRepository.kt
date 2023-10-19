package com.example.marsphotos.data

import com.example.marsphotos.model.MarsPhoto
import com.example.marsphotos.network.MarsApiService
// adalah pernyataan impor yang mengimpor kelas MarsPhoto dan MarsApiService yang diperlukan dalam implementasi repositori.

/**
 * Repository that fetch mars photos list from marsApi.
 */
interface MarsPhotosRepository {
    /** Fetches list of MarsPhoto from marsApi */
    suspend fun getMarsPhotos(): List<MarsPhoto>
    // Komentar di atas deklarasi memberikan penjelasan tentang tujuan antarmuka ini, yaitu untuk mengambil daftar foto Mars dari Mars API.
}

/**
 * Network Implementation of Repository that fetch mars photos list from marsApi.
 */
class NetworkMarsPhotosRepository(
    private val marsApiService: MarsApiService
) : MarsPhotosRepository {
    //  Kelas ini menerima marsApiService sebagai dependensi yang digunakan untuk melakukan permintaan ke Mars API.
    /** Fetches list of MarsPhoto from marsApi*/
    override suspend fun getMarsPhotos(): List<MarsPhoto> = marsApiService.getPhotos()
    // adalah implementasi metode getMarsPhotos() yang mendefinisikan bagaimana daftar foto Mars diambil dari Mars API.
}
