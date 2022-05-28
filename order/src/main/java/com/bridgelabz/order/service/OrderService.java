package com.bridgelabz.order.service;

import com.bridgelabz.order.dto.OrderDTO;
import com.bridgelabz.order.exception.BookException;
import com.bridgelabz.order.exception.OrderException;
import com.bridgelabz.order.exception.UserException;
import com.bridgelabz.order.model.Book;
import com.bridgelabz.order.model.Order;
import com.bridgelabz.order.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class OrderService implements IOrderService {

    @Autowired
    OrderRepository repo;

    @Autowired
    RestTemplate restTemplate;

    //to place the order in book store
    @Override
    public Order addOrder(OrderDTO dto) {
        Object user = restTemplate.getForObject("http://localhost:9000/user/findById/" + dto.getUserId(), Object.class);
        if(user.equals(null)) {
            throw new UserException("Invalid user id...please provide valid user id");
        }
        else
        {
            Book book = restTemplate.getForObject("http://localhost:9001/book/getBook/" + dto.getBookId(), Book.class);
            if(book.equals(null))
            {
                throw new BookException("Invalid book id...please provide valid book id");
            }
            else
            {
                if(dto.getQuantity() >(int)book.getQuantity())
                {
                    throw new BookException("Currently we dont have that much books available");
                }
                else
                {
                    Order order = new Order(dto);
                    order.setPrice(dto.getQuantity()*book.getPrice());
                    order.setDate(LocalDate.now());
                    repo.save(order);
                    return order;
                }
            }
        }
    }

    //to retrieve list of orders
    @Override
    public List<Order> getAll() {
        List<Order> orders =  repo.findAll();
        return orders;
    }


    //to get order by id
    @Override
    public Order getByID(Integer orderId) {
        Optional<Order> order = repo.findById(orderId);
        if(order.isPresent()) {
            throw new OrderException("Invalid Order Id...please provide valid Order id");
        }
        return order.get();
    }

    //to update existing Order
    @Override
    public Order updateById(Integer orderId,OrderDTO dto) {
        Optional<Order> order = repo.findById(orderId);
        if(order.isPresent()) {
            throw new OrderException("Invalid Order Id...please provide valid Order id");
        }
        Object user = restTemplate.getForObject("http://localhost:9000/user/findById/" + dto.getUserId(), Object.class);
        if(user.equals(null)) {
            throw new UserException("Invalid user id...please provide valid user id");
        }
        Book book = restTemplate.getForObject("http://localhost:9001/Book/getBook/" + dto.getBookId(), Book.class);
        if(book.equals(null)) {
            throw new BookException("Invalid book id...please provide valid book id");
        }
        Order newOrder = new Order(dto);
        newOrder.setPrice(dto.getQuantity()*book.getPrice());
        repo.save(newOrder);
        return newOrder;
    }

    //to delete order using order Id
    @Override
    public Order deleteById(Integer orderId) {
        Optional<Order> order = repo.findById(orderId);
        if(order.isPresent()) {
            throw new OrderException("Invalid Order Id...please provide valid Order id");
        }
        repo.delete(order.get());
        return order.get();
    }



//    @Override
//    public String cancelOrder(int orderId, int userId) {
//        Optional<User> isPresent = userRepo.findById(userId);
//        if (isPresent.isPresent()) {
//            Optional<Order> order = orderRepo.findById(orderId);
//            if (order.isPresent()) {
//                order.get().setCancel(true);
//                orderRepo.save(order.get());
//                return "Cancel order Successful";
//            }
//        }
//        return "cancel order not successful";
//    }
}
