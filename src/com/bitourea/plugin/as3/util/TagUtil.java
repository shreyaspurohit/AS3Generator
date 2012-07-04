package com.bitourea.plugin.as3.util;

import org.apache.log4j.Logger;

import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaField;
/**
 * This class hold the logic to get the annotation data from the java files. This is called from the
 * velocity template engine while creating the output AS files.
 * 
 * @author Shreyas
 *
 */
public class TagUtil {
	/**
	 * Logger.
	 */
	private final Logger logger = Logger.getLogger("com.bitourea.plugin.as3.util.TagUtil");
	/**
	 * Gets the As3 Class name from the annotation as3.class, attribute 'name'
	 * 
	 * @param metadata The java class.
	 * @return String The tag value from the annotation.
	 */
	public String getAs3Name(Object metadata){
	    JavaClass javaClass = (JavaClass) metadata;
	    return javaClass.getNamedParameter("as3.class","name");
	}

	/**
	 * Gets the value of annotation as3.class, attribute generate-bindable-metadata
	 * 
	 * @param metadata The java class.
	 * @return String The tag value from the annotation.
	 */
	public String isGenerateBindableMetadata(Object metadata){
		JavaClass javaClass = (JavaClass) metadata;
		String attributeValue = javaClass.getNamedParameter("as3.class","generate-bindable-metadata");
		return attributeValue==null?As3PluginConstants.TRUE:attributeValue;
	}
	/**
	 * Gets the value of annotation as3.class, attribute generate-remote-class-metaData
	 * 
	 * @param metadata The java class.
	 * @return String The tag value from the annotation.
	 */
	public String isGenerateRemoteClassMetaData(Object metadata){
		JavaClass javaClass = (JavaClass) metadata;
		String attributeValue = javaClass.getNamedParameter("as3.class","generate-remote-class-metadata");
		return attributeValue==null?As3PluginConstants.TRUE:attributeValue;
	}
	/**
	 * Gets the Field type from the annotation as3.field, attribute 'type'
	 * 
	 * @param metadata The java Field.
	 * @return String The tag value from the annotation.
	 */
	public String getTypeName(Object metadata){
		JavaField javaField = (JavaField)metadata;
		return getTagValue("as3.field", "type", javaField);
	}
	/**
	 * Gets the value from the annotation as3.field, attribute 'import'
	 * 
	 * @param metadata The java Field.
	 * @default false
	 * @return String The tag value from the annotation.
	 */
	public String isImport(Object metadata){
		JavaField javaField = (JavaField)metadata;
		String tagValue = getTagValue("as3.field", "import", javaField);
		return tagValue==null ? As3PluginConstants.FALSE:tagValue; // Have to specify defaults your self
	}
	/**
	 * Gets the value from the annotation as3.field, attribute 'generate-bindable-field-metadata'
	 * 
	 * @param metadata The java Field.
	 * @default false
	 * @return String The tag value from the annotation.
	 */
	public String isGenerateBindableFieldMetadata(Object metadata){
		JavaField javaField = (JavaField)metadata;
		String tagValue = getTagValue("as3.field", "generate-bindable-field-metadata", javaField);
		return tagValue==null ? As3PluginConstants.FALSE:tagValue; // Have to specify defaults your self
	}

	/**
	 * Gets the value from the annotation as3.field, attribute 'generate'
	 * 
	 * @param field The java Field.
	 * @default true
	 * @return String The tag value from the annotation.
	 */
	public String shouldFieldBeGenerated(Object field){
		JavaField javaField = (JavaField)field;
		String tagValue = getTagValue("as3.field", "generate", javaField);
		return tagValue==null ? As3PluginConstants.TRUE:tagValue; // Have to specify defaults your self
	}
	/**
	 * Gets the Class name excluding the package prefix.
	 * 
	 * @param typeName A string representing the java fully qualified class name.
	 * @return String
	 */
	public String getClassNameForTypeName(Object typeName){
		return ((String)typeName).substring(((String)typeName).lastIndexOf(".") + 1);
	}
	/**
	 * Gets tag value for the give tag and its attribute on the java field.
	 * 
	 * @param tagName
	 * @param tagAttribute
	 * @param field
	 * @return String
	 */
	public String getTagValue(String tagName,String tagAttribute, JavaField field){
		if(logger.isInfoEnabled()){
			logger.info("Get Tag Value Called: Tag Name: " + tagName + " Attr: " + tagAttribute + " NamedParameter: " + field.getNamedParameter(tagName, tagAttribute));
		}
		return field.getNamedParameter(tagName, tagAttribute);
	}
}
