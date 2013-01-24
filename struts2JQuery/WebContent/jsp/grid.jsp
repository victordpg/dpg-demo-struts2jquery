<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>

<%-- <div id="col2">
	<h2>Grid</h2>
	<p>A simple grid with pager.</p>
	
	<s:url var="remoteurl" action="gridJson" />
    <sjg:grid
    	id="gridtable"
    	caption="Grid Examples"
    	dataType="json"
    	href="%{remoteurl}"
    	pager="true"
    	gridModel="gridModel"
    	rowList="5,10,15,20"
    	rowNum="10"
    	rownumbers="true"
    	resizable="true"
    	resizableAnimate="true"
    	resizableGhost="true"
    	resizableHandles="all"
    	width="800"
    	shrinkToFit="false">
    	<sjg:gridColumn name="id" 		index="id" 		  title="ID" 		width="50"   sortable="true"   formatter="integer" />
    	<sjg:gridColumn name="name" 	index="name" 	  title="Name"  	width="150"  sortable="true"/>
    	<sjg:gridColumn name="filetype" index="filetype"  title="FileType"	width="100"  sortable="false"/>
    	<sjg:gridColumn name="url" 		index="url" 	  title="URL" 	    width="400"  sortable="false"  formatter="formatLink"/>
    </sjg:grid>
		
</div> --%>

<div id="col3">
	<h2>Grid Edit</h2>
	<p>A simple grid can be edited.</p>
	<s:url var="remoteurl" action="gridJson" />
	<s:url var="editURL" action="gridEdit" />
    <sjg:grid
	    	id="gridEdit"
	    	caption="Grid Edit Examples"
	    	dataType="json"
	    	href="%{remoteurl}"
	    	pager="true"
	    	gridModel="gridModel"
	    	rowList="5,10,15,20"
	    	rowNum="10"
	    	editurl="%{editURL}"
	    	editinline="false"
	    	navigator="true"
	    	navigatorAdd="true"
	    	navigatorEdit="true"
	    	navigatorDelete="true"
	    	navigatorView="true"
	    	navigatorAddOptions="{height:280,width:400,reloadAfterSubmit:true}"
	    	navigatorEditOptions="{height:280,width:400,reloadAfterSubmit:false}"
	    	navigatorDeleteOptions="{height:280,width:400,reloadAfterSubmit:true}"
	    	navigatorViewOptions="{height:280,width:400,reloadAfterSubmit:true}"
	    	navigatorSearchOptions="{sopt:['eq','ne','lt','gt']}"
	    	rownumbers="true"
	    	resizable="false"
	    	width="800"
	    	shrinkToFit="false">
		<sjg:gridColumn 
			name="id" 
			title="ID" 
			editable="false"
			width="50" 
			sortable="true" 
			formatter="integer" />
		<sjg:gridColumn 
			name="name" 
			index="name" 
			editable="true"
			edittype="text"
			title="Name" 
			width="150" 
			sortable="true" />
		<sjg:gridColumn 
			name="filetype" 
			index="filetype" 
			editable="true"
			edittype="select" 
			editoptions="{value:'.pdf:PDF;.jpeg:JPEG'}" 			
			title="FileType" 
			width="100" 
			sortable="false" />
		<sjg:gridColumn 
			name="url" 
			index="url" 
			editable="true" 
			edittype="file"
			value="button"
			title="URL" 
			width="400" 
			sortable="false" 
			formatter="formatLink" />
	</sjg:grid>
</div>
