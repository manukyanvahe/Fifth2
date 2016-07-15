package am.fifth.android.fifth.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import am.fifth.android.fifth.Commands.LoadProfileData;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new LoadProfileData(this);
        //finish();
    }
}
