package ml.wonwoo.sample.mapper;

import ml.wonwoo.sample.domain.Customer;
import ml.wonwoo.sample.domain.JavasCustomer;
import ml.wonwoo.sample.dto.CustomerDto;
import ml.wonwoo.sample.dto.JavasCustomerDto;

public interface CustomerMapper {

    CustomerDto customerDto(Customer customer);

    JavasCustomerDto javasCustomerDto(JavasCustomer customer);
}
