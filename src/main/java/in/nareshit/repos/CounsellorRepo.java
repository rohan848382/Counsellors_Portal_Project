package in.nareshit.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import in.nareshit.entities.Counsellor;

import java.util.List;


public interface CounsellorRepo extends JpaRepository<Counsellor, Integer> {
	
	public Counsellor  findByEmail(String email);

	public Counsellor  findByEmailAndPwd(String email, String pwd);
}
