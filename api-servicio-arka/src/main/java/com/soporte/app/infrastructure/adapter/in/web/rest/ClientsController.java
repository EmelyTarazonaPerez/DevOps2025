package com.soporte.app.infrastructure.adapter.in.web.rest;

import com.soporte.app.aplication.useCase.ClientUseCase;
import com.soporte.app.domain.model.Client;
import com.soporte.app.infrastructure.adapter.in.web.dto.request.RequestClient;
import com.soporte.app.infrastructure.adapter.in.web.dto.response.ResponseClient;
import com.soporte.app.infrastructure.adapter.in.web.mapping.MapperClientImplementation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/clients")
public class ClientsController {
    private final ClientUseCase serviceClient;
    private final MapperClientImplementation mapClient;

    @GetMapping
    public ResponseEntity<List<ResponseClient>> getAllClients() {
        List<ResponseClient> clients = serviceClient.getAllClients();
        if (clients.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return new ResponseEntity<>( clients, HttpStatus.OK); // HttpStatus 200
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getClientById(@PathVariable Integer id) {
            try {
                ResponseClient client = serviceClient.getClientById(id);
                return ResponseEntity.ok(client); // 200 OK
            } catch (RuntimeException e) {
                Map<String, String> error = new HashMap<>();
                error.put("message", "Cliente con ID " + id + " no encontrado");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error); // 404 con mensaje
            }
    }

    @PostMapping
    public ResponseEntity<ResponseClient> newClient(@RequestBody RequestClient client) {
        Client clientUpdate = serviceClient.addClient(mapClient.responseClientToModel(client));
        if (clientUpdate == null) {
            return ResponseEntity.badRequest().build(); // 400 Bad Request
        }
        ResponseClient clientResult = mapClient.responseClient(clientUpdate);
        return new ResponseEntity<>(clientResult, HttpStatus.CREATED); // 201 Created
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseClient> updateClient(@PathVariable Integer id, @RequestBody RequestClient client) {
        try {
            Client clientDetails = mapClient.responseClientToModel(client);
            ResponseClient updated = serviceClient.updateClient(id, clientDetails);
            return ResponseEntity.ok(updated); // 200 OK
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(serviceClient.deleteClient(id));
        } catch (RuntimeException e) {
            return new ResponseEntity<>(
                    HttpStatus.NOT_FOUND); // 404 Not Found;
        }
    }

    @GetMapping("/filter/{name}")
    public ResponseEntity<Client> getClientByName(@PathVariable String name) {
        try {
            return ResponseEntity.ok(serviceClient.filterClientByName(name));
        } catch (RuntimeException e) {
            return new ResponseEntity<>(
                    HttpStatus.NOT_FOUND); // 404 Not Found;
        }
    }

    @GetMapping("/order")
    public ResponseEntity<List<ResponseClient>> getClientsByOrderAsc(@RequestParam(defaultValue = "true") boolean orderAsc) {
        try {
            List<ResponseClient> clients = serviceClient.getClientsByOrderAsc(orderAsc);
            if (clients.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return new ResponseEntity<>(clients, HttpStatus.OK); // HttpStatus 200
        } catch (RuntimeException e) {
            return new ResponseEntity<>(
                    HttpStatus.NOT_FOUND); // 404 Not Found;
        }
    }

}
