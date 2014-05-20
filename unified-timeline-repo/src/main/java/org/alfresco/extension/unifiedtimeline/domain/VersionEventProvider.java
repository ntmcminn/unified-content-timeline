package org.alfresco.extension.unifiedtimeline.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.version.Version;
import org.alfresco.service.cmr.version.VersionHistory;
import org.alfresco.service.cmr.version.VersionService;

/**
 * The Version event provider.  Generates an event for each version of the document
 * 
 * @author nmcminn
 *
 */
public class VersionEventProvider extends AbstractAlfrescoEventProvider 
{

	@Override
	public List<TimelineEvent> getEvents(NodeRef node) 
	{
		
		List<TimelineEvent> events = new ArrayList<TimelineEvent>();
    	VersionService vs = registry.getVersionService();
    	
    	VersionHistory history = vs.getVersionHistory(node);
    	Collection<Version> versions = history.getAllVersions();
    	for (Version version : versions)
    	{
    		TimelineEvent ev = new TimelineEvent(
    				version.getFrozenModifiedDate(),
    				version.getFrozenModifiedDate(),
    				"Version " + version.getVersionLabel() + " Created",
    				"A new version was created by " + version.getFrozenModifier(),
    				version);
    		
    		ev.setMedia(getVersionThumbnailUrl(version.getFrozenStateNodeRef()));
    		events.add(ev);
    	}
    	return events;
    	
	}
	
	private String getVersionThumbnailUrl(NodeRef version)
	{
		return "/share/proxy/alfresco/api/node/" 
				+ version.getStoreRef().getProtocol() + "/" 
				+ version.getStoreRef().getIdentifier() + "/"
				+ version.getId() + "/content/thumbnails/doclib/file.png?c=create&ph=true";
	}
}
