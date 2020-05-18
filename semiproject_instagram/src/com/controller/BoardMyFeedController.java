package com.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.BoardDao;
import com.dao.ImageDao;
import com.vo.Board_MemberVo;
import com.vo.ImageVo;

@WebServlet("/feed/myfeed")
public class BoardMyFeedController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletContext application=req.getServletContext(); //웹상의 절대경로를 구함
		String upload=application.getRealPath("upload");
		HttpSession session=req.getSession();
		int member_no=(int)session.getAttribute("member_no");
		String smember_no=(String)req.getAttribute("member_no");
		if(smember_no!=null && !smember_no.equals("")) {
			member_no=Integer.parseInt(smember_no);
		}
		BoardDao boardDao=new BoardDao();
		ImageDao imageDao=new ImageDao();
		//ArrayList<ImageVo> imagelist=dao.selectImg(member_no);
//		ArrayList<Board_MemberVo> board_memberList=boardDao.selectMemberBoard(member_no);
//		List<List<ImageVo>> boardImgList=new ArrayList<List<ImageVo>>();
//		if(board_memberList!=null){
//			for(int i=0; i<board_memberList.size(); i++){
//				Board_MemberVo board_memberVo=board_memberList.get(i);
//				List<ImageVo> imageList=imageDao.selectBoardImg(board_memberVo.getBoard_no());
//				boardImgList.add(imageList);
//			}
//		}
		//게시물당 1개씩 메인이미지가 담긴 ImageVo를 ArrayList에 담음
		ArrayList<ImageVo> mainImgList=imageDao.selectMainImg(member_no);
		req.setAttribute("mainImgList", mainImgList);
//		req.setAttribute("board_memberList", board_memberList);
//		req.setAttribute("boardImgList", boardImgList);
		req.setAttribute("main", "/feed/myfeed.jsp");
		req.getRequestDispatcher("/layout.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
}