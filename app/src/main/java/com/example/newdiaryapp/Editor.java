package com.example.newdiaryapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Editor extends AppCompatActivity {

    private Button btn_save;
    private Button btn_cancel;
    private EditText title;
    private EditText content;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        btn_save = (Button)findViewById(R.id.btn_save);
        btn_cancel = (Button)findViewById(R.id.btn_cancel);

        title = (EditText)findViewById(R.id.title);
        content = (EditText)findViewById(R.id.content);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getTitle = title.getText().toString();
                String getContent = content.getText().toString();

                File saveFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/text"); // 저장 경로

                if(!saveFile.exists()){ // 폴더 없을 경우
                    saveFile.mkdir(); // 폴더 생성
                }
                try {

                    long now = System.currentTimeMillis(); // 현재시간 받아오기
                    Date date = new Date(now); // Date 객체 생성
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String nowTime = sdf.format(date);

                    BufferedWriter buf = new BufferedWriter(new FileWriter(saveFile+"/CarnumData.txt", true));
                    buf.append(nowTime + " "); // 날짜 쓰기
                    buf.append(getTitle); // 파일 쓰기
                    buf.newLine(); // 개행
                    buf.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


                Log.i("Editor", getTitle);

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
    }

}
