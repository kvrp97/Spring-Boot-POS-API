package com.springbootacademybatch4.pointofsalebatch4.service;

import com.springbootacademybatch4.pointofsalebatch4.dto.ItemDTO;
import com.springbootacademybatch4.pointofsalebatch4.dto.paginated.PaginatedResponseItemDTO;
import com.springbootacademybatch4.pointofsalebatch4.dto.request.RequestSaveItemDTO;

import java.util.List;

public interface ItemService {
    void addItem(RequestSaveItemDTO itemDTO);

    List<ItemDTO> getItemByName(String itemName);

    List<ItemDTO> getItemByState(String itemState);

    List<ItemDTO> getAllItems();

    String updateItem(ItemDTO itemDTO);

    String deleteItem(int itemId);

    PaginatedResponseItemDTO getAllItemsActive(int page, int size, boolean activeState);

    PaginatedResponseItemDTO getAllItemsActivePaginated(int page, int size, boolean activeState);


//    ItemDTO getItemById(int itemId);
    
}
