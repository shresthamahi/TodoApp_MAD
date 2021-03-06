package com.tbcmad.todoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.tbcmad.todoapp.model.ETodo;
import com.tbcmad.todoapp.viewModel.TodoViewModel;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private static  final String TAG = "FragmentActivity";
    FragmentManager fragmentManager;
    Fragment fragment;
    FloatingActionButton floatingActionButton;
    TodoViewModel viewModel;
    int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        fragment = new ListTodoFragment();
        fragmentManager.beginTransaction()
                .replace(R.id.list_activity_container, fragment)
                .commit();

        floatingActionButton = findViewById(R.id.floatingActionButton);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);//fill the main activity with main menu
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mnu_delete_all:
                ShowDeleteAlert();

                break;
                case R.id.mnu_delete_cpmpleted:
                    viewModel = new ViewModelProvider(this).get(TodoViewModel.class);
                    viewModel.deleteCompleted();
                    Toast.makeText(getApplicationContext(),"Completed Tasks are deleted", Toast.LENGTH_LONG).show();
                    break;
            case R.id.mnu_logout:
                ShowLogoutAlert();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    void ShowDeleteAlert(){
        viewModel = new ViewModelProvider(this).get(TodoViewModel.class);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage("Are you sure want to delete all tasks?")
                .setTitle(getString(R.string.app_name))
                .setIcon(R.mipmap.todo_icon)
                .setCancelable(false)
                .setPositiveButton("Delete All", new DialogInterface.OnClickListener() {
                    @Override
                        public void onClick(DialogInterface dialog, int which) {
                           viewModel.deleteAll();
                        }
                })
                .setNegativeButton("Cancel",new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });


        alertDialog.show();

    }

    void ShowLogoutAlert(){
        Intent intent2= new Intent(this, LoginActivity.class);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage("Are you sure want to logout?")
                .setTitle(getString(R.string.app_name))
                .setIcon(R.mipmap.todo_icon)
                .setCancelable(false)
                .setPositiveButton("Logout", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences preferences = getApplicationContext().getSharedPreferences("todo_pref", 0);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.clear();
                        editor.commit();
                        startActivity(intent2);
                        finish();
                    }
                })
                .setNegativeButton("Cancel",new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });


        alertDialog.show();

    }


}