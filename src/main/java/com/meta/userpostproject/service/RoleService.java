package com.meta.userpostproject.service;

import com.meta.userpostproject.dto.RoleDto;
import com.meta.userpostproject.model.Role;

import java.util.List;

public interface RoleService {

    RoleDto createRole(RoleDto roleDto);

    List<RoleDto> findAllRole();

    void deleteRole(short id);

    RoleDto findById(short id);

    //void updateById(RoleDto roleDto);


}
