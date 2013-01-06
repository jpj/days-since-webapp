/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

DaysSince.View.Header = Backbone.View.extend({
	events: {
		"click a.user-settings": "loadUserSettings"
	},
	initialize: function() {
		return this;
	},
	render: function () {
		return this;
	},
	loadUserSettings: function(e) {
		e.preventDefault();
		window.app.views.userSettings.model.set({id: this.$el.data("userId")}, {silent: true});
		window.app.views.userSettings.model.fetch();
		window.app.navigate('/user-settings');
	}
});