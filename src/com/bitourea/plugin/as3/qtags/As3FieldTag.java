package com.bitourea.plugin.as3.qtags;

import com.thoughtworks.qdox.model.DocletTag;

/**
 * 
 *@qtags.location field
 *
 */
public interface As3FieldTag  extends DocletTag{
	/**
	 * 
	 * @qtags.required
	 * 
	 */
	String getType();
	
	/**
	 * @qtags.default false
	 * 
	 */
	String isImport();
	/**
	 * @qtags.default true
	 * 
	 */
	String isGenerate();
	/**
     * @qtags.default false
     */
    String isGenerateBindableFieldMetadata();
}
