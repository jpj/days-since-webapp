DaysSince.Router.App = Backbone.Router.extend({

	routes: {
		"": "home",
		"app/incident/:id": "getIncident"
	},

	initialize: function() {
		this.views = {
			home: new DaysSince.View.Home({el: $("#page"), collection: new DaysSince.Collection.Incident()}),
			incident: new DaysSince.View.Incident({el: $("#page"), model: new DaysSince.Model.Incident(), incidentRendered: $("meta[name=incidentRendered]").attr("content") === 'true'})
		}
	},

	home: function() {
		this.views.home.collection.fetch();
	},
	getIncident: function(id) {
		if (!this.views.incident.options.incidentRendered) {
			// Fetch/render only if the view wasn't rendered by the server
			if (this.views.incident.model.get("id") === id) {
				this.views.incident.render();
			} else {
				this.views.incident.model.set({id: id}, {silent: true});
				this.views.incident.model.fetch();
			}
		} else {
			// Next pass will need rendering
			this.views.incident.options.incidentRendered = false;
		}
	}
});