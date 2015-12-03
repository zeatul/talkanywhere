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

	BookmarkDomain load(@Param("id")Long id );
	
	List<BookmarkDomain> loadDynamic(Map<String,Object> params);
	
	int count(Map<String,Object> params);
	
	int insert(BookmarkDomain bookmarkDomain);
	
	int delete(@Param("id")Long id );
	
	int deleteDynamic(Map<String,Object> params);
	
	int update(BookmarkDomain bookmarkDomain);
	
	int updateWithoutNull(BookmarkDomain bookmarkDomain);
	
	int update(Map<String,Object> params);
	
	


}