package materialnotebookplus.pt.myan.notebookplus.ui.fragment

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import materialnotebookplus.pt.myan.notebookplus.R
import materialnotebookplus.pt.myan.notebookplus.Storage.RealmDb
import materialnotebookplus.pt.myan.notebookplus.ui.activity.AddNoteActivity
import materialnotebookplus.pt.myan.notebookplus.ui.adapter.NormalNotesAdapter
import materialnotebookplus.pt.myan.notebookplus.ui.base.BaseFragment
import materialnotebookplus.pt.myan.notebookplus.util.hideFabOnScroll
import org.jetbrains.anko.onClick
import org.jetbrains.anko.singleTop
import org.jetbrains.anko.support.v4.act
import org.jetbrains.anko.support.v4.find
import org.jetbrains.anko.support.v4.intentFor
import org.parceler.Parcels

class NormalNotesFragment: BaseFragment(){

    val ITEM_ADDED_CODE = 123
    val recyclerView by lazy { find<RecyclerView>(R.id.recyclerView) }
    val fabAdd by lazy { find<FloatingActionButton>(R.id.fabAdd) }

    val layoutManager by lazy{
        LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
    }
    val adapter by lazy{
        NormalNotesAdapter(act)
    }
    override val toolbarTitle = R.string.activity_normal_notes_fragment
    override val fragmentLayout = R.layout.fragment_normal_notes

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        configRecyclerView()

        fabAdd.onClick {
            startActivityForResult(
                    intentFor<AddNoteActivity>()
                        .singleTop()
            ,ITEM_ADDED_CODE
            )
        }

        adapter.onItemClickListener = { note ->
            startActivity(
                    intentFor<AddNoteActivity>()
                            .singleTop()
                            .putExtra(AddNoteActivity.INTENT_EDIT_NORMAL_NOTE, Parcels.wrap(note))
            )
        }

        RealmDb.getNormalNotes()
                .subscribe({
                    adapter.setAdapterItems(it)
                })
    }

    override fun onResume() {
        super.onResume()


    }

    private fun configRecyclerView() {
        recyclerView.let {
            it.layoutManager = layoutManager
            it.adapter = adapter

            it.hideFabOnScroll(fabAdd)
        }
    }
}