/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

DaysSince.View.UserSettings = Backbone.View.extend({
	events: {
	},
	initialize: function() {
		this.model.on("change", this.render, this);
		return this;
	},
	render: function() {
		alert(this.model.get("login"));
		return this;
	}
});