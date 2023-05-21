package com.meta.userpostproject.service;

import com.meta.userpostproject.dto.RoleDto;
import com.meta.userpostproject.model.Post;
import com.meta.userpostproject.model.Role;
import com.meta.userpostproject.repo.RoleRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepo roleRepo;

    public RoleServiceImpl(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    @Override
    public RoleDto createRole(RoleDto roleDto) {
        Role role = new Role(roleDto);
        Role savedRole = roleRepo.save(role);
        return new RoleDto(savedRole);
    }

    @Override
    public List<RoleDto> findAllRole() {
        List<Role> roleList = roleRepo.findAll();
        return roleList.stream().map(RoleDto::new).collect(Collectors.toList());
    }

    @Override
    public void deleteRole(short id) {
        roleRepo.deleteById(id);
    }

    @Override
    public RoleDto findById(short id) {
        Optional<Role> optionalRole=roleRepo.findById(id);
        if(optionalRole.isPresent()){
           Role role=optionalRole.get();
           return RoleDto
                   .builder()
                   .id(role.getId())
                   .name(role.getName())
                   .description(role.getDescription())
                   .roleType(role.getRoleType())
                   .build();

        }
        return null;
    }

}
