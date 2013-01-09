/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

DaysSince.Model.User = Backbone.Model.extend({
	urlRoot: DaysSince.Constant.URL_ROOT+'/api/user'
});

DaysSince.Collection.User = Backbone.Collection.extend({
	model: DaysSince.Model.User,
	url: DaysSince.Constant.URL_ROOT+'/api/user'
});