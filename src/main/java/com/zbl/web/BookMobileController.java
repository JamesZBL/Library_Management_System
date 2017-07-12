package com.zbl.web;

import com.zbl.entity.Book;
import com.zbl.service.BookService;
import com.zbl.web.base.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 移动端请求控制类
 * 请求来源：Android端（Retrofit）
 */
@Controller
@RequestMapping("/mobile") // url:/模块/资源/{id}/细分
public class BookMobileController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BookService bookService;

    /**
     * @param startTime
     * @param endTime
     * @param name      书名
     * @param sort      排序字段
     * @param order     排序方式
     * @return
     */
    @RequestMapping(value = "/book_list", method = RequestMethod.GET)
    @ResponseBody
    private Object booklist(String startTime, String endTime, String name, String sort, String order) {
        try {
            List<Book> result = bookService.selectAllBooks();
            return renderRetrofitSuccess(result);
        } catch (Exception e) {
            e.printStackTrace();
            return renderRetrofitError();
        }
    }

    /**
     * 删除图书
     *
     * @param bookId 主键
     * @return
     */
    @RequestMapping(value = "/book_delete/{bookId}", method = RequestMethod.GET)
    @ResponseBody
    private Object bookDelete(@PathVariable(value = "bookId") long bookId) {
        try {
            bookService.deleteByPrimarKey(bookId);

        } catch (Exception e) {
            e.printStackTrace();
            return renderRetrofitError();
        }
        return renderRetrofitSuccess();
    }

    /**
     * 添加图书
     *
     * @param book
     * @return
     */
    @RequestMapping(value = "/book_add", method = RequestMethod.POST)
    @ResponseBody
    private Object addBook(@RequestBody Book book) {//如果请求参数为json格式，此处必须加@RequestBody

        try {
            bookService.insertBook(book);

        } catch (Exception e) {
            e.printStackTrace();
            return renderRetrofitError();
        }
        return renderRetrofitSuccess();
    }

    /**
     * 更新图书信息
     *
     * @param book
     * @return
     */
    @RequestMapping(value = "/book_update", method = RequestMethod.POST)
    @ResponseBody
    private Object updateBook(@RequestBody Book book) {
        try {
            bookService.updateByPrimaryKey(book);

        } catch (Exception e) {
            e.printStackTrace();
            return renderRetrofitError();
        }
        return renderRetrofitSuccess();
    }
}
