package com.example.newdiaryapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Editor extends AppCompatActivity {

    private Button btn_save;
    private Button btn_cancel;
    private EditText title;
    private EditText content;
    private Button btn_capture;
    private Button btnCapture;
    private ImageView img_view;
    private static final int Image_Capture_Code = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        btn_save = (Button)findViewById(R.id.btn_save);
        btn_cancel = (Button)findViewById(R.id.btn_cancel);

        title = (EditText)findViewById(R.id.title);
        content = (EditText)findViewById(R.id.content);

        btn_capture = (Button)findViewById(R.id.btn_capture);
        img_view = (ImageView)findViewById(R.id.img_view);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String getTitle = title.getText().toString();
//                String getContent = content.getText().toString();
//
//                String filename = "myfile";
//                String fileContents = "Hello world!";
//                try (FileOutputStream fos = getApplicationContext().openFileOutput(filename, getApplicationContext().MODE_PRIVATE)) {
//                    fos.write(Integer.parseInt(fileContents));
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }


                Intent intent = new Intent(Editor.this,MainActivity.class);
                startActivity(intent);
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Editor.this,MainActivity.class);
                startActivity(intent);
            }
        });

        btn_capture.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent cInt = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cInt,Image_Capture_Code);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Image_Capture_Code) {
            if (resultCode == RESULT_OK) {
                Bitmap bp = (Bitmap) data.getExtras().get("data");
                img_view.setImageBitmap(bp);
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            }
        }
    }

}
