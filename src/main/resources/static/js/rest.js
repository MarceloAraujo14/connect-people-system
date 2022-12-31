//api functions
async function post(url, obj) {
    const response = await fetch(url, {
      method: 'POST',
      body: JSON.stringify(obj),
      headers: { 'Content-type': 'application/json' }
    })
      .then(res => res);

    console.log(response)

    return response;
  }
