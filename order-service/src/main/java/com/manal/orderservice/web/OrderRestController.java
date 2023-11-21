package com.manal.orderservice.web;

import com.manal.orderservice.entities.Order;
import com.manal.orderservice.model.Customer;


import com.manal.orderservice.model.Product;
import com.manal.orderservice.repository.OrderRepository;
import com.manal.orderservice.repository.ProductItemRepository;
import com.manal.orderservice.service.CustomerRestClientService;
import com.manal.orderservice.service.InventoryRestClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class OrderRestController {
    private OrderRepository orderRepository;
    private ProductItemRepository productItemRepository;
    private CustomerRestClientService customerRestClientService;
    private InventoryRestClientService inventoryRestClientService;

    public OrderRestController(OrderRepository orderRepository, ProductItemRepository productItemRepository, CustomerRestClientService customerRestClientService, InventoryRestClientService inventoryRestClientService) {
        this.orderRepository = orderRepository;
        this.productItemRepository = productItemRepository;
        this.customerRestClientService = customerRestClientService;
        this.inventoryRestClientService = inventoryRestClientService;
    }

    @GetMapping("/fullOrder/{id}")
    public Order getOrder(@PathVariable Long id){
        Order order=orderRepository.findById(id).get();

        Customer customer=customerRestClientService.customerById(order.getCustomerId());
        order.setCustomer(customer);
        order.getProductItems().forEach(pi->
        {
            Product product=inventoryRestClientService.productById(pi.getProductId());
            pi.setProduct(product);
        });

        return order;
    }
}
