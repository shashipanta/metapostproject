package com.meta.userpostproject.controller;

import com.meta.userpostproject.Service.RoleService;
import com.meta.userpostproject.dto.RoleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("role")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @GetMapping
    public String role(Model model) {
        model.addAttribute("role", new RoleDto());
        List<RoleDto> roles = roleService.findAllRole();
        model.addAttribute("roles", roles);
        return "admin/role";
    }

    @PostMapping( "/create")
    public String createRole(@ModelAttribute("role") RoleDto roleDto, BindingResult result, Model model,
                             RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            model.addAttribute("role", roleDto);
            return "admin/role";
        }
        try {
            roleService.createRole(roleDto);
        }catch (Exception e) {
            String msg = "";
            if (e.getMessage().contains("uk_role_name")) {
                msg += "Sorry Role "+roleDto.getName()+ " already Exist.\n";
                redirectAttributes.addFlashAttribute("error", msg);

            } else {
                redirectAttributes.addFlashAttribute("error", e.getMessage());
            }
            return "redirect:/role?fail";
        }
        return "redirect:/role?success";
    }

}
