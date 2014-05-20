package org.alfresco.extension.unifiedtimeline.domain;

import java.util.List;

import org.alfresco.service.cmr.repository.NodeRef;

public interface EventProvider {

	public List<TimelineEvent> getEvents(NodeRef node);
}
