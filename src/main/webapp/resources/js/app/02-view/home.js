/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

DaysSince.View.Home = Backbone.View.extend({
	events: {
		"click #home .incident a": "getIncident",
		"submit #home form": "newIncident"
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
		return this;
	},

	getIncident: function(e) {
		e.preventDefault();
		var $inc = $(e.currentTarget).parent();
		window.app.views.incident.model.set({id: $inc.data("id")});
		window.app.views.incident.model.fetch();
		window.app.navigate('/app/incident/'+$inc.data("id"));
	},

	newIncident: function(e) {
		e.preventDefault();
		var $form = $(e.currentTarget);
		var incident = new DaysSince.Model.Incident();
		incident.set({startDate: new Date(), label: $form.find("input[name=label]").val()});
		incident.save(null, {
			success: function() {
				window.app.views.incident.model.set({id: incident.get("id")}, {silent: true});
				window.app.views.incident.model.fetch();
				window.app.navigate('/app/incident/'+incident.get("id"));
			}
		});
	}
});