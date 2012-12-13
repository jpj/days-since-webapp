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
				window.app = new DaysSince.Router.App();
				Backbone.history.start({pushState: true, root: DaysSince.Constant.URL_ROOT+'/'});
			});
		</script>
	</head>
	<body>
		<div>TODO write content</div>

		<script type="text/html" id="home-template">
			<div id="home">
				<h2>Most Recent Incidents</h2>
				<ul>
				</ul>
			</div>
		</script>

		<script type="text/template" id="incident-preview-template">
			<li data-id="{{id}}" class="incident">{{daysSince}} Days Since {{label}}</li>
		</script>

		<script type="text/template" id="incident-template">
			<div class="incident">
				<div class="back"><a href="../../">Back to Incidents</a></div>
				<div class="incident">{{daysSince}} Days Since {{label}}</div>
			</div>
		</script>
</body>
</html>
