package ir.maktabsharif115.springboot.contorller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
//@RequestMapping("/test")
public class PageController {

//    @RequestMapping(value = "/get", method = RequestMethod.GET)
//    @GetMapping(value = "/get")

    @GetMapping("/with-string")
    public String getPageByString() {
        return "home";
    }

    @GetMapping("/with-model")
    public ModelAndView getPageByModelAndView() {
        ModelAndView view = new ModelAndView("name");
        view.addObject("myName", "ali");
        return view;
    }

//    @RequestMapping(value = "/post", method = RequestMethod.POST)
//    @PostMapping(value = "/post")


}
