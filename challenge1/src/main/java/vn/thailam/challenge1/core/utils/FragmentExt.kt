package vn.thailam.challenge1.core.utils

import android.os.Handler
import androidx.fragment.app.Fragment

fun Fragment.doDelay(duration: Long, action: () -> Unit) {
    Handler().postDelayed({
        if (isAdded) action.invoke()
    }, duration)
}
