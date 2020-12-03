package com.example.userkotlin.ui.log_out

import Library.MemoryData
import ViewModels.LoginViewModels
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.userkotlin.VerifyEmail
import com.google.firebase.auth.FirebaseAuth

class LogOut : Fragment(){
    private var memoryData: MemoryData? = null
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedIntanceState: Bundle?
    ): View?{
        FirebaseAuth.getInstance().signOut()
        memoryData = MemoryData.getInstance(this.requireContext())
        memoryData!!.saveData("user", "")
        startActivity(
                Intent(requireContext(),VerifyEmail::class.java)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        )
        return null
    }
}