/**
 * Created by dbriskin on 01.05.2017.
 */
var myReq; // XMLHttpRequest – central part of AJAX
var reqAdd; // XMLHttpRequest – central part of AJAX
var reqShow; // XMLHttpRequest – central part of AJAX
var reqDelete; // XMLHttpRequest – central part of AJAX
var isIE; // do you use Internet Explorer
var completeField; // link to index.jsp input with id complete-field
var completeTable; // link to index.jsp table with id complete-table
var selectedPhoneNumber; // link to index.jsp table with id complete-table
var autoRow; // link to index.jsp td with id auto-row

/* Initializing when index.jsp page load <body>*/
function init() {
    // find elements
    completeField = document.getElementById("complete-field");
    completeTable = document.getElementById("complete-table");
    autoRow = document.getElementById("auto-row");
    selectedPhoneNumber = document.getElementById("selected-phone-number");
    console.info(selectedPhoneNumber.textContent);

    // try to arrange input field with drop-down table top.
    // place drop down list (table) near the input field
    completeTable.style.top = getElementY(autoRow) + "px";
    completeTable.style.left = getElementX(autoRow) + "px";
    completeTable.style.right = "0";

    doShowServices();

    // alert(selectedPhoneNumber.value)

}

// (function(){
//     var XML = new XMLHttpRequest();
//
//     function f2() {
//        XML.open('GET', '/lol/')
//
//     }
// })();

function sendRequest(myUrl, myCallback) {
    console.info("Come sendRequest");
    myReq = initRequest(); // get the XMLHttpRequest
    console.info(myReq);
    console.info("myReq after initRequest(): " + myReq);
    myReq.open("GET", myUrl, true); // initializes a  XMLHttpRequest as HTTPGET, asynchronously
    console.info(myReq.readyState);
    // myReq.onreadystatechange = myCallback; //функция, которая будет вызываться при изменении свойства readyState
    myReq.onreadystatechange = () => {myCallback()}; //функция, которая будет вызываться при изменении свойства readyState
    // myReq.onreadystatechange = () => {
    //     console.log(myReq.readyState);
    // }; //функция, которая будет вызываться при изменении свойства readyState
    myReq.send(null); // send request
    // myReq.onreadystatechange = function () {
    //
    // }

}

function doAddService() {

    console.info("doAddService() Service: " + completeField.options[completeField.selectedIndex].value)
    console.info("doAddService() Phone Number: " + selectedPhoneNumber.value)
    console.info("doAddService() Phone Number: " + selectedPhoneNumber.textContent)

    var url = "customerservices/add?selectedServiceName=" + escape(completeField.options[completeField.selectedIndex].value) + "&selectedPhoneNumber=" + escape(selectedPhoneNumber.textContent);
    sendRequest(url, callbackForAddService);

    // reqAdd = initRequest(); // get the XMLHttpRequest
    // reqAdd.open("GET", url, true); // initializes a  XMLHttpRequest as HTTPGET, asynchronously
    //
    //
    // // req.onreadystatechange = callback;  //функция, которая будет вызываться при изменении свойства readyState
    //
    // reqAdd.send(null); // send request
}


/* There is XMLHttpRequest is repeatedly sent on user key up*/
function doShowServices() {
    // prepare URL to search products
    // Функция escape возвращает строковое значение (в формате Юникода), содержащее содержимое аргумента
    // escape("abc123"); // "abc123" escape("текст"); // "%u0442%u0435%u043A%u0441%u0442“

    var url = "customerservices/show?selectedPhoneNumber=" + escape(selectedPhoneNumber.textContent);
    sendRequest(url, callback);

    // reqShow = initRequest(); // get the XMLHttpRequest
    // reqShow.open("GET", url, true); // initializes a  XMLHttpRequest as HTTPGET, asynchronously
    //
    //
    // reqShow.onreadystatechange = callback;  //функция, которая будет вызываться при изменении свойства readyState
    //
    // reqShow.send(null); // send request
}


// function doDeleteService() {
//     alert("2");
// }

function doDeleteService(localServiceName) {
// alert("1");
// alert(localServiceName);
// alert(localServiceName.value);
// alert("/customerservice/" + selectedPhoneNumber.value + "/" + localServiceName + "/delete");

    var url = "/customerservice/" + selectedPhoneNumber.textContent + "/" + localServiceName + "/delete";
    sendRequest(url, callbackForDeleteService);

    // reqDelete = initRequest(); // get the XMLHttpRequest
    // reqDelete.open("GET", url, true); // initializes a  XMLHttpRequest as HTTPGET, asynchronously
    // reqDelete.onreadystatechange = callbackForDeleteService();  //функция, которая будет вызываться при изменении свойства readyState
    // reqDelete.send(null); // send request
}

/* Obtain XMLHttpRequest*/
function initRequest() {
    console.info("Come initRequest()")
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
// var callback = function zz() {
function callback() {
    console.info("Come callback()")
    console.info(myReq.readyState)
    // console.info(myReq.status)
    // clear previous results
    clearTable();
    // check if there any successful result
    if (myReq.readyState === 4) { // readyState=4 DONE Операция завершена. Все данные получены.
        // console.info("Come if(4)")
        if (myReq.status === 200) { // status Равен кодам HTTP (200 - успешно, 404 не найдено...
            // console.info("Come if(200)")
            parseMessages(myReq.responseXML);
        }
    }
}


// var callbackForAddService = function callbackForDeleteService() {
function callbackForAddService() {
    console.info("Come callbackForAddService()")
    console.info(myReq.readyState)
    // console.info(myReq.status)
    // check if there any successful result
    if (myReq.readyState === 4) { // readyState=4 DONE Операция завершена. Все данные получены.
        // console.info("Come if(4)")
        if (myReq.status === 200) { // status Равен кодам HTTP (200 - успешно, 404 не найдено...
            // console.info("Come if(200)")
            doShowServices();
        }
    }
}


// var callbackForDeleteService = function callbackForDeleteService() {
function callbackForDeleteService() {
    console.info("Come callbackForDeleteService()")
    console.info(myReq.readyState)
    // console.info(myReq.status)
    // check if there any successful result
    if (myReq.readyState == 4) { // readyState=4 DONE Операция завершена. Все данные получены.
        // console.info("Come if(4)")
        if (myReq.status == 200) { // status Равен кодам HTTP (200 - успешно, 404 не найдено...
            // console.info("Come if(200)")
            doShowServices();
        }
    }
}



/* Create <td> tag with data from one product as a link (<a href...>)*/
function appendComposer(serviceName, servicePayroll, serviceStatus) {


    var row;
    var cell1;
    var cell2;
    var cell3;
    var cell4;
    var linkElement;
    var buttonElement1;
    var buttonElement2;
    var submitElement3;

    // var myServiceName = serviceName.value;

    // alert(myServiceName);

    // check if we hav IE
    // create a row <tr> and one cell <td>
    if (isIE) {
        completeTable.style.display = 'block';
        row = completeTable.insertRow(completeTable.rows.length);
        cell1 = row.insertCell(0);
        cell2 = row.insertCell(1);
        cell3 = row.insertCell(2);
        cell4 = row.insertCell(3);
    } else {
        completeTable.style.display = 'table';
        row = document.createElement("tr");
        cell1 = document.createElement("td");
        cell2 = document.createElement("td");
        cell3 = document.createElement("td");
        cell4 = document.createElement("td");
        row.appendChild(cell1);
        row.appendChild(cell2);
        row.appendChild(cell3);
        row.appendChild(cell4);
        completeTable.appendChild(row);
    }

    cell1.className = "popupCell";
    // // create link <a>
    // linkElement = document.createElement("a");
    // linkElement.className = "popupItem";
    // // link to servlet served page ....autocomplete?action=lookup&id=[product id]
    // linkElement.setAttribute("href", "getProduct?id=" + serviceName);
    // // add name to link
    // linkElement.appendChild(document.createTextNode(serviceName + " : " + servicePayroll + " : " + serviceStatus));
    // cell.appendChild(linkElement);
    cell1.appendChild(document.createTextNode(serviceName));
    cell2.appendChild(document.createTextNode(servicePayroll));
    cell3.appendChild(document.createTextNode(serviceStatus));

    buttonElement1 = document.createElement("button");
    buttonElement2 = document.createElement("button");
    submitElement3 = document.createElement("input");
    buttonElement1.setAttribute("onclick", "/customerservice/" + serviceName);
    buttonElement2.setAttribute("onclick", "/customerservice/" + serviceName + "/update");
    submitElement3.setAttribute("type", "submit");
    submitElement3.setAttribute("value", "Delete");
    // submitElement3.setAttribute("onclick", "doDeleteService(" + selectedPhoneNumber.value + ", " + serviceName.value + ")");
    submitElement3.setAttribute("onclick", "doDeleteService('" + serviceName + "')");
    // submitElement3.setAttribute("onclick", "doDeleteService()");
    // submitElement3.setAttribute("onclick", "doShowServices()");
    buttonElement1.appendChild(document.createTextNode("Info"));
    buttonElement2.appendChild(document.createTextNode("Update"));
    // submitElement3.appendChild(document.createTextNode("Delete"));
    cell4.appendChild(buttonElement1);
    cell4.appendChild(buttonElement2);
    cell4.appendChild(submitElement3);
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

// T H

// V


/*Parse XML */
function parseMessages(responseXML) {

    // no matches returned
    if (responseXML == null) {
        return false;
    } else {
        // find root element products
        var customer_services = responseXML.getElementsByTagName("customer_services")[0];

        if (customer_services.childNodes.length > 0) {
            completeTable.setAttribute("bordercolor", "black");
            completeTable.setAttribute("border", "1");
            // loop for all <service> in <services> and run appendComposer() for every service
            for (loop = 0; loop < customer_services.childNodes.length; loop++) {
                var customer_service = customer_services.childNodes[loop];
                var serviceName = customer_service.getElementsByTagName("serviceName")[0];
                var servicePayroll = customer_service.getElementsByTagName("servicePayroll")[0];
                var serviceStatus = customer_service.getElementsByTagName("serviceStatus")[0];
                // var productPrice = product.getElementsByTagName("price")[0];
                appendComposer(serviceName.childNodes[0].nodeValue,
                    servicePayroll.childNodes[0].nodeValue,
                    serviceStatus.childNodes[0].nodeValue
                    // productPrice.childNodes[0].nodeValue
                );
            }
        }
    }
}

// private int id;
// private String phoneNumber;
// private String serviceName;
// private float servicePayroll;
// private String serviceStatus;