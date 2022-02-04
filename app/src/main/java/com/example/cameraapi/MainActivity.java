package com.example.cameraapi;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mycamera.CameraActivity;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.image);

        File mediaStorageDir = new File(getExternalFilesDir(""), "/Piyush");
        if (!mediaStorageDir.exists()) {
            mediaStorageDir.mkdirs();
        }

        String filePath = mediaStorageDir.getPath() + "/pic4.jpg";

        findViewById(R.id.btn_takepicture).setOnClickListener(v -> {
            Intent intent = new Intent(this, CameraActivity.class);
            intent.putExtra("CameraType", "BACK");
            intent.putExtra("FilePath", filePath);
            startActivityForResult(intent, 1001);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null) {
            String filePath = data.getStringExtra("FilePath");

            imageView.setImageDrawable(null);

            imageView.setImageURI(Uri.parse(filePath));
        }
    }
}