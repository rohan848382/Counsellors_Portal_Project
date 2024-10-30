package in.nareshit.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nareshit.dto.DashboardResponse;
import in.nareshit.dto.ViewEnqFilterRequest;
import in.nareshit.entities.Counsellor;
import in.nareshit.entities.Enquiry;
import in.nareshit.repos.CounsellorRepo;
import in.nareshit.repos.EnquiryRepo;

@Service
public class CounsellorServiceImpl implements CounsellorService {

	private CounsellorRepo counsellorRepo;

	private EnquiryRepo enqRepo;

	public CounsellorServiceImpl(CounsellorRepo counsellorRepo, EnquiryRepo enqRepo) {
		this.counsellorRepo = counsellorRepo;
		this.enqRepo = enqRepo;
	}

	@Override
	public boolean register(Counsellor counsellor) {

		Counsellor savedCounsellor = counsellorRepo.save(counsellor);
		if (null != savedCounsellor.getCounsellorId()) {
			return true;
		}

		return false;
	}

	@Override
	public Counsellor login(String email, String pwd) {

		return counsellorRepo.findByEmailAndPwd(email, pwd);

	}

	@Override
	public DashboardResponse getDashboardInfo(Integer counsellorId) {

		DashboardResponse response = new DashboardResponse();

		List<Enquiry> enqList = enqRepo.getEnquriesByCounsellorId(counsellorId);
		int totalEnq = enqList.size();

		int enrolledEnqs = enqList.stream().filter(e -> e.getEnqStatus().equals("Enrolled"))
				.collect(Collectors.toList()).size();

		int lostEnqs = enqList.stream().filter(e -> e.getEnqStatus().equals("Lost")).collect(Collectors.toList())
				.size();

		int openEnqs = enqList.stream().filter(e -> e.getEnqStatus().equals("Open")).collect(Collectors.toList())
				.size();

		response.setTotalEnqs(totalEnq);
		response.setEnrolledEnqs(enrolledEnqs);
		response.setLoseEnqu(lostEnqs);
		response.setOpenEnqs(openEnqs);

		return response;
	}

	@Override
	public Counsellor findByEmail(String email) {

		return counsellorRepo.findByEmail(email);
	}

}
