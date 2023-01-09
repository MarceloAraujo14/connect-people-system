//api functions
async function post(url, obj) {

  const csrfToken = document.querySelector("input[name='_csrf']").value;

  const response = await fetch(url, {
    method: 'POST',
    body: JSON.stringify(obj),
    headers: {
      'Content-type': 'application/json',
      'mode': 'cors',
      'X-CSRF-TOKEN': csrfToken},
  })
    .then(res => res);

  console.log(response)

  return response;
}

function errorHandler(response){
  
}
