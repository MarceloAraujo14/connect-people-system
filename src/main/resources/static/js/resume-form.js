document.addEventListener("DOMContentLoaded", function () {
  loadSchooling();
});

function loadSchooling() {
  var schooling = document.getElementById("schooling");

  var gradeValue = ['PRIMARIO_INCOMPLETO', 'PRIMARIO_COMPLETO',
    'FUNDAMENTAL_INCOMPLETO', 'FUNDAMENTAL_COMPLETO',
    'MEDIO_INCOMPLETO', 'MEDIO_COMPLETO',
    'SUPERIOR_INCOMPLETO', 'SUPERIOR_COMPLETO']

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
    option.value = gradeValue[grades.indexOf(grade)];
    option.text = grade;
    schooling.appendChild(option);
  }
}

function loadMonthAndYear() {

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
      option.value = months.indexOf(monthName) +1;
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

let jobCount = 0;

function addJobExp() {

  if(jobCount == 3){
    return;
  }
  
  const job_xp = document.createDocumentFragment();
  const job = document.createElement('div');
  job.className = "job-experience";
  job.innerHTML = `<div id="jobExperiences">
  <div class="row">
    <div class="col-md-6">
      <label for="title" class="form-label">Título</label>
      <input type="text" name="title" class="form-control" id="title" placeholder="Hoteleiro">
    </div>
  
    <div class="col-md-6">
      <label for="company" class="form-label">Empresa</label>
      <input type="text" name="company" class="form-control" id="company" placeholder="Company S.A.">
    </div>
  </div>
  <br>
  <div class="row">
    <div class="col">
      <p>Entrada</p>
      <div class="row">
          <div class="col-md-5">
              <label for="month" class="form-label">Mês</label>
              <select class="form-select month" id="startMonth" name="month"></select>
            </div>
        <div class="col-md-4">
          <label for="year" class="form-label">Ano</label>
          <select class="form-select year" id="startYear" name="year"></select>
        </div>
        <div class="row-cols-1">
          <label class="form-check-label" for="isCurrentJob">Trabalho atual</label>
          <input class="form-check-input" type="checkbox" id="isCurrentJob" name="isCurrentJob" >
        </div>
      </div>
    </div>
  
    <div class="col">
      <p>Saída</p>
      <div class="row">
        <div class="col-md-5">
          <label for="month" class="form-label">Mês</label>
          <select class="form-select month" id="endMonth" name="month"></select>
        </div>
        <div class="col-md-4">
          <label for="year" class="form-label">Ano</label>
          <select class="form-select year" id="endYear" name="year"></select>
        </div>
      </div>
    </div>
  </div>
  
  <div class="mb-3">
    <label for="description" class="form-label">Descrição</label>
    <textarea class="form-control" id="description" rows="3" name="description"></textarea>
  </div>
  <div class="delxp-div">
    <button type="button" class="delxp-btn" onclick="deleteJobExp(this)">- Remover Experiência</button>
  </div>
  <div class="divider"></div>
  <br> </div>
</div>`;

  job_xp.append(job);
  element = document.getElementById("job-xp")
  element.append(job_xp);
  loadMonthAndYear();
  jobCount++;
  if(jobCount == 3){
    document.getElementById("xp-btn").hidden = true;
  }
}

function deleteJobExp(element) {
  element.parentElement.parentElement.outerHTML = `<div id="job-xp"></div>`;
  jobCount--;
  if(jobCount < 3){
    document.getElementById("xp-btn").hidden = false;
  }
}

async function buildResumeFromJson(){
  var resume = await fetch('./json/ResumeRequest.json').then(response => response.json());

  var form = document.querySelector('form')
  resume.name = form.querySelector('#name').value;
  resume.birthDate = form.querySelector('#birthDate').value;
  resume.gender = form.querySelector('#gender').value;
  resume.phone = form.querySelector('#phone').value;
  resume.cellPhone = form.querySelector('#cellPhone').value;
  resume.email = form.querySelector('#email').value;
  resume.linkedin = form.querySelector('#linkedin').value;
  resume.postalCode = form.querySelector('#postalCode').value;
  resume.district = form.querySelector('#district').value;
  resume.jobOptionOne = form.querySelector('#jobOptionOne').value;
  resume.jobOptionTwo = form.querySelector('#jobOptionTwo').value;
  resume.jobOptionThree = form.querySelector('#jobOptionThree').value;
  resume.schooling = form.querySelector('#schooling').value;

  const jobExperiences = form.querySelectorAll('#jobExperiences');

  var count = jobExperiences.length;

  for(let i = 0; i < count; i++){
    resume['jobExperiences'][i] = {};
    resume.jobExperiences[i]['title'] = jobExperiences[i].querySelector('#title').value;
    resume.jobExperiences[i]['company'] = jobExperiences[i].querySelector('#company').value;
    resume.jobExperiences[i]['startMonth'] = jobExperiences[i].querySelector('#startMonth').value;
    resume.jobExperiences[i]['startYear'] = jobExperiences[i].querySelector('#startYear').value;
    resume.jobExperiences[i]['isCurrentJob'] = jobExperiences[i].querySelector('#isCurrentJob').checked;
    resume.jobExperiences[i]['endMonth'] = jobExperiences[i].querySelector('#endMonth').value;
    resume.jobExperiences[i]['endYear'] = jobExperiences[i].querySelector('#endYear').value;
    resume.jobExperiences[i]['description'] = jobExperiences[i].querySelector('#description').value;
  }

  return resume;
}

async function sendResume(){

  const resume = await buildResumeFromJson().then(resp => resp);

  const resp = await post('/api/resume', resume).then(resp => resp.json());

  console.log(resp);

}

//api functions
async function post(url, obj){
  console.log(obj)
  const req = await fetch(url, {
  method: 'POST',
  body: JSON.stringify(obj),
  headers: {'Content-type': 'application/json'}
  });

  const resp = await req.json();
  console.log(resp);
}

