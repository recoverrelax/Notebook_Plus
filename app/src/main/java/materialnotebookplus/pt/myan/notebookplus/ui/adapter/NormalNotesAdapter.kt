package materialnotebookplus.pt.myan.notebookplus.ui.adapter

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import materialnotebookplus.pt.myan.notebookplus.R
import materialnotebookplus.pt.myan.notebookplus.model.NormalNote
import org.jetbrains.anko.find
import org.jetbrains.anko.layoutInflater
import org.jetbrains.anko.onClick

class NormalNotesAdapter(context: Context): RecyclerView.Adapter<NormalNotesAdapter.ViewHolder>() {

    val context = context
    val childLayout = R.layout.cardview_normal_note_disabled
    var items: MutableList<NormalNote> = arrayListOf()
    var onItemClickListener: ((NormalNote) -> Unit)? = null

    override fun getItemCount() = items.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder?
            = ViewHolder(parent.context.layoutInflater.inflate(childLayout, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setItem(items[position])
    }

    fun setAdapterItems(note: List<NormalNote>){
        items.clear()
        items.addAll(note)
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val title : EditText = view.find(R.id.title)
        val message : EditText = view.find(R.id.message)
        val cardview : CardView = view.find(R.id.cardview)

        fun setItem(item: NormalNote) {
            title.setText(item.title)
            message.setText(item.message)
            cardview.onClick {onItemClickListener?.invoke(item)}
        }
    }
}