function getLocation() {
  if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(showPosition, showError);
  }


}

function showPosition(position) {
  var map;
  var directionsDisplay;
  var directionsService = new google.maps.DirectionsService();

  var lat = position.coords.latitude;
  var lon = position.coords.longitude;
  var latlon = new google.maps.LatLng(lat, lon);
  var mapOptions = {
    center: latlon,
    zoom: 16,
    mapTypeId: google.maps.MapTypeId.ROADMAP,

    disableDefaultUI: true,
    styles: [
      {
        elementType: 'geometry',
        stylers: [{
          color: '#242f3e'
        }]
      },
      {
        elementType: 'labels.text.stroke',
        stylers: [{
          color: '#242f3e'
        }]
      },
      {
        elementType: 'labels.text.fill',
        stylers: [{
          color: '#746855'
        }]
      },
      {
        featureType: 'administrative.locality',
        elementType: 'labels.text.fill',
        stylers: [{
          color: '#d59563'
        }]
      },
      {
        featureType: 'poi',
        elementType: 'labels.text.fill',
        stylers: [{
          color: '#d59563'
        }]
      },
      {
        featureType: 'poi.park',
        elementType: 'geometry',
        stylers: [{
          color: '#263c3f'
        }]
      },
      {
        featureType: 'poi.park',
        elementType: 'labels.text.fill',
        stylers: [{
          color: '#6b9a76'
        }]
      },
      {
        featureType: 'road',
        elementType: 'geometry',
        stylers: [{
          color: '#38414e'
        }]
      },
      {
        featureType: 'road',
        elementType: 'geometry.stroke',
        stylers: [{
          color: '#212a37'
        }]
      },
      {
        featureType: 'road',
        elementType: 'labels.text.fill',
        stylers: [{
          color: '#9ca5b3'
        }]
      },
      {
        featureType: 'road.highway',
        elementType: 'geometry',
        stylers: [{
          color: '#746855'
        }]
      },
      {
        featureType: 'road.highway',
        elementType: 'geometry.stroke',
        stylers: [{
          color: '#1f2835'
        }]
      },
      {
        featureType: 'road.highway',
        elementType: 'labels.text.fill',
        stylers: [{
          color: '#f3d19c'
        }]
      },
      {
        featureType: 'transit',
        elementType: 'geometry',
        stylers: [{
          color: '#2f3948'
        }]
      },
      {
        featureType: 'transit.station',
        elementType: 'labels.text.fill',
        stylers: [{
          color: '#d59563'
        }]
      },
      {
        featureType: 'water',
        elementType: 'geometry',
        stylers: [{
          color: '#17263c'
        }]
      },
      {
        featureType: 'water',
        elementType: 'labels.text.fill',
        stylers: [{
          color: '#515c6d'
        }]
      },
      {
        featureType: 'water',
        elementType: 'labels.text.stroke',
        stylers: [{
          color: '#17263c'
        }]
      }
    ]
  };
  map = new google.maps.Map(document.getElementById("mapPanel"), mapOptions);
  //directionsDisplay.setMap(map);


  var user = new google.maps.Marker({
    position: latlon,
    map: map,
    title: "You are Here!",
    icon: '/person_marker.png'
  });
  
var eventCount = 0;
  for (var i = 0; i < eventInfo.length; i++){
    var marker = new google.maps.Marker({  
      position: new google.maps.LatLng(eventInfo[i].lat, eventInfo[i].long),
      map: map,
      title: eventInfo[i].name,
      icon: '/party_popper_map.png'
    });
	if(eventCount <= 5){
		eventCount = eventCount + 1;
	}
  }
  nearbyEvents(eventCount);

  

  //tester marker using pub on king
  var pub = new google.maps.Marker({
    position: new google.maps.LatLng(43.4678, -80.5232),
    map: map,
    title: 'Desination',
    icon: '/party_popper_map.png'
  });

}

function showError(error) {
  switch (error.code) {
    case error.PERMISSION_DENIED:
      x.innerHTML = "User denied the request for Geolocation."
      break;
    case error.POSITION_UNAVAILABLE:
      x.innerHTML = "Location information is unavailable."
      break;
    case error.TIMEOUT:
      x.innerHTML = "The request to get user location timed out."
      break;
    case error.UNKNOWN_ERROR:
      x.innerHTML = "An unknown error occurred."
      break;
  }
}
getLocation();

function nearbyEvents(count){
	  for (var i = 0; i < count; i++){
	  var EventsContainer = document.getElementById("NearbyEvents");
	  var eventPanel = document.createElement("div");
	  eventPanel.onclick = goToDisplay(eventInfo[i].eventID);
	  var name = document.createElement("p");
	  var date = document.createElement("p");
	  var description = document.createElement("p");
	  var line = document.createElement("hr");
	  eventPanel.append(name);
	  eventPanel.append(date);
	  eventPanel.append(description);
	  eventPanel.append(line);
	  name.innerHTML = eventInfo[i].name;
	  name.style="font-size:18px; text-align:left";
	  date.innerHTML = eventInfo[i].startTime + "-" + eventInfo[i].endTime;
	  date.style="font-size:15px; text-align:right;";
	  description.innerHTML = eventInfo[i].description+;
	  description.style="font-size:15px;text-align:left; overflow:hidden; text-overflow:ellipsis;";
	  line.style="color:white;
  }
}
