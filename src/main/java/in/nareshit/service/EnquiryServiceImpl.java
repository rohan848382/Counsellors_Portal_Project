package in.nareshit.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import in.nareshit.dto.ViewEnqFilterRequest;
import in.nareshit.entities.Counsellor;
import in.nareshit.entities.Enquiry;
import in.nareshit.repos.CounsellorRepo;
import in.nareshit.repos.EnquiryRepo;
import io.micrometer.common.util.StringUtils;



@Service
public  class EnquiryServiceImpl implements EnquiryService {

	private EnquiryRepo enqRepo;

	private CounsellorRepo counsellorRepo;

	public EnquiryServiceImpl(EnquiryRepo enqRepo, CounsellorRepo counsellorRepo) {
		this.enqRepo = enqRepo;
		this.counsellorRepo = counsellorRepo;
	}

	@Override
	public boolean addEnquiry(Enquiry enq, Integer counsollerId) throws Exception {

		Counsellor counsellor = counsellorRepo.findById(counsollerId).orElse(null);

		if (counsellor == null) {
			throw new Exception("No counsellor found");
		}

		enq.setCounsellor(counsellor);
		Enquiry save = enqRepo.save(enq);

		if (save.getEnqId() != null) {
			return true;
		}

		return false;
	}

	@Override
	public List<Enquiry> getAllEnquiry(Integer counsellorId) {
		return enqRepo.getEnquriesByCounsellorId(counsellorId);

	}

	@Override
	public Enquiry getEnquriyById(Integer enqId) {
		return enqRepo.findById(enqId).orElse(null);

	}

	@Override
	public List<Enquiry> getEnquiresWithFilter(ViewEnqFilterRequest filterReq, Integer counsellorId) {
		
		Enquiry enq = new Enquiry();
		
		if(StringUtils.isNotEmpty(filterReq.getClassMode())) {
			enq.setClassMode(filterReq.getClassMode());
		}
		
		if(StringUtils.isNotEmpty(filterReq.getCourseName())) {
			enq.setCourseName(filterReq.getCourseName());
		}
		
		if(StringUtils.isNotEmpty(filterReq.getEnqStatus())) {
			enq.setEnqStatus(filterReq.getEnqStatus());
		}
		
		Counsellor c = counsellorRepo.findById(counsellorId).orElse(null);
		enq.setCounsellor(c);
		
		Example<Enquiry> of = Example.of(enq);
		
		List<Enquiry> enqList = enqRepo.findAll(of);
		
		return enqList;
	}

}
