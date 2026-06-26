package org.radagon.day3;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

    @GetMapping("/greet/{name}")
    public String helo1(@PathVariable String name, Model model) {

        model.addAttribute("name", name);

        return "hello1";
    }

//    @GetMapping("/calc/query")
//    public String calc(
//            @RequestParam("a") double a,
//            @RequestParam("b") double b,
//            @RequestParam("op") String op,
//            Model model) {
//
//        model.addAttribute("a", a);
//        model.addAttribute("b", b);
//        model.addAttribute("op", op);
//        model.addAttribute("result", calculate(a, b, op));
//
//        return "calculator";
//    }

    @GetMapping("/calculator")
    public String calculator(
            @RequestParam(value="a", required = false) Double a,
            @RequestParam(value="b", required = false) Double b,
            @RequestParam(value="action", required = false) String op,
            Model model) {
        if (a != null && b != null && op != null) {

            model.addAttribute("a", a);
            model.addAttribute("b", b);
            model.addAttribute("op", op);
            model.addAttribute("result", calculate(a, b, op));
        }


        return "calculator";
    }

    private double calculate(double a, double b, String op) {
        if("add".equalsIgnoreCase(op)){
            return a+b;
        }else if("sub".equalsIgnoreCase(op)){
            return a-b;
        }
        return 0;
    }
}
