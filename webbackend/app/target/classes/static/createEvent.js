/* Set the width of the side navigation to 250px */
function openNav() {
  document.getElementById("navigatorPanel").style.width = "240px";
}

/* Set the width of the side navigation to 0 */
function closeNav() {
  document.getElementById("navigatorPanel").style.width = "0%";
}

function pageSettings() {
  var title = document.title;
  if (title == "REACH Create Event") {
    document.getElementById("create").href = "#";
    document.getElementById("create").style.color = "#FF6E83";
  }
}

function eventSubmit(){
  var location = getCoordinates();
  var range = document.getElementById("eventRange").value;
  var name = document.getElementById("eventName").value;
  var startDateTime = document.getElementById("eventStartDate").value;
  var endDateTime = document.getElementById("eventEndDate").value;
  var capacity = document.getElementById("eventCapacity").value;
  var type = document.getElementById("eventType").value;
  var numDays = document.getElementById("eventDays").value;
  var description = document.getElementById("eventDescription").value;
  var userID = document.getElementById("userID").value;

  var errMessage = "";
  var returnCode = createEvent(location,range,name,startDateTime,endDateTime,
    capacity,type,numDays,description);

  if (returnCode != 0){
    switch(result){
      case -1:
        /*unknown error; we need to change the error message so that it tells them what to do lol*/
        errMessage = "An unknown error has occurred.";
      case -2:
        /*invalid userID*/
      case -3:
        /**/
      case -4:
        /**/
      case -5:
        /**/
    }
  }


  document.getElementById("errMessage").value = errMessage;
}
var geocoder;
function initialize(){
	geocoder = new google.maps.Geocoder();
}

function getCoordinates() {
var address = document.getElementById('eventLocation').value;
var coordinates;
 geocoder.geocode( { 'address': address}, function(results, status) {
      if (status == 'OK') {
        coordinates = results[0].geometry.location;
      } else {
        alert('Geocode was not successful for the following reason: ' + status);
      }
    });
	return coordinates;
  }
