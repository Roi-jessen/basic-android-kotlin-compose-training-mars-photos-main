package com.example.marsphotos.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.marsphotos.MarsPhotosApplication
import com.example.marsphotos.data.MarsPhotosRepository
import com.example.marsphotos.model.MarsPhoto
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
// Ini adalah bagian impor yang mengimpor berbagai kelas, fungsi, dan properti yang digunakan dalam implementasi ViewModel.

/**
 * UI state for the Home screen
 */
sealed interface MarsUiState {
    data class Success(val photos: List<MarsPhoto>) : MarsUiState
    object Error : MarsUiState
    object Loading : MarsUiState
    // Ini adalah definisi dari antarmuka MarsUiState yang menyatakan berbagai status atau keadaan UI yang mungkin terjadi di layar beranda aplikasi, seperti keberhasilan (Success), kesalahan (Error), atau dalam proses loading (Loading).
}

class MarsViewModel(private val marsPhotosRepository: MarsPhotosRepository) : ViewModel() {
    /** The mutable State that stores the status of the most recent request */
    var marsUiState: MarsUiState by mutableStateOf(MarsUiState.Loading)
        private set
    // Ini adalah properti marsUiState yang digunakan untuk menyimpan status terbaru dari permintaan data. Properti ini menggunakan mutableStateOf untuk mengamati perubahan status dan memperbarui tampilan sesuai dengan perubahan tersebut.

    /**
     * Call getMarsPhotos() on init so we can display status immediately.
     */
    init {
        getMarsPhotos()
      //  Ini adalah blok inisialisasi (init) yang digunakan untuk memanggil getMarsPhotos() saat MarsViewModel diinisialisasi. Ini memastikan bahwa status loading ditampilkan secara langsung saat layar beranda dimuat.
    }

    /**
     * Gets Mars photos information from the Mars API Retrofit service and updates the
     * [MarsPhoto] [List] [MutableList].
     */
    fun getMarsPhotos() {
        viewModelScope.launch {
            marsUiState = MarsUiState.Loading
            marsUiState = try {
                MarsUiState.Success(marsPhotosRepository.getMarsPhotos())
            } catch (e: IOException) {
                MarsUiState.Error
            } catch (e: HttpException) {
                MarsUiState.Error
            }
            // Ini adalah metode getMarsPhotos() yang menginisialisasi permintaan data ke API Mars melalui Retrofit. Metode ini menggunakan viewModelScope.launch untuk menjalankan permintaan secara asinkron. Selanjutnya, metode ini mengatur marsUiState sesuai dengan hasil permintaan, seperti sukses (Success) atau kesalahan (Error).
        }
    }

    /**
     * Factory for [MarsViewModel] that takes [MarsPhotosRepository] as a dependency
     */
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as MarsPhotosApplication)
                val marsPhotosRepository = application.container.marsPhotosRepository
                MarsViewModel(marsPhotosRepository = marsPhotosRepository)
            }
        }
        // Ini adalah blok kode yang mendefinisikan factory (Factory) untuk MarsViewModel. Factory ini digunakan untuk membuat instance MarsViewModel dengan dependensi MarsPhotosRepository.
    }
}
