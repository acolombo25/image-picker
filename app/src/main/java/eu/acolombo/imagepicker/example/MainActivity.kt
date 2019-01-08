package eu.acolombo.imagepicker.example

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.yalantis.ucrop.UCrop
import eu.acolombo.imagepicker.ImagePicker
import eu.acolombo.imagepicker.ImagePickerListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var imagePicker: ImagePicker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val uCropOptions = UCrop.Options().apply { setFreeStyleCropEnabled(true) }

        imagePicker = ImagePicker(this, object : ImagePickerListener {
            override fun onImagePicked(imageUri: Uri) {
                imagePicked.load(imageUri)
            }
        }).setDefaultCropConfiguration(uCropOptions)

        buttonCamera.setOnClickListener {
            imagePicker.showCameraPicker()
        }

        buttonGallery.setOnClickListener {
            imagePicker.showGalleryPicker()
        }

        buttonFile.setOnClickListener {
            imagePicker.showFilePicker()
        }

        buttonCameraPicker.setOnClickListener {
            imagePicker.showGenericPicker("Select image", true, false)
        }

        buttonGalleryPicker.setOnClickListener {
            imagePicker.showGenericPicker("Select image", false, false)
        }

        buttonFilePicker.setOnClickListener {
            imagePicker.showGenericPicker("Select file", true, true)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        imagePicker.handleActivityResult(resultCode, requestCode, data)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        imagePicker.handlePermission(requestCode, grantResults)
    }

}
