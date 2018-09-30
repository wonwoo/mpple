package ml.wonwoo.sample.mapper;

import ml.wonwoo.sample.domain.Customer;
import ml.wonwoo.sample.dto.CustomerDto;
import ml.wonwoo.spring.Mppled;

@Mppled
public interface SpringMapper {

    CustomerDto customerDto(Customer customer);
}
