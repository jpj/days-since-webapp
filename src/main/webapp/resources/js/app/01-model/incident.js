/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

DaysSince.Model.Incident = Backbone.Model.extend({
	urlRoot: DaysSince.Constant.URL_ROOT+'/api/incident'
});

DaysSince.Collection.Incident = Backbone.Collection.extend({
	model: DaysSince.Model.Incident,
	url: DaysSince.Constant.URL_ROOT+'/api/incident'
});