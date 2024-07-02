package com.library.service.admin;

import com.library.dto.admin._normal.AdminDTO;
import com.library.repository.admin.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import com.library.dto.admin._normal.AdminDTO;

public interface AdminService {

	// 모든 관리자 목록 조회
    List<AdminDTO> allAdminManage();

    AdminDTO findAdminById(String adminId);

}
