package am.fifth.android.fifth.Managers;

import android.content.Context;
import android.content.SharedPreferences;

public class TokenManager {

    private static String token;

    public static String getToken(Context context) {
        if (token != null) {
            return token;
        } else {
            return getStoredToken(context);
        }
    }
    public static Boolean hasAccessToken() {
        return token != null;
    }

    public static String getStoredToken(Context context) {
        return context.getSharedPreferences("tokenManager", Context.MODE_PRIVATE).getString("token","");
    }
    public  static void storeToken(Context context, String token){
        TokenManager.token = token;
        context.getSharedPreferences("tokenManager", Context.MODE_PRIVATE)
            .edit()
            .putString("token", token)
            .commit();
    }
}
