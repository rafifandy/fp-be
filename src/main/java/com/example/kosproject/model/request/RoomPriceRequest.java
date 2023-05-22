package com.example.kosproject.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter
@ToString
public class RoomPriceRequest {
    @NotBlank(message = "Room id is required")
    private Integer roomId;

    @NotBlank(message = "Price is required")
    private Integer price;
}
