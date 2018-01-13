var quidBoxes = [];
var allPerc = [];
var arrNames = [];
var arrPerc = [];
var declarationBoxes = [];
var allParent = [];

var allOrder = [];
var arrOrder = [];

var arrDeclaredNames = [];
var arrParent = [];

var allAllergens = [];
var arrAllergens = [];

var allFunctions = [];
var arrFunctions = [];


var allQuid = [];


var allDeclared = [];


var productName;


var csrfParameter;
var csrfToken;


//to get the all checkBoxes of certain column
function getBoxes(colNum) {
    var boxes = [];

    var myTable = document.getElementsByTagName("table")[0];


    for (var i = 1; i < myTable.rows.length; i++) {

        var objCells = myTable.rows.item(i).cells;


        boxes.push(objCells[colNum].firstElementChild)


    }
    return boxes;
}


//to get innerText of certain column
function getInnerColData(colNumber) {

    var myTable = document.getElementsByTagName("table")[0];
    var res = [];

    for (var i = 1; i < myTable.rows.length; i++) {

        var objCells = myTable.rows.item(i).cells;

        res.push(objCells[colNumber].innerText);


    }
    return res;
}

function getColData(colNumber) {

    var myTable = document.getElementsByTagName("table")[0];
    var res = [];

    for (var i = 1; i < myTable.rows.length; i++) {

        var objCells = myTable.rows.item(i).cells;

        res.push(objCells[colNumber]);


    }
    return res;
}

function getAllQuid(){

    var boxes = getBoxes(6);

    for (var i = 0; i < boxes.length; i++) {

        if (boxes[i] != null && boxes[i].checked) {

            allQuid.push("Yes");

        } else{

            allQuid.push("");
        }
    }



}

function getAllDecalaration(){

    var boxes = getBoxes(7);

    for (var i = 0; i < boxes.length; i++) {

        if (boxes[i] != null && boxes[i].checked) {

            allDeclared.push("Yes");

        } else{

            allDeclared.push("");
        }
    }



}



function getQuidNamesAndPerc(quidBoxes, allPerc, arrNames, arrPerc) {


    for (var i = 0; i < quidBoxes.length; i++) {

        if (quidBoxes[i] != null && quidBoxes[i].checked) {

            arrNames.push(quidBoxes[i].name);

            arrPerc.push(allPerc[i]);
        }

    }


}

function getDeclarationData(declarationBoxes, allParent, arrDeclaredNames, arrParent, allOrder, arrOrder, allFunctions, arrFunctions, allAllergens, arrAllergens) {

    for (var i = 0; i < declarationBoxes.length; i++) {

        if (declarationBoxes[i].checked) {

            arrDeclaredNames.push(declarationBoxes[i].name);

            arrParent.push(allParent[i]);

            arrOrder.push(allOrder[i]);

            arrAllergens.push(allAllergens[i]);

            arrFunctions.push(allFunctions[i]);


        }

    }


}

function getAllFunctions(colNumber) {

    var myTable = document.getElementsByTagName("table")[0];
    var res = [];

    for (var i = 1; i < myTable.rows.length; i++) {

        var objCells = myTable.rows.item(i).cells;

        res.push(objCells[colNumber].firstElementChild.options[objCells[colNumber].firstElementChild.selectedIndex].text);


    }
    return res;
}

function getAllAllergens(colNumber) {

    var myTable = document.getElementsByTagName("table")[0];
    var res = [];

    for (var i = 1; i < myTable.rows.length; i++) {

        var objCells = myTable.rows.item(i).cells;

        if (objCells[colNumber].firstElementChild != null) {

            res.push(objCells[colNumber].firstElementChild.options[objCells[colNumber].firstElementChild.selectedIndex].text);
        } else {
            res.push(objCells[colNumber].innerText);

        }


    }
    return res;

}

function clearArrays() {

    quidBoxes = [];
    allPerc = [];
    arrNames = [];
    arrPerc = [];
    declarationBoxes = [];
    allParent = [];

    allOrder = [];
    arrOrder = [];

    arrDeclaredNames = [];
    arrParent = [];

    allFunctions = [];
    arrFunctions = [];

    allAllergens = [];
    arrAllergens = [];


    allQuid = [];


    allDeclared = [];


    productName = "";


}

function initData() {
    clearArrays();

    quidBoxes = getBoxes(6);
    declarationBoxes = getBoxes(7);
    allPerc = getInnerColData(4);
    allParent = getInnerColData(0);

    allOrder = getInnerColData(1);

    allAllergens = getAllAllergens(8);

    allFunctions = getAllFunctions(9);

    productName = getInnerColData(0)[0];

     getAllQuid();

     getAllDecalaration();

    getQuidNamesAndPerc(quidBoxes, allPerc, arrNames, arrPerc);
    getDeclarationData(declarationBoxes, allParent, arrDeclaredNames, arrParent, allOrder, arrOrder, allFunctions, arrFunctions, allAllergens, arrAllergens);


}


function getContextPath() {

    var t = window.location.pathname;
    t = t.substr(0, t.lastIndexOf("/"));
    return t;
}


function getQD() {

    initData();


    var quidData = {
        "productName": productName,
        "arrQuidNames": arrNames,
        "arrQuidPerc": arrPerc,
        "arrDeclaredNames": arrDeclaredNames,
        "arrParent": arrParent,
        "arrOrder": arrOrder,
        "arrAllergens": arrAllergens,
        "arrFunctions": arrFunctions,
        "allQuid": allQuid,
        "allDeclared": allDeclared,
        "allAllergens": allAllergens,
        "allFunctions": allFunctions
    };
    quidData['parentId'] = 1;
    quidData[csrfParameter] = csrfToken;


    $.ajax({
        type: "POST",
        contentType: 'application/json; charset=utf-8',
        dataType: 'text',
        url: getContextPath() + "/parseQuid",
        data: JSON.stringify(quidData),

        success: function (response) {
            document.getElementById('qd').innerHTML = response;
        },
        error: function (e) {
            console.log('Error:', e);
            document.getElementById('qd').innerHTML = e;
        }
    });


}

function selectAllergen(selected) {

    var componentName = selected.value;

    var selectedOption = selected.options[selected.selectedIndex].text;

    var allerNames = getInnerColData(2);

    var colData = getColData(8);


    for (var i = 0; i < colData.length; i++) {

        if (allerNames[i] == componentName) {

            if (colData[i].firstElementChild == null) {

                colData[i].innerText = selectedOption;
            }
        }

    }

}



