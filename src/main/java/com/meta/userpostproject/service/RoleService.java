package com.meta.userpostproject.service;

import com.meta.userpostproject.dto.RoleDto;

import java.util.List;

public interface RoleService {

    RoleDto createRole(RoleDto roleDto);

    List<RoleDto> findAllRole();
}
