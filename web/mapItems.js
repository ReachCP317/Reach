var slider = document.getElementById("myRadius");
var output = document.getElementById("radius");
output.innerHTML = slider.value+ " km"; // Display the default slider value
// Update the current slider value (each time you drag the slider handle)
slider.oninput = function() {
    output.innerHTML = this.value + " km";
}
function EventList(value){
	distance = output.innerHTML;
	var mysql = require('mysql');
	var con = mysql.createConnection({
		host: "host",
		user: "",
		password: "",
		database: "",
	});
	con.connect(function(err)){
		if(err) throw err;
		con.query("SELECT * FROM .....", function(err, result, fields){
			if(err) throw err;
			console.log(result);
		});
	});
	for()
	con.connect(function(err) {
	  if (err) throw err;
	  var sql = "UPDATE customers SET address = 'Canyon 123' WHERE address = 'Valley 345'";
	  con.query(sql, function (err, result) {
		if (err) throw err;
		console.log(result.affectedRows + " record(s) updated");
	  });
	});

}