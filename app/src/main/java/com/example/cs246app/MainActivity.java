package com.example.cs246app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //takes away the title from the top of the page
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.SEND_SMS}, 1);
        }
        Button createNotificationButton = findViewById(R.id.button_notification);

            // private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // CharSequence name = getString(R.string.channel_name);
            // String description = getString(R.string.channel_discription);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("Notification", "Notification", NotificationManager.IMPORTANCE_DEFAULT);
            // channel.setDescription(description);
            // NotificationManager notificationManager = getSystemService(NotificationManager.class);
            // NotificationManager.createNotificationChannel(channel);

            // NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
            // managerCompat.notify(0, builder.build());
        }

        createNotificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // addNotification();
                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, "Notification" )
                        builder.setSmallIcon(R.drawable.ic_launcher_background);
                        builder.setContentTitle("Pain Journal reminder");
                        builder.setContentText("Pain Journal entry needed");
                        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
//                        builder.setContentIntent(pendingIntent);
                        builder.setAutoCancel(true);;

                NotificationManagerCompat managerCompat = NotificationManagerCompat.from(MainActivity.this);
                managerCompat.notify(0, builder.build());


            }
        });
    }

    /* private void addNotification() {
        // Builds Notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "Notification" )
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Pain Journal reminder")
                .setContentText("Pain Journal entry needed")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                // .setContentIntent(pendingIntent)
                .setAutoCancel(true);



        // Creates intent needed to show the notification
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        // Add as notification
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    } */




    public void callAllen(View view){
        Uri number = Uri.parse("tel:(870)2467070");
        Intent callIntent = new Intent(Intent.ACTION_DIAL, number);

        try{
            startActivity(callIntent);
        }catch (ActivityNotFoundException e){
            Toast.makeText(MainActivity.this, "Your phone can't make this call.", Toast.LENGTH_SHORT).show();
        }
    }
    public void openLawyerAct(View view){
        Intent intent = new Intent(this, Do_I_Need_A_Lawyer.class);
        startActivity(intent);
    }


    public void openCaseActivity(View view) {

        SharedPreferences data = getSharedPreferences("com.example.cs246app.data", Context.MODE_PRIVATE);
        Boolean isCaseActive = data.getBoolean("isCaseActive", false);
        Intent intent;
        if (isCaseActive) {
            intent = new Intent(this, CaseActivity.class);
        }else{
            intent = new Intent(this, StartCase.class);
        }
        startActivity(intent);
    }



    // This is for granting SMS permissions
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ContextCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission granted!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Well... This app won't be worth much to you...", Toast.LENGTH_SHORT).show();
            }
        }
    }
}