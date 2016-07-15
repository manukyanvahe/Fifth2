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
import am.fifth.android.fifth.Entities.User;
import am.fifth.android.fifth.Managers.AuthUserManager;
import am.fifth.android.fifth.Managers.TokenManager;
import am.fifth.android.fifth.R;

public class Login {

    Context context;
    private static final String TAG = "Login Commmand";
    private static String Url = "login";

    public Login(final Context context) {

        this.context = context;
        StringRequest LoginRequest = new StringRequest(
                Request.Method.POST,
                context.getResources().getString(R.string.api_url) + Url,
                 new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonLoginResponse = new JSONObject(response);

                            if (jsonLoginResponse.has("error")){
                                String errorMessage = "";

                                JSONArray errors = jsonLoginResponse.getJSONArray("error");

                                for (int i = 0; i < errors.length(); i++) {
                                    Log.i(TAG, errors.get(0) + "");
                                    errorMessage = errorMessage + '\n' + errors.get(i);
                                }
                                //mTextView.setText(errorMessage);
                            }
                            else{
                                //loginUser(response);
                                storeToken(response);
                                new LoadProfileData(context);
                            }
                        } catch (JSONException e) {
                            Log.e("MYAPP", "unexpected JSON exception", e);
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
                params.put("email", "manukyanvahe@gmail.com");
                params.put("password", "asdzxc");
                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(LoginRequest);
    }

    private void storeToken(String response) throws JSONException {
        JSONObject UserJson = new JSONObject(response);
        TokenManager.storeToken(context,UserJson.getJSONObject("profile_data").getString("api_token"));
        redirectToMain();
    }

    private void redirectToMain() {
        Intent intent = new Intent(context, Main.class);
        context.startActivity(intent);
    }

}
