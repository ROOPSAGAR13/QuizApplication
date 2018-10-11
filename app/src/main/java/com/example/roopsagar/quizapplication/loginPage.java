package com.example.roopsagar.quizapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class loginPage extends AppCompatActivity {

    public ArrayList<Question> questions = new ArrayList<>();
    public ArrayList<Question> wrong = new ArrayList<>();
    public ArrayList<Integer> alreadySelected = new ArrayList<>();
    public TextView textView;
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    public int sahe=0,galat=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        textView= (TextView)findViewById(R.id.txtview);
        btn1=(Button) findViewById(R.id.btn1);
        btn2=(Button)findViewById(R.id.btn2);
        btn3=(Button)findViewById(R.id.btn3);
        btn4=(Button)findViewById(R.id.btn4);

         String question = getIntent().getStringExtra("question_type");


//

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        databaseReference.child(question).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    String q = dataSnapshot1.child("question").getValue().toString();
                    String a1 = dataSnapshot1.child("a1").getValue().toString();
                    String a2 = dataSnapshot1.child("a2").getValue().toString();
                    String a3 = dataSnapshot1.child("a3").getValue().toString();
                    String a4 = dataSnapshot1.child("a4").getValue().toString();
                    String right = dataSnapshot1.child("answer").getValue().toString();
                    questions.add(new Question(q,a1,a2,a3,a4,right));




                }
                showQuestions();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }


    public void showQuestions () {

        //random o - questions.length .size

        //Toast.makeText(this, ""+questions.size(), Toast.LENGTH_SHORT).show();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive

        if (alreadySelected.size() == 10)
        {
         Intent intent= new Intent(loginPage.this,score.class) ;
         intent.putExtra("wrong",galat);
         intent.putExtra("correct",sahe);
         startActivity(intent);
        this.finish();
        }

        int val = questions.size()-1;
        int randomNum = (int)(Math.random() * val);

        while(alreadySelected.contains(randomNum)){
            randomNum = (int)(Math.random() * val);
        }

        alreadySelected.add(randomNum);

        final Question q = questions.get(randomNum);

        textView.setText(q.getQuestion());
        btn1.setText(q.getA1());
        btn2.setText(q.getA2());
        btn3.setText(q.getA3());
        btn4.setText(q.getA4());

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btn1.getText().toString().equals(q.getRightAnswer())) {
                    ++sahe;
                    showQuestions();
                }else{
                    wrong.add(q);
                    ++galat;
                    showQuestions();
                }
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (btn2.getText().toString().equals(q.getRightAnswer())) {
                    ++sahe;
                    showQuestions();
                }else{
                    wrong.add(q);
                    ++galat;
                    showQuestions();
                }

            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (btn3.getText().toString().equals(q.getRightAnswer())) {
                    ++sahe;
                    showQuestions();
                }else{
                    wrong.add(q);
                   ++galat;
                   showQuestions();
                }

            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (btn4.getText().toString().equals(q.getRightAnswer())) {
                    ++sahe;
                    showQuestions();
                }else{
                    wrong.add(q);
                   ++galat;
                   showQuestions();
                }

            }
        });

    }
}
