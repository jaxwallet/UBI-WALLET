package com.bonuswallet.token.entity;

/**
 * Created by James on 19/04/2019.
 * AJ TECHNOLOGIES LTD
 */
public class XMLDsigVerificationResult
{
    public boolean isValid;
    public String keyName;
    public String issuerPrincipal;
    public String subjectPrincipal;
    public String keyType;
    public String failureReason;

    public XMLDsigVerificationResult()
    {
        isValid = false;
        keyName = "";
        issuerPrincipal = "";
        subjectPrincipal = "";
        keyType = "";
    }
}
