<!--
To change this template, choose Tools | Templates
and open the template in the editor.
-->

<%@taglib prefix="jwr" uri="http://jawr.net/tags" %>

<!DOCTYPE html>
<html>
	<head>
		<title>Main App</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

		<jwr:script src="/resources/js/app.js"/>
		<jwr:style src="/resources/css/app.css"/>

		<script type="text/javascript">
			$.ajaxSetup({ cache: false });

			$(function() {
				// Start routing
				var app = new DaysSince.Router.App();
				Backbone.history.start({pushState: true, root: '/days-since-webapp/'});
			});
		</script>
	</head>
	<body>
		<div>TODO write content</div>
	</body>
</html>
