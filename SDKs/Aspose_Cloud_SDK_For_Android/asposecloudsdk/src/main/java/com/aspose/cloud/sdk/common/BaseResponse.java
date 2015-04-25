/**
 * 
 */
package com.aspose.cloud.sdk.common;

import java.lang.String;

public class BaseResponse {
    public BaseResponse() { }

    private String Code;
    private String Status;
 
    public String getCode(){return Code;}
    public String getStatus(){return Status;}
    
    public void  setCode(String temCode){ Code=temCode;}
    public void setStatus(String temStatus){ Status=temStatus;}
}
