package com.library.controller.admin;

import com.library.dto.admin._normal.InquiryDTO;
import com.library.service.admin.InquiryService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin/Inquiry")
public class InquiryController {

    private InquiryService inquiryService;

    @Autowired
    public void setInquiryService(InquiryService inquiryService) {
        this.inquiryService = inquiryService;
    }

    // 초기 출력
    @GetMapping()
    public String inquiry(Model model) {
        List<InquiryDTO> inquiry = inquiryService.allInquiryManage(false, false);
        model.addAttribute("inquiry", inquiry);
        return "admin/adminManagements/inquiry/inquiryManage";
    }

    // 검색
    @GetMapping("/search")
    @ResponseBody
    public ResponseEntity<List<InquiryDTO>> searchInquiries(
            @RequestParam(value = "searchType",name = "searchType", required = false) String searchType,
            @RequestParam(value = "searchKeyword",name = "searchKeyword", required = false) String searchKeyword,
            @RequestParam(value = "showAnswered",name = "showAnswered", required = false, defaultValue = "false") boolean showAnswered,
            @RequestParam(value = "showOnlyAnswered",name = "showOnlyAnswered", required = false, defaultValue = "false") boolean showOnlyAnswered) {

        List<InquiryDTO> inquiries;
        if (searchKeyword == null || searchKeyword.trim().isEmpty()) {
            inquiries = inquiryService.allInquiryManage(showAnswered, showOnlyAnswered);
        } else {
            inquiries = inquiryService.searchInquiries(searchType, searchKeyword, showAnswered, showOnlyAnswered);
        }
        return ResponseEntity.ok(inquiries);
    }

    // 삭제
    @PostMapping("/deleteInquiry")
    @ResponseBody
    public ResponseEntity<String> deleteInquiry(@RequestBody List<String> inquiryIds) {
        inquiryService.deleteInquiry(inquiryIds);
        return ResponseEntity.ok("삭제 성공");
    }

    // POST 요청 처리
    @PostMapping("/createBtnClick/{id}")
    @ResponseBody
    public String createBtnClick(@PathVariable("id") int inquiryid) {
        return "success";
    }

    // 작성페이지 이동
    @GetMapping("/WriteInquiry")
    public String writeInquiry(@RequestParam("inquiryId") int inquiryId, Model model) {
        InquiryDTO inquiry = inquiryService.getInquiryById(inquiryId);
        int count = inquiryService.count();
        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String changedDate = formatter.format(now);

        model.addAttribute("now", changedDate);
        model.addAttribute("inquiry", inquiry);
        model.addAttribute("nextId", count + 1);
        return "admin/adminManagements/inquiry/inquiryWrite";
    }

    // 질의 답변 등록 처리
    @PostMapping("/UploadInquiry")
    @ResponseBody
    public String uploadInquiry(@RequestParam("inquiryId") int inquiryId,
                                @RequestParam("responseContents") String responseContents,
                                HttpSession session) {
        try {
            String adminId = getCurrentAdminId();
            inquiryService.createInquiry(inquiryId, responseContents, adminId);
            return "등록 성공";
        } catch (Exception e) {
            e.printStackTrace();
            return "오류 발생: " + e.getMessage();
        }
    }

    private String getCurrentAdminId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            return principal.toString();
        }
    }

    // POST 요청 처리
    @PostMapping("/viewBtnClick/{id}")
    @ResponseBody
    public String viewBtnClick(@PathVariable("id") int id) {
        return "success";
    }

    // 확인페이지 이동
    @GetMapping("/DetailInquiry")
    public String detailInquiry(@RequestParam("inquiryId") int inquiryId, Model model) {
        InquiryDTO inquiry = inquiryService.getInquiryById(inquiryId);
        model.addAttribute("inquiry", inquiry);
        return "admin/adminManagements/inquiry/inquiryDetail";
    }

    @GetMapping("/prevInquiry")
    @ResponseBody
    public ResponseEntity<InquiryDTO> getPreviousInquiry(@RequestParam("inquiryId") int inquiryId) {
        try {
            InquiryDTO prevInquiry = inquiryService.previousInquiry(inquiryId);
            return new ResponseEntity<>(prevInquiry, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/nextInquiry")
    @ResponseBody
    public ResponseEntity<InquiryDTO> getNextInquiry(@RequestParam("inquiryId") int inquiryId) {
        try {
            InquiryDTO nextInquiry = inquiryService.nextInquiry(inquiryId);
            return new ResponseEntity<>(nextInquiry, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
