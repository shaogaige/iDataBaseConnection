/**
 * ClassName: Value.java
 * Date: 2017年6月1日
 */
package com.ojdbc.sql;

import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.Time;
import java.sql.Types;

/**
 * Author: ShaoGaige
 * Description: 数据值存放类
 * Log: 
 */
public class Value {
	
	private String string_value = "";
	
	private int int_value = 0;
	
	private long long_value = 0;
	
	private double double_value = 0;
	
	private float float_value = 0;
	
	private boolean boolean_value = false;
	
	private BigDecimal bigdecimal_value;
	
	private Blob blob_value = null;
	
	private Clob clob_value = null;
	
	private byte byte_value;
	
	private byte[] bytes_value;
	
	private Date date_value;
	
	private Time time_value;
	
	private Object object_value;
	
	private int dataType = 0;
	
	public Value()
	{
		
	}
	
	public Value(int type)
	{
		this.dataType = type;
	}
	
	public int getDataType() {
		return dataType;
	}

	public String getString_value() {
		return string_value;
	}

	public Value setString_value(String string_value) {
		this.string_value = string_value;
		if(this.dataType == 0){
			this.dataType = Types.VARCHAR;
		}
		return this;
	}
	
	public boolean isStringValue() {
		if(this.dataType==Types.CHAR || this.dataType==Types.VARCHAR || this.dataType==Types.LONGVARCHAR ||
			this.dataType==Types.NCHAR || this.dataType==Types.NVARCHAR || this.dataType==Types.LONGNVARCHAR)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public int getInt_value() {
		return int_value;
	}

	public Value setInt_value(int int_value) {
		this.int_value = int_value;
		this.double_value = int_value;
		if(this.dataType == 0){
			this.dataType = Types.INTEGER;
		}
		return this;
	}
	
	public boolean isIntValue() {
		if(this.dataType==Types.INTEGER ||  this.dataType==Types.SMALLINT)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public long getLong_value() {
		return long_value;
	}

	public Value setLong_value(long long_value) {
		this.long_value = long_value;
		if(this.dataType == 0){
			this.dataType = Types.BIGINT;
		}
		this.double_value = long_value;
		return this;
	}
	
	public boolean isLongValue() {
		if(this.dataType==Types.BIGINT)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public double getDouble_value() {
		return double_value;
	}

	public Value setDouble_value(double double_value) {
		this.double_value = double_value;
		if(this.dataType == 0){
			this.dataType = Types.DOUBLE;
		}
		this.int_value = (int) double_value;
		return this;
	}
	
	public boolean isDoubleValue() {
		if(this.dataType==Types.FLOAT || this.dataType==Types.DOUBLE)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public float getFloat_value() {
		return float_value;
	}

	public Value setFloat_value(float float_value) {
		this.float_value = float_value;
		this.double_value = float_value;
		this.int_value = (int) double_value;
		if(this.dataType == 0){
			this.dataType = Types.REAL;
		}
		return this;
	}
	
	public boolean isFloatValue() {
		if(this.dataType==Types.REAL)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public boolean getBoolean_value() {
		return boolean_value;
	}

	public Value setBoolean_value(boolean boolean_value) {
		this.boolean_value = boolean_value;
		if(this.dataType == 0){
			this.dataType = Types.BOOLEAN;
		}
		return this;
	}
	
	public boolean isBooleanValue() {
		if(this.dataType==Types.BIT || this.dataType==Types.BOOLEAN)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public BigDecimal getBigdecimal_value() {
		return bigdecimal_value;
	}

	public Value setBigdecimal_value(BigDecimal bigdecimal_value) {
		this.bigdecimal_value = bigdecimal_value;
		if(bigdecimal_value != null)
		{
			this.double_value = bigdecimal_value.doubleValue();
		}
		
		if(this.dataType == 0){
			this.dataType = Types.NUMERIC;
		}
		this.int_value = (int) double_value;
		return this;
	}
	
	public boolean isBigdecimalValue()
	{
		if(this.dataType==Types.NUMERIC || this.dataType==Types.DECIMAL)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public Blob getBlob_value() {
		return blob_value;
	}

	public Value setBlob_value(Blob blob_value) {
		this.blob_value = blob_value;
		if(this.dataType == 0){
			this.dataType = Types.BLOB;
		}
		return this;
	}
	
	public boolean isBlobValue() {
		if(this.dataType==Types.BLOB)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public byte getByte_value() {
		return byte_value;
	}

	public Value setByte_value(byte byte_value) {
		this.byte_value = byte_value;
		if(this.dataType == 0){
			this.dataType = Types.TINYINT;
		}
		return this;
	}
	
	public boolean isByteValue() {
		if(this.dataType==Types.TINYINT)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public byte[] getBytes_value() {
		return bytes_value;
	}

	public Value setBytes_value(byte[] bytes_value) {
		this.bytes_value = bytes_value;
		if(this.dataType == 0){
			this.dataType = Types.VARBINARY;
		}
		return this;
	}
	
	public boolean isBytesValue() {
		if(this.dataType==Types.BINARY || this.dataType==Types.VARBINARY || this.dataType==Types.LONGVARBINARY)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public Date getDate_value() {
		return date_value;
	}

	public Value setDate_value(Date date_value) {
		this.date_value = date_value;
		if(this.dataType == 0){
			this.dataType = Types.DATE;
		}
		return this;
	}
	
	public boolean isDateValue() {
		if(this.dataType==Types.DATE)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public Time getTime_value() {
		return time_value;
	}

	public Value setTime_value(Time time_value) {
		this.time_value = time_value;
		if(this.dataType == 0){
			this.dataType = Types.TIME;
		}
		return this;
	}
	
	public boolean isTimeValue() {
		if(this.dataType==Types.TIME || this.dataType==Types.TIMESTAMP)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public Object getObject_value() {
		return object_value;
	}

	public Value setObject_value(Object object_value) {
		this.object_value = object_value;
		return this;
	}

	public Clob getClob_value() {
		return clob_value;
	}

	public Value setClob_value(Clob clob_value) {
		this.clob_value = clob_value;
		if(this.dataType == 0){
			this.dataType = Types.CLOB;
		}
		return this;
	}
	
	public boolean isClobValue() {
		if(this.dataType==Types.CLOB)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

}
