package mond.mamind.src.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostOrderReq
{
    private Long restaurantIdx;

    private String ownerComment;
    private String riderComment;
    private String payment;

    private int deliveryPrice;
    private int discountPer;
    private int discountPrice;
}
