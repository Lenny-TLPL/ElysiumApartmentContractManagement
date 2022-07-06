/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
//   function showDiv(divId, element)
//{
//    if(element.value==1){
//        document.getElementById(divId).style.display = 'block' ;
//    }
//    
//}
function showColor(element)
{
    if (element.value == "true") {
        document.getElementById('status').style.backgroundColor = "#669c19";
    } else if (element.value == "false") {
        document.getElementById('status').style.backgroundColor = "#d3190d";
    }

}
function showDiv(element)
{
    if (element.value == "leasing") {
        document.getElementById('amortization').style.display = 'none';
        document.getElementById('leasing').style.display = 'flex';
    } else if (element.value == "amortization") {
        document.getElementById('leasing').style.display = 'none';
        document.getElementById('amortization').style.display = 'flex';
    } else if (element.value == "buying") {
        document.getElementById('leasing').style.display = 'none';
        document.getElementById('amortization').style.display = 'none';
    }
}


