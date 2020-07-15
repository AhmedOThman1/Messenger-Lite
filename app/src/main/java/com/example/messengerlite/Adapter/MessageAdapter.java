package com.example.messengerlite.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
import com.example.messengerlite.R;
import com.example.messengerlite.pojo.PhotosMessage;
import com.example.messengerlite.pojo.message;
import com.example.messengerlite.ui.activities.PhotoActivity;
import com.like.LikeButton;
import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;
import static com.example.messengerlite.ui.activities.Launcher.StringToBitMap;
import static com.example.messengerlite.ui.activities.MainActivity.encodeImage;

public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Activity activity;
    private List<message> Message;
    static message currentmessage;
    public static Bitmap openImage;


    public MessageAdapter(Activity context, List<message> messages) {
        activity = context;
        Message = messages;
    }

//    FirebaseUser user;

    @Override
    public int getItemViewType(int position) {

        currentmessage = Message.get(position);
//        if (currentmessage.getUserID().equals(FirebaseAuth.getInstance().getCurrentUser().toString()))
        if(true)
        {
            // Me
            switch (currentmessage.getMessage_type()) {
                case "text":
                    // Me , Text
                    return 10;

                case "voice":
                    // me  ,  voice
                    return 20;

                case "photo":
                    // me  ,  one photo
                    return 30;

                case "photos":
                    // me  ,  multi photos
                    return 40;

                case "video":
                    // me  ,  video
                    return 50;

                case "icon":
                    return 60;
            }

        } else {
            switch (currentmessage.getMessage_type()) {
                case "text":
                    // him , Text
                    return 15;

                case "voice":
                    //him , voice
                    return 25;

                case "photo":
                    //him , one photo
                    return 35;

                case "photos":
                    // him  ,  multi photos
                    return 45;

                case "video":
                    // him  ,  video
                    return 55;

            }
        }

        return 0;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        switch (viewType) {
            case 10:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.your_message_design, parent, false);
                return new yourMsgViewHolder(view);
            case 20:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.your_voice_message, parent, false);
                return new yourVoiceViewHolder(view);
            case 30:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.your_photo_message, parent, false);
                return new yourImgViewHolder(view);
            case 40:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.your_photos_message, parent, false);
                return new yourImgsViewHolder(view);
            case 50:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.your_video_message, parent, false);
                return new yourVideoViewHolder(view);
            case 60:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.your_icon_message, parent, false);
                return new yourIconViewHolder(view);


            case 15:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.his_message_design, parent, false);
                return new hisMsgViewHolder(view);
            case 25:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.his_voice_message, parent, false);
                return new hisVoiceViewHolder(view);
            case 35:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.his_photo_message, parent, false);
                return new hisImgViewHolder(view);
            case 45:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.his_photos_message, parent, false);
                return new hisImgsViewHolder(view);
            case 55:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.his_video_message, parent, false);
                return new hisVideoViewHolder(view);

            default:
                return null;

        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        currentmessage = Message.get(position);
        switch (holder.getItemViewType()) {
            case 10:
                final yourMsgViewHolder msgViewHolder = (yourMsgViewHolder) holder;

                msgViewHolder.messageText.setText(currentmessage.getMessage_text());
                msgViewHolder.message_time.setText(currentmessage.getMessage_time());
//                    if( getItem(position-1).getMessage_time() - currentmessage.getMessage_time() >1)
//                        message_time.setVisibility(View.VISIBLE);
//
                msgViewHolder.messageText.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        msgViewHolder.message_time.setVisibility(View.VISIBLE);
                    }
                });
/**msgViewHolder.replay_content.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
// int pos =  msgViewHolder.replay_content.getTag(101);
Toast.makeText(activity, "pos : " + position + "\nreplay pos : " + Message.get(position).getReplay_postion(), Toast.LENGTH_LONG).show();
chatRecycler.scrollToPosition(Message.get(position).getReplay_postion());
final Animation zoom_in = AnimationUtils.loadAnimation(activity, R.anim.zoom_in),
zoom_out = AnimationUtils.loadAnimation(activity, R.anim.zoom_out);

final TextView test = (TextView) msgViewHolder.replay_content.getTag();
test.setAnimation(zoom_in);
zoom_in.setAnimationListener(new Animation.AnimationListener() {
@Override
public void onAnimationStart(Animation animation) {

}

@Override
public void onAnimationEnd(Animation animation) {
test.setAnimation(zoom_out);
}

@Override
public void onAnimationRepeat(Animation animation) {

}
});
}
});
 // msgViewHolder.message_time.setTag(msgViewHolder.message_time);

 if (currentmessage.getReplay_postion() == -1) {
 msgViewHolder.replay_to_LL.setVisibility(View.GONE);
 msgViewHolder.replay_content.setVisibility(View.GONE);
 } else {

 msgViewHolder.replay_to_LL.setVisibility(View.VISIBLE);
 msgViewHolder.replay_content.setVisibility(View.VISIBLE);
 msgViewHolder.replay_content.setText(Message.get(currentmessage.getReplay_postion()).getMessage_text());
 //            if(currentmessage.isme)
 //            msgViewHolder.replay_to.setText("You replay to yourself");
 //            else
 //                msgViewHolder.replay_to.setText("You replay to "+currentmessage.getUserName);

 }


 //
 //        final GestureDetector detector=new GestureDetector(activity, new GestureDetector.SimpleOnGestureListener(){
 //            @Override
 //            public boolean onSingleTapUp(MotionEvent e) {
 //                //if (msgViewHolder.message_time.getVisibility() == View.GONE)
 //                    msgViewHolder.message_time.setVisibility(View.VISIBLE);
 //                //else
 //                   // msgViewHolder.message_time.setVisibility(View.GONE);
 //
 //                return false;
 //            }
 //
 //
 //
 //            @Override
 //            public void onLongPress(MotionEvent e) {
 //
 //                reacts_replay.setVisibility(View.VISIBLE);
 //                reacts_replay.animate().y(-reacts_replay.getY()+e.getRawY()).setDuration(0).start();
 //            }
 //        });
 //
 //        msgViewHolder.messageText.setOnTouchListener(new View.OnTouchListener() {
 //            @Override
 //            public boolean onTouch(View v, MotionEvent event) {
 //                detector.onTouchEvent(event);
 //                return true;
 //            }
 //        });
 msgViewHolder.react_Container.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
Toast.makeText(activity, "Oops! " + position, Toast.LENGTH_SHORT).show();
}
});


 react();*/

                break;

            case 20:
                final yourVoiceViewHolder voiceViewHolder = (yourVoiceViewHolder) holder;

                voiceViewHolder.your_prog.setMax(currentmessage.getVoice_time() / 1000);
                voiceViewHolder.your_voice_time.setText("00:" + (currentmessage.getVoice_time() / 1000));
                voiceViewHolder.play_your.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (!currentmessage.isVoice_message_running()) {

                            voiceViewHolder.your_voice_time.setBase(SystemClock.elapsedRealtime() - currentmessage.getBaseoffset());
                            voiceViewHolder.your_voice_time.start();
                            currentmessage.setVoice_message_running(true);
                            voiceViewHolder.play_your.setImageResource(R.drawable.pause_yours);

                        } else {
                            voiceViewHolder.your_voice_time.stop();
                            currentmessage.setBaseoffset(SystemClock.elapsedRealtime() - voiceViewHolder.your_voice_time.getBase());
                            currentmessage.setVoice_message_running(false);
                            voiceViewHolder.play_your.setImageResource(R.drawable.play_yours);
                        }

                    }
                });

                voiceViewHolder.your_voice_time.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
                    @Override
                    public void onChronometerTick(Chronometer chronometer) {
                        int time = currentmessage.getVoice_time(),
                                currentTime = (int) ((SystemClock.elapsedRealtime() - chronometer.getBase()) / 1000);

                        voiceViewHolder.your_prog.setProgress(currentTime);

                        if (time > 0 && (SystemClock.elapsedRealtime() - chronometer.getBase()) >= time) {
                            Log.i("test", "" + SystemClock.elapsedRealtime() + "   ,  time :     " + time + "      ,    f  =   " + (SystemClock.elapsedRealtime() - chronometer.getBase()));
                            chronometer.setBase(SystemClock.elapsedRealtime());
                            voiceViewHolder.your_voice_time.stop();
                            currentmessage.setVoice_message_running(false);
                            voiceViewHolder.play_your.setImageResource(R.drawable.play_yours);
                            voiceViewHolder.your_voice_time.setText("00:" + (currentmessage.getVoice_time() / 1000));

                        }
                    }
                });


                break;
            case 30:
                yourImgViewHolder imgViewHolder = (yourImgViewHolder) holder;

                imgViewHolder.one_image.setImageBitmap(StringToBitMap(currentmessage.getMessage_photo()));
                imgViewHolder.one_image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        // TODO photo activity

                        Intent photoActivity = new Intent(activity, PhotoActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("photo", currentmessage.getMessage_photo());
                        photoActivity.putExtras(bundle);
                        activity.startActivity(photoActivity);
                    }
                });

                imgViewHolder.share.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(activity, "share my img", Toast.LENGTH_SHORT).show();
                    }
                });
                break;

            case 40:
                yourImgsViewHolder imgsViewHolder = (yourImgsViewHolder) holder;


                ArrayList<PhotosMessage> photosMessages = new ArrayList<>();

                List<String> photos = currentmessage.getPhotos();

                for (int i = 0; i < photos.size(); i++) {
                    photosMessages.add(new PhotosMessage(photos.get(i)));
                }

                PhotosAdapter adapter = new PhotosAdapter(activity, photosMessages);

                imgsViewHolder.photosRecycler.setAdapter(adapter);
                int spacingInPixels = activity.getResources().getDimensionPixelSize(R.dimen.grid_layout_margi);
                imgsViewHolder.photosRecycler.addItemDecoration(new GridSpacingItemDecoration(2, spacingInPixels, true, 0));

                if (photos.size() > 2)
                    imgsViewHolder.photosRecycler.setLayoutManager(new GridLayoutManager(activity, 3));
                else
                    imgsViewHolder.photosRecycler.setLayoutManager(new GridLayoutManager(activity, 2));

                imgsViewHolder.share.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(activity, "share my imgs", Toast.LENGTH_SHORT).show();
                    }
                });

                break;
            case 50:
                yourVideoViewHolder videoViewHolder = (yourVideoViewHolder) holder;

                MediaController mediaController = new MediaController(activity);
                mediaController.setMediaPlayer(videoViewHolder.one_video);
                mediaController.setAnchorView(videoViewHolder.one_video);
                videoViewHolder.one_video.setMediaController(mediaController);
                videoViewHolder.one_video.setVideoURI(Uri.parse(currentmessage.getMessage_video()));
                videoViewHolder.one_video.seekTo(currentmessage.getVideo_position());
                // mediaController.show();
                videoViewHolder.one_video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        Toast.makeText(activity, "here !", Toast.LENGTH_SHORT).show();
                    }
                });

                videoViewHolder.share.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(activity, "share my video", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case 60:
                yourIconViewHolder iconViewHolder = (yourIconViewHolder) holder;

                iconViewHolder.like.setLikeDrawableRes(currentmessage.getIcon());
                iconViewHolder.like.setUnlikeDrawableRes(currentmessage.getIcon());
                if (currentmessage.getIconSize() == 400) {
                    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(currentmessage.getIconSize(), currentmessage.getIconSize());
                    params.addRule(RelativeLayout.ALIGN_PARENT_END);
                    iconViewHolder.like.setLayoutParams(params);
                    iconViewHolder.like.setIconSizeDp(currentmessage.getIconSize());
                }
                iconViewHolder.like.setLiked(true);
                //iconViewHolder.like.setEnabled(false);
                iconViewHolder.like.performClick();
                break;
            case 15:
                final hisMsgViewHolder msgViewHolder1 = (hisMsgViewHolder) holder;

                msgViewHolder1.messageText.setText(currentmessage.getMessage_text());
                msgViewHolder1.message_time.setText(currentmessage.getMessage_time());
                msgViewHolder1.messageText.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        msgViewHolder1.message_time.setVisibility(View.VISIBLE);
                    }
                });

                msgViewHolder1.his_image.setImageResource(currentmessage.getHis_profile_img());
                msgViewHolder1.his_image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent photoActivity = new Intent(activity, PhotoActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("photo", encodeImage(((BitmapDrawable) msgViewHolder1.his_image.getDrawable()).getBitmap()));
                        photoActivity.putExtras(bundle);
                        activity.startActivity(photoActivity);

                    }
                });
                msgViewHolder1.his_image.setVisibility(currentmessage.isImage_visabilty() ? View.VISIBLE : View.INVISIBLE);
                try {
                    message nextMessage = Message.get(position + 1);
                    if (position + 1 < Message.size() && !nextMessage.isMe_or_him()) {
                        //TODO do this foe the pre item
                        //nextMessage.setImage_visabilty(false);
                        msgViewHolder1.his_image.setVisibility(View.INVISIBLE);

//                        if(position-1>=0 && !Message.get(position-1).isMe_or_him())
//                        {
//                            msgViewHolder1.messageText.setBackgroundResource(R.drawable.background_his_message_center);
//                        }
//                        else
//                        {
//                            msgViewHolder1.messageText.setBackgroundResource(R.drawable.background_his_message_up);
//                        }
                    }
//                    else if( position-1 >=0 && ( ( position+1 <Message.size() && nextMessage.isMe_or_him() ) || ( position+1==Message.size() ) ) && !Message.get(position-1).isMe_or_him()  )
//                    {
//                        msgViewHolder1.messageText.setBackgroundResource(R.drawable.background_his_message_down);
//                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
            case 25:
                final hisVoiceViewHolder voiceViewHolder1 = (hisVoiceViewHolder) holder;

                voiceViewHolder1.his_image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent photoActivity = new Intent(activity, PhotoActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("photo", encodeImage(((BitmapDrawable) voiceViewHolder1.his_image.getDrawable()).getBitmap()));
                        photoActivity.putExtras(bundle);
                        activity.startActivity(photoActivity);

                    }
                });
                voiceViewHolder1.his_prog.setMax(currentmessage.getVoice_time() / 1000);
                voiceViewHolder1.his_voice_time.setText("00:" + (currentmessage.getVoice_time() / 1000));
                voiceViewHolder1.his_image.setImageResource(currentmessage.getHis_profile_img());
                voiceViewHolder1.play_his.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (!currentmessage.isVoice_message_running()) {
                            Toast.makeText(activity, "Test", Toast.LENGTH_SHORT).show();
                            voiceViewHolder1.his_voice_time.setBase(SystemClock.elapsedRealtime() - currentmessage.getBaseoffset());
                            voiceViewHolder1.his_voice_time.start();
                            currentmessage.setVoice_message_running(true);
                            voiceViewHolder1.play_his.setImageResource(R.drawable.pause_him);
                        } else {
                            voiceViewHolder1.his_voice_time.stop();
                            currentmessage.setBaseoffset(SystemClock.elapsedRealtime() - voiceViewHolder1.his_voice_time.getBase());
                            currentmessage.setVoice_message_running(false);
                            voiceViewHolder1.play_his.setImageResource(R.drawable.play_him);
                        }

                    }
                });

                voiceViewHolder1.his_voice_time.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
                    @Override
                    public void onChronometerTick(Chronometer chronometer) {
                        int time = currentmessage.getVoice_time(),
                                currentTime = (int) ((SystemClock.elapsedRealtime() - chronometer.getBase()) / 1000);

                        voiceViewHolder1.his_prog.setProgress(currentTime);

                        if (time > 0 && (SystemClock.elapsedRealtime() - chronometer.getBase()) >= time) {
                            Log.i("test", "" + SystemClock.elapsedRealtime() + "   ,  time2 :     " + time + "      ,    f2  =   " + (SystemClock.elapsedRealtime() - chronometer.getBase()));
                            chronometer.setBase(SystemClock.elapsedRealtime());
                            voiceViewHolder1.his_voice_time.stop();
                            currentmessage.setVoice_message_running(false);
                            voiceViewHolder1.play_his.setImageResource(R.drawable.play_him);
                            voiceViewHolder1.his_voice_time.setText("00:" + (currentmessage.getVoice_time() / 1000));

                        }
                    }
                });
                voiceViewHolder1.his_image.setVisibility(currentmessage.isImage_visabilty() ? View.VISIBLE : View.INVISIBLE);
                try {
                    message nextMessage = Message.get(position + 1);
                    if (position + 1 < Message.size() && !nextMessage.isMe_or_him()) {
                        //TODO do this foe the pre item
                        //nextMessage.setImage_visabilty(false);
                        voiceViewHolder1.his_image.setVisibility(View.INVISIBLE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case 35:
                final hisImgViewHolder imgViewHolder1 = (hisImgViewHolder) holder;

                imgViewHolder1.his_image.setImageResource(currentmessage.getHis_profile_img());
                imgViewHolder1.one_image.setImageBitmap(StringToBitMap(currentmessage.getMessage_photo()));

                final String thePhoto = currentmessage.getMessage_photo();
                imgViewHolder1.one_image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent photoActivity = new Intent(activity, PhotoActivity.class);
                        photoActivity.putExtra("photo", thePhoto);
                        activity.startActivity(photoActivity);

                    }
                });
                imgViewHolder1.his_image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent photoActivity = new Intent(activity, PhotoActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("photo", encodeImage(((BitmapDrawable) imgViewHolder1.his_image.getDrawable()).getBitmap()));
                        photoActivity.putExtras(bundle);
                        activity.startActivity(photoActivity);

                    }
                });

                imgViewHolder1.his_image.setVisibility(currentmessage.isImage_visabilty() ? View.VISIBLE : View.INVISIBLE);
                try {
                    message nextMessage = Message.get(position + 1);
                    if (position + 1 < Message.size() && !nextMessage.isMe_or_him()) {
                        //TODO do this foe the pre item
                        //nextMessage.setImage_visabilty(false);
                        imgViewHolder1.his_image.setVisibility(View.INVISIBLE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                imgViewHolder1.share.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(activity, "share his img", Toast.LENGTH_SHORT).show();
                    }
                });

                break;
            case 45:
                final hisImgsViewHolder imgsViewHolder1 = (hisImgsViewHolder) holder;

                imgsViewHolder1.his_image.setImageResource(currentmessage.getHis_profile_img());

                ArrayList<PhotosMessage> photosMessages2 = new ArrayList<>();

                List<String> photos2 = currentmessage.getPhotos();
                for (int i = 0; i < photos2.size(); i++) {
                    photosMessages2.add(new PhotosMessage(photos2.get(i)));
                }

                PhotosAdapter adapter2 = new PhotosAdapter(activity, photosMessages2);

                imgsViewHolder1.photosRecycler.setAdapter(adapter2);
                int spacingInPixels2 = activity.getResources().getDimensionPixelSize(R.dimen.grid_layout_margi);

                while (imgsViewHolder1.photosRecycler.getItemDecorationCount() > 0) {
                    imgsViewHolder1.photosRecycler.removeItemDecorationAt(0);
                }
                imgsViewHolder1.photosRecycler.addItemDecoration(new GridSpacingItemDecoration(2, spacingInPixels2, true, 0));

                if (photos2.size() > 2)
                    imgsViewHolder1.photosRecycler.setLayoutManager(new GridLayoutManager(activity, 3));
                else
                    imgsViewHolder1.photosRecycler.setLayoutManager(new GridLayoutManager(activity, 2));


                //imgsViewHolder1.his_image.setVisibility(currentmessage.isImage_visabilty() ? View.VISIBLE : View.INVISIBLE);
                try {
                    message nextMessage = Message.get(position + 1);
                    if (position + 1 < Message.size() && !nextMessage.isMe_or_him()) {
                        //TODO do this foe the pre item
                        //nextMessage.setImage_visabilty(false);
                        imgsViewHolder1.his_image.setVisibility(View.INVISIBLE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                imgsViewHolder1.his_image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent photoActivity = new Intent(activity, PhotoActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("photo", encodeImage(((BitmapDrawable) imgsViewHolder1.his_image.getDrawable()).getBitmap()));
                        photoActivity.putExtras(bundle);
                        activity.startActivity(photoActivity);

                    }
                });

                imgsViewHolder1.share.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(activity, "share his imgs", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case 55:
                final hisVideoViewHolder videoViewHolder1 = (hisVideoViewHolder) holder;

                videoViewHolder1.his_image.setImageResource(currentmessage.getHis_profile_img());

                mediaController = new MediaController(activity);
                mediaController.setMediaPlayer(videoViewHolder1.one_video);
                mediaController.setAnchorView(videoViewHolder1.one_video);
                videoViewHolder1.one_video.setMediaController(mediaController);
                videoViewHolder1.one_video.setVideoURI(Uri.parse(currentmessage.getMessage_video()));
                //mediaController.show();
                videoViewHolder1.one_video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        Toast.makeText(activity, "here !", Toast.LENGTH_SHORT).show();
                    }
                });
                videoViewHolder1.his_image.setVisibility(currentmessage.isImage_visabilty() ? View.VISIBLE : View.INVISIBLE);
                try {
                    message nextMessage = Message.get(position + 1);
                    if (position + 1 < Message.size() && !nextMessage.isMe_or_him()) {
                        //TODO do this foe the pre item
                        //nextMessage.setImage_visabilty(false);
                        videoViewHolder1.his_image.setVisibility(View.INVISIBLE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                videoViewHolder1.his_image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent photoActivity = new Intent(activity, PhotoActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("photo", encodeImage(((BitmapDrawable) videoViewHolder1.his_image.getDrawable()).getBitmap()));
                        photoActivity.putExtras(bundle);
                        activity.startActivity(photoActivity);

                    }
                });
                videoViewHolder1.share.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(activity, "share his video", Toast.LENGTH_SHORT).show();
                    }
                });

                break;
        }
    }

    @Override
    public int getItemCount() {
        return Message.size();
    }

    public static void react() {
        int sum = 0, mx = 0, mxx = 0, imx = -1, imxx = -1;
        for (int i = 0; i < currentmessage.getReacts().length; i++) {
            sum += currentmessage.getReacts()[i];
        }

        //      max
        for (int i = 0; i < currentmessage.getReacts().length; i++) {
            if (currentmessage.getReacts()[i] >= mx) {
                mx = currentmessage.getReacts()[i];
                imx = i;
            }
        }
        for (int i = 0; i < currentmessage.getReacts().length; i++) {
            if (i != imx && currentmessage.getReacts()[i] > mxx) {
                mxx = currentmessage.getReacts()[i];
                imxx = i;
            }
        }
        int[] reacts = new int[]{
                R.drawable.love_react,
                R.drawable.haha_react,
                R.drawable.wow_react,
                R.drawable.sad_react,
                R.drawable.angry_react,
                R.drawable.like_react,
                R.drawable.dislike_react,
        };
//        if (imx >= 0)
//            msgViewHolder.react_small1.setImageResource(reacts[imx]);
//        if (imxx >= 0)
//            msgViewHolder.react_small2.setImageResource(reacts[imxx]);
//
////              Visibility
//        if (sum == 0) msgViewHolder.react_Container.setVisibility(View.GONE);
//        else if (sum == 1) {
//            msgViewHolder.react_Container.setVisibility(View.VISIBLE);
//            msgViewHolder.react_small1.setVisibility(View.VISIBLE);
//            msgViewHolder.react_small2.setVisibility(View.GONE);
//
//        } else if (sum > 1) {
//            msgViewHolder.react_Container.setVisibility(View.VISIBLE);
//            msgViewHolder.react_small1.setVisibility(View.VISIBLE);
//            if (mxx == 0)
//                msgViewHolder.react_small2.setVisibility(View.GONE);
//            else
//                msgViewHolder.react_small2.setVisibility(View.VISIBLE);
//
//
//        }
//        msgViewHolder.react_count.setText(sum + "");
    }

    public static class yourMsgViewHolder extends RecyclerView.ViewHolder {

        TextView messageText, message_time, react_count, replay_to, replay_content;
        RelativeLayout react_Container;
        LinearLayout replay_to_LL;
        ImageView react_small1, react_small2;

        public yourMsgViewHolder(@NonNull View itemView) {
            super(itemView);
            messageText = itemView.findViewById(R.id.your_message);
            message_time = itemView.findViewById(R.id.message_time);
            react_Container = itemView.findViewById(R.id.react_Container);
            react_count = itemView.findViewById(R.id.reacts_count);
            react_small1 = itemView.findViewById(R.id.react_small1);
            react_small2 = itemView.findViewById(R.id.react_small2);
            replay_content = itemView.findViewById(R.id.replay_content);
            replay_to_LL = itemView.findViewById(R.id.replay_to_LL);
            replay_to = itemView.findViewById(R.id.replay_to);
        }
    }

    class yourVoiceViewHolder extends RecyclerView.ViewHolder {

        TextView message_time;
        ImageView play_your;
        ProgressBar your_prog;
        Chronometer your_voice_time;

        public yourVoiceViewHolder(@NonNull View view) {
            super(view);
            your_voice_time = view.findViewById(R.id.your_voice_time);
            play_your = view.findViewById(R.id.play_your);
            your_prog = view.findViewById(R.id.voice_progress_your);
            message_time = view.findViewById(R.id.message_time);

        }
    }

    class yourImgViewHolder extends RecyclerView.ViewHolder {

        TextView message_time;
        ImageView one_image;
        RelativeLayout share;

        public yourImgViewHolder(@NonNull View itemView) {
            super(itemView);
            one_image = itemView.findViewById(R.id.one_image);
            message_time = itemView.findViewById(R.id.message_time);
            share = itemView.findViewById(R.id.share);

        }
    }

    class yourImgsViewHolder extends RecyclerView.ViewHolder {

        TextView message_time;
        RecyclerView photosRecycler;
        RelativeLayout share;

        public yourImgsViewHolder(@NonNull View itemView) {
            super(itemView);
            photosRecycler = itemView.findViewById(R.id.GridPhotos);
            message_time = itemView.findViewById(R.id.message_time);
            share = itemView.findViewById(R.id.share);

        }
    }

    class yourVideoViewHolder extends RecyclerView.ViewHolder {

        TextView message_time;
        VideoView one_video;
        RelativeLayout share;

        public yourVideoViewHolder(@NonNull View itemView) {
            super(itemView);
            message_time = itemView.findViewById(R.id.message_time);
            one_video = itemView.findViewById(R.id.one_video);
            share = itemView.findViewById(R.id.share);

        }
    }

    class yourIconViewHolder extends RecyclerView.ViewHolder {

        LikeButton like;

        public yourIconViewHolder(@NonNull View itemView) {
            super(itemView);
            like = itemView.findViewById(R.id.like_icon);
        }
    }


    class hisMsgViewHolder extends RecyclerView.ViewHolder {

        TextView messageText, message_time;
        CircleImageView his_image;

        public hisMsgViewHolder(@NonNull View itemView) {
            super(itemView);
            messageText = itemView.findViewById(R.id.his_message);
            message_time = itemView.findViewById(R.id.message_time);
            his_image = itemView.findViewById(R.id.his_image);
        }
    }

    class hisVoiceViewHolder extends RecyclerView.ViewHolder {

        TextView message_time;
        ImageView play_his;
        ProgressBar his_prog;
        Chronometer his_voice_time;
        CircleImageView his_image;

        public hisVoiceViewHolder(@NonNull View view) {
            super(view);
            his_image = itemView.findViewById(R.id.his_image);
            his_voice_time = view.findViewById(R.id.his_voice_time);
            play_his = view.findViewById(R.id.play_his);
            his_prog = view.findViewById(R.id.voice_progress_his);
            message_time = view.findViewById(R.id.message_time);

        }
    }

    class hisImgViewHolder extends RecyclerView.ViewHolder {
        CircleImageView his_image;
        TextView message_time;
        ImageView one_image;
        RelativeLayout share;

        public hisImgViewHolder(@NonNull View itemView) {
            super(itemView);
            his_image = itemView.findViewById(R.id.his_image);
            one_image = itemView.findViewById(R.id.one_image);
            message_time = itemView.findViewById(R.id.message_time);
            share = itemView.findViewById(R.id.share);

        }
    }

    class hisImgsViewHolder extends RecyclerView.ViewHolder {
        CircleImageView his_image;
        TextView message_time;
        RecyclerView photosRecycler;
        RelativeLayout share;

        public hisImgsViewHolder(@NonNull View itemView) {
            super(itemView);
            his_image = itemView.findViewById(R.id.his_image);
            photosRecycler = itemView.findViewById(R.id.GridPhotos);
            message_time = itemView.findViewById(R.id.message_time);
            share = itemView.findViewById(R.id.share);

        }
    }

    class hisVideoViewHolder extends RecyclerView.ViewHolder {

        TextView message_time;
        VideoView one_video;
        CircleImageView his_image;
        RelativeLayout share;

        public hisVideoViewHolder(@NonNull View itemView) {
            super(itemView);
            his_image = itemView.findViewById(R.id.his_image);
            message_time = itemView.findViewById(R.id.message_time);
            one_video = itemView.findViewById(R.id.one_video);
            share = itemView.findViewById(R.id.share);

        }
    }


    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;
        private int headerNum;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge, int headerNum) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
            this.headerNum = headerNum;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view) - headerNum; // item position

            if (position >= 0) {
                int column = position % spanCount; // item column

                if (includeEdge) {
                    outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                    outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                    if (position < spanCount) { // top edge
                        outRect.top = spacing;
                    }
                    outRect.bottom = spacing; // item bottom
                } else {
                    outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                    outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                    if (position >= spanCount) {
                        outRect.top = spacing; // item top
                    }
                }
            } else {
                outRect.left = 0;
                outRect.right = 0;
                outRect.top = 0;
                outRect.bottom = 0;
            }
        }
    }


}
