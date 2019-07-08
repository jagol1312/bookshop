package com.bookshop.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bookshop.model.AdminInfo;
import com.bookshop.model.BookInfo;
import com.bookshop.service.AdminService;
import com.bookshop.service.BookService;
import com.bookshop.util.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;
    @Autowired
    private AdminService adminService;

    @GetMapping("/SelectByBookId")
    public BookInfo GetProduct(long bookid)
    {
        return bookService.GetBookInfoByBookId(bookid);
    }

    @GetMapping("/SelectAll")
    public List<BookInfo> GetAllBookInfo() {
        return bookService.GetAllBookInfo();
    }

    @GetMapping("/list")
    public String GetBookList(HttpServletRequest request) {
        String adminName =  request.getSession().getAttribute("admin").toString();
        if(adminName == null) return JSON.toJSONString("管理员未登录，或者没有权限！");
        return JSON.toJSONString(bookService.GetAllBookInfo());
    }

    @GetMapping("/SelectByTitle")
    public List<BookInfo> GetBookInfoByTitle(HttpServletRequest request)
    {
        String title = request.getParameter("title");
        return bookService.GetBookInfoByTitle(title);
    }

    @PostMapping("/editbook")
    public JSONObject EditById(HttpServletRequest request,BookInfo bookInfo)
    {
        if(bookInfo.getBookId()<10)
        {
            bookInfo.setPicname("book-0"+bookInfo.getBookId()+".jpg");
        }
        else {
            bookInfo.setPicname("book-"+bookInfo.getBookId()+".jpg");
        }
        JSONUtil jsonUtil = new JSONUtil();
        String adminName =  request.getSession().getAttribute("admin").toString();
        if(adminName == null)
        {
            return  jsonUtil.fail("管理员未登录，或者没有权限！");
        }
        String result = bookService.UpdateBookInfo(bookInfo);
        if(result.equals("修改成功！")) return jsonUtil.success(result);
        return jsonUtil.fail(result);
    }

    @PostMapping("/add")
    public JSONObject addbook(HttpServletRequest request, BookInfo bookInfo)
    {
        bookInfo.setPicname("book-01.jpg");
        JSONUtil jsonUtil = new JSONUtil();
        String adminName =  request.getSession().getAttribute("admin").toString();
        if(adminName == null)
        {
            return  jsonUtil.fail("管理员未登录，请先登录！");
        }
        String result = bookService.InsertBookInfo(bookInfo);
        if (result.equals("添加成功！"))
        {
            return jsonUtil.success(result);
        }
        return jsonUtil.fail(result);
    }

    @GetMapping("/Delete")
    public JSONObject DeleteBookInfo(long bookid, HttpServletRequest request)
    {
        JSONUtil jsonUtil = new JSONUtil();
        String adminName =  request.getSession().getAttribute("admin").toString();
        if(adminName == null)
        {
            return jsonUtil.success("删除失败，管理员未登录！");
        }
        String result = bookService.DeleteBookInfo(bookid);
        if(result.equals("删除成功！")) return jsonUtil.success(result);
        return jsonUtil.fail(result);
    }


    /*
     *
     *
     *
     *
     * */

    @GetMapping("/SelectByType")
    public String GetBookInfoByType(HttpServletRequest request, Model model)
    {
        String type = request.getParameter("type");
        model.addAttribute("SelectByType", bookService.GetBookInfoByType(type));
        return "book/list";
    }

    @GetMapping("/AllType")
    public List<String> GetAllType(Model model)
    {
        model.addAttribute("GetAllType", bookService.GetAllType());
        return bookService.GetAllType();
    }

    @PostMapping("/Update")
    public String UpdateBookInfo(HttpServletRequest request, Model model)
    {
        BookInfo bookInfo = requestToBookInfo(request);
        String result = bookService.UpdateBookInfo(bookInfo);
        JSONUtil jsonUtil = new JSONUtil();
        if(result.equals("修改成功"))
            model.addAttribute("Update", jsonUtil.success(result));
        else
            model.addAttribute("Update", jsonUtil.fail(result));
        return "book/list";
    }






    public BookInfo requestToBookInfo(HttpServletRequest request)
    {
        BookInfo bookInfo = new BookInfo();
        bookInfo.setISBN(request.getParameter("ISBN"));
        bookInfo.setBookId(Integer.parseInt(request.getParameter("bookid")));
        bookInfo.setTitle(request.getParameter("title"));
        bookInfo.setAuthor(request.getParameter("author"));
        bookInfo.setVersion(request.getParameter("version"));
        bookInfo.setPubdate(request.getParameter("pubdate"));
        bookInfo.setIntrodu(request.getParameter("introdu"));
        bookInfo.setPrice(Double.parseDouble((request.getParameter("price"))));
        bookInfo.setPicname(request.getParameter("picname"));
        bookInfo.setType(request.getParameter("type"));
        return bookInfo;
    }
}
