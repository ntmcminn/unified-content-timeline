package org.alfresco.extension.unifiedtimeline.webscripts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.alfresco.error.AlfrescoRuntimeException;
import org.alfresco.extension.unifiedtimeline.domain.EventProvider;
import org.alfresco.extension.unifiedtimeline.domain.TimelineEvent;
import org.alfresco.model.ContentModel;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.extensions.webscripts.Cache;
import org.springframework.extensions.webscripts.DeclarativeWebScript;
import org.springframework.extensions.webscripts.Status;
import org.springframework.extensions.webscripts.WebScriptRequest;

public class GetTimelineData extends DeclarativeWebScript {

	private 						ServiceRegistry registry;
	private static final 			Log 			logger 		= LogFactory.getLog(GetTimelineData.class);
	private List<EventProvider> 					providers;
	
	@Override
	public Map<String, Object> executeImpl(WebScriptRequest req, Status status, Cache cache) 
	{
		String nodeRef = req.getParameter("nodeRef");
		NodeService ns = registry.getNodeService();
		NodeRef node = new NodeRef(nodeRef);
		
		if(!ns.exists(node))
		{
			throw new AlfrescoRuntimeException("Node " + nodeRef + " does not exist");
		}
	
		Map<String, Object> model = new HashMap<String, Object>();
		List<TimelineEvent> events = new ArrayList<TimelineEvent>();
		
		if(logger.isDebugEnabled())
		{
			logger.debug("Building timeline data for : " + nodeRef);
		}
		
		// first, get the doc creation date and name for the timeline title
		Object docName = ns.getProperty(node, ContentModel.PROP_NAME);
		Object startDate = ns.getProperty(node, ContentModel.PROP_CREATED);
		model.put("docName", docName);
		model.put("startDate", startDate);
		
		for(EventProvider provider : providers)
		{
			events.addAll(provider.getEvents(node));
		}

		model.put("events", events);
		return model;
	}
    
	public void setServiceRegistry(ServiceRegistry registry)
	{
		this.registry = registry;
	}
	
	public void setEventProviders(List<EventProvider> providers)
	{
		this.providers = providers;
	}
}
