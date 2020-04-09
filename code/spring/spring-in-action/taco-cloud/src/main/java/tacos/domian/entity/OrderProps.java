package tacos.domian.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author liutiantian
 * @create 2020-04-09 15:45
 */
@Component
@Data
@ConfigurationProperties(prefix = "taco.orders")
@Validated
public class OrderProps {
    @Min(value = 5, message = "must be between 5 and 25")
    @Max(value = 5, message = "must be between 5 and 25")
    private int pageSize = 20;
}
