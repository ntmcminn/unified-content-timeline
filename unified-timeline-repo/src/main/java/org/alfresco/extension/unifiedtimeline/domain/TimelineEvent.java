package org.alfresco.extension.unifiedtimeline.domain;

import java.util.Date;

public class TimelineEvent 
{
	
	private Date startDate;
	private Date endDate;
	private String headline = "";
	private String text = "";
	private String media = "";
	private Object eventSource;
	
	public TimelineEvent() {}
	
	public TimelineEvent(Date startDate, Date endDate, String headline, String text)
	{
		this.startDate = startDate;
		this.endDate = endDate;
		this.headline = headline;
		this.text = text;
	}

	public TimelineEvent(Date startDate, Date endDate, String headline, String text, Object eventSource)
	{
		this.startDate = startDate;
		this.endDate = endDate;
		this.headline = headline;
		this.text = text;
		this.eventSource = eventSource;
	}
	
	public Date getStartDate() 
	{
		return startDate;
	}
	public void setStartDate(Date startDate) 
	{
		this.startDate = startDate;
	}
	public Date getEndDate() 
	{
		return endDate;
	}
	public void setEndDate(Date endDate) 
	{
		this.endDate = endDate;
	}
	public String getHeadline() 
	{
		return headline;
	}
	public void setHeadline(String headline) 
	{
		this.headline = headline;
	}
	public String getText() 
	{
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public void setEventSource(Object eventSource)
	{
		this.eventSource = eventSource;
	}
	public Object getEventSource()
	{
		return eventSource;
	}
	public String getMedia() {
		return media;
	}
	public void setMedia(String media) {
		this.media = media;
	}
}
