package com.bookstoreapplication.service;

import com.bookstoreapplication.dto.OrderDTO;
import com.bookstoreapplication.model.Order;

import java.util.List;

public interface IOrderService {

    Order addOrder(OrderDTO dto);

    Order getByID(Integer orderId);

    List<Order> getAll();

    Order updateById(Integer orderId,OrderDTO dto);

    Order deleteById(Integer orderId);


   // String cancelOrder(int orderId, int userId);
}