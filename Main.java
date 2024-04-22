package edu.ejercicios;

import clases.Manager;
import clases.Telefono;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Conexión a la base de datos
        String connectionString = "mongodb://localhost:27017";
        try (var mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase("miBaseDeDatos");
            MongoCollection<Document> collection = database.getCollection("telefonos");

            // Crear un objeto Manager para interactuar con la base de datos
            Manager manager = new Manager(collection);

            // Crear dos teléfonos y almacenarlos en la base de datos
            Telefono telefono1 = new Telefono("Xiaomi", "Redmi Note 13", "Android", 6.6,
                    8, 128, true, 64, true, "189753289089754");
            Telefono telefono2 = new Telefono("Samsung", "Galaxy S24 Ultra", "Android", 6.8,
                    12, 512, true, 200, true, "987124429825873");

            manager.crearTelefono(telefono1);
            manager.crearTelefono(telefono2);

            // Leer los teléfonos de la base de datos y mostrarlos en consola
            List<Telefono> telefonos = manager.leerTelefonos();
            for (Telefono telefono : telefonos) {
                System.out.println("Marca: " + telefono.getMarca() + ", Modelo: " + telefono.getModelo());
            }

            // Actualizar un teléfono en la base de datos
            Telefono nuevoTelefono1 = new Telefono("Samsung", "Galaxy S22", "Android", 6.5,
                    12, 256, true, 108, true, "189753289089754");
            manager.actualizarTelefono("189753289089754", nuevoTelefono1);

            // Eliminar un teléfono de la base de datos
            manager.eliminarTelefono("987124429825873");
        } catch (Exception e) {
            System.err.println("Error al conectar con la base de datos MongoDB: " + e.getMessage());
        }
    }
}