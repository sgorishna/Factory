var PARENT_COL_NUM = 0;
var ORDER_COL_NUM = 1;
var INGREDIENT_COL_NUM = 2;
var PERCENT_COL_NUM = 4;
var QUID_COL_NUM = 8;
var DECLARATION_COL_NUM = 9;
var ALLERGEN_COL_NUM = 10;
var FUNCTION_COL_NUM = 11;

var quidBoxes = [];
var percColumn = [];
var arrNames = [];
var arrPerc = [];
var quidOrder = [];
var declarationBoxes = [];
var parentColumn = [];

var orderColumn = [];
var arrOrder = [];

var arrDeclaredNames = [];
var arrParent = [];

var allergenColumn = [];
var arrAllergens = [];

var functionColumn = [];
var arrFunctions = [];


var quidColumn = [];


var declaredColumn = [];


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

function getAllQuid() {

    var boxes = getBoxes(QUID_COL_NUM);

    for (var i = 0; i < boxes.length; i++) {

        if (boxes[i] != null && boxes[i].checked) {

            quidColumn.push("Yes");

        } else {

            quidColumn.push("");
        }
    }


}

function getAllDecalaration() {

    var boxes = getBoxes(DECLARATION_COL_NUM);

    for (var i = 0; i < boxes.length; i++) {

        if (boxes[i] != null && boxes[i].checked) {

            declaredColumn.push("Yes");

        } else {

            declaredColumn.push("");
        }
    }


}


function getQuidNamesAndPerc(quidBoxes, allPerc, arrNames, arrPerc, quidOrder, arrOrder) {


    for (var i = 0; i < quidBoxes.length; i++) {

        if (quidBoxes[i] != null && quidBoxes[i].checked) {

            arrNames.push(quidBoxes[i].name);

            arrPerc.push(allPerc[i]);

            quidOrder.push(arrOrder[i]);
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
//

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

        res.push(objCells[colNumber].firstElementChild.options[objCells[colNumber].firstElementChild.selectedIndex].text);


    }
    return res;

}

function clearArrays() {

    quidBoxes = [];
    percColumn = [];
    arrNames = [];
    arrPerc = [];
    quidOrder = [];
    declarationBoxes = [];
    parentColumn = [];

    orderColumn = [];
    arrOrder = [];

    arrDeclaredNames = [];
    arrParent = [];

    functionColumn = [];
    arrFunctions = [];

    allergenColumn = [];
    arrAllergens = [];


    quidColumn = [];


    declaredColumn = [];


    productName = "";


}

function initData() {
    clearArrays();

    console.log(QUID_COL_NUM);
    console.log(ALLERGEN_COL_NUM);

    quidBoxes = getBoxes(QUID_COL_NUM);


    declarationBoxes = getBoxes(DECLARATION_COL_NUM);
    percColumn = getInnerColData(PERCENT_COL_NUM);
    parentColumn = getInnerColData(PARENT_COL_NUM);

    orderColumn = getInnerColData(ORDER_COL_NUM);

    allergenColumn = getAllAllergens(ALLERGEN_COL_NUM);

    functionColumn = getAllFunctions(FUNCTION_COL_NUM);

    productName = getInnerColData(0)[0];

    getAllQuid();

    getAllDecalaration();

    getQuidNamesAndPerc(quidBoxes, percColumn, arrNames, arrPerc, quidOrder, orderColumn);
    getDeclarationData(declarationBoxes, parentColumn, arrDeclaredNames, arrParent, orderColumn, arrOrder, functionColumn, arrFunctions, allergenColumn, arrAllergens);


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
        "arrQuidOrder": quidOrder,
        "arrDeclaredNames": arrDeclaredNames,
        "arrParent": arrParent,
        "arrOrder": arrOrder,
        "arrAllergens": arrAllergens,
        "arrFunctions": arrFunctions,
        "quidColumn": quidColumn,
        "declaredColumn": declaredColumn,
        "allergenColumn": allergenColumn,
        "functionColumn": functionColumn
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
            copyDeclarationToTextarea();
        },
        error: function (e) {
            console.log('Error:', e);
            document.getElementById('qd').innerHTML = e;

        }
    });


}

function getDeclaredIngredientList(){

    var data = getFrameContents().innerHTML;

    document.getElementById("myinput").setAttribute('value', data);

    document.getElementById("myinput1").setAttribute('value', data);



}

function selectAllergen(selected) {

    var componentName = selected.value;


    var index = selected.selectedIndex;


    var allerNames = getInnerColData(INGREDIENT_COL_NUM);

    var colData = getColData(ALLERGEN_COL_NUM);


    for (var i = 0; i < colData.length; i++) {

        if (allerNames[i] == componentName) {


            colData[i].firstElementChild.selectedIndex = index;

        }

    }

}

function copyDeclarationToTextarea(){

    var text = document.getElementById('qd').innerHTML;

    var iFrame =  document.getElementsByTagName("iframe")[0];

    var iFrameBody = getFrameContents();

    iFrameBody.innerHTML = text;


}


function getFrameContents(){

    var iFrame =  document.getElementsByTagName("iframe")[0];
    var iFrameBody;
    if ( iFrame.contentDocument )
    { // FF
        iFrameBody = iFrame.contentDocument.getElementsByTagName('body')[0];
    }
    else if ( iFrame.contentWindow )
    { // IE
        iFrameBody = iFrame.contentWindow.document.getElementsByTagName('body')[0];
    }

    return iFrameBody;
}


