package com.zbl.web;

import com.zbl.entity.Book;
import com.zbl.service.BookService;
import com.zbl.util.PageInfo;
import com.zbl.web.base.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/book") // url:/模块/资源/{id}/细分
public class BookController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    private String list(Model model) {
        return "book";
    }

    @RequestMapping(value = "/addPage", method = RequestMethod.GET)
    private String bookAddPage(Model model) {
        return "bookAdd";
    }

    @RequestMapping(value = "/editPage", method = RequestMethod.GET)
    private String bookEditPage(Model model, Long id) {
        Book book = bookService.selectByPrimaryKey(id);
        model.addAttribute("book", book);
        return "bookEdit";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    private Object booklist(String startTime, String endTime, String name, Integer page, Integer rows, String sort, String order) {
        PageInfo pageInfo = new PageInfo(page, rows);
        Map<String, Object> condition = new HashMap<String, Object>();

        condition.put("name", name);
        condition.put("sort", sort);
        condition.put("order", order);
        pageInfo.setCondition(condition);
        bookService.selectPage(pageInfo);
        return pageInfo;
    }

    @RequestMapping(value = "/saveBook", method = RequestMethod.POST)
    @ResponseBody
    private Object booksave(Book book) {
        try {
            bookService.insertBook(book);
        } catch (Exception e) {
            renderError("提交失败");
        }
        return renderSuccess("添加成功！");
    }

    @RequestMapping(value = "/updateBook", method = RequestMethod.POST)
    @ResponseBody
    private Object bookupadate(Book book) {
        try {
            bookService.updateByPrimaryKey(book);
        } catch (Exception e) {
            renderError("提交失败");
        }
        return renderSuccess("修改成功！");
    }

    @RequestMapping(value = "/deleteBook", method = RequestMethod.POST)
    @ResponseBody
    private Object bookdelete(Long id) {
        try {
            bookService.deleteByPrimarKey(id);
        } catch (Exception e) {
            renderError("删除失败");
        }
        return renderSuccess("删除成功！");
    }
}
