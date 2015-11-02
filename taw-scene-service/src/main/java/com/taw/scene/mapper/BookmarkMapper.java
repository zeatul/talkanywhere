package com.taw.scene.mapper;
import java.util.List;
import java.util.Map;
import com.taw.scene.domain.BookmarkDomain;
import org.apache.ibatis.annotations.Param;

/**
 * t_tm_bookmark
 * 
 * 
 * @author Gen
 */
public interface BookmarkMapper  {

	BookmarkDomain loadBookmark(@Param("id")Long id );
	
	List<BookmarkDomain> loadBookmarkDynamic(Map<String,Object> params);
	
	int count(Map<String,Object> params);
	
	int insert(BookmarkDomain bookmarkDomain);
	
	int delete(@Param("id")Long id );
	
	int deleteDynamic(Map<String,Object> params);
	
	int updateBookmark(BookmarkDomain bookmarkDomain);
	
	int updateBookmarkWithoutNull(BookmarkDomain bookmarkDomain);
	
	int update(Map<String,Object> params);
	
	


}