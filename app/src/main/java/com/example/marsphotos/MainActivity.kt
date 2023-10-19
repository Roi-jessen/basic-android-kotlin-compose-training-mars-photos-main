package com.example.marsphotos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import com.example.marsphotos.ui.MarsPhotosApp
import com.example.marsphotos.ui.theme.MarsPhotosTheme
/** adalah bagian impor yang mengimpor berbagai kelas dan fungsi yang digunakan dalam kelas MainActivity */

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        /** metode onCreate, yang dijalankan saat aktivitas dibuat. Pada metode ini, setDecorFitsSystemWindows digunakan untuk mengatur agar konten aktivitas tidak terlalu dekat dengan batas jendela sistem (system windows) seperti status bar atau navigasi bar. */
        setContent {
            MarsPhotosTheme {
                //  digunakan untuk menerapkan tema yang sesuai pada seluruh tampilan aplikasi.
                Surface(
                    //adalah wadah yang berisi konten aplikasi dan menggunakan warna latar belakang dari tema.
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MarsPhotosApp()
                    // adalah komponen Compose utama yang digunakan untuk menampilkan antarmuka pengguna aplikasi.
                }
            }
        }
    }
}
