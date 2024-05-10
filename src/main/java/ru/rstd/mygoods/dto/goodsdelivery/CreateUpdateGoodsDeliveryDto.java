package ru.rstd.mygoods.dto.goodsdelivery;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import ru.rstd.mygoods.dto.validation.group.OnCreate;
import ru.rstd.mygoods.dto.validation.group.OnUpdate;

@Data
@Builder
public class CreateUpdateGoodsDeliveryDto {

    @NotNull(groups = OnUpdate.class)
    private Long id;

    @NotBlank(message = "name must be filled in")
    @Length(max = 255, message = "name length must be less than 255 characters", groups = {OnCreate.class, OnUpdate.class})
    private String name;

    @NotNull(groups = {OnCreate.class, OnUpdate.class})
    private Long goodsId;

    @NotNull
    @Min(value = 1, groups = {OnCreate.class, OnUpdate.class})
    private Integer amount;
}
