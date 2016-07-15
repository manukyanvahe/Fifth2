package am.fifth.android.fifth.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import am.fifth.android.fifth.Activities.Conversation;
import am.fifth.android.fifth.Commands.LoadThreads;
import am.fifth.android.fifth.R;

public class Threads extends Fragment {

    private static final String TAG = "Conversation fragment";
    private View fragmentView ;

    public Threads() {
        // Required empty public constructor
    }

    public static Threads newInstance(String param1, String param2) {
        Threads fragment = new Threads();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.threads, container, false);
        ListView listConversation = (ListView) fragmentView .findViewById(R.id.listConversation);
        listConversation.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> listView, View threadView, int arg2,long arg3) {
                Intent intent = new Intent(getContext(), Conversation.class);
                intent.putExtra("thread_id", threadView.getId());
                startActivity(intent);
                //Log.i(TAG, "Items " + threadView.getId() );
            }

        });

        new LoadThreads(getActivity());
        return fragmentView;
    }

}
