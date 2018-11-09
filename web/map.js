function getLocation(){
if(navigator.geolocation){
navigator.geolocation.getCurrentPosition(showPosition,showError);
}
}
function showPosition(position){
var lat =position.coords.latitude;
var lon = position.coords.longitude;
var latlon = new google.maps.LatLng(lat,lon);
var mapOptions={center:latlon,zoom:16, mapTypeId:google.maps.MapTypeId.ROADMAP};
var map = new google.maps.Map(document.getElementById("mapPanel"),mapOptions);
var marker = new google.maps.Marker({position:latlon,map:map,title:"You are Here!",animation:google.maps.Animation.BOUNCE});
}
function showError(error) {
    switch(error.code) {
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
