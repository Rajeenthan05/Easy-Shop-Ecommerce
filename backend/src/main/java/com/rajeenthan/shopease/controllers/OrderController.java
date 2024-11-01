package com.rajeenthan.shopease.controllers;

import com.stripe.model.PaymentIntent;
import com.rajeenthan.shopease.auth.dto.OrderResponse;
import com.rajeenthan.shopease.dto.OrderRequest;
import com.rajeenthan.shopease.entities.Order;
import com.rajeenthan.shopease.services.OrderService;
import com.rajeenthan.shopease.services.PaymentIntentService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/order")
@CrossOrigin
public class OrderController {


    @Autowired
    OrderService orderService;



    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest orderRequest, Principal principal) throws Exception {
        OrderResponse orderResponse = orderService.createOrder(orderRequest,principal);
            //return new ResponseEntity<>(order, HttpStatus.CREATED);

        return new ResponseEntity<>(orderResponse,HttpStatus.OK);
    }

    @PostMapping("/update-payment")
    public ResponseEntity<?> updatePaymentStatus(@RequestBody Map<String,String> request){
        Map<String,String> response = orderService.updateStatus(request.get("paymentIntent"),request.get("status"));
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

}
