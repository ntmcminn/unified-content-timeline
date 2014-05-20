package org.alfresco.extension.unifiedtimeline.domain;

import org.alfresco.service.ServiceRegistry;

public abstract class AbstractAlfrescoEventProvider implements EventProvider 
{
	protected ServiceRegistry registry;
	
	public void setServiceRegistry(ServiceRegistry registry)
	{
		this.registry = registry;
	}
}
