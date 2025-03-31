package com.example.workoutpal

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.workoutpal.databinding.ActivityPushUpBinding
import com.example.workoutpal.databinding.DialogCustomBackConfirmationBinding
import com.example.workoutpal.databinding.DialogCustomCompletitionBinding

class PushUpActivity : AppCompatActivity() {

    private var binding: ActivityPushUpBinding? =null
    private var counter = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPushUpBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setSupportActionBar(binding?.toolbarPushUpActivity)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = "Push Up Counter"
        }

        binding?.toolbarPushUpActivity?.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        binding?.btnIncrement?.setOnClickListener {
            counter++
            binding?.tvCounter?.text = "$counter"
        }

        binding?.btnDone?.setOnClickListener {
            showCompletionDialog()
        }
    }

    private fun showCompletionDialog() {
        val customDialog = Dialog(this)
        val dialogBinding = DialogCustomCompletitionBinding.inflate(layoutInflater)
        dialogBinding.pushupindicator.text = counter.toString()
        customDialog.setContentView(dialogBinding.root)
        customDialog.setCancelable(false)

        dialogBinding.btnYes.setOnClickListener {
            this@PushUpActivity.finish()
            customDialog.dismiss()
        }
        dialogBinding.btnNo.setOnClickListener {
            customDialog.dismiss()
        }
        customDialog.show()
    }

}