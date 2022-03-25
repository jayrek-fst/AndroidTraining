package com.fs.jayrek.trainingtask.helper

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.Window
import androidx.databinding.DataBindingUtil
import com.fs.jayrek.trainingtask.R
import com.fs.jayrek.trainingtask.databinding.ProgressDialogBinding

object DialogHelper {
    private var dialog: Dialog? = null

    fun showProgressDialog(message: String, context: Context, cancellable: Boolean) {
        val binding: ProgressDialogBinding = DataBindingUtil
            .inflate(LayoutInflater.from(context), R.layout.progress_dialog, null, false);
        dialog = Dialog(context)
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.setContentView(binding.root)
        dialog!!.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        binding.progress.indeterminateDrawable
        binding.tvMessage.text = message
        dialog!!.setCancelable(cancellable)
        dialog!!.setCanceledOnTouchOutside(cancellable)
        dialog!!.show()
    }

    fun dismissProgressDialog() = dialog!!.dismiss()
}