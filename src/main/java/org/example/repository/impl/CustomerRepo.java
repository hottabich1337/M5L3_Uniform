package org.example.repository.impl;

import lombok.SneakyThrows;
import org.example.entity.Customer;
import org.example.repository.ConnectionPool;
import org.example.repository.Repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class CustomerRepo implements Repo<Customer> {
    private final ConnectionPool pool;
    public static final String GET_BY_ID= """
            SELECT id,login,password
            from users
            where id= &""";

    public CustomerRepo(ConnectionPool pool) {
        this.pool = pool;
    }

    @Override
    @SneakyThrows
    public Customer getById(Long id){
        try(Connection connection = pool.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID);
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                Customer customer = new Customer();
                customer.setId(resultSet.getLong("id"));
                customer.setLogin(resultSet.getString("login"));
                customer.setPassword(resultSet.getString("password"));
                return customer;
            }

        }
        return null;
    }

    @Override
    @SneakyThrows
    public void update(Customer entity){

    }
}
