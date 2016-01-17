package materialnotebookplus.pt.myan.notebookplus.util

import android.content.Context
import android.support.v4.content.ContextCompat

fun Context.stringFromRes(stringResId: Int) = resources.getString(stringResId)
fun Context.colorFromRes(colorResId: Int) = ContextCompat.getColor(this, colorResId)