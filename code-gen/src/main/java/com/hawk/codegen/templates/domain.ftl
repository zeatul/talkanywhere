package ${packageName}.domain;
import java.io.Serializable;
<#if hasDate??>import java.util.Date;</#if>



/**
 * ${tableName}
 * ${tableComment}
 * 
 * @author Code-Gen
 */
public class ${className} implements Serializable {

	private static final long serialVersionUID = -1L;
	
	<#list fields as field>
	/*${field.columnComment}*/
	private ${field.filedType} ${field.fieldName};
	</#list>
	
	<#list fields as field>
	public ${field.filedType} get${field.fieldName?cap_first}(){
		return ${field.fieldName};
	}	
	public void set${field.fieldName?cap_first} (${field.filedType} ${field.fieldName}) {
		this.${field.fieldName} = ${field.fieldName};
	}
	
	</#list>


}
