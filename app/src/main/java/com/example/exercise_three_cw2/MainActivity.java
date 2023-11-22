package com.example.exercise_three_cw2;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.exercise_three_cw2.adapter.ContactAdapter;
import com.example.exercise_three_cw2.database.DatabaseHelper;
import com.example.exercise_three_cw2.databinding.ActivityMainBinding;
import com.example.exercise_three_cw2.model.ContactModel;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding viewBinding;
    private DatabaseHelper databaseHelper;
    private List<ContactModel> contacts;
    private ContactAdapter contactAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(viewBinding.getRoot());
        databaseHelper = new DatabaseHelper(this);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    private void initView() {
        contacts = new ArrayList<>();
        contactAdapter = new ContactAdapter(contacts);
        viewBinding.rvContacts.setAdapter(contactAdapter);
        viewBinding.btnAddContact.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddContactActivity.class);
            startActivity(intent);
        });
    }

    private void initData() {
        // Get list contact from database
        List<ContactModel> listContact = databaseHelper.readData();
        if (listContact != null && !listContact.isEmpty()) {
            contacts.clear();
            contacts.addAll(listContact);
            contactAdapter.notifyDataSetChanged();
        }
    }
}