package materialnotebookplus.pt.myan.notebookplus.model;

import org.parceler.Parcel;

import java.util.Date;

import io.realm.NormalNoteRealmProxy;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

@Parcel(implementations = { NormalNoteRealmProxy.class },
        value = Parcel.Serialization.BEAN,
        analyze = { NormalNote.class })
public class NormalNote extends RealmObject{

    public NormalNote(){

    }

    public long getId_() {
        return id_;
    }

    public void setId_(long id_) {
        this.id_ = id_;
    }

    public NormalNote(String title, String message, Date lastUpdated) {
        this.title = title;
        this.message = message;
        this.lastUpdated = lastUpdated;
    }

    @PrimaryKey
    private long id_ = System.currentTimeMillis();

    @Required
    private String title;

    @Required
    private String message;

    @Required
    private Date creationDate = new Date();

    @Required
    private Date lastUpdated;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
