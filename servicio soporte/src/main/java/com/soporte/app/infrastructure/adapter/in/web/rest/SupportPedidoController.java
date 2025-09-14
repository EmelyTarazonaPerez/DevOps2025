package com.soporte.app.infrastructure.adapter.in.web.rest;

import com.soporte.app.aplication.useCase.SupportOrderUseCase;
import com.soporte.app.infrastructure.adapter.in.web.dto.response.BodyResponse;
import com.soporte.app.infrastructure.adapter.in.web.dto.request.RequestOrder;
import com.soporte.app.infrastructure.adapter.in.web.mapping.IMapperBill;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static java.sql.DriverManager.println;

@RestController
@AllArgsConstructor
@RequestMapping("/api/support/bill")
public class SupportPedidoController {

    private final SupportOrderUseCase supportOrderUseCase;
    private final IMapperBill mapperBill;

    @GetMapping("/getAll")
    public ResponseEntity<BodyResponse> getAllBills() {
        println("Fetching all bills...");
        BodyResponse response = supportOrderUseCase.getAllBills();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<BodyResponse> createBill(@RequestBody RequestOrder requestOrder) {
        println("Creating a new bill...");
        BodyResponse response = supportOrderUseCase.saveOrder(mapperBill.requestToModel(requestOrder));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<BodyResponse> getBillById(@PathVariable Integer id) {
        println("Fetching bill with ID: " + id);
        BodyResponse response = supportOrderUseCase.getBillById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<BodyResponse> deleteBill(@PathVariable Integer id) {
        println("Deleting bill with ID: " + id);
        BodyResponse response = supportOrderUseCase.deleteBill(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<BodyResponse> updateBill(@PathVariable Integer id, @RequestBody RequestOrder requestOrder) {
        println("Updating bill with ID: " + id);
        BodyResponse response = supportOrderUseCase.updateBill(id, mapperBill.requestToModel(requestOrder));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
