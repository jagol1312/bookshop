package com.bookshop.dao;

import com.bookshop.model.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface BookDao {

    /* 查询所有书籍*/
    @Select("select * from book")
    public List<Book> SelectAllBookInfo();
    /* 模糊查询书籍名*/
    @Select("select * from book where bookname LIKE CONCAT('%',#{0},'%')")
    public List<Book> SelectBookInfoByBookname(String bookname);
    /* 根据书籍类型查询书籍 */
    @Select("select * from book where type = #{type}")
    public List<Book> SelectBookInfoByType(String type);
    /* 根据书籍id查询书籍 */
    @Select("select * from book where bookid = #{bookid}")
    public Book SelectBookInfoByBookId(long bookid);
    /* 根据书籍ISBN查询 */
    @Select("select * from book where isbn = #{isbn}")
    public Book SelectBookInfoByISBN(String isbn);
    /* 根据书籍种类 distinct不显示重复项 */
    @Select("select distinct type from book")
    public List<String> SeclectAllType();
    /* 根据书籍名 修改书籍信息 */
    @Update("update book set bookname=#{bookname},isbn=#{isbn},press=#{press},author=#{author},pubdate=#{pubdate},price=#{price},bookintroduce=#{bookintroduce},stock=#{stock},type=#{type} where bookid=#{bookid} ")
    public void UpdateBookInfo(Book book);

}
