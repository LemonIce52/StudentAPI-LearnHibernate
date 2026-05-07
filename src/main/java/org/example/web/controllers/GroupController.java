package org.example.web.controllers;

import org.example.web.dto.CreateGroupDTO;
import org.example.web.dto.GroupDTO;
import org.example.web.service.GroupWebService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/group")
public class GroupController {

    private final GroupWebService groupWebService;

    public GroupController(GroupWebService groupWebService) {
        this.groupWebService = groupWebService;
    }

    @GetMapping("/{id}")
    public GroupDTO getGroup(
            @PathVariable("id") Long id
    ) {
        return groupWebService.getGroup(id);
    }

    @PostMapping("/create")
    public GroupDTO createGroup(
            @RequestBody CreateGroupDTO createGroup
    ) {
        return groupWebService.createGroup(createGroup);
    }
}
