/**
 * 
 */
package org.iplantc.core.uicommons.client.models.deployedcomps;

import java.util.List;

import com.google.web.bindery.autobean.shared.AutoBean.PropertyName;

/**
 * @author sriram
 *
 */
public interface DeployedComponentList {

    @PropertyName("components")
    List<DeployedComponent> getDCList();

}
