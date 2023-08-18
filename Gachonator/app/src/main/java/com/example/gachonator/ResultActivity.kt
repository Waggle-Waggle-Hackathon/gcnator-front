package com.example.gachonator

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.FileProvider
import com.example.gachonator.databinding.ActivityResultBinding
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream

class ResultActivity : AppCompatActivity() {
    lateinit var binding: ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.shareBtnTv.setOnClickListener {
            val screenshot = takeScreenshotOfRootView(binding.root)
            shareImage(screenshot)
        }
    }
    private fun takeScreenshotOfRootView(view: View): Uri {
        val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        view.draw(canvas)

        val imagePath = File(this.cacheDir, "screenshot.png")
        val fos = FileOutputStream(imagePath)
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos)
        fos.flush()
        fos.close()

        return FileProvider.getUriForFile(this, "${this.packageName}.provider", imagePath)
    }

    private fun shareImage(uri: Uri) {
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_STREAM, uri)
            type = "image/png"
            flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
        }
        startActivity(Intent.createChooser(shareIntent, "이미지 공유하기"))
    }
}