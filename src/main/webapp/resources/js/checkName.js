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

function checkCategoryName() {
    var xmlhttp;
    var k = document.getElementById("name").value;


    var urls = getContextPath() + "/checkCategoryName?name=" + k;

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
    return t;
}

function checkComponentCode() {
    var xmlhttp;
    var k = document.getElementById("code").value;


    var urls = getContextPath() + "/checkComponentCode?code=" + k;

    if (window.XMLHttpRequest) {
        xmlhttp = new XMLHttpRequest();
    }
    else {
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4) {

            document.getElementById("checkCode").innerHTML = xmlhttp.responseText;

        }
    };
    xmlhttp.open("GET", urls, true);
    xmlhttp.send();
}

function checkCompoundCode() {
    var xmlhttp;
    var k = document.getElementById("code").value;


    var urls = getContextPath() + "/checkCompoundCode?code=" + k;

    if (window.XMLHttpRequest) {
        xmlhttp = new XMLHttpRequest();
    }
    else {
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4) {

            document.getElementById("checkCode").innerHTML = xmlhttp.responseText;

        }
    };
    xmlhttp.open("GET", urls, true);
    xmlhttp.send();
}

function checkProductCode() {
    var xmlhttp;
    var k = document.getElementById("code").value;


    var urls = getContextPath() + "/checkProductCode?code=" + k;

    if (window.XMLHttpRequest) {
        xmlhttp = new XMLHttpRequest();
    }
    else {
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4) {

            document.getElementById("checkCode").innerHTML = xmlhttp.responseText;

        }
    };
    xmlhttp.open("GET", urls, true);
    xmlhttp.send();
}

// function findCodeByComponentName() {
//     var xmlhttp;
//     var k = document.getElementById("componentName").value;
//
//
//     var urls = getContextPath() + "/findCodeByComponentName?name=" + k;
//
//     if (window.XMLHttpRequest) {
//         xmlhttp = new XMLHttpRequest();
//     }
//     else {
//         xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
//     }
//     xmlhttp.onreadystatechange = function () {
//         if (xmlhttp.readyState == 4) {
//
//             document.getElementsByName("code")[0].value =  xmlhttp.responseText ;
//
//         }
//     };
//     xmlhttp.open("GET", urls, true);
//     xmlhttp.send();
// }
//
// function findCodeByCompoundName() {
//     var xmlhttp;
//     var k = document.getElementById("compoundName").value;
//
//
//     var urls = getContextPath() + "/findCodeByCompoundName?name=" + k;
//
//     if (window.XMLHttpRequest) {
//         xmlhttp = new XMLHttpRequest();
//     }
//     else {
//         xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
//     }
//     xmlhttp.onreadystatechange = function () {
//         if (xmlhttp.readyState == 4) {
//
//             document.getElementsByName("code")[0].value =  xmlhttp.responseText ;
//
//         }
//     };
//     xmlhttp.open("GET", urls, true);
//     xmlhttp.send();
// }