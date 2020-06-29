/*
 * This class file was automatically generated by ASN1bean (http://www.beanit.com)
 */

package com.beanit.iec61850bean.internal.mms.asn1;

import com.beanit.asn1bean.ber.BerLength;
import com.beanit.asn1bean.ber.BerTag;
import com.beanit.asn1bean.ber.ReverseByteArrayOutputStream;
import com.beanit.asn1bean.ber.types.BerType;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

public class VariableAccessSpecification implements BerType, Serializable {

  private static final long serialVersionUID = 1L;

  private byte[] code = null;
  private VariableDefs listOfVariable = null;
  private ObjectName variableListName = null;

  public VariableAccessSpecification() {}

  public VariableAccessSpecification(byte[] code) {
    this.code = code;
  }

  public VariableDefs getListOfVariable() {
    return listOfVariable;
  }

  public void setListOfVariable(VariableDefs listOfVariable) {
    this.listOfVariable = listOfVariable;
  }

  public ObjectName getVariableListName() {
    return variableListName;
  }

  public void setVariableListName(ObjectName variableListName) {
    this.variableListName = variableListName;
  }

  @Override
  public int encode(OutputStream reverseOS) throws IOException {

    if (code != null) {
      reverseOS.write(code);
      return code.length;
    }

    int codeLength = 0;
    int sublength;

    if (variableListName != null) {
      sublength = variableListName.encode(reverseOS);
      codeLength += sublength;
      codeLength += BerLength.encodeLength(reverseOS, sublength);
      // write tag: CONTEXT_CLASS, CONSTRUCTED, 1
      reverseOS.write(0xA1);
      codeLength += 1;
      return codeLength;
    }

    if (listOfVariable != null) {
      codeLength += listOfVariable.encode(reverseOS, false);
      // write tag: CONTEXT_CLASS, CONSTRUCTED, 0
      reverseOS.write(0xA0);
      codeLength += 1;
      return codeLength;
    }

    throw new IOException("Error encoding CHOICE: No element of CHOICE was selected.");
  }

  @Override
  public int decode(InputStream is) throws IOException {
    return decode(is, null);
  }

  public int decode(InputStream is, BerTag berTag) throws IOException {

    int tlvByteCount = 0;
    boolean tagWasPassed = (berTag != null);

    if (berTag == null) {
      berTag = new BerTag();
      tlvByteCount += berTag.decode(is);
    }

    if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 0)) {
      listOfVariable = new VariableDefs();
      tlvByteCount += listOfVariable.decode(is, false);
      return tlvByteCount;
    }

    if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 1)) {
      BerLength length = new BerLength();
      tlvByteCount += length.decode(is);
      variableListName = new ObjectName();
      tlvByteCount += variableListName.decode(is, null);
      tlvByteCount += length.readEocIfIndefinite(is);
      return tlvByteCount;
    }

    if (tagWasPassed) {
      return 0;
    }

    throw new IOException("Error decoding CHOICE: Tag " + berTag + " matched to no item.");
  }

  public void encodeAndSave(int encodingSizeGuess) throws IOException {
    ReverseByteArrayOutputStream reverseOS = new ReverseByteArrayOutputStream(encodingSizeGuess);
    encode(reverseOS);
    code = reverseOS.getArray();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    appendAsString(sb, 0);
    return sb.toString();
  }

  public void appendAsString(StringBuilder sb, int indentLevel) {

    if (listOfVariable != null) {
      sb.append("listOfVariable: ");
      listOfVariable.appendAsString(sb, indentLevel + 1);
      return;
    }

    if (variableListName != null) {
      sb.append("variableListName: ");
      variableListName.appendAsString(sb, indentLevel + 1);
      return;
    }

    sb.append("<none>");
  }
}