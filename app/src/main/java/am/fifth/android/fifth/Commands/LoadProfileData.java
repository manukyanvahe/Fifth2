package am.fifth.android.fifth.Commands;


import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import am.fifth.android.fifth.Activities.Main;
import am.fifth.android.fifth.Managers.AuthUserManager;
import am.fifth.android.fifth.Managers.TokenManager;
import am.fifth.android.fifth.R;

public class LoadProfileData {

    Context context;
    private static String Url = "profile";
    private static final String TAG = "LoadProfileData";

    public LoadProfileData(final Context context) {

        this.context = context;
        final String apiToken = TokenManager.getToken(context);
        if (apiToken.isEmpty()){
            new Login(context);
            return;
        }
        StringRequest LoginRequest = new StringRequest(
                Request.Method.POST,
                context.getResources().getString(R.string.api_url) + Url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("Unauthorized")){
                            new Login(context);
                            Log.i(TAG, "Unauthorized");
                        }else{
                            try {
                                Log.i(TAG, response.toString());
                                AuthUserManager.setUser(response);
                                redirectToMain();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            //fillProfileData(response);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "unexpected JSON exception", error);
                    }
                }){
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> params = new HashMap<String, String>();
                params.put("api_token", apiToken);
                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(LoginRequest);
    }
    private void redirectToMain() {
        Intent intent = new Intent(context, Main.class);
        context.startActivity(intent);
    }
}