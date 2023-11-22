package com.example.exercise_three_cw2;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.exercise_three_cw2.adapter.AvatarAdapter;
import com.example.exercise_three_cw2.database.DatabaseHelper;
import com.example.exercise_three_cw2.databinding.ActivityAddContactBinding;
import com.example.exercise_three_cw2.model.AvatarModel;
import com.example.exercise_three_cw2.model.ContactModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public class AddContactActivity extends AppCompatActivity {
    private ActivityAddContactBinding viewBinding;
    private DatabaseHelper databaseHelper;
    private List<AvatarModel> avatars;
    private AvatarAdapter avatarAdapter;
    private int selectedAvatar = -1;
    private Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding = ActivityAddContactBinding.inflate(getLayoutInflater());
        setContentView(viewBinding.getRoot());
        databaseHelper = new DatabaseHelper(this);
        initView();
    }

    private void initView() {
        avatars = new ArrayList<>();
        avatars.add(new AvatarModel(R.drawable.avatar_1, false));
        avatars.add(new AvatarModel(R.drawable.avatar_2, false));
        avatars.add(new AvatarModel(R.drawable.avatar_3, false));
        avatars.add(new AvatarModel(R.drawable.avatar_4, false));
        avatars.add(new AvatarModel(R.drawable.avatar_5, false));

        avatarAdapter = new AvatarAdapter(avatars, position -> {
            selectedAvatar = avatars.get(position).getAvatar();
            List<AvatarModel> avatarsTemp = new ArrayList<>(avatars);
            avatars.clear();
            for (AvatarModel avatarModel : avatarsTemp) {
                AvatarModel newAvatarModel = new AvatarModel(
                        avatarModel.getAvatar(),
                        avatarModel.getAvatar() == selectedAvatar
                );
                avatars.add(newAvatarModel);
            }
            avatarAdapter.notifyDataSetChanged();
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        viewBinding.rvAvatars.setLayoutManager(layoutManager);
        viewBinding.rvAvatars.setAdapter(avatarAdapter);

        viewBinding.edtContactDob.setOnClickListener(v -> showDatePickerDialog());

        viewBinding.btnAddContact.setOnClickListener(v -> {
            String name = viewBinding.edtContactName.getText().toString();
            String email = viewBinding.edtContactEmail.getText().toString();
            String dob = viewBinding.edtContactDob.getText().toString();

            if (name.isEmpty() || email.isEmpty() || dob.isEmpty()) {
                Toast.makeText(AddContactActivity.this, "Please fill all information", Toast.LENGTH_LONG).show();
                return;
            }

            if (selectedAvatar < 0) {
                Toast.makeText(AddContactActivity.this, "Please select avatar", Toast.LENGTH_LONG).show();
                return;
            }

            databaseHelper.insertData(new ContactModel(
                    UUID.randomUUID().toString(),
                    name,
                    dob,
                    email,
                    selectedAvatar
            ));

            onBackPressed();
        });
    }

    private void showDatePickerDialog() {
        DatePickerDialog.OnDateSetListener dateListener = (view, year, month, dayOfMonth) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
            viewBinding.edtContactDob.setText(dateFormat.format(calendar.getTime()));
        };

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                AddContactActivity.this,
                dateListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );

        datePickerDialog.show();
    }
}