package com.arka.cotizador.utils;

import java.util.HashMap;
import java.util.Map;

public class FakeDB {
    private static final Map<String, String> productos = new HashMap<>();

    static {
        productos.put("XYZ", "Producto XYZ");
        productos.put("ABC", "Producto ABC");
        productos.put("123", "Producto 123");
    }

    public static boolean existeProducto(String codigo) {
        return productos.containsKey(codigo);
    }

    public static String obtenerProducto(String codigo) {
        return productos.get(codigo);
    }

    public static void main(String[] args) {
        String codigo = "XYZ";

        if (existeProducto(codigo)) {
            System.out.println("Producto encontrado: " + obtenerProducto(codigo));
        } else {
            System.out.println(" Producto no existe");
        }
    }
}
