package com.example.messengerlite.ui.fragments;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.messengerlite.Adapter.ChatsAdapter;
import com.example.messengerlite.R;
import com.example.messengerlite.pojo.ChatsModel;
import com.example.messengerlite.ui.activities.MainActivity;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static com.example.messengerlite.ui.activities.Launcher.encodeImage;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChatsFragment extends Fragment {


    private static final int CODE1_CAM = 1;
    private static final int CODE3_PERMISSION = 3;

    public ChatsFragment() {
        // Required empty public constructor
    }

    ArrayList<ChatsModel> messages = new ArrayList<>();
    ChatsAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_chats, container, false);

//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("allMessages").child(user.getUid());

        Toolbar toolbar = rootView.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);//Make sure you have this line of code.

        CircleImageView profile_image = rootView.findViewById(R.id.profile_image);
        profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Hi!", Toast.LENGTH_SHORT).show();
            }
        });

        //debug
        ImageView hidden = rootView.findViewById(R.id.hidden_img);

        RecyclerView chats = rootView.findViewById(R.id.chats);

        chats.setLayoutManager(new LinearLayoutManager(getActivity()));

        messages.clear();
        messages.add(new ChatsModel());

        List<String> photos = new ArrayList<>();
        hidden.setImageResource(R.drawable.osman2);
        photos.add(encodeImage(((BitmapDrawable) hidden.getDrawable()).getBitmap()));
//        hidden.setImageResource(R.drawable.samy);
//        photos.add(encodeImage(((BitmapDrawable) hidden.getDrawable()).getBitmap()));
//        hidden.setImageResource(R.drawable.shawky);
//        photos.add(encodeImage(((BitmapDrawable) hidden.getDrawable()).getBitmap()));
//        hidden.setImageResource(R.drawable.ziad);
//        photos.add(encodeImage(((BitmapDrawable) hidden.getDrawable()).getBitmap()));
//        hidden.setImageResource(R.drawable.me_so5);
//        photos.add(encodeImage(((BitmapDrawable) hidden.getDrawable()).getBitmap()));
//        hidden.setImageResource(R.drawable.me_so7);
//        photos.add(encodeImage(((BitmapDrawable) hidden.getDrawable()).getBitmap()));
//        hidden.setImageResource(R.drawable.me_so3);
//        photos.add(encodeImage(((BitmapDrawable) hidden.getDrawable()).getBitmap()));
//        hidden.setImageResource(R.drawable.osman2);
//        photos.add(encodeImage(((BitmapDrawable) hidden.getDrawable()).getBitmap()));
        List<String> names = new ArrayList<>();
//        names.add("ahmed");
//        names.add("mohamed");
//        names.add("ali");
//        names.add("mona");
//        names.add("sofia");
//        names.add("mahmoud");
//        names.add("osman");
        names.add("Ahmed Osman");

        messages.add(new ChatsModel(photos, names));

        messages.add(new ChatsModel("Ahmed Osman", "hi how are you ?", encodeImage(((BitmapDrawable) hidden.getDrawable()).getBitmap()), "10:11 PM"));
//        hidden.setImageResource(R.drawable.me_so3);
//        messages.add(new ChatsModel("Asma Adel", "how are you ?", encodeImage(((BitmapDrawable) hidden.getDrawable()).getBitmap()), "9:11 PM"));
//        hidden.setImageResource(R.drawable.samy);
//        messages.add(new ChatsModel("Ahmed Samy", "how r u ?", encodeImage(((BitmapDrawable) hidden.getDrawable()).getBitmap()), "8:11 PM"));
//        hidden.setImageResource(R.drawable.shawky);
//        messages.add(new ChatsModel("Ahmed Mohamed", "hi !", encodeImage(((BitmapDrawable) hidden.getDrawable()).getBitmap()), "6:11 PM"));
//        hidden.setImageResource(R.drawable.me_so5);
//        messages.add(new ChatsModel("Asmaa Osman", "whats up ?", encodeImage(((BitmapDrawable) hidden.getDrawable()).getBitmap()), "3:11 PM"));
//        hidden.setImageResource(R.drawable.ziad);
//        messages.add(new ChatsModel("ابو بكر البغدادي", "الا تريد ان تجاهد معانا في سبيل الله", encodeImage(((BitmapDrawable) hidden.getDrawable()).getBitmap()), "12:11 PM"));
//        hidden.setImageResource(R.drawable.osman2);
//        messages.add(new ChatsModel("طارق السيد", "عامل اي يا علق \uD83D\uDE02\uD83D\uDE02", encodeImage(((BitmapDrawable) hidden.getDrawable()).getBitmap()), "10:11 AM"));
        adapter = new ChatsAdapter(getContext(), messages);

        adapter.setListener(new ChatsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ChatsModel item) {

                Intent home = new Intent(getActivity(), MainActivity.class);
//                home.putExtra("profile_image", item.getProfileMessage() );
                home.putExtra("user_name" ,item.getUsername());
                //home.putExtra("last_seen" ,item.id);
                startActivity(home);
            }
        });
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        //manager.setReverseLayout(true);
        chats.setAdapter(adapter);
        chats.setLayoutManager(manager);


        return rootView;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);//Make sure you have this line of code.
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.chats_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.cam:
                if (ActivityCompat.checkSelfPermission(getContext(),
                        Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.CAMERA}, CODE3_PERMISSION);
                } else {
                    Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(camera, CODE1_CAM);
                }

                break;

            case R.id.pen:
//                Intent call = new Intent(MainActivity.this, call.class);
//                call.putExtra("call","voice");
//                startActivity(prof);
//                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                break;
        }
        return false;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODE1_CAM && resultCode == RESULT_OK) {
            // one photo from camera
            try {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                if (bitmap != null) {

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
