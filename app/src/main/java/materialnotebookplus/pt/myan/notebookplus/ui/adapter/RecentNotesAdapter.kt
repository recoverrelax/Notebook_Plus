package materialnotebookplus.pt.myan.notebookplus.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import materialnotebookplus.pt.myan.notebookplus.R
import materialnotebookplus.pt.myan.notebookplus.model.NormalNote
import org.jetbrains.anko.find
import org.jetbrains.anko.layoutInflater

class RecentNotesAdapter(): RecyclerView.Adapter<RecentNotesAdapter.ViewHolder>() {

    val childLayout = R.layout.cardview_normal_note_disabled
    var items: MutableList<NormalNote> = arrayListOf()

    override fun getItemCount() = items.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder?
            = ViewHolder(parent.context.layoutInflater.inflate(childLayout, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setItem(items[position])
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val title : EditText = view.find(R.id.title)
        val message : EditText = view.find(R.id.message)

        fun setItem(item: NormalNote) {
//            itemView?.singleClick { onItemClickListener?.invoke(item) }
            title.setText(item.title)
            message.setText(item.message)
        }
    }
}