package com.xh3140.core.extensions

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

fun Context.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

fun Context.toast(@StringRes resId: Int, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, resId, duration).show()
}

fun Activity.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    runOnUiThread { Toast.makeText(this, message, duration).show() }
}

fun Activity.toast(@StringRes resId: Int, duration: Int = Toast.LENGTH_SHORT) {
    runOnUiThread { Toast.makeText(this, resId, duration).show() }
}

fun Fragment.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    requireActivity().runOnUiThread { Toast.makeText(requireContext(), message, duration).show() }
}

fun Fragment.toast(@StringRes resId: Int, duration: Int = Toast.LENGTH_SHORT) {
    requireActivity().runOnUiThread { Toast.makeText(requireContext(), resId, duration).show() }
}
