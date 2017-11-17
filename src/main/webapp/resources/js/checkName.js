function checkProductName() {
    var xmlhttp;
    var k = document.getElementById("name").value;


    var urls = getContextPath() + "/checkProductName?name=" + k;

    if (window.XMLHttpRequest) {
        xmlhttp = new XMLHttpRequest();
    }
    else {
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4) {

            document.getElementById("checkName").innerHTML = xmlhttp.responseText;

        }
    };
    xmlhttp.open("GET", urls, true);
    xmlhttp.send();
}

function checkCompoundName() {
    var xmlhttp;
    var k = document.getElementById("name").value;


    var urls = getContextPath() + "/checkCompoundName?name=" + k;

    if (window.XMLHttpRequest) {
        xmlhttp = new XMLHttpRequest();
    }
    else {
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4) {

            document.getElementById("checkName").innerHTML = xmlhttp.responseText;

        }
    };
    xmlhttp.open("GET", urls, true);
    xmlhttp.send();
}

function checkComponentName() {
    var xmlhttp;
    var k = document.getElementById("name").value;


    var urls = getContextPath() + "/checkComponentName?name=" + k;

    if (window.XMLHttpRequest) {
        xmlhttp = new XMLHttpRequest();
    }
    else {
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4) {

            document.getElementById("checkName").innerHTML = xmlhttp.responseText;

        }
    };
    xmlhttp.open("GET", urls, true);
    xmlhttp.send();
}

function getContextPath() {

    var t = window.location.pathname;
    t = t.substr(0, t.lastIndexOf("/"));
    return t; //window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
}