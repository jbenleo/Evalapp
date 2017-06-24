package com.example.jbleo.evalapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.ArrayMap;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.jbleo.evalapp.model.TestTemplate;
import com.example.jbleo.evalapp.model.TextTestTemplate;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

/*
import com.example.jbleo.evalapp.model.EvalTest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
*/
public class TestTemplateActivity extends SessionActivity implements View.OnClickListener{

    private TestTemplate mTemplate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_template);

        Bundle b = getIntent().getExtras();
        mTemplate = (TestTemplate) b.getSerializable("template");
        if (mTemplate != null){
            ((TextView)findViewById(R.id.text_title)).setText(mTemplate.getTitle());
            ((TextView)findViewById(R.id.text_description)).setText(mTemplate.getDescription());
        }

        Button saveButton = (Button) findViewById(R.id.save_button);
        saveButton.setOnClickListener(this);
    }

    private void saveTemplate(){
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        UUID test_id;
        if (mTemplate == null){
            test_id = UUID.randomUUID();
            ArrayMap<String, String> users = new ArrayMap<String, String>();
            users.put(FirebaseAuth.getInstance().getCurrentUser().getUid(),"true");
            mTemplate = new TextTestTemplate(
                    test_id.toString(),
                    ((TextView)findViewById(R.id.text_title)).getText().toString(),
                    ((TextView)findViewById(R.id.text_description)).getText().toString(),
                    new Date().getTime(),
                    users,
                    new ArrayList<String>());
        }else{
            test_id = UUID.fromString(mTemplate.getUuid());
            mTemplate.setTitle(((TextView)findViewById(R.id.text_title)).getText().toString());
            mTemplate.setDescription(((TextView)findViewById(R.id.text_description)).getText().toString());
        }

        DatabaseReference testsRef = database.getReference("tests/" + test_id);
        testsRef.setValue(mTemplate);
        DatabaseReference userTestsRef = database.getReference("users/" + FirebaseAuth.getInstance().getCurrentUser().getUid() + "/tests/" + test_id);
        userTestsRef.setValue("true");
    }

    @Override
    protected void loginComplete() {

    }

    @Override
    protected void logoutComplete() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        saveTemplate();
    }
}
