var graphsAndTablesFromNote;

function sendMail() {
  alert(graphsAndTablesFromNote);
  getGraphsAndTablesFromNote();
  alert(graphsAndTablesFromNote);
	jQuery.ajax({
		type : "POST",
		url : "http://localhost:8080/api/sendMail",
		contentType: "application/html",
		data : graphsAndTablesFromNote
	});
}

function getGraphsAndTablesFromNote() {
	graphsAndTablesFromNote = "<html><head><!-- NVD3 Imports --><link href=\"http://nvd3.org/assets/css/common.css\" rel=\"stylesheet\"><link href=\"http://nvd3.org/assets/css/nv.d3.css\" rel=\"stylesheet\"><script src=\"http://nvd3.org/assets/lib/d3.v3.js\"></script><script src=\"http://nvd3.org/assets/js/nv.d3.js\"></script><script src=\"http://nvd3.org/assets/js/data/stream_layers.js\"></script><script src=\"http://nvd3.org/assets/js/page/index.js\"></script><title>Report</title></head><body><div id='chart'>";;
	  var parent = "";
	  var elements = jQuery('.paragraph-col svg, .paragraph-col table.floatThead-table').closest('.paragraph-col');
    elements.each(function(index, obj) {
       graphsAndTablesFromNote += obj.innerHTML;
    });

	graphsAndTablesFromNote += "</div></body></html>"
}
