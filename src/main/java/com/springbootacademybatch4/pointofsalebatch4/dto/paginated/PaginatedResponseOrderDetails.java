package com.springbootacademybatch4.pointofsalebatch4.dto.paginated;

import com.springbootacademybatch4.pointofsalebatch4.dto.response.ResponseOrderDetailsDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaginatedResponseOrderDetails {
    private List<ResponseOrderDetailsDTO> orderDetailsList;
    private long dataCount;
}
