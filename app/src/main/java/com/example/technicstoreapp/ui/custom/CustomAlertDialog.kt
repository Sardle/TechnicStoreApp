package com.example.technicstoreapp.ui.custom

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.technicstoreapp.R

class CustomAlertDialog(context: Context) : Dialog(context) {
    private var message: String? = null
    private var backText: String? = null
    private var okText: String? = null
    private var image: Int? = null
    private var isCancelable: Boolean = true
    private var onOkClickListener: DialogInterface.OnClickListener? = null
    private var onBackClickListener: DialogInterface.OnClickListener? = null

    fun setMessage(message: String): CustomAlertDialog {
        this.message = message
        return this
    }

    fun setOnCancelable(isCancelable: Boolean): CustomAlertDialog {
        this.isCancelable = isCancelable
        return this
    }

    fun setBackText(message: String): CustomAlertDialog {
        this.backText = message
        return this
    }

    fun setOkText(message: String): CustomAlertDialog {
        this.okText = message
        return this
    }

    fun setImage(image: Int): CustomAlertDialog {
        this.image = image
        return this
    }

    fun setOnOkClickListener(listener: DialogInterface.OnClickListener): CustomAlertDialog {
        this.onOkClickListener = listener
        return this
    }

    fun setOnBackClickListener(listener: DialogInterface.OnClickListener): CustomAlertDialog {
        this.onBackClickListener = listener
        return this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.custom_dialog_layout)
        setCancelable(isCancelable)

        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        window?.setLayout(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        window?.setBackgroundDrawableResource(R.drawable.bg_custom_dialog)

        findViewById<Button>(R.id.btn_cancel).text = backText
        findViewById<Button>(R.id.btn_okay).text = okText

        findViewById<TextView>(R.id.message_dialog).text = message
        findViewById<ImageView>(R.id.imageView_dialog).setImageResource(image ?: 0)

        findViewById<Button>(R.id.btn_okay).setOnClickListener {
            dismiss()
            onOkClickListener?.onClick(this, BUTTON_POSITIVE)
        }

        findViewById<Button>(R.id.btn_cancel).setOnClickListener {
            dismiss()
            onBackClickListener?.onClick(this, BUTTON_POSITIVE)
        }
    }
}
