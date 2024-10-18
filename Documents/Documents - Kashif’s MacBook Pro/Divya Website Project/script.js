
var state;
var loc;
var phone;
var emailid;


function presence(){
if (state == "Gujarat"){

    document.getElementById("loc") = "Unit No. P04-01 (A, B, C),4th Floor, Tower A, WTC Gift City, Building No.51A, Block No.51, Road 5 E, Gift City Gandhinagar Gujarat-382355"

    document.getElementById("phone") = " 0120-4229945, 4229946"
   document.getElementById("emailid")= "divyacap@divyacapitalone.com"

}
}







$(document).ready(function() {
$('#carousel').owlCarousel({
    loop: true,
    autoplay: true,
    autoplayTimeout: 5000,
    responsiveClass: true,
    nav:true,
    responsive: {
        0: {
            items: 1,
            margin: 20,
        },
        500: {
            items: 2,
            margin: 20,
        },
        768: {
            items: 2,
            margin: 20,
        },
        992: {
            items: 3,
            margin: 20,
        },
        1200: {
            items: 4,
            margin: 20,
        }
    }
})
});
