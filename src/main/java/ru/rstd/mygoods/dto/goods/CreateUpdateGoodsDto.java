package ru.rstd.mygoods.dto.goods;


import jakarta.validation.constraints.DecimalMin;;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import ru.rstd.mygoods.dto.validation.group.OnCreate;
import ru.rstd.mygoods.dto.validation.group.OnUpdate;
import ru.rstd.mygoods.dto.validation.annotation.InStock;

@Data
public class CreateUpdateGoodsDto {

    @NotBlank(groups = OnUpdate.class)
    private String id;

    @NotBlank(message = "name must be filled in")
    @Length(max = 255, message = "name length must be less than 255 characters", groups = {OnCreate.class, OnUpdate.class})
    private String name;

    @Length(max = 4096, message = "name length must be less than 4096 characters", groups = {OnCreate.class, OnUpdate.class})
    private String description;
    @DecimalMin(value = "0", message = "price can not be less than 0", groups = {OnCreate.class, OnUpdate.class})
    private String price;
    @InStock(groups = {OnCreate.class, OnUpdate.class})
    private String inStock;
    // TODO: 06.05.2024 write custom validator
}
