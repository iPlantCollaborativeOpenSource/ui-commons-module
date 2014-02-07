package org.iplantc.de.commons.client.models.sysmsgs;

import java.util.List;

import com.google.web.bindery.autobean.shared.AutoBean.PropertyName;

/**
 * describes a list of system messages
 */
public interface MessageList {

	@PropertyName("system-messages")
	List<Message> getList();
	
	@PropertyName("system-messages")
	void setList(List<Message> messages);
	
}