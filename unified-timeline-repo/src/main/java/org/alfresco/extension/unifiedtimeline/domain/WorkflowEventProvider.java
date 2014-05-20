package org.alfresco.extension.unifiedtimeline.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.alfresco.repo.web.scripts.workflow.WorkflowModelBuilder;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.cmr.workflow.WorkflowInstance;
import org.alfresco.service.cmr.workflow.WorkflowService;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

/**
 * The Workflow event provider.  This provider reads some simple events from the default
 * Activiti workflows.  It may or may not work right with custom workflows, depending on 
 * what the model looks like.
 * 
 * @author nmcminn
 *
 */
public class WorkflowEventProvider extends AbstractAlfrescoEventProvider 
{

	@Override
	public List<TimelineEvent> getEvents(NodeRef node) 
	{
		
		NodeService ns = registry.getNodeService();
    	WorkflowService wfs = registry.getWorkflowService();
    	DateTimeFormatter dateParser = ISODateTimeFormat.dateTimeParser();
    	List<TimelineEvent> events = new ArrayList<TimelineEvent>();
    	WorkflowModelBuilder modelBuilder = 
    			new WorkflowModelBuilder(registry.getNamespaceService(), 
    									 registry.getNodeService(), 
    									 registry.getAuthenticationService(), 
    									 registry.getPersonService(),
    									 registry.getWorkflowService(), 
    									 registry.getDictionaryService());

		List<WorkflowInstance> active = wfs.getWorkflowsForContent(node, true);
		List<WorkflowInstance> inactive = wfs.getWorkflowsForContent(node, false);
		
		// merge the lists
		ArrayList<WorkflowInstance> all = new ArrayList<WorkflowInstance>(active.size() + inactive.size());
		all.addAll(active);
		all.addAll(inactive);
		
		for(WorkflowInstance instance : all)
		{
			
			Map<String, Object> instanceDetail = modelBuilder.buildDetailed(instance, true);
			
			ArrayList<HashMap<String, Object>> tasks = (ArrayList<HashMap<String, Object>>)instanceDetail.get(WorkflowModelBuilder.TASK_WORKFLOW_INSTANCE_TASKS);
			
			for(HashMap<String, Object> task : tasks)
			{
				TimelineEvent event = new TimelineEvent();
				HashMap taskProps = (HashMap)task.get("properties");
				
				if(taskProps.get("bpm_startDate") != null)
				{
					Date startDate = dateParser.parseDateTime(String.valueOf(taskProps.get("bpm_startDate"))).toDate();
					event.setStartDate(startDate);
				}
				if(taskProps.get("bpm_endDAte") != null)
				{
					Date endDate = dateParser.parseDateTime(String.valueOf(taskProps.get("bpm_comletionDate"))).toDate();
					event.setEndDate(endDate);
				}
				else
				{
					event.setEndDate(new Date());
				}
				
				event.setText(String.valueOf(task.get(WorkflowModelBuilder.TASK_DESCRIPTION)));
				event.setHeadline(String.valueOf(task.get(WorkflowModelBuilder.TASK_TITLE)));
				events.add(event);
						
			}
		}

    	return events;
	}
}
