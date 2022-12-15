package br.com.connectpeople.api.resource;

import br.com.connectpeople.api.request.ResumeRequest;
import br.com.connectpeople.resume.domain.Resume;
import org.springframework.web.bind.annotation.RequestBody;

public interface ResumeController {

    Resume register(@RequestBody ResumeRequest request);
}
