package de.hsb.jdbc.presentation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.hsb.jdbc.service.MemberService;
import de.hsb.jdbc.service.dto.MembersDto;

@WebServlet("/members")
public class MemberServlet extends HttpServlet {

	private static final long serialVersionUID = -2037993721520503612L;
	private MemberService memberService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MembersDto members = memberService.fetchMembers();

		String json = new ObjectMapper().writeValueAsString(members);
		resp.getWriter().append(json);
		resp.setContentType("application/json");
		resp.setStatus(200);
	}

	@Override
	public void init() throws ServletException {
		memberService = new MemberService();
	}

	@Override
	public void destroy() {
		try {
			memberService.close();
		} catch (Exception e) {
			System.err.println("Failed closing memberSerice: " + e.getMessage());
		}
	}
}
