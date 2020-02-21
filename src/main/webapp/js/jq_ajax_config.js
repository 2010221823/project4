jQuery.ajaxSetup({
	timeout: 6000,
	xhrFields: {
		withCredentials: true
	},
	crossDomain: true
});

jQuery(document).ajaxError(function(ex) {
	console.log(ex);
	alert("服务器正忙....");
});