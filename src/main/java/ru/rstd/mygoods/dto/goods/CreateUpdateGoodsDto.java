package ru.rstd.mygoods.dto.goods;


import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import ru.rstd.mygoods.dto.validation.annotation.ValidPrice;
import ru.rstd.mygoods.dto.validation.group.OnCreate;
import ru.rstd.mygoods.dto.validation.group.OnUpdate;
import ru.rstd.mygoods.dto.validation.annotation.InStock;

@Data
@Builder
public class CreateUpdateGoodsDto {

    @NotBlank(groups = OnUpdate.class)
    private String id;

    @NotBlank(message = "name must be filled in")
    @Length(max = 255, message = "name length must be less than 255 characters", groups = {OnCreate.class, OnUpdate.class})
    private String name;

    @Length(max = 4096, message = "name length must be less than 4096 characters", groups = {OnCreate.class, OnUpdate.class})
    private String description;

    @ValidPrice(groups = {OnCreate.class, OnUpdate.class})
    private String price;

    @InStock(groups = {OnCreate.class, OnUpdate.class})
    private String inStock;
}
