<%--
    Document   : main
    Created on : Nov 17, 2010, 6:36:40 AM
    Author     : josh
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="jwr" uri="http://jawr.net/tags" %>

<c:set var="appcache"><decorator:getProperty property="meta.appcache"/></c:set>

<!doctype html>
<html<c:if test="${appcache eq 'on'}"> manifest="<c:url value="/resources/days-since.appcache"/>"</c:if>>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="HandheldFriendly" content="true"/>
		<meta name="viewport" content="width=device-width, height=device-height"/>

		<title><decorator:title/></title>

		<jwr:script src="/resources/js/app.js"/>
		<jwr:style src="/resources/css/app.css"/>

		<script type="text/javascript">
			$.ajaxSetup({ cache: false });

			$(function() {
				// Start routing
				window.app = new DaysSince.Router.App();
				Backbone.history.start({pushState: true, root: DaysSince.Constant.URL_ROOT+'/'});
			});
		</script>

		<decorator:head/>

	</head>
	<body>
		<decorator:body/>

		<script type="text/html" id="home-template">
			<div id="home">
				<div>
					<form>
						<div>
							Days Since
							<input type="text" name="label" placeholder="..."/>
							<input type="submit" value="Go"/>
						</div>
					</form>
				</div>
				<h2>Most Recent Incidents</h2>
				<ul>
				</ul>
			</div>
		</script>

		<script type="text/template" id="incident-preview-template">
			<li data-id="{{id}}" class="incident">{{daysSince}} Days Since <a href="app/incident/{{id}}">{{label}}</a></li>
		</script>

		<script type="text/template" id="incident-template">
			<%@include file="../../partial/incident.html" %>
		</script>
	</body>
</html>
