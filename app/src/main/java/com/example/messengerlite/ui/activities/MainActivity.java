package com.example.messengerlite.ui.activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.messengerlite.Adapter.MessageAdapter;
import com.example.messengerlite.R;
import com.example.messengerlite.pojo.message;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    static public ArrayList<message> messages = new ArrayList<>();
    ImageView cam, gallery, mic, hide, like, back, online;
    CircleImageView profile_image;
    TextView acc_name, last_seen;
    EditText message;
    LinearLayout inner_container, outer_container;
    RelativeLayout profile;
    public static RecyclerView chatRecycler;
    final static int CODE1_CAM = 0, CODE2_GAL = 1, CODE3_PERMISSION = 2, SEND = 3, LIKE = 4;
    int main_button_val = LIKE;
    public static MessageAdapter adapter;
    MediaRecorder recorder = null;
    private static final String TAG = "MainActivity";
    boolean recording = false;
    String date , mfile;
//    DatabaseReference myRef;
//    FirebaseUser user;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        user = FirebaseAuth.getInstance().getCurrentUser();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        storage = FirebaseStorage.getInstance();
//        storageReference =storage.getReference();
//        myRef = FirebaseDatabase.getInstance().getReference("allMessages");


        cam = findViewById(R.id.cam);
        gallery = findViewById(R.id.gallery);
        mic = findViewById(R.id.mic);
        like = findViewById(R.id.like);
        hide = findViewById(R.id.hide);
        inner_container = findViewById(R.id.inner_container);
        outer_container = findViewById(R.id.outer_container);
        profile = findViewById(R.id.profile);

        message = findViewById(R.id.message);

        profile_image = findViewById(R.id.profile_image);
        acc_name = findViewById(R.id.acc_name);
        last_seen = findViewById(R.id.last_seen);
        back = findViewById(R.id.back);
        online = findViewById(R.id.online);

        profile_image.setImageResource(R.drawable.osman);
        //if(he is online)
        online.setVisibility(View.VISIBLE);
        acc_name.setText("Ahmed Osman");
        last_seen.setText("57m ago");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent prof = new Intent(MainActivity.this, profileActivity.class);
                startActivity(prof);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

            }
        });


//        Intent i = getIntent();
//
//        profile_image.setImageBitmap(StringToBitMap(i.getStringExtra("profile_image")) );
        acc_name.setText(getIntent().getStringExtra("user_name"));
//        last_seen.setText(i.getStringExtra("last_seen"));

        chatRecycler = findViewById(R.id.chatRecyclerView);

        date = DateFormat.getDateInstance(DateFormat.FULL).format(Calendar.getInstance().getTime());

        messages.clear();
//        messages.add(new message("text",user.getUid(), "this is a demo message from me i'm the programmer ho made this fucking app !111", date));
//        messages.add(new message("voice",user.getUid(), 43, 0, R.drawable.so, date));
//        messages.add(new message("text", "this message from me !333", R.drawable.me_so, date));
//        messages.add(new message("text", "this is a  message from me i'm the programmer ho made this  app !444", R.drawable.me_so2, date));
//        messages.add(new message("text", "this message from me !555", date));
//        messages.add(new message("voice", 23, 0, date));
//        messages.add(new message("text", "this is a demo message from me i'm the programmer ho made this fucking app !777", date));
//        messages.add(new message("photo", 0, encodeImage(((BitmapDrawable) profile_image.getDrawable()).getBitmap()), R.drawable.ziad, date));
//        messages.add(new message("text", "this is a demo message from me i'm the programmer ho made this fucking app !888", R.drawable.me_so3, date));
//        messages.add(new message("text", "this is a demo message from me i'm the programmer ho made this fucking app !999", date));
//        messages.add(new message("voice", 23, 0, R.drawable.me_so4, date));
//        messages.add(new message("voice", 25, 0, date));
//        messages.add(new message("voice", 3, 0, date));
//        messages.add(new message("voice", 53, 0, R.drawable.me_so5, date));
//        messages.add(new message("text", "this is a demo message from me i'm the programmer ho made this fucking app !1414", date));
//        messages.add(new message("text", "this is a demo message from me i'm the programmer ho made this fucking app !1515", date));
//        messages.add(new message("voice", 23, 0, date));
//        messages.add(new message("voice", 32, 0, R.drawable.me_so6, date));
//        messages.add(new message("text", "this is a demo message from me i'm the programmer ho made this fucking app !1818", date));
//        messages.add(new message("text", "this is a demo message from me i'm the programmer ho made this fucking app !1919", date));
//        messages.add(new message("text", "this is a demo message from me i'm the programmer ho made this fucking app !2020", R.drawable.me_so7, date));
//        messages.add(new message("voice", 19, 0, R.drawable.me_so8, date));
//        messages.add(new message("voice", 45, 0, R.drawable.me_so9, date));
//        messages.add(new message("text", "this is a demo message from me i'm the programmer ho made this application !2323", date));
//        messages.add(new message("text", "this is a demo message from me i'm the programmer ho made this app !2424", R.drawable.me_so10, date));
//        messages.add(new message("text", "this is a demo message from me i'm the programmer ho made the app !2525", date));
//        messages.add(new message("photo", 0, encodeImage(((BitmapDrawable) profile_image.getDrawable()).getBitmap()), R.drawable.ziad, date));
//        List<String> photos = new ArrayList<>();
//        photos.add(encodeImage(((BitmapDrawable) profile_image.getDrawable()).getBitmap()));
//        photos.add(encodeImage(((BitmapDrawable) profile_image.getDrawable()).getBitmap()));
//        photos.add(encodeImage(((BitmapDrawable) profile_image.getDrawable()).getBitmap()));
//        messages.add(new message("photos", photos, date));
//        photos.clear();
//        photos.add(encodeImage(((BitmapDrawable) profile_image.getDrawable()).getBitmap()));
//        photos.add(encodeImage(((BitmapDrawable) profile_image.getDrawable()).getBitmap()));
//        messages.add(new message("photos", photos, R.drawable.ziad, date));
//        photos.clear();
//        photos.add(encodeImage(((BitmapDrawable) profile_image.getDrawable()).getBitmap()));
//        photos.add(encodeImage(((BitmapDrawable) profile_image.getDrawable()).getBitmap()));
//        photos.add(encodeImage(((BitmapDrawable) profile_image.getDrawable()).getBitmap()));
//        photos.add(encodeImage(((BitmapDrawable) profile_image.getDrawable()).getBitmap()));
//        photos.add(encodeImage(((BitmapDrawable) profile_image.getDrawable()).getBitmap()));
//        messages.add(new message("photos", photos, date));
//        messages.add(new message("text", "this is a demo made this app !3030", R.drawable.osman2, date));
//        messages.add(new message("text", "this message from the programmer ho made the app !3131",  R.drawable.osman2,date));
//        messages.add(new message("text", "this is a demo message from me i'm the programmer ho made this app !3232", R.drawable.osman2, date));
//        messages.add(new message("text", "this is a demo message from me i'm the programmer ho made the app !3333",  R.drawable.osman2,date));
//

        adapter = new MessageAdapter(MainActivity.this, messages);

        LinearLayoutManager manager = new LinearLayoutManager(MainActivity.this);


        new ItemTouchHelper(startSwipe).attachToRecyclerView(chatRecycler);
        //manager.setReverseLayout(true);
        chatRecycler.setAdapter(adapter);
        chatRecycler.setLayoutManager(manager);
        chatRecycler.scrollToPosition(adapter.getItemCount() - 1);


        message.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                Toast.makeText(MainActivity.this, "before", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (message.getText().toString().isEmpty()) {
                    like.setImageResource(R.drawable.like);
                    main_button_val = LIKE;

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            if (message.getText().toString().isEmpty()) {

                                stop_writing();
                            }
                        }
                    }, 3000);

                } else {
                    like.setImageResource(R.drawable.send);
                    main_button_val = SEND;
                    writing_now();
                }

                Toast.makeText(MainActivity.this, "during", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void afterTextChanged(Editable s) {

                Toast.makeText(MainActivity.this, "after", Toast.LENGTH_SHORT).show();

            }
        });


        hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                stop_writing();
            }
        });

        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (main_button_val == LIKE ) {
//                    messages.add(new message("icon",user.getUid(), R.drawable.like, 48));
                    adapter.notifyDataSetChanged();
                    chatRecycler.smoothScrollToPosition(adapter.getItemCount() - 1);

                    //Toast.makeText(MainActivity.this, "likee" + main_button_val, Toast.LENGTH_SHORT).show();
                } else if (main_button_val == SEND ) {
                    // Toast.makeText(MainActivity.this, "send "+ main_button_val, Toast.LENGTH_SHORT).show();
                    date = DateFormat.getDateInstance(DateFormat.FULL).format(Calendar.getInstance().getTime());
//                    messages.add(new message("text",user.getUid(), message.getText().toString(), date));
                    adapter.notifyDataSetChanged();
                    message.setText("");
                    chatRecycler.smoothScrollToPosition(adapter.getItemCount() - 1);
                }
            }
        });
        like.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (main_button_val == LIKE) {
//                    messages.add(new message("icon",user.getUid(), R.drawable.like, 400));
                    adapter.notifyDataSetChanged();
                    chatRecycler.smoothScrollToPosition(adapter.getItemCount() - 1);
                    // Toast.makeText(MainActivity.this, "longg"  , Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });

        cam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.CAMERA}, CODE3_PERMISSION);
                } else {
                    Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(camera, CODE1_CAM);
                }
            }
        });

        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ActivityCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, CODE3_PERMISSION);
                } else {
                    Intent gal = new Intent().setType("image/* video/*").setAction(Intent.ACTION_GET_CONTENT);
                    gal.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                    gal.putExtra(Intent.EXTRA_MIME_TYPES, new String[]{"image/*", "video/*"});
                    startActivityForResult(Intent.createChooser(gal, "select media file"), CODE2_GAL);
                }
            }
        });

        mic.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_DOWN)
                {
                    if (ActivityCompat.checkSelfPermission(MainActivity.this,
                            Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {

                        ActivityCompat.requestPermissions(MainActivity.this,
                                new String[]{Manifest.permission.RECORD_AUDIO}, CODE3_PERMISSION);
                    } else if (ActivityCompat.checkSelfPermission(MainActivity.this,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                        ActivityCompat.requestPermissions(MainActivity.this,
                                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, CODE3_PERMISSION);
                    }else
                    {
                        startRecording();
                        Toast.makeText(MainActivity.this, "START recording....", Toast.LENGTH_SHORT).show();
                    }
                }
                else if(event.getAction() == MotionEvent.ACTION_UP)
                {
                    stopRecording();
                    Toast.makeText(MainActivity.this, "STOP recording....", Toast.LENGTH_SHORT).show();

                }

                return false;
            }
        });



    }

    public static ItemTouchHelper.SimpleCallback startSwipe = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.START) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return true;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            adapter.notifyDataSetChanged();
            
            //Toast.makeText(MainActivity2.this, "swiped! "+direction, Toast.LENGTH_SHORT).show();
        }
    } , endSwipe = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.END) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

           // Toast.makeText(MainActivity2.this, "swiped!", Toast.LENGTH_SHORT).show();
        }
    };

    private void startRecording() {
        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        mfile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/test" + System.currentTimeMillis() + ".mp3";
        recorder.setOutputFile(mfile);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        try {
            recorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        recorder.start();



    }

    private void stopRecording() {
        recorder.stop();
        recorder.release();
        recorder = null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.messages_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.video_call:
//                Intent call = new Intent(MainActivity.this, call.class);
//                call.putExtra("call","video");
//                startActivity(prof);
//                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                break;

            case R.id.voice_call:
//                Intent call = new Intent(MainActivity.this, call.class);
//                call.putExtra("call","voice");
//                startActivity(prof);
//                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                break;

            case R.id.more_menu:
                Intent prof = new Intent(MainActivity.this, profileActivity.class);
                startActivity(prof);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                break;
        }

        return false;
    }

    void writing_now() {
        mic.setVisibility(View.GONE);
        cam.setVisibility(View.GONE);
        gallery.setVisibility(View.GONE);
        hide.setVisibility(View.VISIBLE);
        outer_container.setWeightSum(10f);

        for (int i = 0; i < 20; i++) {
            final int count = i;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    inner_container.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, (float) (6 + (count / 10.0))));
                }
            }, 100);
        }
        hide.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));
        like.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));

    }

    private void stop_writing() {

        mic.setVisibility(View.VISIBLE);
        cam.setVisibility(View.VISIBLE);
        gallery.setVisibility(View.VISIBLE);
        hide.setVisibility(View.GONE);
        outer_container.setWeightSum(10f);
        inner_container.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 6f));
        like.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));
        mic.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));
        cam.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));
        gallery.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODE1_CAM && resultCode == RESULT_OK) {
            // one photo from camera
            try {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                if (bitmap != null) {
                    date = DateFormat.getDateInstance(DateFormat.FULL).format(Calendar.getInstance().getTime());
//                    messages.add(new message("photo",user.getUid(), 0, encodeImage(bitmap), date));
                    String url = uploadImage(data.getData(),false).get(0);
                    adapter.notifyDataSetChanged();
                    chatRecycler.smoothScrollToPosition(adapter.getItemCount() - 1);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (requestCode == CODE2_GAL && resultCode == RESULT_OK) {
            // images or videos from gallery
            Uri selectedMediaUri = data.getData();
            ClipData clipData = data.getClipData();


            Log.d(TAG, "onActivityResult: " + selectedMediaUri + " \n " + data.getClipData());
            //    Toast.makeText(this, selectedMediaUri.toString(), Toast.LENGTH_SHORT).show();


            if (selectedMediaUri == null && clipData != null && clipData.toString().contains("image%")) {
                //handle  images
                List<String> photos = new ArrayList<>();

                // multi image
                for (int i = 0; i < clipData.getItemCount(); i++) {

                    Uri imageUri = clipData.getItemAt(i).getUri();
                    uploadImage(imageUri,true);
                    try {
                        InputStream is = getContentResolver().openInputStream(imageUri);
                        Bitmap bitmap = BitmapFactory.decodeStream(is);

                        photos.add(encodeImage(bitmap));

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                List<String> photosURL  = uploadImage(null,true);
                date = DateFormat.getDateInstance(DateFormat.FULL).format(Calendar.getInstance().getTime());
//                messages.add(new message("photos",user.getUid(), photos, date));
                adapter.notifyDataSetChanged();
                chatRecycler.smoothScrollToPosition(adapter.getItemCount() - 1);
                Toast.makeText(this, photos.size() + " photo", Toast.LENGTH_SHORT).show();

            } else if (selectedMediaUri != null && selectedMediaUri.toString().contains("image")) {
                // one image
                Uri imageUri = data.getData();
                try {
                    if (imageUri != null) {
                        InputStream is = getContentResolver().openInputStream(imageUri);
                        Bitmap bitmap = BitmapFactory.decodeStream(is);

                        date = DateFormat.getDateInstance(DateFormat.FULL).format(Calendar.getInstance().getTime());
//                        messages.add(new message("photo",user.getUid(), 0, encodeImage(bitmap), date));
                        adapter.notifyDataSetChanged();
                        chatRecycler.smoothScrollToPosition(adapter.getItemCount() - 1);
                        Toast.makeText(this, "one photo", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "imageUri is equal to null", Toast.LENGTH_SHORT).show();
                    }

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            } else if (selectedMediaUri == null && clipData != null && clipData.toString().contains("video%")) {
                //handle videos

                // multi image
                for (int i = 0; i < clipData.getItemCount(); i++) {

                    Uri videoUri = clipData.getItemAt(i).getUri();

                    date = DateFormat.getDateInstance(DateFormat.FULL).format(Calendar.getInstance().getTime());

//                    messages.add(new message("video",user.getUid(), false, 0, videoUri.toString(), date));
                }
                adapter.notifyDataSetChanged();
                chatRecycler.smoothScrollToPosition(adapter.getItemCount() - 1);
            } else if (selectedMediaUri != null && selectedMediaUri.toString().contains("video")) {
                //handle video
                Uri videoUri = data.getData();
                if (videoUri != null) {
                    date = DateFormat.getDateInstance(DateFormat.FULL).format(Calendar.getInstance().getTime());
//                    messages.add(new message("video",user.getUid(),false, 0, videoUri.toString(), date));
                    adapter.notifyDataSetChanged();
                    chatRecycler.smoothScrollToPosition(adapter.getItemCount() - 1);
                }
            }

        }

    }

    /** Fire Base**/
//     FirebaseStorage storage;
//     StorageReference storageReference , ref;
     List<String> URL = new ArrayList<>() ;
    private List<String> uploadImage(final Uri filePath , final boolean multi) {
        if(!multi)
            URL.clear();

        if(filePath!=null)
        {
//            ref = storageReference.child("images/"+ UUID.randomUUID().toString());
//            ref.putFile(filePath)
//                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                        @Override
//                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//
//                            //TODO message status turn to send
//                            ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                                @Override
//                                public void onSuccess(Uri uri) {
//                                    URL.add(uri.toString());
//                                }
//                            });
//                        }
//                    }).addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception e) {
//                    uploadImage(filePath,multi);
//                }
//            });


        }

        return URL;
    }

    /**
     * convert Bitmap to String
     **/
    static public String encodeImage(Bitmap imagee) {

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        imagee.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        byte[] b = bytes.toByteArray();
        String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
        return encodedImage;
    }


    /**
     * to convert string to Bitmap
     **/
    static public Bitmap StringToBitMap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }


}

