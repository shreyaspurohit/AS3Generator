package com.ssb.plugin.as3.qtags;

public class As3FieldTagImpl extends org.xdoclet.XDocletTag implements com.ssb.plugin.as3.qtags.As3FieldTag {
    public static final String NAME = "as3.field";
    private static final java.util.List ALLOWED_PARAMETERS = java.util.Arrays.asList( new String[] {
		"type",
		"import",
		"generate",
		"generate-bindable-field-metadata",
    	""
    });
    
    private static final java.util.List ALLOWED_VALUES = java.util.Arrays.asList( new String[] {
        ""
    });
    public As3FieldTagImpl(String name, String value, com.thoughtworks.qdox.model.AbstractJavaEntity entity, int lineNumber, org.xdoclet.QDoxPropertyExpander expander) {
        super(name, value, entity, lineNumber, expander);
    }
    public As3FieldTagImpl(String name, String value, com.thoughtworks.qdox.model.AbstractJavaEntity entity, int lineNumber) {
        super(name, value, entity, lineNumber);
    }

    public java.lang.String getType() {
		boolean required = true;
        String result = getNamedParameter("type");
        if(required && result == null) {
            bomb("type=\"???\" must be specified.");
        }

		java.lang.String retVal = null;


		if (result != null) {
	            retVal = result;
		}
		
		return retVal;
    }
    public java.lang.String isImport() {
		boolean required = false;
        String result = getNamedParameter("import");
        if(required && result == null) {
            bomb("import=\"???\" must be specified.");
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
    public java.lang.String isGenerateBindableFieldMetadata() {
		boolean required = false;
        String result = getNamedParameter("generate-bindable-field-metadata");
        if(required && result == null) {
            bomb("generate-bindable-field-metadata=\"???\" must be specified.");
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
		if(isOnClass) {
        	bomb("is not allowed on classes");
		}
		if(isOnConstructor) {
        	bomb("is not allowed on constructors");
		}
		if(isOnMethod) {
        	bomb("is not allowed on methods");
		}
        
        // check uniqueness

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
        getType();
        isImport();
        isGenerate();
        isGenerateBindableFieldMetadata();
    }

    public void validateModel() {
        // check uniqueness
    }
}