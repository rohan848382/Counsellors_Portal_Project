package in.nareshit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import in.nareshit.dto.DashboardResponse;
import in.nareshit.entities.Counsellor;
import in.nareshit.service.CounsellorService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class CounsellorController {

	private CounsellorService counsellorService;

	public CounsellorController(CounsellorService counsellorService) {
		this.counsellorService = counsellorService;
	}

	@GetMapping("/")
	public String index(Model model) {

		Counsellor cobj = new Counsellor();

		model.addAttribute("counsellor", cobj);

		return "index";
	}

	@PostMapping("/login")
	public String handleloginBtn( Counsellor counsellor, HttpServletRequest request,
			Model model) {

		Counsellor c = counsellorService.login(counsellor.getEmail(), counsellor.getPwd());

		if (c == null) {

			model.addAttribute("emsg", "Invalid credentials");

			return "index";
		} else {

			HttpSession session = request.getSession(true);
			session.setAttribute("counsellorId", c.getCounsellorId());

			
			return "redirect:/dashboard";
		}

	}

	@GetMapping("/dashboard")
	public String displayDashboard(HttpServletRequest req, Model model) {

		HttpSession session = req.getSession(true);
		Integer counsellorId = (Integer) session.getAttribute("counsellorId");

		DashboardResponse dbresObj = counsellorService.getDashboardInfo(counsellorId);
		model.addAttribute("dashboardInfo", dbresObj);

		return "dashboard";
	}
	

	@GetMapping("/register")
	public String registerPage(Model model) {

		Counsellor cobj = new Counsellor();

		model.addAttribute("counsellor", cobj);

		return "register";
	}

	@PostMapping("/register")
	public String handleRegister(Counsellor counsellor, Model model) {

		Counsellor byEmail = counsellorService.findByEmail(counsellor.getEmail());

		if (byEmail != null) {
			model.addAttribute("emsg", "Duplicate Email");
			return "register";

		}

		boolean isRegistered = counsellorService.register(counsellor);

		if (isRegistered) {

			model.addAttribute("smsg", "Registeration Success..!!");

		} else {

			model.addAttribute("emsg", "Registration Failed");
		}

		return "register";
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest req) {

		HttpSession session = req.getSession(false);
		session.invalidate();
		return "redirect:/";

	}
}
