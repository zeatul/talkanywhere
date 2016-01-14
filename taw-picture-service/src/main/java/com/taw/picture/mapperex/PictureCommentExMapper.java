package com.taw.picture.mapperex;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.taw.picture.domain.PictureCommentDomain;

public interface PictureCommentExMapper {
	
	public List<PictureCommentDomain> searchComment(@Param("picId")Long picId,@Param("offset")Integer offset , @Param("limit")Integer limit);

}
