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

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;
import com.microsoft.azure.storage.blob.ListBlobItem;

import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

public class ImageManager {
    // Connect to Azure by giving appropriate name and key
    public static final String storageConnectionString = "DefaultEndpointsProtocol=https;"
            + "AccountName=smarthealth123;"
            + "AccountKey=9Tlf78eXWCf7/Gn31+JdLYnWMnyDdcFro5ArFPAmRw1/okk19N5+sdsh2hq+WjnQxaR/R3id5FGslE3pY0EDkg==";


    private static CloudBlobContainer getContainer() throws Exception {
        // Retrieve storage account from connection-string.

        CloudStorageAccount storageAccount = CloudStorageAccount
                .parse(storageConnectionString);

        // Create the blob client.
        CloudBlobClient blobClient = storageAccount.createCloudBlobClient();

        // Get a reference to a container.h
        CloudBlobContainer container = blobClient.getContainerReference("image-container");

        return container;
    }

    public static String[] UploadImage(InputStream image, int imageLength) throws Exception {
        // Returns a reference to the container
        CloudBlobContainer container = getContainer();

        //Creates the container if it does not exist
        container.createIfNotExists();

        // Gives a random name to the image
        //String imageName = randomString(10);
        String imageName = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
        imageName = imageName+".jpg";

        // Gets the blob reference and uploads the image to that particular blob storage on azure.
        CloudBlockBlob imageBlob = container.getBlockBlobReference(imageName);
        imageBlob.upload(image, imageLength);

        String url = String.valueOf(imageBlob.getQualifiedUri());

        String res[] = new String[2];
        res[0] = url;
        res[1] = imageName;

        return res;

    }

    public static String[] ListImages() throws Exception{
        CloudBlobContainer container = getContainer();

        Iterable<ListBlobItem> blobs = container.listBlobs();

        LinkedList<String> blobNames = new LinkedList<>();

        // Gets all the blobs which contain the images and adds it to a linked list.
        for(ListBlobItem blob: blobs) {
            blobNames.add(((CloudBlockBlob) blob).getName());
        }

        return blobNames.toArray(new String[blobNames.size()]);
    }

    public static void GetImage(String name, OutputStream imageStream, long imageLength) throws Exception {
        CloudBlobContainer container = getContainer();

        CloudBlockBlob blob = container.getBlockBlobReference(name);
        // If the blob exists, gets all the properties of the image such as length.
        if(blob.exists()){
            blob.downloadAttributes();

            imageLength = blob.getProperties().getLength();
            System.out.println(blob.getProperties());

            blob.download(imageStream);
        }
    }

}
