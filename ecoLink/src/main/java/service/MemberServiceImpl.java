package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.MemberDAO;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	MemberDAO dao;
}
