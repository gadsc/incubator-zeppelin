var graphsAndTablesFromNote;

function getGraphsAndTablesFromNote() {
	graphsAndTablesFromNote = "<html><body>"

	jQuery("div > table, div > svg").each(function(index) {
		graphsAndTablesFromNote += jQuery(this).html();
	});

	graphsAndTablesFromNote += "</body></html>"
}

function sendMail() {
	jQuery.ajax({
		type : "POST",
		url : "http://localhost:8080/api/sendMail",
		data : jQuery(graphsAndTablesFromNote).serialize()
	});
}