package am.fifth.android.fifth.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageSize;

import java.text.ParseException;
import java.util.List;

import am.fifth.android.fifth.Entities.Thread;
import am.fifth.android.fifth.R;

public class MessageAdapter extends ArrayAdapter {

    public LayoutInflater inflater;
    public List<Thread> threadsList;

    public MessageAdapter(Context context, int resource, List<Message> messageList) {
        super(context, resource, messageList);
        this.threadsList = threadsList;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        if (convertView == null){
            convertView = inflater.inflate(R.layout.thread, null);

            holder = new ViewHolder();

            holder.image = (ImageView) convertView.findViewById(R.id.image);
            holder.type_icon = (ImageView) convertView.findViewById(R.id.type_icon);
            holder.message = (TextView) convertView.findViewById(R.id.message);
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.time = (TextView) convertView.findViewById(R.id.time);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        convertView.setId(threadsList.get(position).getId());

        String name  = threadsList.get(position).getName();
        if (threadsList.get(position).getUnreadCount() > 0){
            name = name+" ("+ threadsList.get(position).getUnreadCount() +")";
            holder.name.setTypeface(null, Typeface.BOLD);
            holder.message.setTypeface(null, Typeface.BOLD);
        }

        holder.name.setText(name);

        holder.message.setText(threadsList.get(position).getLastMessage());
        try {
            holder.time.setText(threadsList.get(position).getLastSeen());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (threadsList.get(position).getIsReceived()){
            holder.type_icon.setImageResource(R.drawable.ic_received);
        }else{
            holder.type_icon.setImageResource(R.drawable.ic_sent);
        }
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();

        ImageLoader.getInstance().displayImage(threadsList.get(position).getPictureUrl(), holder.image, defaultOptions);


        return convertView;
    }

    class ViewHolder{
        private TextView time;
        private TextView name;
        private TextView message;

        private ImageView image;
        private ImageView type_icon;
    }

}
