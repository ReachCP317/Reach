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
  if (title == "Dashboard") {
    document.getElementById("search").href = "#";
    document.getElementById("search").style.color = "#FF6E83";
  }
}
