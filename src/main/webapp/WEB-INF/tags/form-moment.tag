<%--
- form-moment.tag
-
- Copyright (C) 2012-2021 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@tag language="java" body-content="empty"
	import="acme.framework.helpers.MessageHelper"
%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<%@attribute name="path" required="true" type="java.lang.String"%>
<%@attribute name="code" required="true" type="java.lang.String"%>
<%@attribute name="placeholder" required="false" type="java.lang.String"%>
<%@attribute name="readonly" required="false" type="java.lang.Boolean"%>



<jstl:if test="${placeholder == null}">
	<jstl:set var="placeholder" value="${MessageHelper.getMessage('default.placeholder.moment')}"/>	
</jstl:if>

<jstl:if test="${readonly == null}">
	<jstl:set var="readonly" value="false"/>
</jstl:if>

<div class="form-group" >
	<label for="${path}">
		<acme:message code="${code}"/>
	</label>
	<input 
		id="${path}" 
		name="${path}"
		value="<acme:print value="${requestScope[path]}"/>"
		type="text"
		class="form-control"
		placeHolder="${placeholder}"
		<jstl:if test="${readonly}">
       		readonly
       	</jstl:if>     	
	/>
</div>

<script>
    $(document).ready(function(){
      var date_input=$('#${path}'); //our date input has the name "date"
      var container=$('.bootstrap-iso form').length>0 ? $('.bootstrap-iso form').parent() : "body";
      var options={
        format: 'mm/dd/yyyy',
        container: container,
        todayHighlight: true,
        autoclose: true,
      };
      date_input.datepicker(options);
    })
</script>
