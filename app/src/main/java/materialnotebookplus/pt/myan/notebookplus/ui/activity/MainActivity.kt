package materialnotebookplus.pt.myan.notebookplus.ui.activity

import android.os.Bundle
import materialnotebookplus.pt.myan.notebookplus.R
import materialnotebookplus.pt.myan.notebookplus.ui.base.BaseActivity

class MainActivity : BaseActivity(){
    override val toolbarTitle = null
    override val layoutRes = R.layout.activity_base_notes
    override val hasNavigationView = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}