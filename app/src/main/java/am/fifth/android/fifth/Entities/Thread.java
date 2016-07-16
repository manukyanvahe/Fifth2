package am.fifth.android.fifth.Entities;

import android.provider.ContactsContract;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Thread {
    private static final String TAG = "THREAD";
    public int id;
    public int type;
    public int unreadCount;
    public String name;

    public String pictureUrl;
    public String lastMessage;
    public String lastSeen;
    public Boolean isAttachment;
    public Boolean isReceived;

    public Thread(JSONObject threadJson) throws JSONException {
        //Log.i(TAG, threadJson.toString());
        setId(threadJson.getInt("id"));
        setType(threadJson.getInt("type"));
        setUnreadCount(threadJson.getInt("unread_count"));
        setName(threadJson.getString("name"));
        setPictureUrl(threadJson.getString("picture"));
        setLastMessage(threadJson.getString("last_message"));
        setLastSeen(threadJson.getString("date"));
        setIsAttachment(threadJson.getInt("is_attachment"));
        setIsReceived(threadJson.getInt("send_type"));
    }

    public Boolean getIsReceived() {
        return isReceived;
    }

    public void setIsReceived(int isReceived) {
        if (isReceived == 1) {
            this.isReceived = false;
        }else{
            this.isReceived = true;
        }
    }

    public Boolean getIsAttachment() {
        return isAttachment;
    }

    public void setIsAttachment(int attachment) {
        if (attachment == 0){
            isAttachment = false;
        }else{
            isAttachment = true;
        }
    }


    public String getLastSeen() throws ParseException {
        return new SimpleDateFormat("HH:mm").format( new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(lastSeen));
    }

    public void setLastSeen(String lastSeen) {
        this.lastSeen = lastSeen;
    }


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getUnreadCount() {
        return unreadCount ;
    }

    public void setUnreadCount(int unreadCount ) {
        this.unreadCount = unreadCount ;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
