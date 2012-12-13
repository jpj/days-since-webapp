/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

DaysSince.View.Home = Backbone.View.extend({
	events: {
		"click .incident": "getIncident"
	},

	initialize: function() {
		this.collection.on("reset", this.render, this);
		return this;
	},

	render: function() {
		this.$el.empty();
		this.collection.each(function(model) {
			this.$el.append('<div class="incident">'+model.get("id")+'</div>');
		}, this);
		return this;
	},

	getIncident: function(e) {
		var $inc = $(e.currentTarget);
		window.app.views.incident.model.set({id: $inc.text()}, {silent: true});
		window.app.views.incident.model.fetch();
	}
});