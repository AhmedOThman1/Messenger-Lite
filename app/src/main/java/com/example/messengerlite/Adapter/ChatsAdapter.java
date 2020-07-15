package com.example.messengerlite.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.messengerlite.R;
import com.example.messengerlite.pojo.ChatsModel;
import com.example.messengerlite.pojo.CircleStory;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.messengerlite.ui.activities.Launcher.StringToBitMap;
public class ChatsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<ChatsModel> Message;
    ChatsModel currentmessage;

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public ChatsAdapter(Context context, List<ChatsModel> messages) {
        this.context = context;
        Message = messages;
    }

    @Override
    public int getItemViewType(int position) {
        if(position>2)
            position=2;

        return position;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        switch (viewType) {
            case 0:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_design, parent, false);
                return new searchHolder(view);
            case 1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.stories_recycler, parent, false);
                return new storiesHolder(view);
            case 2:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chats_custom_design, parent, false);
                return new chatsHolder(view);

            default:
                return null;

        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        currentmessage = Message.get(position);
        switch (holder.getItemViewType()) {
            case 0:
                final searchHolder search_Holder = (searchHolder) holder;


                break;

            case 1:
                final storiesHolder stories_Holder = (storiesHolder) holder;

                List<CircleStory> circleStories = new ArrayList<>();

                List<String> photos = currentmessage.getStoryProfileImgs();
                List<String> names = currentmessage.getStoryProfileNames();


                for (int i = 0; i < photos.size(); i++) {
                    circleStories.add(new CircleStory(photos.get(i) , names.get(i) , i%2==1 ,i%2==0 ));
                }

                CircleStoryAdapter adapter = new CircleStoryAdapter(context, circleStories);

                stories_Holder.stories.setAdapter(adapter);
                stories_Holder.stories.setLayoutManager(new LinearLayoutManager(context , RecyclerView.HORIZONTAL , false));

                break;
            case 2:
                chatsHolder chats_Holder = (chatsHolder) holder;
                chats_Holder.profile_img.setImageBitmap( StringToBitMap(currentmessage.getProfileMessage()));
                chats_Holder.username.setText(currentmessage.getUsername());
                chats_Holder.message_text.setText(currentmessage.getMessageText());
                chats_Holder.message_time.setText(currentmessage.getMessage_time());
                chats_Holder.message_in.setTag(currentmessage);
                if(listener != null) {
                    chats_Holder.message_in.getTag();
                    chats_Holder.message_in.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            listener.onItemClick(currentmessage);
                        }
                    });
                }
                break;

        }
    }


    @Override
    public int getItemCount() {
        return Message.size();
    }



    class searchHolder extends RecyclerView.ViewHolder {

        SearchView search;

        public searchHolder(@NonNull View itemView) {
            super(itemView);
            search = itemView.findViewById(R.id.search);
        }
    }


    class storiesHolder extends RecyclerView.ViewHolder {

        RecyclerView stories;

        public storiesHolder(@NonNull View itemView) {
            super(itemView);
            stories = itemView.findViewById(R.id.storiesRecycler);
        }
    }

    class chatsHolder extends RecyclerView.ViewHolder {

        TextView message_time,message_text , username;
        CircleImageView profile_img;
        RelativeLayout message_in;

        public chatsHolder(@NonNull View itemView) {
            super(itemView);
            message_time = itemView.findViewById(R.id.message_time);
            message_text = itemView.findViewById(R.id.message_text);
            profile_img = itemView.findViewById(R.id.profile_image);
            username = itemView.findViewById(R.id.acc_name);
            message_in= itemView.findViewById(R.id.message_in);
        }
    }


    public interface OnItemClickListener {
        void onItemClick(ChatsModel item);
    }

}
