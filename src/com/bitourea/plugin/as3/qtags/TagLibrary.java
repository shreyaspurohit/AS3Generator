package com.bitourea.plugin.as3.qtags;

import org.generama.MetadataProvider;

public class TagLibrary {
    public static final String AS3_CLASS = As3ClassTagImpl.NAME;
    public static final String AS3_FIELD = As3FieldTagImpl.NAME;

    public TagLibrary(MetadataProvider metadataProvider) {
        metadataProvider.getDocletTagFactory().registerTag(As3ClassTagImpl.NAME, As3ClassTagImpl.class);
        metadataProvider.getDocletTagFactory().registerTag(As3FieldTagImpl.NAME, As3FieldTagImpl.class);
    }
}
