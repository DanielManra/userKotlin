package Library

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.widget.Toast

class Multimedia (private val _activity: Activity){
    private var mCurrentPhotoPath: String? = null
    private var photoURI: Uri? = null

    companion object {
        private const val RESQUEST_CODE_CROP_IMAGE = 1
        const val REQUEST_CODE_TAKE_PHOTO = 0
        private const val TEMP_PHOYO_FILE  = "temporary_img.png"
    }

    fun dispatchTakePictureIntent(){
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val values = ContentValues()
        photoURI = _activity.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values
        )
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
        _activity.startActivityForResult(
            takePictureIntent,
            REQUEST_CODE_TAKE_PHOTO
        )
    }

    fun cropCapturedImage(action: Int){
        var cropIntent: Intent? = null
        when (action){
            0 ->{
                cropIntent = Intent("com.android.camera.action.CROP")
                cropIntent.setDataAndType(photoURI, "image/*")
                //Resolucion de la foto
                cropIntent.putExtra("outputX", 400)
                cropIntent.putExtra("outputY", 250)
            }
        }
        val list =
                _activity.packageManager.queryIntentActivities(cropIntent!!, 0)
        if (0 == list.size){
            Toast.makeText(_activity, "No cuentas con la funcion de recortar imagenes", Toast.LENGTH_SHORT).show()
        }else {
            //se habilita el crop con un intent
            cropIntent!!.putExtra("crop","true")
            cropIntent.putExtra("aspetX",1)
            cropIntent.putExtra("aspetY",1)
            cropIntent.putExtra("outputFormat", Bitmap.CompressFormat.PNG)
            cropIntent.putExtra("scale", true)
            //True retornara la foto como un Bitmap, false retornara la URL de la imagen la guardada.
            cropIntent.putExtra("return_data", true)
            //se inicia activity y se le pasa un odigo de respuesta
            _activity.startActivityForResult(cropIntent, RESQUEST_CODE_CROP_IMAGE)

        }
    }

}