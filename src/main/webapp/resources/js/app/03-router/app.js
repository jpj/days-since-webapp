DaysSince.Router.App = Backbone.Router.extend({

	routes: {
		"": "home",
		"app/incident/:id": "getIncident"
	},

	initialize: function() {
	},

	home: function() {
		alert("Home");
	},
	getIncident: function(id) {
		alert("Calling "+id);
	}
});