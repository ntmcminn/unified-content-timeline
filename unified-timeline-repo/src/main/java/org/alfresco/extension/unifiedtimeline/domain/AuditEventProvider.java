package org.alfresco.extension.unifiedtimeline.domain;

import java.util.ArrayList;
import java.util.List;

import org.alfresco.service.cmr.repository.NodeRef;

public class AuditEventProvider extends AbstractAlfrescoEventProvider 
{

	@Override
	public List<TimelineEvent> getEvents(NodeRef node) 
	{
		// TODO - query audit service for events about this node
		List<TimelineEvent> events = new ArrayList<TimelineEvent>();
		return events;
	}

}
