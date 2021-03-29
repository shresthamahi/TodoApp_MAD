package com.tbcmad.todoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;

public class EditActivity extends AppCompatActivity {
    private static  final String TAG = "EditActivity";
    FragmentManager fragmentManager;
    Fragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        fragmentManager = getSupportFragmentManager();
        fragment = new EditTodoFragment();
        fragmentManager.beginTransaction()
                .replace(R.id.edit_activity_container, fragment)
                .commit();
    }


}