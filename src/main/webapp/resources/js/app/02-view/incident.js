/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

DaysSince.View.Incident = Backbone.View.extend({
	initialize: function() {
		this.model.on("change", this.render, this);
		return this;
	},

	render: function() {
		this.$el.html('<h1>Got '+this.model.get("label")+'</h1>');
		window.app.navigate('/app/incident/'+this.model.get("id"));
		return this;
	}
});