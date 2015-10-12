package ${packageName}.mapper;
import java.util.List;
import java.util.Map;
import ${packageName}.domain.${className};

/**
 * ${tableName}
 * ${tableComment}
 * 
 * @author Gen
 */
public interface ${className}Mapper  {

	${className} load${className}(Integer id);
	
	List<${className}> load${className}Dynamic(Map<String,Object> params);
	
	int count${className}Dynamic(Map<String,Object> params);
	
	void insert${className}(${className} ${className?uncap_first});
	
	void delete${className}(Integer id);
	
	void delete${className}Dynamic(Map<String,Object> params);
	
	void update${className}(${className} ${className?uncap_first});
	
	void update${className}Dynamic(Map<String,Object> params);
	
	void update${className}WithoutNull(${className} ${className?uncap_first});


}