package com.firebase;

import Usuarios.Usuario;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class CRUDFirebase {
    static Firestore bd;

    public static boolean agregar(Usuario usuario){

        Map<String,Object> u = new HashMap<>();
        u.put("Contrasena",usuario.getContrasenia());
        u.put("Telefono",usuario.getNro_telefono());
        u.put("Correo",usuario.getCorreo());

        //TODO: VER
        bd = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> future = bd.collection("Usuario").document(UUID.randomUUID().toString()).set(u);
        try {
            System.out.println("Hora de actualizacion: " + future.get().getUpdateTime());
        } catch (InterruptedException| ExecutionException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return true;
    }


}

