package materialnotebookplus.pt.myan.notebookplus.ui.activity

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.widget.EditText
import com.jakewharton.rxbinding.widget.RxTextView
import materialnotebookplus.pt.myan.notebookplus.R
import materialnotebookplus.pt.myan.notebookplus.Storage.RealmDb
import materialnotebookplus.pt.myan.notebookplus.model.NormalNote
import materialnotebookplus.pt.myan.notebookplus.ui.base.BaseActivity
import materialnotebookplus.pt.myan.notebookplus.util.setGone
import materialnotebookplus.pt.myan.notebookplus.util.stringFromRes
import org.jetbrains.anko.find
import org.jetbrains.anko.onClick
import org.parceler.Parcels
import rx.Observable
import java.util.*

class AddNoteActivity: BaseActivity() {

    companion object {
        val INTENT_EDIT_NORMAL_NOTE = "Intent.Edit.NormalNote"
    }

    override val layoutRes = R.layout.activity_add_note
    override val hasNavigationView = false
    override val toolbarTitle = R.string.activity_add_note

    val fabAdd by lazy { find<FloatingActionButton>(R.id.fabAdd) }
    val title by lazy { find<EditText>(R.id.title) }
    val note by lazy { find<EditText>(R.id.note) }

    var normalNote: NormalNote? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            normalNote = Parcels.unwrap(intent?.extras?.getParcelable(INTENT_EDIT_NORMAL_NOTE))

            normalNote?.let {
                normalNote = it
                title.setText(it.title)
                note.setText(it.message)
                setTitle(stringFromRes(R.string.activity_edit_note))
            }
        }

        configFabInteractions()
    }

    private fun configFabInteractions() {
        fabAdd.setGone()

        val titleTextChanged = RxTextView.textChangeEvents(title)
                .map {
                    it.text().toString().trim().length > 0
                }
        val noteTextChanged = RxTextView.textChangeEvents(note)
                .map {
                    it.text().toString().trim().length > 0
                }

        Observable.combineLatest(
                titleTextChanged,
                noteTextChanged, { t1, t2 -> t1 && t2 }
        ).subscribe {
            if (it)
                fabAdd.show()
            else
                fabAdd.hide()
        }

        fabAdd.onClick {
            onSave()
            finish()
        }
    }

    private fun onSave() {

        if (normalNote == null)
            normalNote = NormalNote()

            normalNote!!.setLastUpdated(Date())
            normalNote!!.setTitle(title.text.toString())
            normalNote!!.setMessage(note.text.toString())

            RealmDb.addOrUpdateNormalNote(normalNote!!)
    }
}
