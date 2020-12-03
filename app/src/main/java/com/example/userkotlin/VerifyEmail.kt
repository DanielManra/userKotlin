package com.example.userkotlin

import Library.MemoryData
import ViewModels.LoginViewModels
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import android.os.PersistableBundle
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import com.example.userkotlin.databinding.VerifyEmailBinding

class VerifyEmail : AppCompatActivity() {
    private var memoryData: MemoryData? = null
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.verify_email)

        memoryData = MemoryData.getInstance(this)
        if (memoryData!!.getData("user")== ""){
            var binding = DataBindingUtil.setContentView<VerifyEmailBinding>(this,R.layout.verify_email)
            binding.emailModel = LoginViewModels(this, binding,null)
            val window = this.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = this.resources.getColor(R.color.colorBlue, null)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }else{
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }

    }
}