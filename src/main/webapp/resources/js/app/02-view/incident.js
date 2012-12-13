/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

DaysSince.View.Incident = Backbone.View.extend({
	events: {
		"#incident .back a": "getIncidents"
	},

	initialize: function() {
		this.model.on("change", this.render, this);
		return this;
	},

	render: function() {
		this.$el.html(DaysSince.Util.templateRender(DaysSince.Template["incident-template"], {
			daysSince: ((new Date().getTime() - this.model.get("startDate")) / 1000 / 60 / 60 / 24).toFixed(3),
			label: this.model.get("label")
		}));
		window.app.navigate('/app/incident/'+this.model.get("id"));
		return this;
	},

	getIncidents: function(e) {
		e.preventDefault();
		window.app.views.home.collection.fetch();
	}
});