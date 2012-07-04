package com.ssb.plugin.as3.qtags;

public class As3ClassTagImpl extends org.xdoclet.XDocletTag implements com.ssb.plugin.as3.qtags.As3ClassTag {
    public static final String NAME = "as3.class";
    private static final java.util.List ALLOWED_PARAMETERS = java.util.Arrays.asList( new String[] {
		"name",
		"generate",
		"generate-bindable-metadata",
		"generate-remote-class-metadata",
    	""
    });
    
    private static final java.util.List ALLOWED_VALUES = java.util.Arrays.asList( new String[] {
        ""
    });
    public As3ClassTagImpl(String name, String value, com.thoughtworks.qdox.model.AbstractJavaEntity entity, int lineNumber, org.xdoclet.QDoxPropertyExpander expander) {
        super(name, value, entity, lineNumber, expander);
    }
    public As3ClassTagImpl(String name, String value, com.thoughtworks.qdox.model.AbstractJavaEntity entity, int lineNumber) {
        super(name, value, entity, lineNumber);
    }

    public java.lang.String getName_() {
		boolean required = true;
        String result = getNamedParameter("name");
        if(required && result == null) {
            bomb("name=\"???\" must be specified.");
        }

		java.lang.String retVal = null;


		if (result != null) {
	            retVal = result;
		}
		
		return retVal;
    }
    public java.lang.String isGenerate() {
		boolean required = false;
        String result = getNamedParameter("generate");
        if(required && result == null) {
            bomb("generate=\"???\" must be specified.");
        }

		java.lang.String retVal = null;

        if(result == null) {
            result = "true";
        }

		if (result != null) {
	            retVal = result;
		}
		
		return retVal;
    }
    public java.lang.String isGenerateBindableMetadata() {
		boolean required = false;
        String result = getNamedParameter("generate-bindable-metadata");
        if(required && result == null) {
            bomb("generate-bindable-metadata=\"???\" must be specified.");
        }

		java.lang.String retVal = null;

        if(result == null) {
            result = "true";
        }

		if (result != null) {
	            retVal = result;
		}
		
		return retVal;
    }
    public java.lang.String isGenerateRemoteClassMetadata() {
		boolean required = false;
        String result = getNamedParameter("generate-remote-class-metadata");
        if(required && result == null) {
            bomb("generate-remote-class-metadata=\"???\" must be specified.");
        }

		java.lang.String retVal = null;

        if(result == null) {
            result = "false";
        }

		if (result != null) {
	            retVal = result;
		}
		
		return retVal;
    }

    protected void validateLocation() {
        if(isOnField) {
            bomb("is not allowed on fields");
        }
		if(isOnConstructor) {
        	bomb("is not allowed on constructors");
		}
		if(isOnMethod) {
        	bomb("is not allowed on methods");
		}
        
        // check uniqueness
        // deprecated here. check validateModel
		if(getContext().getTagsByName(NAME).length > 1) {
			bomb("is allowed only once");
		}

        // warn deprecation
        
        // check for allowed values for whole tag
        if( ALLOWED_VALUES.size() > 1 && !ALLOWED_VALUES.contains(getValue())) {
            bomb( "\"" + getValue() +"\" is not a valid value. Allowed values are ");
        }        
        // Verify that all parameters are known.
        final java.util.Collection parameterNames = getNamedParameterMap().keySet();
        for (java.util.Iterator iterator = parameterNames.iterator(); iterator.hasNext();) {
            String parameterName = (String) iterator.next();
            if (!ALLOWED_PARAMETERS.contains(parameterName)) {
                bomb(parameterName + " is an invalid parameter name.");
            }
        }
        
        // Get all the parameters to validate their contents
        getName_();
        isGenerate();
        isGenerateBindableMetadata();
        isGenerateRemoteClassMetadata();
    }

    public void validateModel() {
        // check uniqueness
        if(getContext().getTagsByName(NAME).length > 1) {
            bomb("is allowed only once");
        }
    }
}