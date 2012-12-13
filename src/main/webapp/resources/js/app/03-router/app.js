DaysSince.Router.App = Backbone.Router.extend({

	routes: {
		"": "home",
		"app/incident/:id": "getIncident"
	},

	initialize: function() {
		this.views = {
			home: new DaysSince.View.Home({el: $("body"), collection: new DaysSince.Collection.Incident()}),
			incident: new DaysSince.View.Incident({el: $("body"), model: new DaysSince.Model.Incident()})
		}
	},

	home: function() {
		this.views.home.collection.fetch();
	},
	getIncident: function(id) {
		this.views.incident.model.set({id: id}, {silent: true});
		this.views.incident.model.fetch();
	}
});