package com.firebase;

import com.google.auth.oauth2.GoogleCredentials;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileInputStream;
import java.io.IOException;

public class Conexion {
    public static void conectarFirebase(){
        try {
            FileInputStream serviceAccount =
                    new FileInputStream("dds-2023-firebase.json");

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://dds-2023-grupo-diegote-default-rtdb.firebaseio.com")
                    .build();

            FirebaseApp.initializeApp(options);
            System.out.println("Conexion con firebase completada!\n");
        } catch (IOException e) {
            System.out.println("Error: "+e.getMessage());
        }
    }
}
