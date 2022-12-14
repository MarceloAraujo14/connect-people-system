package br.com.connectpeople.api.resource;

import br.com.connectpeople.api.request.ResumeRequest;
import br.com.connectpeople.resume.domain.Resume;
import br.com.connectpeople.resume.usecase.resume.RegisterResumeUseCase;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/resume")
public class ResumeRestResource implements ResumeController{

    private final RegisterResumeUseCase registerResumeUseCase;

    @Override
    @PostMapping
    public Resume register(@RequestBody ResumeRequest request){
       return registerResumeUseCase.execute(request.toResume());
    }

}
