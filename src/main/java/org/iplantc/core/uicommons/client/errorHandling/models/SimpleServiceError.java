package org.iplantc.core.uicommons.client.errorHandling.models;

import com.google.web.bindery.autobean.shared.AutoBean.PropertyName;

public interface SimpleServiceError {

    @PropertyName("action")
    String getServiceName();

    @PropertyName("error_code")
    String getErrorCode();

    @PropertyName("status")
    String getStatus();

    /**
     * XXX JDS This key is only used in one or two error codes and should not be relied upon.
     * TODO JDS Request consistent error message JSON response format from backend services.
     * 
     * @return
     */
    String getReason();
}