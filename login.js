var name

function keydown() {
	// when you press enter contents of the input will save in name
  if (window.event.keyCode == 13) {
      name = document.getElementById("nickname").value; 
      location.replace("room.html?nick_name=<%=name%>);
  }
}