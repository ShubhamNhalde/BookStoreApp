package com.bookstoreapplication.controller;

import com.bookstoreapplication.dto.OrderDTO;
import com.bookstoreapplication.dto.ResponseDTO;
import com.bookstoreapplication.model.Order;
import com.bookstoreapplication.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/order")

public class OrderController {

    @Autowired
    IOrderService service;;

    @PostMapping("/insert")
    public ResponseEntity<ResponseDTO> addOrder(@RequestBody OrderDTO dto){
        Order order = service.addOrder(dto);
        ResponseDTO response = new ResponseDTO("Order Placed : ", order);
        return new ResponseEntity<ResponseDTO>(response,HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<ResponseDTO> getAll(){
        List<Order> list = service.getAll();
        ResponseDTO response = new ResponseDTO("Orders Placed : ", list);
        return new ResponseEntity<ResponseDTO>(response,HttpStatus.OK);

    }

    @GetMapping("/getById/{orderId}")
    public ResponseEntity<ResponseDTO> getById(@PathVariable Integer orderId){
        Order order = service.getByID(orderId);
        ResponseDTO response = new ResponseDTO("Order Retrieved : ", order);
        return new ResponseEntity<ResponseDTO>(response,HttpStatus.OK);

    }

    @PutMapping("/update/{orderId}")
    public ResponseEntity<ResponseDTO> updateById(@PathVariable Integer orderId,@RequestBody OrderDTO dto){
        Order order = service.updateById(orderId,dto);
        ResponseDTO response = new ResponseDTO("Order Updated : ", order);
        return new ResponseEntity<ResponseDTO>(response,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{orderId}")
    public ResponseEntity<ResponseDTO> deleteById(@PathVariable Integer orderId){
        Order order = service.deleteById(orderId);
        ResponseDTO response = new ResponseDTO("Order deleted : ", order);
        return new ResponseEntity<ResponseDTO>(response,HttpStatus.OK);
    }

//    @PutMapping("/cancelOrder/{orderId}/{userId}")
//    public ResponseEntity<ResponseDTO> cancelOrder(@PathVariable int orderId, @PathVariable int userId) {
//        String order = orderService.cancelOrder(orderId, userId);
//        ResponseDTO response = new ResponseDTO("id " + orderId, order);
//        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
//    }
}