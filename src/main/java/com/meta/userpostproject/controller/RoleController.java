package com.meta.userpostproject.controller;

import com.meta.userpostproject.service.RoleService;
import com.meta.userpostproject.dto.RoleDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("role")

public class RoleController {


    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }


    @GetMapping
    public String role(Model model) {
        if (model.getAttribute("roleDto") == null) {
            model.addAttribute("role", new RoleDto());
        } else {
            model.addAttribute("role", model.getAttribute("roleDto"));
        }

        List<RoleDto> roles = roleService.findAllRole();
        model.addAttribute("roles", roles);
        return "admin/role";
    }

    @PostMapping("/create")
    public String createRole(@ModelAttribute("role") RoleDto roleDto, BindingResult result, Model model,
                             RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            model.addAttribute("role", roleDto);
            return "admin/role";
        }
        try {
            roleService.createRole(roleDto);
        } catch (Exception e) {
            String msg = "";
            if (e.getMessage().contains("uk_role_name")) {
                msg += "Sorry Role " + roleDto.getName() + " already Exist.\n";
                redirectAttributes.addFlashAttribute("error", msg);

            } else {
                redirectAttributes.addFlashAttribute("error", e.getMessage());
            }
            return "redirect:/role?fail";
        }
        return "redirect:/role?success";
    }

    @GetMapping("/delete/{id}")
    public String deleteRole(@PathVariable("id") Short id) {
        roleService.deleteRole(id);
        return "redirect:/role";
    }

    @GetMapping("/edit/{id}")
    public String editRole(@PathVariable("id") Short id, RedirectAttributes redirectAttributes) {
        RoleDto roleDto = roleService.findById(id);
        redirectAttributes.addFlashAttribute("roleDto", roleDto);
        return "redirect:/role";

    }
}
