package com.bookshop.dao;

import com.bookshop.model.BookInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface IBookInfoDao {

    @Select("select * from bookinfo")
    public List<BookInfo> SelectAllBookInfo();

    @Select("select * from bookinfo where title LIKE CONCAT('%',#{0},'%')")
    public List<BookInfo> SelectBookInfoByTitle(String title);

    @Select("select * from bookinfo ORDER BY pubdate DESC limit 4")
    public List<BookInfo> GetFrontOfBookInfoF();

    @Select("select * from bookinfo ORDER BY pubdate DESC limit 6")
    public List<BookInfo> GetFrontOfBookInfoS();

    @Select("select * from bookinfo ORDER BY pubdate DESC limit 8")
    public List<BookInfo> GetFrontOfBookInfoE();

    @Select("select * from bookinfo where type = #{type}")
    public List<BookInfo> SelectBookInfoByType(String type);

    @Select("select * from bookinfo where bookid = #{bookid}")
    public BookInfo SelectBookInfoByBookId(long bookid);

    @Select("select * from bookinfo where ISBN = #{ISBN}")
    public BookInfo SelectBookInfoByISBN(String ISBN);

    @Select("select * from (select * from bookinfo where type = #{type}) where title = #{title}")
    List<BookInfo> SelectBookInfoByTitleAndType(BookInfo bookinfo);

    @Select("select distinct type from bookinfo")
    public List<String> GetAllType();

    @Update("update bookinfo set ISBN = #{ISBN}, title = #{title}, author = #{author}, version = #{version}, pubdate = #{pubdate}, introdu = #{introdu}, price = #{price}, picname = #{picname}, type = #{type} where bookid = #{bookid}")
    public void UpdateBookInfo(BookInfo bookInfo);

    @Insert("insert into bookinfo(ISBN, title, author, version, pubdate, introdu, price, picname, type) values(#{ISBN}, #{title}, #{author}, #{version}, #{pubdate}, #{introdu}, #{price}, #{picname}, #{type})")
    public void InsertBookInfo(BookInfo bookInfo);

    @Delete("delete from bookinfo where bookid = #{bookid}")
    public void DeleteBookInfo(long bookid);
}
