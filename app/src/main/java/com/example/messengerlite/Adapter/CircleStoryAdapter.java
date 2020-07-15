package com.example.messengerlite.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.messengerlite.R;
import com.example.messengerlite.pojo.CircleStory;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.messengerlite.ui.activities.Launcher.StringToBitMap;

public class CircleStoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<CircleStory> stories;
    CircleStory currentStory;


    public CircleStoryAdapter(Context context, List<CircleStory> stories) {
        this.context = context;
        this.stories = stories;
    }

    @Override
    public int getItemViewType(int position) {

        if(position==0)
            return 0;
        else
            return 1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        switch (viewType) {
            case 0:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_new_story, parent, false);
                return new addHolder(view);
            case 1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.stories_circle_chat_design, parent, false);
                return new storyHolder(view);

            default:
                return null;

        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        currentStory = stories.get(position);
        if (position == 0) {
            final addHolder add_Holder = (addHolder) holder;

           add_Holder.add_story.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Toast.makeText(context, "new story", Toast.LENGTH_SHORT).show();
               }
           });


        } else {
            final storyHolder story_Holder = (storyHolder) holder;
            String[] frist_name = currentStory.getProfile_name().split(" ");
            story_Holder.profile_name.setText(frist_name[0]);
            story_Holder.profile_img.setImageBitmap(StringToBitMap(currentStory.getProfile_img()));
            story_Holder.online.setVisibility(currentStory.isOnline() ? View.VISIBLE : View.GONE);
            story_Holder.profile_img.setBorderWidth(currentStory.isStory()? R.dimen.story_profile_border : R.dimen.no_story_profile_border );
            story_Holder.profile_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "pos : "+position, Toast.LENGTH_SHORT).show();
                }
            });
    }
}


    @Override
    public int getItemCount() {
        return stories.size();
    }


class addHolder extends RecyclerView.ViewHolder {

    FrameLayout add_story;

    public addHolder(@NonNull View itemView) {
        super(itemView);
        add_story = itemView.findViewById(R.id.add_story);
    }
}


class storyHolder extends RecyclerView.ViewHolder {

    TextView profile_name;
    CircleImageView profile_img;
    ImageView online;


    public storyHolder(@NonNull View itemView) {
        super(itemView);
        profile_img = itemView.findViewById(R.id.story_profile_image);
        profile_name = itemView.findViewById(R.id.story_profile_name);
        online = itemView.findViewById(R.id.story_online);
    }
}


}
