<!--
To change this template, choose Tools | Templates
and open the template in the editor.
-->

<%@taglib prefix="jwr" uri="http://jawr.net/tags" %>

<!DOCTYPE html>
<html>
	<head>
		<title>Days Since is Loading...</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="HandheldFriendly" content="true"/>
		<meta name="viewport" content="width=device-width, height=device-height"/>

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
		<div>Loading...</div>

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
			<div id="incident">
				<h1 class="incident">{{daysSince}} Days Since {{label}}</h1>
				<div class="back"><a href="../../" title="Back to Incidents"><i class="icon-list"></i></a></div>
			</div>
		</script>
</body>
</html>
