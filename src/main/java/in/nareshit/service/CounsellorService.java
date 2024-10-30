package in.nareshit.service;

import java.util.List;

import in.nareshit.dto.DashboardResponse;
import in.nareshit.dto.ViewEnqFilterRequest;
import in.nareshit.entities.Counsellor;
import in.nareshit.entities.Enquiry;

public interface CounsellorService {
	
	public Counsellor findByEmail(String email);

public boolean register(Counsellor counsellor);
	
	public Counsellor login(String email,String pwd);
	
	public DashboardResponse getDashboardInfo(Integer counsellorId);
}
