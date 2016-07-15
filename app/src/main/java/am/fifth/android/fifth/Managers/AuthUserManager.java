package am.fifth.android.fifth.Managers;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import am.fifth.android.fifth.Entities.User;

public class AuthUserManager {
    private static User user;

    public static User getUser(){
        return user;
    }

    public static Boolean hasUser(){
        return user != null;
    }

    public static void setUser(String userJson) throws JSONException {
        JSONObject UserJson = new JSONObject(userJson);
        user = new User(UserJson);
    }
}
