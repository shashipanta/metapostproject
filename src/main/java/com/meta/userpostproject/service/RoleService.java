package com.meta.userpostproject.Service;

import com.meta.userpostproject.dto.RoleDto;

import java.util.List;

public interface RoleService {

    RoleDto createRole(RoleDto roleDto);

    List<RoleDto> findAllRole();
}
