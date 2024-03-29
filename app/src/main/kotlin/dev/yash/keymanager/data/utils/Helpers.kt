package dev.yash.keymanager.utils

import android.content.ClipData
import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.ActionBar

object Helpers {
    fun resetActionBar(context: Context, actionBar: ActionBar?) {
        actionBar?.show()
        actionBar?.setDisplayHomeAsUpEnabled(false)
        actionBar?.setHomeButtonEnabled(false)
        actionBar?.title = context.getAppName()
    }

    private fun Context.getAppName(): String = applicationInfo.loadLabel(packageManager).toString()

    fun copyToClipboard(context: Context, label: String, text: String) {
        val clipboard =
            context.getSystemService(Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
        val clip = ClipData.newPlainText(label, text)
        clipboard.setPrimaryClip(clip)
        Toast.makeText(context, "Copied key to clipboard", Toast.LENGTH_SHORT).show()
    }

    fun exceptionHandler(code: Int): String {
        return when (code) {
            422 -> "Validation failed! Please check entered key."
            304 -> "Not Modified"
            401 -> "Unauthorized to process this request."
            403 -> "Forbidden"
            404 -> "Resource Not Found"
            else -> "Something went wrong!"
        }
    }

    fun encryptionType(key: String): String {
        return when {
            key.contains("ssh-dsa") -> "dsa"
            key.contains("ssh-ecdsa") -> "ecdsa"
            key.contains("ssh-ed25519") -> "ed25519"
            key.contains("ssh-ecdsa-sk") -> "ecdsa-sk"
            key.contains("ssh-ed25519-sk") -> "ed25519-sk"
            else -> "rsa"
        }.uppercase()
    }
}