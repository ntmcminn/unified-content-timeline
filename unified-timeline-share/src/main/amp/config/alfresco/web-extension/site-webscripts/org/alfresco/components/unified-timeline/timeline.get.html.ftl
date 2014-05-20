<#assign el=args.htmlid?js_string>

<div id="${el}-body" class="details-panel">

	<h2 id="${el}-heading" class="thin dark">
		${msg("header.timeline")}
	</h2>

	<div class="panel-body timeline">
		<div id="${el}-timeline" class="timeline"></div>
	</div>
	<script type="text/javascript">//<![CDATA[
	(function($)
	{
		var source = (Alfresco.constants.PROXY_URI + 'unified-timeline/timelinedata?nodeRef=' + '${nodeRef?js_string}');
		
	    $(document).ready(function() {
			createStoryJS({
				type:			'timeline',
				width:			'100%',
				height:			'400',
				source:			source,
				embed_id:		'${el}-timeline',
				start_at_slide: '1',
				debug:			true,
				css:        	'${page.url.context}/res/unified-timeline/js/TimelineJS/css/timeline.css',     
	            js:         	'${page.url.context}/res/unified-timeline/js/TimelineJS/js/timeline-min.js'
			});
		});
	})(jQuery);
 	//]]></script>
</div>