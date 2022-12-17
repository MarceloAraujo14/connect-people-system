document.addEventListener("DOMContentLoaded", function() {
    loadMonthAndYear();
    loadSchooling();
 });

function loadSchooling(){
  var schooling = document.getElementById("schooling");

  var grades = ['Primário Incompleto', 'Primário completo', 
  'Fundamental Incompleto', 'Fundamental completo', 
  'Médio incompleto', 'Médio completo', 
  'Superior Incompleto', 'Superior completo'];

  const defaultOption = document.createElement("option");
  defaultOption.value = "";
  defaultOption.text = "Selecione sua escolaridade";
  defaultOption.selected = true;
  schooling.appendChild(defaultOption);

  for (const grade of grades) {
    const option = document.createElement("option");
    option.value = grades.indexOf(grade) + 1;
    option.text = grade;
    schooling.appendChild(option);
  }
}

function loadMonthAndYear(){

var monthSelect = document.getElementsByName("month");

var months = [
 "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho",
 "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"
];


monthSelect.forEach(month => {

  const defaultOption = document.createElement("option");
  defaultOption.value = "";
  defaultOption.text = "";
  month.appendChild(defaultOption);

  for (const monthName of months) {
    const option = document.createElement("option");
    option.value = months.indexOf(month);
    option.text = monthName;
    month.appendChild(option);
  }
});

var yearSelec = document.getElementsByName("year");

var startYear = 1922;
var endYear = 2022;

yearSelec.forEach(years => {
    const defaultOption = document.createElement("option");
    defaultOption.value = "";
    defaultOption.text = "";
    years.appendChild(defaultOption);

    for (var year = startYear; year <= endYear; year++) {
         var option = document.createElement("option");
        option.value = year;
        option.text = year;
        years.add(option);
        }
});
}


function addJobExp(element){
  var html = `
  <div id="job-experience"> 
          
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
<div class="delxp-div">
<button type="button" class="delxp-btn" onclick="deleteJobExp(this)">- Remover Experiência</button> 
</div>
<div class="divider"></div>
<br>

<button type="button" class="xp-btn" onclick="addJobExp(this)">+ Adicionar Experiência</button>
<div>

`
   element.outerHTML = html;
   loadMonthAndYear();
}

function deleteJobExp(element){
  element.parentElement.parentElement.outerHTML = '<button type="button" class="xp-btn" onclick="addJobExp(this)">+ Adicionar Experiência</button>';
}