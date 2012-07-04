package com.bitourea.sample;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * 
 * @author Shreyas
 * @as3.class name="Model" generate="true" generate-bindable-metadata="true" generate-remote-class-metadata="true"
 */
public class Model {
	
	private int i;
	private double d;
	private long l;
	private com.bitourea.sample.model.Model3 model3;
	private com.bitourea.sample.Model2 model2;
	
	/**
	 * @as3.field type="Number" import="false" generate-bindable-field-metadata="true"
	 */
	private BigDecimal bdecimal;
	
	/**
	 * @as3.field type="mx.flex.BigInt" import="true"
	 */
	private BigInteger binteger;

	/**
	 * @as3.field type="mx.flex.BigInt" generate="false"
	 */
	private transient BigInteger doNotGeneratebinteger;

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public double getD() {
		return d;
	}

	public void setD(double d) {
		this.d = d;
	}

	public long getL() {
		return l;
	}

	public void setL(long l) {
		this.l = l;
	}

	public BigDecimal getBdecimal() {
		return bdecimal;
	}

	public void setBdecimal(BigDecimal bdecimal) {
		this.bdecimal = bdecimal;
	}

	public BigInteger getBinteger() {
		return binteger;
	}

	public void setBinteger(BigInteger binteger) {
		this.binteger = binteger;
	}

	public com.bitourea.sample.model.Model3 getModel() {
		return model3;
	}

	public void setModel(com.bitourea.sample.model.Model3 model3) {
		this.model3 = model3;
	}

	public BigInteger getDoNotGeneratebinteger() {
		return doNotGeneratebinteger;
	}

	public void setDoNotGeneratebinteger(BigInteger doNotGeneratebinteger) {
		this.doNotGeneratebinteger = doNotGeneratebinteger;
	}

	public com.bitourea.sample.Model2 getModel2() {
		return model2;
	}

	public void setModel2(com.bitourea.sample.Model2 model2) {
		this.model2 = model2;
	}
	
	
}
