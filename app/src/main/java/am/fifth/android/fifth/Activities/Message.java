package am.fifth.android.fifth.Activities;

import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import am.fifth.android.fifth.Commands.LoadMessages;
import am.fifth.android.fifth.R;

public class Message extends AppCompatActivity {

    private static final String TAG = "Message";
    public int threadId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.messages);
        threadId = getIntent().getIntExtra("thread_id",0);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        new LoadMessages(this, threadId);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
