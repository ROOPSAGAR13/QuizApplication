package com.example.roopsagar.quizapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class score extends AppCompatActivity {

     private Button exit;
     private   Button restart;
     private EditText editText1;
     private EditText editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        int w = getIntent().getIntExtra("wrong",0);
        int c = getIntent().getIntExtra("correct", 0);


        TextView textView=(TextView)findViewById(R.id.correct);
        TextView textView1=(TextView)findViewById(R.id.wrong);
        final Button button =(Button)findViewById(R.id.exit);
        final Button button1 =(Button)findViewById(R.id.restart);
        TextView textView2=(TextView) findViewById(R.id.score);
        TextView textView3=(TextView) findViewById(R.id.score1);

        textView2.setText(c+"");
        textView3.setText(w+"");

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (score.this,type.class);
                startActivity(intent);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                homeIntent.addCategory( Intent.CATEGORY_HOME );
                homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);
            }

        });


    }
}
