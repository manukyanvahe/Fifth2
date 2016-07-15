package am.fifth.android.fifth.Entities;


import android.graphics.Bitmap;
import android.nfc.Tag;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class User {
    private static final String TAG = "User Entity";

    int id;
    String name;
    String email;
    String profileUrl;
    String todayHours;
    int todayProgress;
    String weeklyHours;
    int weeklyProgress;
    String isWorking;
    JSONObject profileData;

    public User(JSONObject UserJson) throws JSONException {
        JSONObject profile_data = UserJson;
        Log.i(TAG, profile_data.toString() );
        setProfileData(profile_data);
        setId(profile_data.getInt("id"));
        setEmail(profile_data.getString("email"));
        setName(profile_data.getString("first_name"));
        setIsWorking(profile_data.getString("is_working"));
        setPhotoUrl(profile_data.getString("profile_picture"));
        setTodayHours(profile_data.getString("today"));
        setTodayProgress(profile_data.getInt("today_progress"));
        setWeeklyHours(profile_data.getString("weekly"));
        setWeeklyProgress(profile_data.getInt("weekly_progress"));


    }
    public void setProfileData(JSONObject profile_data){
        this.profileData = profile_data;
    }
    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getEmail(){
        return this.email;
    }
    public void setIsWorking(String isWorking){
        this.isWorking = isWorking;
    }
    public Boolean getIsWorking(){
        return this.isWorking.equals("true");
    }

    public void setPhotoUrl(String profileUrl){
        this.profileUrl = profileUrl;
    }
    public String getPhotoUrl(){
        return this.profileUrl;
    }

    public void setTodayHours(String todayHours) {
        this.todayHours = todayHours;
    }
    public String getTodayHours(){ return this.todayHours; }

    public void setWeeklyHours(String weeklyHours) {
        this.weeklyHours = weeklyHours;
    }
    public String getWeeklyHours(){ return this.weeklyHours; }

    public void setTodayProgress(int todayProgress) {
        this.todayProgress = todayProgress;
    }
    public int getTodayProgress(){ return this.todayProgress; }

    public void setWeeklyProgress(int weeklyProgress) {
        this.weeklyProgress = weeklyProgress;
    }
    public int getWeeklyProgress(){ return this.weeklyProgress; }

}
