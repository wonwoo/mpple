package ml.wonwoo.sample;

import ml.wonwoo.Mpple;
import ml.wonwoo.modelmapper.mapped.ModelMapperMapped;
import ml.wonwoo.sample.domain.Customer;
import ml.wonwoo.sample.dto.CustomerDto;
import ml.wonwoo.sample.mapper.CustomerMapper;
import org.junit.Test;

public class ModelMapperSample extends AbstractSample {

    @Test
    public void model_mapper_sample() {
        CustomerMapper mapper = Mpple.builder()
            .mapped(new ModelMapperMapped())
            .target(CustomerMapper.class);
        Customer customer = createCustomer();
        CustomerDto customerDto = mapper.customerDto(customer);
        assertThatCustomerDto(customerDto);
    }
}
