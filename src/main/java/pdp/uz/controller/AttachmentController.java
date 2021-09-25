package pdp.uz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import pdp.uz.domain.Attachment;
import pdp.uz.service.AttachmentService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@RestController
@RequestMapping("/api/attachment")
public class AttachmentController {

    private final AttachmentService attachmentService;

    @Autowired
    public AttachmentController(AttachmentService attachmentService) {
        this.attachmentService = attachmentService;
    }

    @PostMapping("/upload")
    public ResponseEntity<Attachment> upload(MultipartHttpServletRequest request) throws IOException {
        return attachmentService.upload(request);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return attachmentService.get(id);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<?> download(@PathVariable Long id, HttpServletResponse response) throws IOException{
        return attachmentService.download(id, response);
    }
}
