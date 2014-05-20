<#include "../../include/alfresco-template.ftl" />
<@templateHeader>
<@script type="text/javascript" src="${page.url.context}/res/unified-timeline/js/JQuery/jquery-1.11.1.js"></@script>
<@script type="text/javascript" src="${page.url.context}/res/unified-timeline/js/JQueryUI/js/jquery-ui-1.10.4.custom.js"></@script>
</@>

<@templateBody>
   <div id="alf-hd">
      <@region id="share-header" scope="global" chromeless="true"/>
      <@region id="title" scope="template"/>
   </div>
   <div id="bd">
      <div class="yui-gc"><@region id="timeline" scope="template"/></div>
   </div>
</@>

<@templateFooter>
   <div id="alf-ft">
      <@region id="footer" scope="global"/>
   </div>
</@>