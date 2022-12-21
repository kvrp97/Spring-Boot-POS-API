package com.springbootacademybatch4.pointofsalebatch4.util.mappers;

import com.springbootacademybatch4.pointofsalebatch4.dto.ItemDTO;
import com.springbootacademybatch4.pointofsalebatch4.dto.request.RequestSaveItemDTO;
import com.springbootacademybatch4.pointofsalebatch4.entity.Item;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    Item requestDtoToEntity(RequestSaveItemDTO requestSaveItemDTO);
    List<ItemDTO> entityListToDtoList(List<Item> items);
    List<ItemDTO> pageToList(Page<Item> items);
}
