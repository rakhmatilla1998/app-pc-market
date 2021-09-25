package pdp.uz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import pdp.uz.domain.Attachment;
import pdp.uz.domain.AttachmentContent;
import pdp.uz.repository.AttachmentContentRepo;
import pdp.uz.repository.AttachmentRepo;
import pdp.uz.service.AttachmentService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.Optional;

@Service
public class AttachmentServiceImpl implements AttachmentService {

    private final AttachmentRepo attachmentRepo;
    private final AttachmentContentRepo attachmentContentRepo;

    @Autowired
    public AttachmentServiceImpl(AttachmentRepo attachmentRepo, AttachmentContentRepo attachmentContentRepo) {
        this.attachmentRepo = attachmentRepo;
        this.attachmentContentRepo = attachmentContentRepo;
    }


    @Override
    public ResponseEntity<Attachment> upload(MultipartHttpServletRequest request) throws IOException {
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());
        assert file != null;
        Attachment attachment = Attachment.builder()
                .name(file.getOriginalFilename())
                .size(file.getSize())
                .contentType(file.getContentType()).build();
        Attachment save = attachmentRepo.save(attachment);

        AttachmentContent content = AttachmentContent.builder()
                .attachment(save)
                .data(file.getBytes()).build();
        attachmentContentRepo.save(content);
        return  ResponseEntity.ok(save);
    }

    @Override
    public ResponseEntity<?> get(Long id) {
        Optional<Attachment> optionalAttachment = attachmentRepo.findById(id);
       if (!optionalAttachment.isPresent()){
           return new ResponseEntity<>("Attachment not found", HttpStatus.NOT_FOUND);
       }
       return ResponseEntity.ok(optionalAttachment.get());
    }

    @Override
    public ResponseEntity<?> download(Long id, HttpServletResponse response) throws IOException {
        Optional<Attachment> optionalAttachment = attachmentRepo.findById(id);
        if (!optionalAttachment.isPresent()){
            return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
        }
        Attachment attachment = optionalAttachment.get();
        Optional<AttachmentContent> optionalAttachmentContent = attachmentContentRepo.findByAttachmentId(attachment.getId());
        if (!optionalAttachmentContent.isPresent())
            return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);

        response.setHeader("Content-Disposition", "attachment; filename = \"" + attachment.getName() + "\"");
        response.setContentType(attachment.getContentType());

        FileCopyUtils.copy(optionalAttachmentContent.get().getData(), response.getOutputStream());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
