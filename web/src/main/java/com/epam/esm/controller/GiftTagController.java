package com.epam.esm.controller;

import com.epam.esm.dto.GiftTagDTO;
import com.epam.esm.service.GiftTagService;
import com.epam.esm.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/giftTag")
public class GiftTagController {

    private final GiftTagService giftTagService;

    @Autowired
    public GiftTagController(GiftTagService giftTagService) {
        this.giftTagService = giftTagService;
    }

    @PostMapping
    public GiftTagDTO save(@RequestBody GiftTagDTO giftTagDTO) throws ServiceException {
        return giftTagService.save(giftTagDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable(name = "id") int id) throws ServiceException {
        giftTagService.delete(id);
    }
}
