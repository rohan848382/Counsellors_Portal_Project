package in.nareshit.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.nareshit.entities.Enquiry;
import jakarta.persistence.criteria.CriteriaBuilder.In;

public interface EnquiryRepo extends JpaRepository<Enquiry, Integer> {

	@Query(value = "select * from enquiry_tlb where counsellor_id =:counsellorId", nativeQuery = true)
	public List<Enquiry> getEnquriesByCounsellorId(Integer counsellorId);
}
