package com.example.cs246app;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class RequestedPhotosActivity extends AppCompatActivity {
    LinearLayout imagePreviews;
    ClipData clip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //takes away the title from the top of the page
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        // set up content view and image preview reference
        setContentView(R.layout.activity_requested_photos);
        imagePreviews = findViewById(R.id.requested_photos_image_preview);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public void upload(View view) {
        Intent getPhotos = new Intent();
        getPhotos.setType("image/*");
        getPhotos.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        getPhotos.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(getPhotos, "Select Picture"), 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            clip = data.getClipData();

            InputStream in;
            try {
                for(int i = 0; i < clip.getItemCount(); i++) {
                    in = getContentResolver().openInputStream(clip.getItemAt(i).getUri());
                    final Bitmap selected_img = BitmapFactory.decodeStream(in);
                    ImageView child = (ImageView) getLayoutInflater().inflate(R.layout.image_preview_item, null);
                    child.setImageBitmap(selected_img);
                    imagePreviews.addView(child, i);
                }
            } catch (FileNotFoundException e) {
                Toast.makeText(this, "Couldn't find that picture?", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void submit(View view) {
        if (clip == null) {
            Toast.makeText(this, "No Photos", Toast.LENGTH_SHORT).show();
            return;
        } else {

            Intent intent = new Intent(Intent.ACTION_SEND_MULTIPLE);
            intent.setType("image/png");
            ArrayList<Uri> uris = new ArrayList<>();
            for (int i = 0; i < clip.getItemCount(); i++) {
                uris.add(clip.getItemAt(i).getUri());
            }
            intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uris);

            startActivity(intent);
        }
    }
}