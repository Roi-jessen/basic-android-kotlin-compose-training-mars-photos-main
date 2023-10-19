package com.example.marsphotos.model
/**
 * Ini adalah pernyataan impor yang mengimpor pustaka-pustaka yang digunakan untuk serialisasi dan deserialisasi objek dalam Kotlin. Anotasi @Serializable digunakan untuk menandai bahwa kelas ini adalah serializable.
 */
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * This data class defines a Mars photo which includes an ID, and the image URL.
 */
@Serializable
data class MarsPhoto(
    val id: String,
    @SerialName(value = "img_src")
    val imgSrc: String
    /**
     * @SerialName(value = "img_src") val imgSrc: String: Ini adalah properti yang mewakili URL gambar foto Mars. Anotasi @SerialName digunakan untuk menyesuaikan nama yang digunakan dalam serialisasi dengan nama yang diberikan dalam JSON.
     */
)
