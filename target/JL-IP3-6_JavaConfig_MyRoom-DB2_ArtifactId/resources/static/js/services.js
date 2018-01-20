/**
 * Created by dbriskin on 01.05.2017.
 */
var req; // XMLHttpRequest – central part of AJAX
var isIE; // do you use Internet Explorer
var completeField; // link to index.jsp input with id complete-field
var completeTable; // link to index.jsp table with id complete-table
var autoRow; // link to index.jsp td with id auto-row

/* Initializing when index.jsp page load <body>*/
function init() {
    // find elements
    completeField = document.getElementById("complete-field");
    completeTable = document.getElementById("complete-table");
    autoRow = document.getElementById("auto-row");

    // try to arrange input field with drop-down table top.
    // place drop down list (table) near the input field
    completeTable.style.top = getElementY(autoRow) + "px";
     completeTable.style.left = getElementX(autoRow) + "px";
    completeTable.style.right = "0";

}

/* There is XMLHttpRequest is repeatedly sent on user key up*/
function doCompletion() {
    // prepare URL to search products
    // Функция escape возвращает строковое значение (в формате Юникода), содержащее содержимое аргумента
    // escape("abc123"); // "abc123" escape("текст"); // "%u0442%u0435%u043A%u0441%u0442“

    var url = "getSearchResult?action=complete&search=" + escape(completeField.value);

    req = initRequest(); // get the XMLHttpRequest
    req.open("GET", url, true); // initializes a  XMLHttpRequest as HTTPGET, asynchronously


    req.onreadystatechange = callback;  //функция, которая будет вызываться при изменении свойства readyState

    req.send(null); // send request
}

/* Obtain XMLHttpRequest*/
function initRequest() {
    // if we are not in IE (i.e. Chrome, Mozilla, etc.)
    if (window.XMLHttpRequest) {
        if (navigator.userAgent.indexOf('MSIE') != -1) {
            isIE = false;
        }
        return new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        // if we are in IE
        isIE = true;
        return new ActiveXObject("Microsoft.XMLHTTP");
    }
}

/* Runs when XMLHttpRequest is returned with data*/
function callback() {
    // clear previous results
    clearTable();
    // check if there any successful result
    if (req.readyState == 4) { // readyState=4 DONE Операция завершена. Все данные получены.
        if (req.status == 200) { // status Равен кодам HTTP (200 - успешно, 404 не найдено...
            parseMessages(req.responseXML);
        }
    }
}

/* Create <td> tag with data from one product as a link (<a href...>)*/
function appendComposer(name, payroll) {

    var row;
    var cell;
    var linkElement;

    // check if we hav IE
    // create a row <tr> and one cell <td>
    if (isIE) {
        completeTable.style.display = 'block';
        row = completeTable.insertRow(completeTable.rows.length);
        cell = row.insertCell(0);
    } else {
        completeTable.style.display = 'table';
        row = document.createElement("tr");
        cell = document.createElement("td");
        row.appendChild(cell);
        completeTable.appendChild(row);
    }

    cell.className = "popupCell";
    // create link <a>
    linkElement = document.createElement("a");
    linkElement.className = "popupItem";
    // link to servlet served page ....autocomplete?action=lookup&id=[product id]
    linkElement.setAttribute("href", "getProduct?id=" + name);
    // add name to link
    linkElement.appendChild(document.createTextNode(name + " : " + payroll + " : "));
    cell.appendChild(linkElement);
}

/*look for Y position of element in the window */
function getElementY(element) {

    var targetTop = 0;

    if (element.offsetParent) {
        while (element.offsetParent) {
            targetTop += element.offsetTop;
            element = element.offsetParent;
        }
    } else if (element.y) {
        targetTop += element.y;
    }
    return targetTop;
}

/*look for X position of element in the window */
function getElementX(element) {
    var targetLeft = 0;
    if (element.offsetParent) {
        while (element.offsetParent) {
            targetLeft += element.offsetLeft;
            element = element.offsetParent;
        }
    } else if (element.x) {
        targetLeft += element.x;
    }
    return targetLeft;
}


/* HTML table clearing*/
function clearTable() {
    // find rows <tr>
    if (completeTable.getElementsByTagName("tr").length > 0) {
        completeTable.style.display = 'none';
        // delete all row children - <td>
        for (loop = completeTable.childNodes.length - 1; loop >= 0; loop--) {
            completeTable.removeChild(completeTable.childNodes[loop]);
        }
    }
}

/*Parse XML */
function parseMessages(responseXML) {

    // no matches returned
    if (responseXML == null) {
        return false;
    } else {
        // find root element products
        var products = responseXML.getElementsByTagName("services")[0];

        if (products.childNodes.length > 0) {
            completeTable.setAttribute("bordercolor", "black");
            completeTable.setAttribute("border", "1");
            // loop for all <product> in <products> and run appendComposer() for every product
            for (loop = 0; loop < products.childNodes.length; loop++) {
                var product = products.childNodes[loop];
                var name = product.getElementsByTagName("name")[0];
                var payroll = product.getElementsByTagName("payroll")[0];
                // var productId = product.getElementsByTagName("id")[0];
                // var productPrice = product.getElementsByTagName("price")[0];
                appendComposer(name.childNodes[0].nodeValue,
                    payroll.childNodes[0].nodeValue
                    // productId.childNodes[0].nodeValue,
                    // productPrice.childNodes[0].nodeValue
                );
            }
        }
    }
}