package ml.wonwoo.sample.mapper;

import ml.wonwoo.sample.domain.Customer;
import ml.wonwoo.sample.dto.CustomerDto;

public interface CustomerMapper {

    CustomerDto customerDto(Customer customer);

}
