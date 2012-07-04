package com.bitourea.plugin.as3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.generama.QDoxCapableMetadataProvider;
import org.generama.VelocityTemplateEngine;
import org.generama.WriterMapper;
import org.generama.defaults.QDoxPlugin;

import com.bitourea.plugin.as3.qtags.TagLibrary;
import com.bitourea.plugin.as3.util.As3PluginConstants;
import com.bitourea.plugin.as3.util.TagUtil;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaField;
/**
 * A plugin to generate AS files from the java files. The supported annotations are:
 * <p>
 * <ol>
 * <li>1. 	as3.class - A class level tag.
 * <li>1.a. name - The name of the generated AS class. Should be same as the java class name. Required.
 * <li>1.b. generate - On false, does not generate the AS file for the java class. Default: true.
 * <li>1.c. generate-bindable-metadata - Generates [Bindable] at class level in AS. Default value: true.
 * <li>1.d. generate-remote-class-metadata - Generates [RemoteClass(alias="..")] on true. Default value: false.
 * </ol>
 * </p>
 * <p>
 * 2. 	as3.field - A Field level tag.
 * <ol>
 * <li>2.a. type - The fully qualified flex type to be generated in the AS file. Required.
 * <li>2.b. import - Imports the flex type defined above. Default: false.
 * <li>2.c. generate-bindable-field-metadata - Generate field level [Bindable] in the AS file. Default: false.
 * <li>2.d. generate - Controls generation of the field in as files. Default: true.
 * </ol>
 * </p>
 * <p>
 * The Java-Flex mapping supported are:
 * <ol>
 * <li>int - Number	
 * <li>double - Number	
 * <li>long - Number	
 * <li>java.lang.Short - Number	
 * <li>java.lang.Byte - Number	
 * <li>java.lang.Integer - Number	
 * <li>java.lang.Double - Number	
 * <li>java.lang.Long - Number	
 * <li>java.lang.Float - Number	
 * <li>java.lang.String - String	
 * <li>java.lang.Character - String	
 * <li>java.util.Collection - mx.collection.ArrayCollection	
 * <li>java.util.Map - Object	
 * <li>java.util.Dictionary - Object
 * </ol>
 * </p>
 * <p>
 * For any other type define the as3.field with the flex type, and if the import is necessary.
 * If that does not solve the problem, define it as Object, then cas it in the flex code.
 * If this too does not solve the problem contact me on my blog. Will add the feature and release the next version.
 * </p>
 * 
 * @author Shreyas Purohit
 * @see http://sacrosanctblood.blogspot.com/
 * 
 *
 */
public class As3Plugin extends QDoxPlugin{
	
	/**
	 * Used to hold the default type conversion map from java to flex types.
	 */
	private Map<String, String> typeMap = new HashMap<String, String>();
	/**
	 * Logger.
	 */
	private final Logger logger = Logger.getLogger("com.bitourea.plugin.as3.As3Plugin");
	/**
	 * List to keep track of imports.
	 */
	private List<String> importedTypes = new ArrayList<String>(); 
	/**
	 * Constructor for xdoclet2 plugin.
	 * 
	 * @param templateEngine
	 * @param metadataProvider
	 * @param writerMapper
	 */
	public As3Plugin(VelocityTemplateEngine templateEngine,
			QDoxCapableMetadataProvider metadataProvider,
			WriterMapper writerMapper) {
		//Call the superclass constructor.
		super(templateEngine, metadataProvider, writerMapper);
		
		if(logger.isDebugEnabled()){
			logger.debug("Entering As3Plugin Constructor...");
		}
		
		//Replace .java with .as extensions
		setFileregex(".java");
		setFilereplace(".as");
		
		//Set Multiple file output to true. 
		setMultioutput(true);
		
		//Instantiate the generated tag library. 
		new TagLibrary(metadataProvider);
		
		//Initialize the the type map 
		initTypeMap();
		
		if(logger.isDebugEnabled()){
			logger.debug("Exting As3Plugin Constructor...");
		}
	}
	public void initializeForNewFile(){
		importedTypes = new ArrayList<String>();
	}
	/**
	 * Initializes the type map.
	 */
	protected void initTypeMap() {
		typeMap.put("int", "Number");
		typeMap.put("double", "Number");
		typeMap.put("long", "Number");
		typeMap.put("java.lang.Short", "Number");
		typeMap.put("java.lang.Byte", "Number");
		typeMap.put("java.lang.Integer", "Number");
		typeMap.put("java.lang.Double", "Number");
		typeMap.put("java.lang.Long", "Number");
		typeMap.put("java.lang.Float", "Number");
		typeMap.put("java.lang.String", "String");
		typeMap.put("java.lang.Character", "String");
		typeMap.put("java.util.Collection", "mx.collection.ArrayCollection");
		typeMap.put("java.util.Map", "Object");
		typeMap.put("java.util.Dictionary", "Object");
		if(logger.isInfoEnabled()){
			logger.info("Type map being used... " + typeMap.toString());
		}
	}
	
	/**
	 * Defines whether the field should be imported or not.
	 * 
	 * @param metadata The java Field.
	 * @default false
	 * @return String The tag value from the annotation.
	 */
	public String shouldImport(Object metadata){
		TagUtil util = new TagUtil();
		
		JavaField javaField = (JavaField)metadata;
		String tagValue = util.getTagValue("as3.field", "import", javaField);
		String javaType = javaField.getType().getValue();
		String defaultFlexType = typeMap.get(javaType);
		if(logger.isDebugEnabled()){
			logger.debug(" Field Name: " + javaField.getName() + " Tagvalue: " + tagValue + " javaType: " + javaType + " defaultFleType: " + defaultFlexType);
		}
		 if(As3PluginConstants.FALSE.equals(util.shouldFieldBeGenerated(metadata))){
			 return As3PluginConstants.FALSE;
		 }
		if(As3PluginConstants.TRUE.equals(tagValue)){
			String typeName= util.getTypeName(metadata);
			if(!importedTypes.contains(typeName)){
				importedTypes.add(typeName);
				logger.debug("Returning : " + typeName);
				return typeName;
			}
			return As3PluginConstants.FALSE;
		}else if(As3PluginConstants.FALSE.equals(tagValue)){
			return As3PluginConstants.FALSE;
		}
		if(null == defaultFlexType || "".equalsIgnoreCase(defaultFlexType)){
			if(!importedTypes.contains(javaType)){
				importedTypes.add(javaType);
				logger.debug("Returning : " + javaType);
				return javaType;
			}
			return As3PluginConstants.FALSE;
		}
		return As3PluginConstants.FALSE;
	}
	/**
	 * Determines if the given field is already imported.
	 * 
	 * @param metadata
	 * @return Sting
	 */
	public String isImported(Object metadata){
		JavaField field = (JavaField)metadata;
		return importedTypes.contains(field.getType().getValue())?As3PluginConstants.TRUE:As3PluginConstants.FALSE;
	}
	/**
	 * Returns the default flex type for the input java type.
	 * 
	 * @param type Java type.
	 * @return String Flex type.
	 */
	public String getDefaultType(Object type){
		JavaField javaField = (JavaField)type;
		String javaType = javaField.getType().getValue();
		
		String defaultFlexType = typeMap.get(javaType);
		if(logger.isInfoEnabled()){
			logger.info("Java Type: " + javaType + "Converted to default Flex type : " + defaultFlexType);
		}
		if(null == defaultFlexType || "".equalsIgnoreCase(defaultFlexType)){
			if(As3PluginConstants.TRUE.equals(isImported(type))){
				return new TagUtil().getClassNameForTypeName(javaType);
			}
			return javaType;
		}
		return defaultFlexType;
	}
	/**
	 * Over ridden method, determines whether the given java class should be converted to as3 or not.
	 * 
	 * @param metadata A java Class.
	 * @return
	 */
	 public boolean shouldGenerate(Object metadata) {
		    JavaClass javaClass = (JavaClass) metadata;
		    String namedParameter = javaClass.getNamedParameter("as3.class","generate");
			boolean ignore = As3PluginConstants.FALSE.equalsIgnoreCase(namedParameter) || null == namedParameter;
		    if(logger.isDebugEnabled()){
				logger.debug("javaClass " + javaClass.getName() + "ignore: " + ignore);
		    }
		    if (!ignore)
		      return true;
		    else
		      return false;
	 }
	/**
	 * Initializes the context map by adding tagUtil, so that it can be accessed by velocity
	 * template.
	 * 
	 * @param map
	 */
	protected void populateContextMap(Map map) {
		super.populateContextMap(map);
		map.put("tagUtil", new TagUtil());
	}
	 
	 
}
