package materialnotebookplus.pt.myan.notebookplus.util

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity

fun FragmentActivity.initFragment(
        containerId: Int,
        fragment: Fragment){

        supportFragmentManager.beginTransaction()
                .replace(
                        containerId,
                        fragment
                ).commit()

}