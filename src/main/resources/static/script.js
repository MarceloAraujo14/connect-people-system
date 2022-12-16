 // Get the year select element
//  var yearSelect = document.getElementsByClassName("year");

//  // Set the start and end years
//  var startYear = 1922;
//  var endYear = 2022;

//  // Loop through the years and create an option element for each one
//  for (var year = startYear; year <= endYear; year++) {
//    var option = document.createElement("option");
//    option.value = year;
//    option.text = year;
//    yearSelect.add(option);
//  }

document.addEventListener("DOMContentLoaded", function() {
    // Get the month select element
    var yearSelec = document.getElementsByName("year");
    
    // Set the names of the months
    var startYear = 1922;
    var endYear = 2022;
    
    // Loop through the months and create an option element for each one
    yearSelec.forEach(years => {
        for (var year = startYear; year <= endYear; year++) {
             var option = document.createElement("option");
            option.value = year;
            option.text = year;
            years.add(option);
            }
    });
    
 });

document.addEventListener("DOMContentLoaded", function() {
   // Get the month select element
   var monthSelect = document.getElementsByName("month");
   
   // Set the names of the months
   var months = [
    "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho",
    "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"
   ];
   
   // Loop through the months and create an option element for each one
   monthSelect.forEach(month => {
    for (var i = 0; i < months.length; i++) {
        var option = document.createElement("option");
        option.value = i + 1; // Set the value to the month number (1-12)
        option.text = months[i];
        month.appendChild(option);
    }
   });
   
});

