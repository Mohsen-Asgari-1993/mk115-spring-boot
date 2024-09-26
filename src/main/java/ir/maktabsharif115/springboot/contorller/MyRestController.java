package ir.maktabsharif115.springboot.contorller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class MyRestController {

//    @RequestMapping(value = "/get", method = RequestMethod.GET)
//    @GetMapping(value = "/get")

    @GetMapping("/with-string")
    public MyResponse getPageByString() {
        return new MyResponse("home");
    }

//    @RequestMapping(value = "/post", method = RequestMethod.POST)
//    @PostMapping(value = "/post")


    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MyResponse {
        private String name;
    }

}
