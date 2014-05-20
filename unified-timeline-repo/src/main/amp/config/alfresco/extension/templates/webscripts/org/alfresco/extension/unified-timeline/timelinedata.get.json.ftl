<#assign dateFormat = "yyyy-MM-dd'T'HH:mm:ss">
<#assign tjsFormat = "yyyy,MM,dd,HH,mm,ss">
{
    "timeline":
    {
        "headline":"Document Timeline",
        "type":"default",
		"text":"${docName}",
		"startDate":"${startDate?string(tjsFormat)}",
        "date": [
			<#list events as event>
			{
                "startDate":"${event.startDate?string(tjsFormat)}",
				"endDate":"${event.endDate?string(tjsFormat)}",
                "headline":"${event.headline}",
                "text":"${event.text}",
                "asset":
                {
                    "media":"${event.media}",
                    "credit":"",
                    "caption":""
                }  
			}<#if event_has_next>,</#if>
			</#list>
			
        ]
    }
}