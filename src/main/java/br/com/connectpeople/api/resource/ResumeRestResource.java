package br.com.connectpeople.api.resource;

import br.com.connectpeople.api.request.ResumeRequest;
import br.com.connectpeople.resume.domain.Resume;
import br.com.connectpeople.resume.usecase.RegisterResume;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/resume")
public class ResumeRestResource {

    private final RegisterResume registerResume;

    @PostMapping
    public Resume save(@RequestBody ResumeRequest request){
       return registerResume.execute(request.toResume());
    }

}
