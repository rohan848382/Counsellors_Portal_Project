package in.nareshit.service;

import java.util.List;

import in.nareshit.dto.DashboardResponse;
import in.nareshit.dto.ViewEnqFilterRequest;
import in.nareshit.entities.Counsellor;
import in.nareshit.entities.Enquiry;

public interface EnquiryService {

    public boolean addEnquiry(Enquiry enq, Integer counsollerId) throws Exception;
	
	public List<Enquiry> getAllEnquiry(Integer counsellorId);
	
//	public List<Enquiry> getEnquiryWithFilter(ViewEnqFilterRequest filterReq, Integer counsellorId);
	
	public Enquiry getEnquriyById(Integer enqId);

	List<Enquiry> getEnquiresWithFilter(ViewEnqFilterRequest filterReq, Integer counsellorId);
}
