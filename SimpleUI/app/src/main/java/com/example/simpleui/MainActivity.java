package com.example.simpleui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText inputText;
    private CheckBox hideCheckBox;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences("settings", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        inputText = (EditText)findViewById(R.id.inputText);
        //inputText.setText("1234");
        inputText.setText(sharedPreferences.getString("inputText", ""));

        inputText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
               if(event.getAction() == KeyEvent.ACTION_DOWN){
                   if(keyCode == KeyEvent.KEYCODE_ENTER){
                       submit(v);
                       return true;
                   }
               }
                return false;
            }
        });

        hideCheckBox = (CheckBox) findViewById(R.id.hideCheckBox);
    }

    public void submit(View view){
        String text = inputText.getText().toString();
        editor.putString("inputText", text);
        editor.commit();
        if(hideCheckBox.isChecked()){
            text = "**********";
            inputText.setText("***********");
        }
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }
}
