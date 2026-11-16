package com.renan.teste_spring.controllers;

import com.renan.teste_spring.model.Car;
import org.javalite.activejdbc.Base;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cars")
public class CarController {

    @GetMapping("/index")
    public String index(ModelMap model) {

        Base.open("org.sqlite.JDBC", "jdbc:sqlite:Usuarios", "", "");

        try {
            List<Car> listCar = Car.findAll();

            model.addAttribute("cars", listCar);
            model.addAttribute("size", listCar.size());

        } finally {
            Base.close();
        }

        return "Cars/index";
    }

    @GetMapping("/new")
    public String carNew(ModelMap model) {

        model.addAttribute("car", new Car());

        return "Cars/new";
    }

    @PostMapping("/create")
    public String carCreate(@ModelAttribute Car car) {

        Base.open("org.sqlite.JDBC", "jdbc:sqlite:Usuarios", "", "");

        try {
            car.saveIt();

        } finally {
            Base.close();
        }

        return "redirect:/cars/index";
    }

    @GetMapping("/edit/{id}")
    public String editCar(@PathVariable Long id, ModelMap model) {

        Base.open("org.sqlite.JDBC", "jdbc:sqlite:Usuarios", "", "");

        try {
            Car carEdit = Car.findById(id);

            if (carEdit == null) {
                return "redirect:/cars/index";
            }

            model.addAttribute("car", carEdit);

        } finally {
            Base.close();
        }

        return "Cars/edit";
    }

    @PostMapping("/update/{id}")
    public String carUpdate(@PathVariable Long id,
                            @ModelAttribute Car car) {

        Base.open("org.sqlite.JDBC", "jdbc:sqlite:Usuarios", "", "");

        try {
            Car carEdit = Car.findById(id);

            if (carEdit != null) {

                carEdit.setMarca(car.getMarca());
                carEdit.setModelo(car.getModelo());
                carEdit.setAno(car.getAno());

                carEdit.saveIt();
            }

        } finally {
            Base.close();
        }

        return "redirect:/cars/index";
    }

    @GetMapping("/show/{id}")
    public String show(@PathVariable Long id, ModelMap model) {

        Base.open("org.sqlite.JDBC", "jdbc:sqlite:Usuarios", "", "");

        try {
            Car carShow = Car.findById(id);

            if (carShow == null) {
                return "redirect:/cars/index";
            }

            model.addAttribute("car", carShow);

        } finally {
            Base.close();
        }

        return "Cars/show";
    }

    @GetMapping("/delete/{id}")
    public String deleteCar(@PathVariable Long id) {

        Base.open("org.sqlite.JDBC", "jdbc:sqlite:Usuarios", "", "");

        try {
            Car car = Car.findById(id);

            if (car != null) {
                car.delete();
            }

        } finally {
            Base.close();
        }

        return "redirect:/cars/index";
    }
}
