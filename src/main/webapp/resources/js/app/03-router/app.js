DaysSince.Router.App = Backbone.Router.extend({

	routes: {
		"": "home",
		"/": "home",
		"/vehicle/:vehicleId": "getVehicle"
	},

	initialize: function() {

	},

	home: function() {
		alert("Home");
	}
});