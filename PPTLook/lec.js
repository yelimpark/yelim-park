var INDENT = "    ";
var DRAWING = false;

var smax = 1;
var snum = 0;

function nodeValue(node) {
    var result = "";
    if (node.nodeType == 1) {
        var children = node.childNodes;
        for (var i = 0; i < children.length; ++i) {
            result += nodeValue(children[i]);
        }
    }
    else if (node.nodeType == 3) {
        result = node.nodeValue;
    }
    return(result);
}

function hasClass(object, className) {
    if (!object.className) return false;
    return (object.className.search('(^|\\s)' + className + '(\\s|$)') != -1);
}

function GetElementsWithClassName(elementName,className) {
    var allElements = document.getElementsByTagName(elementName);
    var elemColl = new Array();
    for (var i = 0; i< allElements.length; i++) {
        if (hasClass(allElements[i], className)) {
            elemColl[elemColl.length] = allElements[i];
        }
    }
    return elemColl;
}

function editHtml() {
  var layoutDiv = document.getElementsByClassName("layout");
  var layoutArr = (layoutDiv[0].innerHTML).split(/\n/g);
  layoutDiv[0].innerHTML = '<div id="side">' +
   layoutArr[1] + layoutArr[4] + layoutArr[5] + layoutArr[6] +layoutArr[7] +
   '</div> <div id="fold"> <a href "javascript:fold();">+</a> </div>';
}

//var slideIndexMap = [];

function slideLabel() {
    var slideColl = GetElementsWithClassName('*', 'slide');
    var index = document.getElementById('index');
    smax = slideColl.length;

    for (var n = 0; n < smax; n++) {
        var obj = slideColl[n];
        obj.setAttribute('id', 'slide' + n);
        // if (isOp) continue;

        var otext = '';
        var menu = obj.firstChild;
        if (!menu) continue; // to cope with empty slides
        while (menu != null && menu.nodeType == 3) {
            menu = menu.nextSibling;
        }
        if (!menu) continue; // to cope with slides with only text nodes

        var menunodes = menu.childNodes;
        for (var o = 0; o < menunodes.length; o++) {
            otext += nodeValue(menunodes[o]);
        }

        otext = otext.replace(/\r/g, "");
        otext = otext.replace(/\n/g, "");
        var value = n + ':' + otext ;
        if (hasClass(obj, 'titleslide') || hasClass(obj, 'lecturetitle')) {
            value = n + " : === " + otext + " ===";
        }

        var link = document.createElement("a");
        link.href = '#slide' + n;
        link.innerText = value;

        var li = document.createElement("li");
        li.appendChild(link);
        index.appendChild(li);
//        slideIndexMap[n] = n;
      }
}

function createControls() {
    var controlsDiv = document.getElementById("controls");
    if (!controlsDiv) return;

    controlsDiv.innerHTML = '<form action="#" id="controlForm">' +
    '<div id="navaLinks">' +
    '<a accesskey="z" id="prev" title="Previous Slide" href="javascript:go(-1);">&lsaquo;<\/a>' +
    '<span id="slideNum"> </span>' +
    '<a accesskey="x" id="next" title="Next Slide" href="javascript:go(1);">&rsaquo;<\/a></div>' +
    '<div id="navList"><ul id="index" ><\/ul><\/div>' +
    '<\/form>';
}


function fold(){
    var sl = document.getElementById("slide");
    if(sl.style.display=='none'){
        sl.style.display = 'block';
    }else{
        sl.style.display = 'none';
    }
}

function currentSlide() {
    var sn = document.getElementById('slideNum');

    sn.innerHTML = '<span id="snHere">' + snum + '<\/span> ' +
        '<span id="snSep">\/<\/span> ' +
        '<span id="snTotal">' + (smax-1) + '<\/span>';

    if (snum == 0) {
        location.hash = "";
    } else {
        location.hash = "slide" + snum;
    }
}

function sideResizing() {
  var lDiv = document.getElementsByClassName("layout");
  lDiv[0].style.width = (window.innerWidth * (3/10)) + 'px';
}

function detectResize() {
  jQuery(function($){
    $(window).resize( sideResizing );
    sideResizing();
  });
}

function startup() {
  editHtml();
  createControls();
  slideLabel();
  currentSlide();
  detectResize();
}

window.onload = startup;
