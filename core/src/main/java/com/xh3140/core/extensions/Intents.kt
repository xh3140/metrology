package com.xh3140.core.extensions

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment

inline fun <reified T : Activity> Activity.startActivity() {
    startActivity(Intent(this, T::class.java))
}

inline fun <reified T : Activity> Activity.startActivity(bundle: Bundle) {
    startActivity(Intent(this, T::class.java).apply { putExtras(bundle) })
}

inline fun <reified T : Activity> Fragment.startActivity() {
    startActivity(Intent(requireActivity(), T::class.java))
}

inline fun <reified T : Activity> Fragment.startActivity(bundle: Bundle) {
    startActivity(Intent(requireActivity(), T::class.java).apply { putExtras(bundle) })
}