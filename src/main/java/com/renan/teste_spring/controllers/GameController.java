package com.renan.teste_spring.controllers;

import com.renan.teste_spring.model.Game;
import org.javalite.activejdbc.Base;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/games")
public class GameController {

    @GetMapping("/index")
    public String index(ModelMap model) {

        Base.open("org.sqlite.JDBC", "jdbc:sqlite:Usuarios", "", "");

        try {
            List<Game> listGame = Game.findAll();

            model.addAttribute("games", listGame);
            model.addAttribute("size", listGame.size());

        } finally {
            Base.close();
        }

        return "Games/index";
    }

    @GetMapping("/new")
    public String gameNew(ModelMap model) {

        model.addAttribute("game", new Game());

        return "Games/new";
    }

    @PostMapping("/create")
    public String gameCreate(@ModelAttribute Game game) {

        Base.open("org.sqlite.JDBC", "jdbc:sqlite:Usuarios", "", "");

        try {
            game.saveIt();

        } finally {
            Base.close();
        }

        return "redirect:/games/index";
    }

    @GetMapping("/edit/{id}")
    public String editGame(@PathVariable Long id, ModelMap model) {

        Base.open("org.sqlite.JDBC", "jdbc:sqlite:Usuarios", "", "");

        try {
            Game gameEdit = Game.findById(id);

            if (gameEdit == null) {
                return "redirect:/games/index";
            }

            model.addAttribute("game", gameEdit);

        } finally {
            Base.close();
        }

        return "Games/edit";
    }

    @PostMapping("/update/{id}")
    public String gameUpdate(@PathVariable Long id,
                             @ModelAttribute Game game) {

        Base.open("org.sqlite.JDBC", "jdbc:sqlite:Usuarios", "", "");

        try {
            Game gameEdit = Game.findById(id);

            if (gameEdit != null) {

                gameEdit.setNome(game.getNome());
                gameEdit.setGenero(game.getGenero());
                gameEdit.setPlataforma(game.getPlataforma());

                gameEdit.saveIt();
            }

        } finally {
            Base.close();
        }

        return "redirect:/games/index";
    }

    @GetMapping("/show/{id}")
    public String show(@PathVariable Long id, ModelMap model) {

        Base.open("org.sqlite.JDBC", "jdbc:sqlite:Usuarios", "", "");

        try {
            Game gameShow = Game.findById(id);

            if (gameShow == null) {
                return "redirect:/games/index";
            }

            model.addAttribute("game", gameShow);

        } finally {
            Base.close();
        }

        return "Games/show";
    }

    @GetMapping("/delete/{id}")
    public String deleteGame(@PathVariable Long id) {

        Base.open("org.sqlite.JDBC", "jdbc:sqlite:Usuarios", "", "");

        try {
            Game game = Game.findById(id);

            if (game != null) {
                game.delete();
            }

        } finally {
            Base.close();
        }

        return "redirect:/games/index";
    }
}
