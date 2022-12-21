package com.springbootacademybatch4.pointofsalebatch4.dto.paginated;

import com.springbootacademybatch4.pointofsalebatch4.dto.ItemDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaginatedResponseItemDTO {
    private List<ItemDTO> itemDTOList;
    private long dataCount;
}
