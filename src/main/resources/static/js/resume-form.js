function loadMonthAndYear() {

  var monthSelect = document.getElementsByClassName("month");
  monthSelect = Array.from(monthSelect);
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

  var yearSelec = document.getElementsByClassName("year");
  yearSelec = Array.from(yearSelec);

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
  job.id = "job-experience";
  job.innerHTML = `
  <div id="jobExperiences" name="jobExperiences">
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
            <select class="form-select month" id="startMonth" name="startMonth"></select>
            <error for="startMonth"></error>
          </div>
          <div class="col-md-4">
            <label for="startYear" class="form-label">Ano</label>
            <select class="form-select year" id="startYear" name="startYear"></select>
            <error for="startYear"></error>
          </div>
          <div class="row-cols-1">
            <label class="form-check-label" for="currentJob">Trabalho atual</label>
            <input class="form-check-input" onchange="checkCurrentJob(this)" type="checkbox" id="currentJob" name="currentJob" checked>
          </div>
        </div>
      </div>

      <div class="col" id="endJob">
        <p>Saída</p>
        <div class="row">
          <div class="col-md-5">
            <label for="endMonth" class="form-label">Mês</label>
            <select class="form-select month" id="endMonth" name="endMonth"></select>
            <error for="endMonth"></error>
          </div>
          <div class="col-md-4">
            <label for="endYear" class="form-label">Ano</label>
            <select class="form-select year" id="endYear" name="endYear"></select>
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

function checkCurrentJob(checkbox) {
  if (checkbox.checked) {
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
  } else {
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
        <label for="superior_institution" class="form-label">Instituição</label>
        <input type="text" class="form-control" id="superior_institution" name="superior_institution">
        <error for="superior_institution"></error>
    </div>
    <div class="col-md-4">
        <label for="superior_course" class="form-label">Curso</label>
        <input type="text" class="form-control" id="superior_course" name="superior_course">
        <error for="superior_course"></error>
    </div>
    <div class="col-md-4">
        <label for="superior_status" class="form-label">Situação</label>
        <select id="superior_status" class="form-select option" name="superior_status">
            <option value="CURSANDO">Cursando</option>
            <option value="TRANCADO">Trancado ou Interrompido</option>
        </select>
        <error for="superior_status"></error>
    </div>
  </div>
  <br>
</div>
`;
const supIncompRem = `
  <div class="col" id="superior-child">
    <div class="row">
      <div class="col-md-4">
          <label for="superior_institution
" class="form-label">Instituição</label>
          <input type="text" class="form-control" id="superior_institution
" name="superior_institution
">
          <error for="superior_institution
"></error>
      </div>
      <div class="col-md-4">
          <label for="superior_course
" class="form-label">Curso</label>
          <input type="text" class="form-control" id="superior_course
" name="superior_course
">
          <error for="superior_course
"></error>
      </div>
      <div class="col-md-4">
          <label for="superior_status" class="form-label">Situação</label>
          <select id="superior_status" class="form-select option" name="superior_status">
              <option value="CURSANDO">Cursando</option>
              <option value="TRANCADO">Trancado ou Interrompido</option>
          </select>
          <error for="superior_status"></error>
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
        <label for="superior_institution" class="form-label">Instituição</label>
        <input type="text" class="form-control" id="superior_institution" name="superior_institution">
        <error for="superior_institution"></error>
    </div>
    <div class="col-md-4">
        <label for="superior_course" class="form-label">Curso</label>
        <input type="text" class="form-control" id="superior_course" name="superior_course">
        <error for="superior_course"></error>
    </div>
    <div class="col-md-3">
        <label for="conclusionYear" class="form-label">Ano de Conclusão</label>
        <select id="conclusionYear" class="form-select option year" name="conclusionYear"></select>
    </div>
  </div>
  <br>
</div>
`;
const supCompRem = `
  <div class="col" id="superior-child">
    <div class="row">
      <div class="col-md-4">
          <label for="superior_institution
" class="form-label">Instituição</label>
          <input type="text" class="form-control" id="superior_institution
" name="superior_institution
">
          <error for="superior_institution
"></error>
      </div>
      <div class="col-md-4">
          <label for="superior_course
" class="form-label">Curso</label>
          <input type="text" class="form-control" id="superior_course
" name="superior_course
">
          <error for="superior_course
"></error>
      </div>
      <div class="col-md-3">
          <label for="conclusionYear" class="form-label">Ano de Conclusão</label>
          <select id="conclusionYear" class="form-select option year" name="conclusionYear"></select>
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

  if (superiorCount >= 3) {
    return;
  }

  var superior = document.getElementById("superior-container");
  var schooling = document.getElementById("schooling");

  if (schooling.value == 'SUPERIOR_INCOMPLETO') {
    if (superiorCount == 0) {
      superior.innerHTML = supIncomp;
    } else {
      element.outerHTML = supIncompRem;
    }


  } else if (schooling.value == 'SUPERIOR_COMPLETO') {
    if (superiorCount == 0) {
      superior.innerHTML = supComp;
    } else {
      element.outerHTML = supCompRem;
    }

  } else {
    superior.innerHTML = '';
    return;
  }

  loadMonthAndYear();
  superiorCount++;

  if (superiorCount >= 3) {
    return;
  }
  const btn_sup_container = document.createElement("div");
  btn_sup_container.className = "btn-sup-container";
  btn_sup_container.innerHTML = '<button type="button" class="btn-add" onclick="addSuperior(this)">+ graduação</button>';
  superior.append(btn_sup_container);
}

function deleteSuperior(element) {
  superiorCount--;
  if (superiorCount == 2) {
    const superior = document.getElementById("superior-container");
    const btn_sup_container = document.createElement("div");
    btn_sup_container.className = "btn-sup-container";
    btn_sup_container.innerHTML = '<button type="button" class="btn-add" onclick="addSuperior(this)">+ graduação</button>';
    superior.append(btn_sup_container);
  }
  element.parentElement.parentElement.parentElement.outerHTML = ``;
}

function resetCount() {
  superiorCount = 0;
}

let courseCount = 0;

function addCourse() {
  if (courseCount >= 5) {
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
            <option value="LIVRE">Curso Livre</option>
            <option value="CERTIFICADO">Certificado</option>
            <option value="TECNICO">Curso Técnico</option>
            <option value="POS_GRADUACAO">Pós Graduação</option>
            <option value="MESTRADO">Mestrado</option>
            <option value="MBA">MBA</option>
            <option value="OUTROS">Outros</option>
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
  if (courseCount >= 5) {
    document.querySelector("#btn-add-course-container").innerHTML = '';
  }
}

function deleteCourse(element) {
  element.parentElement.parentElement.parentElement.outerHTML = ``;
  if (courseCount == 5) {
    document.querySelector("#btn-add-course-container").innerHTML = `<button type="button" class="btn-add" id="btn-add-course" onclick="addCourse()">+
    curso</button>`;
  }
  courseCount--;
}

function getJobExperiences() {
  const jobs = document.querySelectorAll('#jobExperiences');

  let jobExperiences = []

  for (i = 0; i < jobs.length; i++) {
    const title = jobs[i].querySelector('#title').value;
    const company = jobs[i].querySelector('#company').value;
    const currentJob = jobs[i].querySelector('#currentJob').checked;
    const startYear = jobs[i].querySelector('#startYear').value;
    const endYear = jobs[i].querySelector('#endYear').value;
    const startMonth = jobs[i].querySelector('#startMonth').value;
    const endMonth = jobs[i].querySelector('#endMonth').value;
    const description = jobs[i].querySelector('#description').value;

    jobExperiences[i] = { title, company, currentJob, startYear, startMonth, endMonth, endYear, description }
  }

  return jobExperiences;
}

function getSuperiorCourses() {
  const superior = document.querySelectorAll('#superior-child')

  let superiorCourses = []

  for (i = 0; i < superior.length; i++) {

    const course = superior[i].querySelector('#superior_course').value;
    const institution = superior[i].querySelector('#superior_institution').value;
    let status = 'CONCLUIDO'
    let conclusionYear = 0;

    try {
      status = superior[i].querySelector('#superior_status').value;
    } catch (error) { }
    try {
      conclusionYear = superior[i].querySelector('#conclusionYear').value;
    } catch (error) { }


    superiorCourses[i] = { course, institution, status, conclusionYear }
  }

  return superiorCourses;
}

function getCourses() {
  const courses = document.querySelectorAll('#course-child')

  let courseList = []

  for (i = 0; i < courses.length; i++) {
    const name = courses[i].querySelector('#course_name').value;
    const institution = courses[i].querySelector('#course_institution').value;
    const type = courses[i].querySelector('#course_type').value;

    courseList[i] = { name, institution, type };
  }
  return courseList;
}

function buildResumeFromJson() {

  const form = document.querySelector('form');

  const data = new FormData(form);

  data.delete("title");
  data.delete("company");
  data.delete("currentJob");
  data.delete("startYear");
  data.delete("endYear");
  data.delete("startMonth");
  data.delete("endMonth");
  data.delete("description");
  data.delete("superior_course");
  data.delete("superior_institution");
  data.delete("superior_status");
  data.delete("conclusionYear");
  data.delete("course_name");
  data.delete("course_institution");
  data.delete("course_type");

  const resume = Object.fromEntries(data.entries());

  resume.jobExperiences = getJobExperiences();

  resume.superiorCourses = getSuperiorCourses();

  resume.courses = getCourses();

  return resume;
}

function cleanInput(){
  const inputs = document.querySelectorAll('input');
  inputs.forEach(function(inp){
    inp.addEventListener('change', function(){
      document.querySelector(`error[for=${inp.name}]`).innerHTML = '';
    })
  })
}

cleanInput();

async function sendResume() {

  const resume = buildResumeFromJson();

  const response = await post('http://localhost:80/api/resume', resume);

  if (response.status >= 200 && response.status <= 299) {
    document.querySelector('.response-message').innerHTML = "<p>Currículo Cadastrado com sucesso!</p>";
    document.querySelectorAll('input').forEach(input => input.value = '');
  }

  if (response.status >= 400 && response.status <= 499) {
    buildError(response);
    document.querySelector('.response-message').innerHTML = "<p>Erro ao Cadastrar curriculo</p>";
  }

}

async function buildError(response) {
  const data = await response.json();
  for (const key in data) {
    document.querySelector(`error[for="${key}"]`).innerHTML = data[key];
  }
}


