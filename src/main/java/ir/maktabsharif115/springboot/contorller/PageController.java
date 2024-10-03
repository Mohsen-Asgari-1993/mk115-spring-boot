package ir.maktabsharif115.springboot.contorller;

import ir.maktabsharif115.springboot.mapper.CategoryMapper;
import ir.maktabsharif115.springboot.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
//@RequestMapping("/test")
@RequiredArgsConstructor
public class PageController {

    private final CategoryService categoryService;

    private final CategoryMapper categoryMapper;

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

    @GetMapping("/categories/list")
    public ModelAndView getCategories() {
        ModelAndView view = new ModelAndView("categories");
//      /templates/categories.html
        view.addObject(
                "cats",
                categoryMapper.convertToSiteDTO(
                        categoryService.findAllForSite()
                )
        );

        return view;
    }

//    @RequestMapping(value = "/post", method = RequestMethod.POST)
//    @PostMapping(value = "/post")


}
