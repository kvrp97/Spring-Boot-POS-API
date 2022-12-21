package com.springbootacademybatch4.pointofsalebatch4.controller;

import com.springbootacademybatch4.pointofsalebatch4.dto.ItemDTO;
import com.springbootacademybatch4.pointofsalebatch4.dto.paginated.PaginatedResponseItemDTO;
import com.springbootacademybatch4.pointofsalebatch4.dto.request.RequestSaveItemDTO;
import com.springbootacademybatch4.pointofsalebatch4.service.ItemService;
import com.springbootacademybatch4.pointofsalebatch4.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import java.util.List;

@RestController
@RequestMapping("api/v1/item")
@CrossOrigin
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping(path = "/save")
    public String saveItem(@RequestBody RequestSaveItemDTO itemDTO){
        itemService.addItem(itemDTO);
        return "Item Saved";
    }

    @PutMapping(path = "/update")
    public String updateItem(@RequestBody ItemDTO itemDTO){
        String updated = itemService.updateItem(itemDTO);
        return updated;
    }

    @GetMapping(path = "/get-by-name", params = "name")
    public List<ItemDTO> getItemByName(@RequestParam(value = "name") String itemName){
        List<ItemDTO> itemDTO = itemService.getItemByName(itemName);
        return itemDTO;
    }

    @GetMapping(path = "/get-by-state", params = "state")
    public List<ItemDTO> getItemByState(@RequestParam(value = "state") String itemState){
        List<ItemDTO> itemDTO = itemService.getItemByState(itemState);
        return itemDTO;
    }
//
//    @GetMapping(path = "/get-all-items")
//    public List<ItemDTO> getAllItems(){
//        List<ItemDTO> itemDTOList = itemService.getAllItems();
//        return itemDTOList;
//    }

    @GetMapping(path = {"/get-all-items"})
    public ResponseEntity<StandardResponse> getAllItems(){
        List<ItemDTO> itemDTOList = itemService.getAllItems();
//        ResponseEntity<StandardResponse> m = new ResponseEntity<StandardResponse>(
//                new StandardResponse(200, "Success", itemDTOList), HttpStatus.OK
//        );
//        return m;

        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "Success", itemDTOList),
                HttpStatus.OK
        );
    }

    @GetMapping(
            path = "/get-all-items-active-paginated",
            params = {"page","size","activeState"}
    )
    public ResponseEntity<StandardResponse> getAllItemsActivePaginated(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") @Max(50) int size,
            @RequestParam(value = "activeState") boolean activeState
    ){
        PaginatedResponseItemDTO paginatedResponseItemDTO = itemService.getAllItemsActivePaginated(page,size,activeState);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "Success", paginatedResponseItemDTO),
                HttpStatus.OK
        );
    }


    @DeleteMapping(path = "/delete/{id}")
    public String deleteItem(@PathVariable(value = "id") int itemId){
        String deleted = itemService.deleteItem(itemId);
        return deleted;
    }

    /*
     sending a variable
    01-send via path - path variable
    02-request param variable
    */



}
