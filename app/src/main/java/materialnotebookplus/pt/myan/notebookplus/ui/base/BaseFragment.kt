package materialnotebookplus.pt.myan.notebookplus.ui.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import materialnotebookplus.pt.myan.notebookplus.util.stringFromRes
import org.jetbrains.anko.support.v4.act

abstract class BaseFragment: Fragment(){
    abstract val toolbarTitle: Int?
    abstract val fragmentLayout: Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
            = inflater.inflate(fragmentLayout, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbarTitle()
    }

    private fun setToolbarTitle() {
        toolbarTitle?.let {
            activity.title = act.stringFromRes(it)
        }
    }
}