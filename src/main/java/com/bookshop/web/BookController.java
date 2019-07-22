package com.bookshop.web;

import com.alibaba.fastjson.JSONObject;
import com.bookshop.model.Book;
import com.bookshop.model.User;
import com.bookshop.service.BookService;
import com.bookshop.util.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    /**
     * 查询所有书籍
     * @return list<book>
     */
    @GetMapping("/AllBook")
    public List<Book> GetAllBookInfo() {

        return bookService.GetAllBookInfo();
    }

    /**
     * 模糊查询书籍名
     * @param request bookname
     * @return list<book>
     */
    @GetMapping("/SelectByName")
    public List<Book> GetBookInfoByBookname(HttpServletRequest request)
    {
        String bookname = request.getParameter("bookname");
        return bookService.GetBookInfoByBookname(bookname);
    }
    /**
     * 根据书籍类型查询 将结果显示书籍列表页面
     * @param request type
     * @return
     */
    @GetMapping("/SelectByType")
    public List<Book> GetBookInfoByType(HttpServletRequest request, String type)
    {
        type = request.getParameter("type");

        return bookService.GetBookInfoByType(type);
     /*   return "/list";*/
    }
    /**
     * 根据书籍id查询 返回结果
     * @param bookid
     * @return book
     */
    @GetMapping("/SelectByBookId")
    public Book GetBookInfoByBookId(long bookid)
    {

        return bookService.GetBookInfoByBookId(bookid);
    }
    /**
     * 根据书籍id查询 返回结果
     * @param isbn
     * @return book
     */
    @GetMapping("/SelectByISBN")
    public Book GetBookInfoByISBN(String isbn)
    {

        return bookService.GetBookInfoByISBN(isbn);
    }
    /**
     * 查询书籍种类
     * @return
     */
    @GetMapping("/SelectAllType")
    public List<String> GetAllTypeOfBookInfo(){
        return bookService.GetAllTypeOfBookInfo();
    }
    /**
     * 删除书籍
     * @return JSON
     */
    @RequestMapping("/DeleteBook")
    public JSONObject delete(long id){
        JSONUtil jsonUtil = new JSONUtil();
        try {
            bookService.DeleteBookInfo(id);
            return jsonUtil.success("删除成功");
        }
        catch (Exception ex){
            return jsonUtil.success("删除失败");
        }
    }
    /**
     * 增加书籍
     * @return JSON
     */
    @PostMapping("/AddBook")
    public JSONObject addbook( Book book)
    {
        //book.setPicname("book-01.jpg");
        JSONUtil jsonUtil = new JSONUtil();


        if (bookService.InsertBookInfo(book))
        {
            return jsonUtil.success("添加成功");
        }
        else
        return jsonUtil.fail("添加失败");
    }
    /**
     * 查询N本新书
     * @param number
     * @return
     */
    @RequestMapping("/GetNewBook")
    public List<Book> GetNewBook(long number){
        return bookService.SelectNewBook(number);
    }
    /**
     * 修改书籍信息
     */
    @RequestMapping("/EditBook")
    public JSONObject EditBook(Book book){
        JSONUtil jsonUtil = new JSONUtil();
        bookService.UpdateBook(book);
        return jsonUtil.success("添加成功");
    }
}

