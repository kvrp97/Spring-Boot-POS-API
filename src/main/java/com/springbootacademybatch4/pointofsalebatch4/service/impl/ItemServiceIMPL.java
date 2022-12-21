package com.springbootacademybatch4.pointofsalebatch4.service.impl;

import com.springbootacademybatch4.pointofsalebatch4.dto.ItemDTO;
import com.springbootacademybatch4.pointofsalebatch4.dto.paginated.PaginatedResponseItemDTO;
import com.springbootacademybatch4.pointofsalebatch4.dto.request.RequestSaveItemDTO;
import com.springbootacademybatch4.pointofsalebatch4.entity.Item;
import com.springbootacademybatch4.pointofsalebatch4.exception.NotFoundException;
import com.springbootacademybatch4.pointofsalebatch4.repo.ItemRepo;
import com.springbootacademybatch4.pointofsalebatch4.service.ItemService;
import com.springbootacademybatch4.pointofsalebatch4.util.mappers.ItemMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceIMPL implements ItemService {

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ItemMapper itemMapper;

    @Override
    public void addItem(RequestSaveItemDTO requestSaveItemDTO) {
//        Item item = modelMapper.map(requestSaveItemDTO, Item.class);
        Item item = itemMapper.requestDtoToEntity(requestSaveItemDTO);
        item.setActiveState(false);

        if (!itemRepo.existsById(item.getItemId())){
            itemRepo.save(item);
        } else {
            System.out.println("Item Id already exists.");
        }
    }

    @Override
    public List<ItemDTO> getItemByName(String itemName) {
        List<Item> items = itemRepo.findAllByItemNameEqualsAndActiveStateEquals(itemName, true);
        List<ItemDTO> itemDTOList = itemMapper.entityListToDtoList(items);
        return itemDTOList;
    }

    @Override
    public List<ItemDTO> getItemByState(String itemState) {

//        if (itemState.equals("active")){
//            List<Item> items = itemRepo.findAllByActiveStateEquals(true);
//            List<ItemDTO> itemDTOList = itemMapper.entityListToDtoList(items);
//            return itemDTOList;
//        } else if (itemState.equals("inactive")) {
//            List<Item> items = itemRepo.findAllByActiveStateEquals(false);
//            List<ItemDTO> itemDTOList = itemMapper.entityListToDtoList(items);
//            return itemDTOList;
//        } else if (itemState.equals("all")) {
//            List<Item> items = itemRepo.findAll();
//            List<ItemDTO> itemDTOList = itemMapper.entityListToDtoList(items);
//            return itemDTOList;
//        }else {
//            return null;
//        }

        switch (itemState){
            case "all":
                List<Item> items1 = itemRepo.findAll();
                List<ItemDTO> itemDTOList1 = itemMapper.entityListToDtoList(items1);
                return itemDTOList1;
            case "active":
                List<Item> items2 = itemRepo.findAllByActiveStateEquals(true);
                return itemMapper.entityListToDtoList(items2);
            case "inactive":
                List<Item> items3 = itemRepo.findAllByActiveStateEquals(false);
                return itemMapper.entityListToDtoList(items3);
            default: return null;
        }
    }
//----------------------------------------------------------------
    @Override
    public List<ItemDTO> getAllItems() {
        List<Item> itemList = itemRepo.findAll();
        if(itemList.size() > 0) {
            List<ItemDTO> itemDTOList = itemMapper.entityListToDtoList(itemList);
            return itemDTOList;
        }
        throw new NotFoundException("No Data Found..!");
    }

    @Override
    public String updateItem(ItemDTO itemDTO) {
        if (itemRepo.existsById(itemDTO.getItemId())){
            Item item = itemRepo.getById(itemDTO.getItemId());

            item.setItemName(itemDTO.getItemName());
            item.setSellingPrice(itemDTO.getSellingPrice());
            item.setSupplierPrice(itemDTO.getSupplierPrice());
            item.setBalanceQty(itemDTO.getBalanceQty());
            item.setMeasuringUnitType(itemDTO.getMeasuringUnitType());

            itemRepo.save(item);
            return "Item Updated";
        } else {
            System.out.println("No item found for that ID");
            return "No item found for that ID";
        }
    }

    @Override
    public String deleteItem(int itemId) {
        if (itemRepo.existsById(itemId)) {
            itemRepo.deleteById(itemId);
            return "Deleted";
        } else {
            return "No customer found for that Id";
        }
    }

    @Override
    public PaginatedResponseItemDTO getAllItemsActive(int page, int size, boolean activeState) {
        Page<Item> items = itemRepo.findAllByActiveStateEquals(activeState, PageRequest.of(page, size));   //filer first one
        if (items.getSize()<1){
            throw new NotFoundException("No Data");
        }
//        List<ItemDTO> itemDTOList = itemMapper.pageToList(items);
//        int count = itemRepo.countAllByActiveStateEquals(activeState);
//        PaginatedResponseItemDTO paginatedResponseItemDTO = new PaginatedResponseItemDTO(
//                itemDTOList,
//                count
//        );
//        return paginatedResponseItemDTO;

        return new PaginatedResponseItemDTO(
                itemMapper.pageToList(items),
                itemRepo.countAllByActiveStateEquals(activeState)
        );
    }

    @Override
    public PaginatedResponseItemDTO getAllItemsActivePaginated(int page, int size, boolean activeState) {
        Page<Item> items = itemRepo.findAllByActiveStateEquals(activeState, PageRequest.of(page, size));   //filer first one
        if (items.isEmpty()){
            throw new NotFoundException("No Data");
        }
        return new PaginatedResponseItemDTO(
                itemMapper.pageToList(items),
                itemRepo.countAllByActiveStateEquals(activeState)
        );
    }

//
//    @Override
//    public ItemDTO getItemById(int itemId) {
//        Item item = itemRepo.getById(itemId);
//        ItemDTO itemDTO = modelMapper.map(item, ItemDTO.class);
//        return itemDTO;
//    }
//
//    @Override
//    public List<ItemDTO> getAllItems() {
//
//        List<Item> getItems = itemRepo.findAll();
//          // using model mapper
//        if (!getItems.isEmpty()) {
//            List<ItemDTO> itemDTOList = modelMapper.map(getItems, new TypeToken<List<ItemDTO>>(){}.getType());
//            return itemDTOList;
//        } else {
//            return null;
//        }

//          // manually
////        List<ItemDTO> itemDTOList = new ArrayList<>();
////
////        for (Item item : getItems){
////            ItemDTO itemDTO = new ItemDTO(
////                    item.getItemId(),
////                    item.getItemName(),
////                    item.getSellingPrice(),
////                    item.isActiveState()
////            );
////
////            itemDTOList.add(itemDTO);
////        }
////        return itemDTOList;
//    }

}
