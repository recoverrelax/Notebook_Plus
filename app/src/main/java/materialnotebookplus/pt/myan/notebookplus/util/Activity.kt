package materialnotebookplus.pt.myan.notebookplus.util

import android.app.Activity
import android.util.TypedValue

fun Activity.getActionBarHeight(): Int?{
    val tv = TypedValue();
    if (theme.resolveAttribute(android.R.attr.actionBarSize, tv, true))
    {
        return TypedValue.complexToDimensionPixelSize(tv.data, resources.displayMetrics);
    }else{
        return null;
    }
}