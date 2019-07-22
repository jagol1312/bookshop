package com.bookshop.service;

import com.alibaba.fastjson.JSONObject;
import com.bookshop.dao.BookDao;
import com.bookshop.model.Book;
import com.bookshop.util.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookDao bookDao;

    /**
     * @return list<book>
     */
    public List<Book> GetAllBookInfo() {
        return this.bookDao.SelectAllBookInfo();
    }

    /**
     * 模糊查询书籍名
     *
     * @param bookname
     * @return list<book>
     */
    public List<Book> GetBookInfoByBookname(String bookname) {

        return this.bookDao.SelectBookInfoByBookname(bookname);
    }

    /**
     * 根据种类查询书籍
     *
     * @param type
     * @return list<book>
     */
    public List<Book> GetBookInfoByType(String type) {

        return this.bookDao.SelectBookInfoByType(type);
    }

    /**
     * 根据书籍id查找书籍
     *
     * @param bookid
     * @return list<book>
     */
    public Book GetBookInfoByBookId(long bookid) {

        return this.bookDao.SelectBookInfoByBookId(bookid);
    }

    /**
     * 根据书籍ISBN查找书籍
     *
     * @param isbn
     * @return list<book>
     */
    public Book GetBookInfoByISBN(String isbn) {
        return this.bookDao.SelectBookInfoByISBN(isbn);
    }

    /**
     * 查找书籍种类
     *
     * @return list<String>
     */
    public List<String> GetAllTypeOfBookInfo() {
        return this.bookDao.SeclectAllType();
    }

    /**
     * 修改书籍信息
     */
    public void UpdateBook(Book book){
        this.bookDao.UpdateBookInfo(book);
    }

    /**
     * 删除书籍
     *
     * @return list<String>
     */
    public void DeleteBookInfo(long bookid) {

        bookDao.DeleteBookInfo(bookid);

    }
    /**
     * 添加书籍
     *
     * @return list<String>
     */
    public boolean InsertBookInfo(Book book) {
        // book.setPicname("book-01.jpg");
        try {
            bookDao.AddBookInfo(book);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    /**
     * 查询4本新书
     *
     * @return list<String>
     */
     public  List<Book> SelectNewBook(long number){
           return  bookDao.SelectNewBook(number);
     }
}
