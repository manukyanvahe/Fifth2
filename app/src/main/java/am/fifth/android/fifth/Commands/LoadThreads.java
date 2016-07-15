package am.fifth.android.fifth.Commands;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import am.fifth.android.fifth.Activities.Main;
import am.fifth.android.fifth.Adapters.ThreadAdapter;
import am.fifth.android.fifth.Entities.Thread;
import am.fifth.android.fifth.Managers.AuthUserManager;
import am.fifth.android.fifth.Managers.TokenManager;
import am.fifth.android.fifth.R;

public class LoadThreads {

    Context context;
    private static String Url = "chat";
    private static final String TAG = "LoadThreads";

    public LoadThreads(final Context context) {

        final String apiToken = TokenManager.getToken(context);

        this.context = context;
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
                        }else{
                            try {
                                JSONArray threadsJsonArray = new JSONArray(response);
                                List<Thread> threadList = new ArrayList<>();
                                for (int i=0; i<threadsJsonArray.length(); i++){
                                    Thread thread = new Thread(threadsJsonArray.getJSONObject(i));
                                    threadList.add(thread);
                                }
                                ThreadAdapter threadAdapter = new ThreadAdapter(context, R.layout.thread,threadList);
                                ListView listConversation = (ListView) ((Activity)context).findViewById(R.id.listConversation);
                                listConversation.setAdapter(threadAdapter);
//                                ArrayAdapter<String> threadsAdapter =
//                                        new ArrayAdapter<String>(context, R.layout.thread, nameList);
//                                ListView listConversation = (ListView) ((Activity)context).findViewById(R.id.listConversation);
//                                listConversation.setAdapter(threadsAdapter);
                                //Log.i(TAG, listConversation.toString());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

//                            try {
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }

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