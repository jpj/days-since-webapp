/**
 * Template Implementation Mustache
 */

/**
 * Template Renderer
 *
 * @param {String} template
 * @param {Object} view
 * @return {String} rendered template
 */

DaysSince.Util.templateRender = function(template, view) {
	return Mustache.to_html(template, view);
};

$(function() {
	$("script[id$=-template]").each(function() {
		DaysSince.Template[$(this).attr("id")] = $(this).html();
	});
});

/*
 * Error Handling
 */

DaysSince.Util.errorResolver = function(error) {
	for(var i = 0; i < error.codes.length; i++) {
		var code = error.codes[i];
		if (DaysSince.Constant.Error.Property[code]) {
			return DaysSince.Constant.Error.Property[code];
		}
	}
	return error.defaultMessage;
};
