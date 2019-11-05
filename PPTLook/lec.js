var INDENT = "    ";
var DRAWING = false;

var smax = 1;
var snum = 0;
var controlVis = 'visible';

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

var slideIndexMap = [];

function slideLabel() {
    var slideColl = GetElementsWithClassName('*', 'slide');
    var list = document.getElementById('jumplist');
    smax = slideColl.length;
    var liCount = 0;

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

        var value = n + ' : '  + otext ;
        if (hasClass(obj, 'titleslide') || hasClass(obj, 'lecturetitle')) {
            value = n + " : === " + otext + " ===";
        }

        var link = document.createElement("a");
        link.href = '#slide' + n;
        link.innerText = value;

        var li = document.createElement("li");
        li.appendChild(link);
        list.appendChild(li);
        slideIndexMap[n] = liCount;
        liCount++;
      }
}

function createControls() {
    var controlsDiv = document.getElementById("controls");
    if (!controlsDiv) return;
    // var hider = ' onmouseover="showHide(\'s\');" onmouseout="showHide(\'h\');"';
    var hider = '';
    var hideDiv, hideList = '';
    if (controlVis == 'hidden') {
        hideDiv = hider;
    } else {
        hideList = hider;
    }
    controlsDiv.innerHTML = '<form action="#" id="controlForm"' + hideDiv + '>' +
    '<div id="navaLinks">' +
    '<a accesskey="z" id="prev" title="Previous Slide" href="javascript:go(-1);">&lsaquo;<\/a>' +
    '<span id="slideNum"> </span>' +
    '<a accesskey="x" id="next" title="Next Slide" href="javascript:go(1);">&rsaquo;<\/a></div>' +
    '<div id="navList"' + hideList + '><ul id="jumplist" ><\/ul><\/div>' +
    '<\/form>';
    if (controlVis == 'hidden') {
        var hidden = document.getElementById('navLinks');
    } else {
        var hidden = document.getElementById('jumplist');
    }
    // *** commented out by marty so that controls will always show
    // addClass(hidden,'hideme');
}

function fold(){
    var con = document.getElementByClassName("layout");
    if(con.style.display=='none'){
        con.style.display = 'block';
    }else{
        con.style.display = 'none';
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

function startup() {
  createControls();
  slideLabel();
  currentSlide();
}

window.onload = startup;
