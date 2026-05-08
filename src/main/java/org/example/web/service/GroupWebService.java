package org.example.web.service;

import org.example.repository.entities.Group;
import org.example.repository.service.GroupDBService;
import org.example.web.converters.ConverterDTO;
import org.example.web.dto.CreateGroupDTO;
import org.example.web.dto.GroupDTO;
import org.example.web.dto.UpdateGroupDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupWebService {

    private final ConverterDTO converterDTO;
    private final GroupDBService groupDBService;

    public GroupWebService(ConverterDTO converterDTO, GroupDBService groupDBService) {
        this.converterDTO = converterDTO;
        this.groupDBService = groupDBService;
    }

    public GroupDTO getGroup(Long id) {
        return converterDTO.convertGroupToDto(groupDBService.getGroup(id));
    }

    public GroupDTO createGroup(CreateGroupDTO createGroupDTO) {
        Group newGroup = new Group(createGroupDTO.name(), createGroupDTO.graduationYear());
        Group savedGroup = groupDBService.saveGroup(newGroup);
        return converterDTO.convertGroupToDto(savedGroup);
    }

    public List<GroupDTO> getAllGroup() {
        List<Group> groupList = groupDBService.getAllGroup();
        return groupList
                .stream()
                .map(converterDTO::convertGroupToDto)
                .toList();
    }

    public void deleteGroup(Long id) {
        groupDBService.deleteGroup(id);
    }

    public GroupDTO updateGroup(UpdateGroupDTO updateGroupDTO) {
        Group group = groupDBService.getGroup(updateGroupDTO.id());

        if (updateGroupDTO.name() != null) {
            group.setName(updateGroupDTO.name());
        }

        if (updateGroupDTO.graduationYear() != null) {
            group.setGraduationYear(updateGroupDTO.graduationYear());
        }

        Group updatebleGroup = groupDBService.updateGroup(group);
        return converterDTO.convertGroupToDto(updatebleGroup);
    }
}
