function loadMonthAndYear() {

  var monthSelect = document.getElementsByName("month");
  var months = [
    "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho",
    "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"
  ];

  monthSelect.forEach(month => {
    for (const monthName of months) {
      const option = document.createElement("option");
      option.value = months.indexOf(monthName) + 1;
      option.text = monthName;
      month.appendChild(option);
    }
  });

  var yearSelec = document.getElementsByName("year");

  var startYear = new Date().getFullYear() - 100;
  var endYear = new Date().getFullYear();

  yearSelec.forEach(years => {

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

  if (jobCount >= 3) {
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
      <error for="title"></error>
    </div>

    <div class="col-md-6">
      <label for="company" class="form-label">Empresa</label>
      <input type="text" name="company" class="form-control" id="company" placeholder="Company S.A.">
      <error for="company"></error>
    </div>
  </div>
  <br>
  <div class="row">
    <div class="col" id="startJob">
      <p>Entrada</p>
      <div class="row">
        <div class="col-md-5">
          <label for="startMonth" class="form-label">Mês</label>
          <select class="form-select month" id="startMonth" name="month"></select>
          <error for="startMonth"></error>
        </div>
        <div class="col-md-4">
          <label for="startYear" class="form-label">Ano</label>
          <select class="form-select year" id="startYear" name="year"></select>
          <error for="startYear"></error>
        </div>
        <div class="row-cols-1">
          <label class="form-check-label" for="isCurrentJob">Trabalho atual</label>
          <input class="form-check-input" onchange="currentJob(this)" type="checkbox" id="isCurrentJob" name="isCurrentJob" checked>
        </div>
      </div>
    </div>

    <div class="col" id="endJob">
      <p>Saída</p>
      <div class="row">
        <div class="col-md-5">
          <label for="endMonth" class="form-label">Mês</label>
          <select class="form-select month" id="endMonth" name="month"></select>
          <error for="endMonth"></error>
        </div>
        <div class="col-md-4">
          <label for="endYear" class="form-label">Ano</label>
          <select class="form-select year" id="endYear" name="year"></select>
          <error for="endYear"></error>
        </div>
      </div>
    </div>
  </div>

  <div class="mb-3">
    <label for="description" class="form-label">Descrição</label>
    <textarea class="form-control" id="description" rows="3" name="description"></textarea>
    <error for="description"></error>
  </div>
  <div class="del-xp-container">
    <button type="button" class="btn-del" onclick="deleteJobExp(this)">- Remover Experiência</button>
  </div>
  <div class="divider"></div>
  <br> </div>
</div>`;

  job_xp.append(job);
  element = document.getElementById("job-xp-container")
  element.append(job_xp);
  loadMonthAndYear();
  jobCount++;
  if (jobCount == 3) {
    document.getElementById("btn-add-xp").hidden = true;
  }
}

function currentJob(checkbox){
  if(checkbox.checked){
      checkbox.parentElement.parentElement.parentElement.parentElement.querySelector("#endJob").innerHTML =
      `<p>Saída</p>
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
    </div>`
  }else {
    checkbox.parentElement.parentElement.parentElement.parentElement.querySelector("#endJob").innerHTML = '';
  }
}

function deleteJobExp(element) {
  element.parentElement.parentElement.outerHTML = `<div id="job-xp-container"></div>`;
  jobCount--;
  if (jobCount < 3) {
    document.getElementById("btn-add-xp").hidden = false;
  }
}

let superiorCount = 0;
const supIncomp = `
<div id="superior">
  <label>Curso Superior</label>
  <div class="row" id="superior-child">
    <div class="col-md-4">
        <label for="college_name" class="form-label">Instituição</label>
        <input type="text" class="form-control" id="college_name" name="college_name">
        <error for="college_name"></error>
    </div>
    <div class="col-md-4">
        <label for="college_course" class="form-label">Curso</label>
        <input type="text" class="form-control" id="college_course" name="college_course">
        <error for="college_course"></error>
    </div>
    <div class="col-md-4">
        <label for="college_step" class="form-label">Situação</label>
        <select id="college_step" class="form-select option" name="college_step">
            <option value="CURSANDO">Cursando</option>
            <option value="TRANCADO">Trancado ou Interrompido</option>
        </select>
        <error for="college_step"></error>
    </div>
  </div>
  <br>
</div>
`;
const supIncompRem = `
  <div class="col" id="superior-child">
    <div class="row">
      <div class="col-md-4">
          <label for="college_name" class="form-label">Instituição</label>
          <input type="text" class="form-control" id="college_name" name="college_name">
          <error for="college_name"></error>
      </div>
      <div class="col-md-4">
          <label for="college_course" class="form-label">Curso</label>
          <input type="text" class="form-control" id="college_course" name="college_course">
          <error for="college_course"></error>
      </div>
      <div class="col-md-4">
          <label for="college_step" class="form-label">Situação</label>
          <select id="college_step" class="form-select option" name="college_step">
              <option value="CURSANDO">Cursando</option>
              <option value="TRANCADO">Trancado ou Interrompido</option>
          </select>
          <error for="college_step"></error>
      </div>
    </div>
    <div class="row">
      <div class="col-md-4">
        <button type="button" class="btn-del" onclick="deleteSuperior(this)"> - remove </button>
      </div>
    </div>
  </div>
`;
const supComp = `
<div id='superior' class="superior">
  <label>Curso Superior</label>
  <div class="row" id="superior-child">
    <div class="col-md-4">
        <label for="college_name" class="form-label">Instituição</label>
        <input type="text" class="form-control" id="college_name" name="college_name">
        <error for="college_name"></error>
    </div>
    <div class="col-md-4">
        <label for="college_course" class="form-label">Curso</label>
        <input type="text" class="form-control" id="college_course" name="college_course">
        <error for="college_course"></error>
    </div>
    <div class="col-md-3">
        <label for="college_year" class="form-label">Ano de Conclusão</label>
        <select id="college_year" class="form-select option" name="year"></select>
    </div>
  </div>
  <br>
</div>
`;
const supCompRem = `
  <div class="col" id="superior-child">
    <div class="row">
      <div class="col-md-4">
          <label for="college_name" class="form-label">Instituição</label>
          <input type="text" class="form-control" id="college_name" name="college_name">
          <error for="college_name"></error>
      </div>
      <div class="col-md-4">
          <label for="college_course" class="form-label">Curso</label>
          <input type="text" class="form-control" id="college_course" name="college_course">
          <error for="college_course"></error>
      </div>
      <div class="col-md-3">
          <label for="college_year" class="form-label">Ano de Conclusão</label>
          <select id="college_year" class="form-select option" name="year"></select>
      </div>
    </div>
    <div class="row">
      <div class="col-md-4">
        <button type="button" class="btn-del" onclick="deleteSuperior(this)"> - remove </button>
      </div>
    </div>
  </div>
`;

function addSuperior(element) {

  if(superiorCount >=3){
    return;
  }

  var superior = document.getElementById("superior-container");
  var schooling = document.getElementById("schooling");

  if (schooling.value == 'SUPERIOR_INCOMPLETO') {
    if (superiorCount == 0){
      superior.innerHTML = supIncomp;
    } else {
      element.outerHTML = supIncompRem;
    }


  } else if (schooling.value == 'SUPERIOR_COMPLETO') {
    if(superiorCount == 0){
      superior.innerHTML = supComp;
    }else{
      element.outerHTML = supCompRem;
    }

  } else {
    superior.innerHTML = '';
    return;
  }

  loadMonthAndYear();
  superiorCount++;

  if(superiorCount >= 3){
    return;
  }
  const btn_sup_container = document.createElement("div");
  btn_sup_container.className = "btn-sup-container";
  btn_sup_container.innerHTML = '<button type="button" class="btn-add" onclick="addSuperior(this)">+ graduação</button>';
  superior.append(btn_sup_container);
}

function deleteSuperior(element) {
  superiorCount--;
  if(superiorCount == 2 ){
    const superior = document.getElementById("superior-container");
    const btn_sup_container = document.createElement("div");
    btn_sup_container.className = "btn-sup-container";
    btn_sup_container.innerHTML = '<button type="button" class="btn-add" onclick="addSuperior(this)">+ graduação</button>';
    superior.append(btn_sup_container);
  }
  element.parentElement.parentElement.parentElement.outerHTML = ``;
}

function resetCount(){
  superiorCount = 0;
  console.log(superiorCount);
}

const course = `<div id="course-child" class="row">
<div class="row">
    <div class="col-md-4">
        <label for="course_institution" class="form-label">Instituição</label>
        <input type="text" class="form-control" id="course_institution"
            name="course_institution">
            <error for="course_institution"></error>
    </div>
    <div class="col-md-4">
        <label for="course_name" class="form-label">Certificado ou Curso</label>
        <input type="text" class="form-control" id="course_name" name="course_name">
        <error for="course_name"></error>
    </div>
    <div class="col-md-4">
        <label for="course_type" class="form-label">Tipo</label>
        <select id="course_type" class="form-select option" name="course_type">
            <option value="LIVRE">Curso Livre</option>
            <option value="CERTIFICADO">Certificado</option>
            <option value="TECNICO">Curso Técnico</option>
            <option value="GRADUACAO">Pós Graduação</option>
            <option value="MESTRADO">Mestrado</option>
            <option value="MBA">MBA</option>
            <option value="OUTROS">Outros</option>
        </select>
        <error for="course_type"></error>
    </div>
</div>
<div class="row">
    <div class="w-25">
        <button class="btn-add" id="btn-add-course">+ curso</button>
    </div>
</div>
</div>`

let courseCount = 0;
function addCourse(){
  if(courseCount >= 5){
    return;
  }
  const course_container = document.querySelector("#course-container");
  const course = document.createElement('div')
  course.className = "row"
  course.id = "course-child"
  course.innerHTML = `
  <div class="row">
    <div class="col-md-4">
        <label for="course_institution" class="form-label">Instituição</label>
        <input type="text" class="form-control" id="course_institution"
            name="course_institution">
    </div>
    <div class="col-md-4">
        <label for="course_name" class="form-label">Certificado ou Curso</label>
        <input type="text" class="form-control" id="course_name" name="course_name">
    </div>
    <div class="col-md-4">
        <label for="course_type" class="form-label">Tipo</label>
        <select id="course_type" class="form-select option" name="course_type">
            <option value="Hoteleiro">Curso Livre</option>
            <option value="Camareira">Certificado</option>
            <option value="Camareira">Curso Técnico</option>
            <option value="Almoxarife">Pós Graduação</option>
            <option value="Almoxarife">Mestrado</option>
            <option value="Almoxarife">MBA</option>
            <option value="Almoxarife">Outros</option>
        </select>
    </div>
  </div>
  <div class="row">
    <div class="w-25">
        <button type="button" class="btn-del" id="btn-del-course" onclick="deleteCourse(this)">- remover</button>
    </div>
  </div>`;

  course_container.appendChild(course);
  courseCount++;
  if(courseCount >= 5){
    document.querySelector("#btn-add-course-container").innerHTML = '';
  }
}

function deleteCourse(element){
  element.parentElement.parentElement.parentElement.outerHTML = ``;
  if(courseCount == 5){
    document.querySelector("#btn-add-course-container").innerHTML = `<button type="button" class="btn-add" id="btn-add-course" onclick="addCourse()">+
    curso</button>`;
  }
  courseCount--;
}

async function buildResumeFromJson() {

  var resume = {};

  var form = document.querySelector('form')
  resume.firstName = form.querySelector('#firstName').value;
  resume.lastName = form.querySelector('#lastName').value;
  resume.birthDate = form.querySelector('#birthDate').value;
  resume.gender = form.querySelector('#gender').value;
  resume.phone = form.querySelector('#phone').value;
  resume.cellPhone = form.querySelector('#cellPhone').value;
  resume.email = form.querySelector('#email').value;
  resume.linkedin = form.querySelector('#linkedin').value;
  resume.postalCode = form.querySelector('#postalCode').value;
  resume.district = form.querySelector('#district').value;
  resume.city = form.querySelector('#city').value;
  resume.jobOptionOne = form.querySelector('#jobOptionOne').value;
  resume.jobOptionTwo = form.querySelector('#jobOptionTwo').value;
  resume.jobOptionThree = form.querySelector('#jobOptionThree').value;
  resume.schooling = form.querySelector('#schooling').value;

  const jobExperiences = form.querySelectorAll('#jobExperiences');

  var count = jobExperiences.length;

  for (let i = 0; i < count; i++) {
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

  console.log(resume)

  return resume;
}

async function sendResume() {

  const resume = buildResumeFromJson();

  const response = await post('http://localhost:80/api/resume', resume);

  if (response.status >= 200 && response.status <= 299) {
    document.querySelector('.success-message').innerHTML = "<p>Currículo Cadastrado com sucesso!</p>";
  }

  if (response.status >= 400 && response.status <= 499) {
    buildError(response);    
    document.querySelector('.success-message').innerHTML = "<p>Erro ao Cadastrar curriculo</p>";
  }

}

async function buildError(response){
  
  const data = await response.json();
    for(const key in data){
      document.querySelector(`error[for="${key}"]`).innerHTML = data[key];
      console.log(key + ":" + data[key])
    }

}


