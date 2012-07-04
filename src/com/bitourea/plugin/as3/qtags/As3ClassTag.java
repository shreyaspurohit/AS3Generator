package com.bitourea.plugin.as3.qtags;
import com.thoughtworks.qdox.model.DocletTag;

/**
 * 
 * @qtags.location class
 * @qtags.once
 *
 */
public interface As3ClassTag extends DocletTag{
	/**
     * @qtags.required
     */
    String getName_();
    
    /**
     * @qtags.default true
     */
    String isGenerate();
    
    /**
     * @qtags.default true 
     */
    String isGenerateBindableMetadata();
    /**
     * @qtags.default false 
     */
    String isGenerateRemoteClassMetadata();
}
