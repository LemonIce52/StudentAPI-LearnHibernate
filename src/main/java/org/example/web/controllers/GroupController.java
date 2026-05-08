package org.example.web.controllers;

import jakarta.validation.Valid;
import org.example.web.dto.CreateGroupDTO;
import org.example.web.dto.GroupDTO;
import org.example.web.dto.UpdateGroupDTO;
import org.example.web.service.GroupWebService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/group")
public class GroupController {

    private final Logger logger = LoggerFactory.getLogger(GroupController.class);
    private final GroupWebService groupWebService;

    public GroupController(GroupWebService groupWebService) {
        this.groupWebService = groupWebService;
    }

    @GetMapping
    public List<GroupDTO> getAllGroup() {
        logger.info("Call method get all group");
        return groupWebService.getAllGroup();
    }

    @GetMapping("/{id}")
    public GroupDTO getGroup(
            @PathVariable("id") Long id
    ) {
        logger.info("Call method get group id={}", id);
        return groupWebService.getGroup(id);
    }

    @PostMapping("/create")
    public GroupDTO createGroup(
            @Valid @RequestBody CreateGroupDTO createGroup
    ) {
        logger.info("Call method create group createGroupDTO={}", createGroup);
        return groupWebService.createGroup(createGroup);
    }

    @PutMapping("/update")
    public GroupDTO updateGroup(
            @Valid @RequestBody UpdateGroupDTO updateGroupDTO
    ) {
        logger.info("Call method update group updateGroupDTO={}", updateGroupDTO);
        return groupWebService.updateGroup(updateGroupDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteGroup(
            @PathVariable("id") Long id
    ) {
        logger.info("Call method delete group id={}", id);
        groupWebService.deleteGroup(id);
    }
}
