async function post(resume){
    console.log(resume)
    const req = await fetch('/api/resume', {
    method: 'POST',
    body: JSON.stringify(resume),
    headers: {'Content-type': 'application/json'}
    });

    const resp = await req.json();
    console.log(resp);
}

const curriculo = {
                      name: "Marcelo Araujo",
                      birthDate: "14-06-1992",
                      gender: "M",
                      phone: "(21)98559-8914",
                      cellPhone: "(21)98559-8912",
                      email: "mbaraujo1410@gmail.com",
                      postalCode: "22739-237",
                      district: "Taquara",
                      jobOptionOne: "Recepcionista",
                      jobOptionTwo: "Camareiro",
                      jobOptionThree: "Porteiro",
                      jobExperiences: [
                          {
                              title: "Engenheiro de Software",
                              company: "NA SA",
                              startMonth: 3,
                              startYear: 2022,
                              isCurrentJob: true,
                              description: "Desenvolver software"
                          },
                          {
                              title: "Designer Grafico",
                              company: "NA SA",
                              startMonth: 3,
                              startYear: 2022,
                              isCurrentJob: false,
                              description: "Desenvolver software",
                              endMonth: 3,
                              endYear: 2021
                          }
                      ],
                      schooling: "SUPERIOR_INCOMPLETO"
                  };

function send(){
    post(curriculo);
}
