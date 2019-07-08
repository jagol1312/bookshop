package com.bookshop.service;

import com.bookshop.dao.IBookInfoDao;
import com.bookshop.model.BookInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private IBookInfoDao dao;

    public List<BookInfo> GetAllBookInfo()
    {
        return this.dao.SelectAllBookInfo();
    }

    public List<BookInfo> GetBookInfoByTitle(String title)
    {
        return this.dao.SelectBookInfoByTitle(title);
    }

    public List<BookInfo> GetBookInfoByType(String type)
    {
        return this.dao.SelectBookInfoByType(type);
    }

    public BookInfo GetBookInfoByBookId(long bookid) { return this.dao.SelectBookInfoByBookId(bookid); }

    public String UpdateBookInfo(BookInfo bookInfo)
    {
        if (dao.SelectBookInfoByBookId(bookInfo.getBookId()) == null) return "修改失败, 书籍不存在！";
        dao.UpdateBookInfo(bookInfo);
        return "修改成功！";
    }

    public String DeleteBookInfo(long bookid) {
        if (dao.SelectBookInfoByBookId(bookid) == null) return "删除失败， 书籍不存在！";
        dao.DeleteBookInfo(bookid);
        return "删除成功！";
    }

    public String InsertBookInfo(BookInfo bookInfo){
        if (dao.SelectBookInfoByISBN(bookInfo.getISBN()) == null)
        {
            bookInfo.setPicname("book-01.jpg");
            dao.InsertBookInfo(bookInfo);
            return "添加成功！";
        }
        else
            return "添加失败，书籍已存在！";
    }

    public List<BookInfo> GetBookInfoByTitleAndType(BookInfo bookinfo) {
        return this.dao.SelectBookInfoByTitleAndType(bookinfo);
    }

    public List<BookInfo> GetFrontOfBookInfo(int num) {
        if(num == 4)
        {
            return this.dao.GetFrontOfBookInfoF();
        }else if(num == 6)
        {
            return this.dao.GetFrontOfBookInfoS();
        }else
        return this.dao.GetFrontOfBookInfoE();
    }

    public List<String> GetAllType() {
        return this.dao.GetAllType();
    }
}
