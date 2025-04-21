package model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import model.book.Book;

public interface BookMapper {

	@Insert("insert into book (writer, title, content) "
			+ " values (#{writer}, #{title}, #{content})")
	int insert(Book book);

	@Select("select * from book")
	List<Book> list();

}