package ml.wonwoo.sample;

import static org.assertj.core.api.Assertions.assertThat;

import ml.wonwoo.sample.domain.Customer;
import ml.wonwoo.sample.dto.CustomerDto;
import ml.wonwoo.sample.mapper.SpringMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CustomSpringBootSample extends AbstractSample {

    @Autowired
    private SpringMapper springMapper;

    @Test
    public void spring_boot_sample() {
        Customer customer = createCustomer();
        CustomerDto customerDto = springMapper.customerDto(customer);
        assertThat(customerDto.getName()).isEqualTo("성남");
    }

    @TestConfiguration
    static class TestConfig {

        @Bean
        Converter<Customer, CustomerDto> converter() {
            return new AbstractConverter<Customer, CustomerDto>() {
                @Override
                protected CustomerDto convert(Customer source) {
                    CustomerDto customerDto = new CustomerDto();
                    customerDto.setName(source.getAddress().getCity());
                    return customerDto;
                }
            };
        }

//        @Bean
//        PropertyMap<Customer, CustomerDto> propertyMap() {
//            return new PropertyMap<Customer, CustomerDto>() {
//                @Override
//                protected void configure() {
//                    map().setName(source.getAddress().getZipcode());
//                }
//            };
//        }
    }
}
