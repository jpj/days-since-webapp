/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

DaysSince.View.Home = Backbone.View.extend({
	events: {
		"click #home .incident": "getIncident"
	},

	initialize: function() {
		this.collection.on("reset", this.render, this);
		return this;
	},

	render: function() {
		this.$el.html(DaysSince.Template["home-template"]);
		this.collection.each(function(model) {
			this.$("#home ul").append(DaysSince.Util.templateRender(DaysSince.Template["incident-preview-template"], {
				daysSince: ((new Date().getTime() - model.get("startDate")) / 1000 / 60 / 60 / 24).toFixed(3),
				label: model.get("label"),
				id: model.get("id")
			}));
		}, this);
		window.app.navigate('/');
		return this;
	},

	getIncident: function(e) {
		var $inc = $(e.currentTarget);
		window.app.views.incident.model.set({id: $inc.data("id")}, {silent: true});
		window.app.views.incident.model.fetch();
	}
});