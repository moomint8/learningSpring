package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello")    // 호출 URL 주소
    public String hello(Model model) {
        model.addAttribute("data", "spring!!");
        return "hello"; // 반환 템플릿 이름
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-tamplate";
    }

    @GetMapping("hello-string")
    @ResponseBody // body 에 직접 데이터를 넣기 위해, html 태그 없이 페이지 보기에 body 내용만 존재
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;

        public String getName() {   // generate 단축기 : Alt + Insert
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
