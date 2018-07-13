package com.example.springbootjpademo.controller;

import com.example.springbootjpademo.dao.CategoryDAO;
import com.example.springbootjpademo.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@Controller
public class CategoryController {
    @Autowired
    CategoryDAO categoryDAO;

    @RequestMapping("/listCategory")
    public  String ListCategory(Model model) throws Exception{
        List<Category> categoryList=categoryDAO.findAll();
        model.addAttribute("categories",categoryList);
        return "listCategory";
    }
    @RequestMapping("/listCategories")
    public  String ListCategories(Model model,
                                  @RequestParam(value = "start",defaultValue = "0")int start,
                                  @RequestParam(value = "size",defaultValue = "5") int size
                                  ) throws Exception{
        start=start<0?0:start;
        Sort sort;
        sort = new Sort(Sort.Direction.DESC,"id");
        Pageable pageable=new PageRequest(start,size,sort);
        Page<Category> page=categoryDAO.findAll(pageable);
//      System.out.println(page.getNumber());
//      System.out.println(page.hasContent());
        if(page.hasContent()==true)
          model.addAttribute("page",page);
        else
            model.addAttribute("page",page);
        return "listCategories";
    }

    //JPA 新增和修改用的都是save. 它根据实体类的id是否为0来判断是进行增加还是修改
    @RequestMapping("/addCategory")
    public String addCategory(Category category) throws Exception{
        categoryDAO.save(category);
        return "redirect:listCategories";
    }
    @RequestMapping("/deleteCategory")
    public String deleteCategory(Category category)throws Exception{
        categoryDAO.delete(category);
        return "redirect:listCategories";
    }
    @RequestMapping("/updateCategory")
    public String updateCategory(Category category)throws Exception{
        categoryDAO.save(category);
        return "redirect:listCategories";
    }
    @RequestMapping("/editCategory")
    public String editCategory(int id,Model model)throws Exception{
        Category category=categoryDAO.getOne(id);
        model.addAttribute("category",category);
        return "editCategory";
    }
}
