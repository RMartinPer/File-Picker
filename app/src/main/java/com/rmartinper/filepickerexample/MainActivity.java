package com.rmartinper.filepickerexample;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.filepicker.filepickerexample.R;
import com.rmartinper.filepicker.controller.DialogSelectionListener;
import com.rmartinper.filepicker.model.DialogConfigs;
import com.rmartinper.filepicker.model.DialogProperties;
import com.rmartinper.filepicker.view.FilePickerDialog;

public class MainActivity extends AppCompatActivity {

    private TextView tvFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvFile = findViewById(R.id.txt_file);

        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.M
                && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, 101);
        }
    }

    //Method for the Pick File button
    public void pickFile(View view) {
        String title = getString(R.string.file_title);
        String[] extensions = new String[]{".gif", ".jpeg", ".jpg", ".png"};
        DialogProperties properties = new DialogProperties(true);
        properties.setExtensions(extensions);
        FilePickerDialog dialog = new FilePickerDialog(MainActivity.this, properties);
        dialog.setTitle(title);
        dialog.setDialogSelectionListener(new DialogSelectionListener() {
            @Override
            public void onSelectedFilePaths(String[] files) {
                String filePath = files[0];
                tvFile.setText(filePath);
            }
        });
        dialog.show();
    }

    //Method for the Pick Folder button
    public void pickFolder(View view) {
        String title = getString(R.string.folder_title);
        DialogProperties properties = new DialogProperties(true);
        properties.setSelectionType(DialogConfigs.DIR_SELECT);
        FilePickerDialog dialog = new FilePickerDialog(MainActivity.this, properties);
        dialog.setTitle(title);
        dialog.setDialogSelectionListener(new DialogSelectionListener() {
            @Override
            public void onSelectedFilePaths(String[] files) {
                String filePath = files[0];
                tvFile.setText(filePath);
            }
        });
        dialog.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 100) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                String permGranted = getString(R.string.perm_granted);
                Toast.makeText(this, permGranted, Toast.LENGTH_SHORT).show();
            } else {
                String permDenied = getString(R.string.perm_denied);
                Toast.makeText(this, permDenied, Toast.LENGTH_SHORT).show();
                finish();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
