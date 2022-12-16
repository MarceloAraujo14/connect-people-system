document.addEventListener("DOMContentLoaded", function() {
    loadMonthAndYear();
 });

function loadMonthAndYear(){

var monthSelect = document.getElementsByName("month");

var months = [
 "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho",
 "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"
];

monthSelect.forEach(month => {
 for (var i = 0; i < months.length; i++) {
     var option = document.createElement("option");
     option.value = i + 1; // Set the value to the month number (1-12)
     option.text = months[i];
     month.appendChild(option);
 }
});

var yearSelec = document.getElementsByName("year");

var startYear = 1922;
var endYear = 2022;

yearSelec.forEach(years => {
    for (var year = startYear; year <= endYear; year++) {
         var option = document.createElement("option");
        option.value = year;
        option.text = year;
        years.add(option);
        }
});
}


function addJobExp(element){
    var jobXp = 
    `
    <div class="row">
    <div class="col-md-6">
      <label for="title" class="form-label">Título</label>
      <input type="text" class="form-control" id="title" placeholder="Hoteleiro" th:field="*{title}">
    </div>

    <div class="col-md-6">
      <label for="company" class="form-label">Empresa</label>
      <input type="text" class="form-control" id="company" placeholder="Company S.A."
        th:field="*{company}">
    </div>
  </div>
  <br>
  <div class="row">
    <div class="col">
      <p>Entrada</p>
      <div class="row">
        <div class="col-md-5">
          <label for="month" class="form-label">Mês</label>
          <select class="form-select month" id="month" name="month"></select>
        </div>
        <div class="col-md-4">
          <label for="year" class="form-label">Ano</label>
          <select class="form-select year" id="year" name="year"></select>
        </div>
        <div class="row-cols-1">
          <label class="form-check-label" for="isCurrentJob">Trabalho atual</label>
          <input class="form-check-input" type="checkbox" id="isCurrentJob">
        </div>
      </div>
      
    </div>

    <div class="col">
      <p>Saída</p>
      <div class="row">
        <div class="col-md-5">
          <label for="month" class="form-label">Mês</label>
          <select class="form-select month" id="month" name="month"></select>
        </div>
        <div class="col-md-4">
          <label for="year" class="form-label">Ano</label>
          <select class="form-select year" id="year" name="year"></select>
        </div>
      </div>
    </div>
  </div>

  <div class="mb-3">
    <label for="description" class="form-label">Descrição</label>
    <textarea class="form-control" id="description" rows="3"></textarea>
  </div>
  <div class="divider"></div>
  <br>
  <button type="button" class="addxp-btn" onclick="addJobExp(this)">+ Adicionar Experiência</button>
  `

   element.outerHTML = jobXp;

   loadMonthAndYear();

}
