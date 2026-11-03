
package com.renan.teste_spring.controllers;

import com.renan.teste_spring.model.User;
import org.javalite.activejdbc.Base;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    // LISTAR USUARIOS
    @GetMapping("/index")
    public String index(ModelMap model) {

        Base.open(
                "org.sqlite.JDBC",
                "jdbc:sqlite:Usuarios",
                "",
                ""
        );

        try {
            List<User> listUser = User.findAll();

            model.addAttribute("users", listUser);
            model.addAttribute("size", listUser.size());

        } finally {
            Base.close();
        }

        return "users/index";
    }

    // ABRIR FORMULARIO DE CADASTRO
    @GetMapping("/new")
    public String userNew(ModelMap model) {

        model.addAttribute("user", new User());

        return "users/new";
    }

    // CADASTRAR USUARIO
    @PostMapping("/create")
    public String userCreate(@ModelAttribute User user) {

        Base.open(
                "org.sqlite.JDBC",
                "jdbc:sqlite:Usuarios",
                "",
                ""
        );

        try {
            user.saveIt();

        } finally {
            Base.close();
        }

        return "redirect:/users/index";
    }

    // ABRIR FORMULARIO DE EDICAO
    @GetMapping("/edit/{id}")
    public String editUser(
            @PathVariable Long id,
            ModelMap model) {

        Base.open(
                "org.sqlite.JDBC",
                "jdbc:sqlite:Usuarios",
                "",
                ""
        );

        try {
            User userEdit = User.findById(id);

            if (userEdit == null) {
                return "redirect:/users/index";
            }

            model.addAttribute("user", userEdit);

        } finally {
            Base.close();
        }

        return "users/edit";
    }

    // ATUALIZAR USUARIO
    @PostMapping("/update/{id}")
    public String userUpdate(
            @PathVariable Long id,
            @ModelAttribute User user) {

        Base.open(
                "org.sqlite.JDBC",
                "jdbc:sqlite:Usuarios",
                "",
                ""
        );

        try {
            User userEdit = User.findById(id);

            if (userEdit != null) {

                userEdit.setName(user.getName());
                userEdit.setArroba(user.getArroba());
                userEdit.setCpf(user.getCpf());

                userEdit.saveIt();
            }

        } finally {
            Base.close();
        }

        return "redirect:/users/index";
    }


    @GetMapping("/show/{id}")
    public String show(@PathVariable Long id, ModelMap model) {

        Base.open(
                "org.sqlite.JDBC",
                "jdbc:sqlite:Usuarios",
                "",
                ""
        );

        try {

            User userShow = User.findById(id);

            if (userShow == null) {
                return "redirect:/users/index";
            }

            model.addAttribute("user", userShow);

        } finally {
            Base.close();
        }

        return "users/show";
    }

    // EXCLUIR USUARIO
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {

        Base.open(
                "org.sqlite.JDBC",
                "jdbc:sqlite:Usuarios",
                "",
                ""
        );

        try {
            User user = User.findById(id);

            if (user != null) {
                user.delete();
            }

        } finally {
            Base.close();
        }

        return "redirect:/users/index";
    }
}
