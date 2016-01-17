package materialnotebookplus.pt.myan.notebookplus.Storage

import io.realm.Realm
import io.realm.RealmResults
import io.realm.Sort
import materialnotebookplus.pt.myan.notebookplus.MainApplication
import materialnotebookplus.pt.myan.notebookplus.model.NormalNote
import rx.Observable

object RealmDb{

    private val realmInstance by lazy{ Realm.getInstance(MainApplication.instance) }

    private fun wrapInTransaction(code:() -> Unit){
        realmInstance.beginTransaction();
        code.invoke()
        realmInstance.commitTransaction();
    }


    fun addOrUpdateNormalNote(note: NormalNote){
        wrapInTransaction {
            realmInstance.copyToRealmOrUpdate(note)
        }
    }

    fun getNormalNotes(): Observable<RealmResults<NormalNote>> {
        return realmInstance.where(NormalNote::class.java)
                .findAllSorted("lastUpdated", Sort.DESCENDING)
                .asObservable()
    }

    fun getNormalNote(noteId: String): NormalNote{
        return realmInstance.where(NormalNote::class.java)
                .equalTo("title", noteId)
                .findFirst()
    }



}