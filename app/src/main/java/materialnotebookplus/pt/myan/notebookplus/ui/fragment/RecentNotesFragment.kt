package materialnotebookplus.pt.myan.notebookplus.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import materialnotebookplus.pt.myan.notebookplus.R
import materialnotebookplus.pt.myan.notebookplus.ui.adapter.RecentNotesAdapter
import materialnotebookplus.pt.myan.notebookplus.ui.base.BaseFragment
import org.jetbrains.anko.support.v4.find

class RecentNotesFragment : BaseFragment(){
    val recyclerView by lazy { find<RecyclerView>(R.id.recyclerView) }
    val layoutManager by lazy{
        LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
    }
    val adapter by lazy{
        RecentNotesAdapter()
    }
    override val fragmentLayout = R.layout.fragment_recent_notes
    override val toolbarTitle = R.string.activity_recent_notes_fragment

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
            = inflater.inflate(fragmentLayout, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

//        recyclerView.let {
//            it.layoutManager = layoutManager
//            it.adapter = adapter
//        }
//        adapter.items = getDummyItems()
    }
}