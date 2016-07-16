package am.fifth.android.fifth.Entities;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class Message {

    private static final String TAG = "Message";

    public int id;
    public String body;
    public String senderImageUrl;

    public Message(JSONObject messageJson) throws JSONException {
        Log.i(TAG, messageJson.toString());
    }

    public String created_at;
}
