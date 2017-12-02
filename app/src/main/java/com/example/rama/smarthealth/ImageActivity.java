package com.example.rama.smarthealth;

/**----------------------------------------------------------------------------------
 * Microsoft Developer & Platform Evangelism
 *
 * Copyright (c) Microsoft Corporation. All rights reserved.
 *
 * THIS CODE AND INFORMATION ARE PROVIDED "AS IS" WITHOUT WARRANTY OF ANY KIND,
 * EITHER EXPRESSED OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND/OR FITNESS FOR A PARTICULAR PURPOSE.
 *----------------------------------------------------------------------------------
 * The example companies, organizations, products, domain names,
 * e-mail addresses, logos, people, places, and events depicted
 * herein are fictitious.  No association with any real company,
 * organization, product, domain name, email address, logo, person,
 * places, or events is intended or should be inferred.
 *----------------------------------------------------------------------------------
 **/

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class ImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        // Creating an image view to display the image
        final ImageView imageView = (ImageView)findViewById(R.id.ChildImageView);

        //Get data from intent
        final String image = this.getIntent().getStringExtra("image");

        // Create an output stream in which the data is written into a byte array
        final ByteArrayOutputStream imageStream = new ByteArrayOutputStream();

        // Create a new Handler. A Handler allows communicating back with UI thread from other background thread.
        final Handler handler = new Handler();

        Thread th = new Thread(new Runnable() {
            public void run() {

                try {

                    long imageLength = 0;

                    ImageManager.GetImage(image, imageStream, imageLength);

                    // Executes this code along with the thread th that was created by the class.
                    handler.post(new Runnable() {

                        public void run() {
                            byte[] buffer = imageStream.toByteArray();

                            //  Create a bitmap from the byte array
                            Bitmap bitmap = BitmapFactory.decodeByteArray(buffer, 0, buffer.length);

                            // Set the image view to the bitmap created.
                            imageView.setImageBitmap(bitmap);
                        }
                    });
                }
                catch(Exception ex) {
                    final String exceptionMessage = ex.getMessage();
                    handler.post(new Runnable() {
                        public void run() {

                            //Displays the message which was received by the exception.
                            Toast.makeText(ImageActivity.this, exceptionMessage, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }});
        th.start();
    }
}

